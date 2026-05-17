package com.tkart.ecommerce.services.interfaces;

import com.tkart.ecommerce.models.dto.*;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    void sendRegistrationOtp(SendOtpRequest request);
    AuthResponse registerUser(RegisterRequest request);
    AuthResponse verifyRegistrationOtp(String email, String otp);
    void forgotPassword(ForgotPasswordRequest request);
    void resetPassword(ResetPasswordRequest request);
    AuthResponse googleLogin(GoogleLoginRequest request);
    UserDTO updateProfile(String userId, UpdateProfileRequest request);
    UserDTO uploadAvatar(String userId, String avatarUrl);
    void changePassword(String userId, ChangePasswordRequest request);
}
