package com.tkart.ecommerce.controllers;

import com.tkart.ecommerce.models.dto.*;
import com.tkart.ecommerce.services.interfaces.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/auth", "/api/auth"})
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }

    @PostMapping("/register/send-otp")
    public ResponseEntity<ApiResponse<Void>> sendRegistrationOtp(@Valid @RequestBody SendOtpRequest request) {
        authService.sendRegistrationOtp(request);
        return ResponseEntity.ok(ApiResponse.success("OTP sent successfully to email", null));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.registerUser(request);
        return ResponseEntity.ok(ApiResponse.success("Registration successful", response));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<AuthResponse>> verifyOtp(@Valid @RequestBody VerifyOtpRequest request) {
        AuthResponse response = authService.verifyRegistrationOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(ApiResponse.success("OTP verified successfully", response));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Void>> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request);
        return ResponseEntity.ok(ApiResponse.success("Password reset OTP sent to email", null));
    }

    @PostMapping("/forgot-password/verify-otp")
    public ResponseEntity<ApiResponse<Void>> verifyForgotPasswordOtp(@Valid @RequestBody VerifyOtpRequest request) {
        authService.verifyForgotPasswordOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(ApiResponse.success("OTP verified successfully", null));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Void>> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request);
        return ResponseEntity.ok(ApiResponse.success("Password reset successfully", null));
    }

    @PostMapping("/google")
    public ResponseEntity<ApiResponse<AuthResponse>> googleLogin(@Valid @RequestBody GoogleLoginRequest request) {
        AuthResponse response = authService.googleLogin(request);
        return ResponseEntity.ok(ApiResponse.success("Google login successful", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        return ResponseEntity.ok(ApiResponse.success("Logout successful", null));
    }
}
