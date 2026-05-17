package com.tkart.ecommerce.controllers;

import com.tkart.ecommerce.models.dto.*;
import com.tkart.ecommerce.models.entities.User;
import com.tkart.ecommerce.repositories.UserRepository;
import com.tkart.ecommerce.services.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping({"/api/v1/profile", "/api/auth/profile"})
@RequiredArgsConstructor
public class UserProfileController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<UserDTO>> getProfile(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        User user = userRepository.findByEmail(authentication.getName());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return ResponseEntity.ok(ApiResponse.success("Profile fetched successfully", mapToUserDTO(user)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserDTO>> updateProfile(
            Authentication authentication, 
            @Valid @RequestBody UpdateProfileRequest request) {
        if (authentication == null || authentication.getName() == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        User user = userRepository.findByEmail(authentication.getName());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        UserDTO updated = authService.updateProfile(user.getId(), request);
        return ResponseEntity.ok(ApiResponse.success("Profile updated successfully", updated));
    }

    @PostMapping("/avatar")
    public ResponseEntity<ApiResponse<UserDTO>> uploadAvatar(
            Authentication authentication,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestBody(required = false) Map<String, String> body) {
        if (authentication == null || authentication.getName() == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        User user = userRepository.findByEmail(authentication.getName());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        String avatarUrl = null;
        if (file != null && !file.isEmpty()) {
            avatarUrl = "/uploads/avatars/" + file.getOriginalFilename();
        } else if (body != null) {
            avatarUrl = body.getOrDefault("avatarUrl", body.get("avatar_url"));
        }

        if (avatarUrl == null) {
            throw new IllegalArgumentException("No avatar file or URL provided");
        }

        UserDTO updated = authService.uploadAvatar(user.getId(), avatarUrl);
        return ResponseEntity.ok(ApiResponse.success("Avatar uploaded successfully", updated));
    }

    @PutMapping("/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            Authentication authentication, 
            @Valid @RequestBody ChangePasswordRequest request) {
        if (authentication == null || authentication.getName() == null) {
            throw new IllegalArgumentException("Unauthorized access");
        }
        User user = userRepository.findByEmail(authentication.getName());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        authService.changePassword(user.getId(), request);
        return ResponseEntity.ok(ApiResponse.success("Password changed successfully", null));
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
