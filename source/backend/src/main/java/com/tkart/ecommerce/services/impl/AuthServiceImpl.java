package com.tkart.ecommerce.services.impl;

import com.tkart.ecommerce.models.dto.*;
import com.tkart.ecommerce.models.entities.OtpToken;
import com.tkart.ecommerce.models.entities.User;
import com.tkart.ecommerce.models.enums.AccountStatus;
import com.tkart.ecommerce.models.enums.AuthProvider;
import com.tkart.ecommerce.models.enums.OtpType;
import com.tkart.ecommerce.models.enums.Role;
import com.tkart.ecommerce.repositories.OtpTokenRepository;
import com.tkart.ecommerce.repositories.UserRepository;
import com.tkart.ecommerce.services.MailService;
import com.tkart.ecommerce.services.OtpService;
import com.tkart.ecommerce.services.interfaces.AuthService;
import com.tkart.ecommerce.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final OtpTokenRepository otpTokenRepository;
    private final OtpService otpService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        if (!user.isVerified()) {
            throw new IllegalArgumentException("Account is not verified. Please verify your OTP.");
        }

        String token = jwtTokenProvider.generateJwtToken(user.getEmail(), 
                user.getRoles().stream().map(Enum::name).toList());

        return new AuthResponse(token, mapToUserDTO(user), "/dashboard");
    }

    @Override
    public void sendRegistrationOtp(SendOtpRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user != null && user.isVerified()) {
            throw new IllegalArgumentException("Email is already registered and verified");
        }
        if (user == null) {
            user = new User();
            user.setEmail(request.getEmail());
            user.setFullName("Customer");
            user.setRole(Role.ROLE_CUSTOMER);
            user.setRoles(Collections.singletonList(Role.ROLE_CUSTOMER));
            user.setVerified(false);
            user.setStatus(AccountStatus.ACTIVE);
            user.setAuthProvider(AuthProvider.LOCAL);
            userRepository.save(user);
        }

        String otp = otpService.generateOtp(request.getEmail());
        mailService.sendOtpEmail(request.getEmail(), otp);
    }

    @Override
    public AuthResponse registerUser(RegisterRequest request) {
        boolean isValid = otpService.validateOtp(request.getEmail(), request.getOtpCode());
        if (!isValid) {
            throw new IllegalArgumentException("Invalid or expired OTP code");
        }

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(request.getEmail());
        }

        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        Role role = Role.ROLE_CUSTOMER;
        if (request.getRole() != null && request.getRole().equalsIgnoreCase("SELLER")) {
            role = Role.ROLE_SELLER;
        }
        user.setRole(role);
        user.setRoles(Collections.singletonList(role));
        user.setVerified(true);
        user.setStatus(AccountStatus.ACTIVE);
        user.setAuthProvider(AuthProvider.LOCAL);

        userRepository.save(user);

        String token = jwtTokenProvider.generateJwtToken(user.getEmail(), 
                user.getRoles().stream().map(Enum::name).toList());

        return new AuthResponse(token, mapToUserDTO(user), "/dashboard");
    }

    @Override
    public AuthResponse verifyRegistrationOtp(String email, String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        if (!isValid) {
            throw new IllegalArgumentException("Invalid or expired OTP code");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.setVerified(true);
        userRepository.save(user);

        String token = jwtTokenProvider.generateJwtToken(user.getEmail(), 
                user.getRoles().stream().map(Enum::name).toList());

        return new AuthResponse(token, mapToUserDTO(user), "/dashboard");
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Email account does not exist");
        }

        String otp = String.valueOf((int) (Math.random() * 900000) + 100000);
        OtpToken otpToken = new OtpToken();
        otpToken.setEmail(request.getEmail());
        otpToken.setCode(otp);
        otpToken.setType(OtpType.RESET_PASSWORD);
        otpToken.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        otpTokenRepository.save(otpToken);

        mailService.sendResetPasswordEmail(request.getEmail(), otp);
    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {
        OtpToken otpToken = otpTokenRepository.findByEmailAndCodeAndTypeAndUsedFalse(
                request.getEmail(), request.getOtp(), OtpType.RESET_PASSWORD)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or expired OTP code"));

        if (otpToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired OTP code");
        }

        otpToken.setUsed(true);
        otpTokenRepository.save(otpToken);

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public AuthResponse googleLogin(GoogleLoginRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> userInfo = null;

        try {
            String url = "https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + request.getTokenId();
            userInfo = restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            log.warn("Access token userinfo failed, trying id_token tokeninfo...");
            try {
                String url = "https://oauth2.googleapis.com/tokeninfo?id_token=" + request.getTokenId();
                userInfo = restTemplate.getForObject(url, Map.class);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Google authentication failed: " + ex.getMessage());
            }
        }

        if (userInfo == null || userInfo.get("email") == null) {
            throw new IllegalArgumentException("Google account does not provide email");
        }

        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setFullName(name);
            user.setAvatar(picture);
            user.setAuthProvider(AuthProvider.GOOGLE);
            user.setRole(Role.ROLE_CUSTOMER);
            user.setRoles(Collections.singletonList(Role.ROLE_CUSTOMER));
            user.setVerified(true);
            user.setStatus(AccountStatus.ACTIVE);
            userRepository.save(user);
        } else if (user.getAvatar() == null && picture != null) {
            user.setAvatar(picture);
            userRepository.save(user);
        }

        String token = jwtTokenProvider.generateJwtToken(user.getEmail(), 
                user.getRoles().stream().map(Enum::name).toList());

        return new AuthResponse(token, mapToUserDTO(user), "/dashboard");
    }

    @Override
    public UserDTO updateProfile(String userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (request.getFullName() != null) user.setFullName(request.getFullName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        if (request.getDob() != null) user.setDob(request.getDob());
        if (request.getGender() != null) user.setGender(request.getGender());

        userRepository.save(user);
        return mapToUserDTO(user);
    }

    @Override
    public UserDTO uploadAvatar(String userId, String avatarUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setAvatar(avatarUrl);
        userRepository.save(user);
        return mapToUserDTO(user);
    }

    @Override
    public void changePassword(String userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password does not match");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setDob(user.getDob());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());
        dto.setVerified(user.isVerified());
        dto.setFullName(user.getFullName());
        dto.setAvatarUrl(user.getAvatar());
        return dto;
    }
}
