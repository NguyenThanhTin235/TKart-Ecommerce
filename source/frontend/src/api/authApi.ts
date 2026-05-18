import axiosClient from './axiosClient';

export interface LoginRequest {
  email: string;
  password?: string;
}

export interface RegisterRequest {
  fullName: string;
  email: string;
  password?: string;
  otpCode: string;
  role?: string;
}

export interface SendOtpRequest {
  email: string;
}

export interface VerifyOtpRequest {
  email: string;
  otp: string;
}

export interface ForgotPasswordRequest {
  email: string;
}

export interface ResetPasswordRequest {
  email: string;
  otp: string;
  newPassword?: string;
}

export interface GoogleLoginRequest {
  tokenId: string;
}

export const authApi = {
  login: (data: LoginRequest) => {
    return axiosClient.post('/auth/login', data);
  },
  sendRegistrationOtp: (data: SendOtpRequest) => {
    return axiosClient.post('/auth/register/send-otp', data);
  },
  register: (data: RegisterRequest) => {
    return axiosClient.post('/auth/register', data);
  },
  verifyOtp: (data: VerifyOtpRequest) => {
    return axiosClient.post('/auth/verify-otp', data);
  },
  forgotPassword: (data: ForgotPasswordRequest) => {
    return axiosClient.post('/auth/forgot-password', data);
  },
  verifyForgotPasswordOtp: (data: VerifyOtpRequest) => {
    return axiosClient.post('/auth/forgot-password/verify-otp', data);
  },
  resetPassword: (data: ResetPasswordRequest) => {
    return axiosClient.post('/auth/reset-password', data);
  },
  googleLogin: (data: GoogleLoginRequest) => {
    return axiosClient.post('/auth/google', data);
  },
  logout: () => {
    return axiosClient.post('/auth/logout');
  },
};
