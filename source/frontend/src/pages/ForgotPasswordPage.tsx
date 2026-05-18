import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/redux/store';
import { setLoading, setError } from '@/redux/authSlice';
import { authApi } from '@/api/authApi';
import MainLayout from '@/components/layout/MainLayout';

const ForgotPasswordPage: React.FC = () => {
  const [step, setStep] = useState<1 | 2 | 3>(1);
  const [email, setEmail] = useState('');
  const [otp, setOtp] = useState<string[]>(['', '', '', '', '', '']);
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [countdown, setCountdown] = useState(60);
  const [showNewPassword, setShowNewPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [formError, setFormError] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const inputRefs = useRef<(HTMLInputElement | null)[]>([]);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading } = useSelector((state: RootState) => state.auth);

  useEffect(() => {
    if (step === 2 && countdown > 0) {
      const timer = setTimeout(() => setCountdown(countdown - 1), 1000);
      return () => clearTimeout(timer);
    }
  }, [step, countdown]);

  // Step 1: Request OTP
  const handleRequestOtp = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormError(null);
    setSuccessMessage(null);

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      setFormError('Please enter a valid email address.');
      return;
    }

    dispatch(setLoading(true));

    try {
      await authApi.forgotPassword({ email });
      setStep(2);
      setCountdown(60);
      setSuccessMessage('A verification code has been sent to your email.');
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
      dispatch(setError(errorMessage));
    } finally {
      dispatch(setLoading(false));
    }
  };

  // Step 2: Handle OTP Input & Resend
  const handleOtpChange = (index: number, value: string) => {
    if (isNaN(Number(value))) return;

    const newOtp = [...otp];
    newOtp[index] = value;
    setOtp(newOtp);

    if (value !== '' && index < 5) {
      inputRefs.current[index + 1]?.focus();
    }
  };

  const handleOtpKeyDown = (index: number, e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Backspace' && otp[index] === '' && index > 0) {
      inputRefs.current[index - 1]?.focus();
    }
  };

  const handleResendOtp = async () => {
    if (!email || countdown > 0) return;
    setFormError(null);
    setSuccessMessage(null);
    dispatch(setLoading(true));

    try {
      await authApi.forgotPassword({ email });
      setCountdown(60);
      setSuccessMessage('A new verification code has been sent to your email.');
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
    } finally {
      dispatch(setLoading(false));
    }
  };

  const handleVerifyOtp = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormError(null);
    setSuccessMessage(null);

    const otpCode = otp.join('');
    if (otpCode.length < 6) {
      setFormError('Please enter the complete 6-digit OTP code.');
      return;
    }

    dispatch(setLoading(true));

    try {
      await authApi.verifyForgotPasswordOtp({ email, otp: otpCode });
      // Move to step 3 to enter new password
      setStep(3);
      setSuccessMessage('OTP confirmed. Please set your new password.');
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
    } finally {
      dispatch(setLoading(false));
    }
  };

  const hasUpperAndLower = /[a-z]/.test(newPassword) && /[A-Z]/.test(newPassword);
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(newPassword);

  // Step 3: Reset Password
  const handleResetPassword = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormError(null);
    setSuccessMessage(null);

    if (newPassword.length < 8) {
      setFormError('Password must be at least 8 characters long.');
      return;
    }

    if (!hasUpperAndLower) {
      setFormError('Password must contain both uppercase and lowercase letters.');
      return;
    }

    if (!hasSpecialChar) {
      setFormError('Password must contain at least one special character.');
      return;
    }

    if (newPassword !== confirmPassword) {
      setFormError('Passwords do not match.');
      return;
    }

    const otpCode = otp.join('');
    dispatch(setLoading(true));

    try {
      await authApi.resetPassword({
        email,
        otp: otpCode,
        newPassword,
      });

      setSuccessMessage('Password reset successfully! Redirecting to login...');
      setTimeout(() => {
        navigate('/login');
      }, 2000);
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
      dispatch(setError(errorMessage));
    } finally {
      dispatch(setLoading(false));
    }
  };

  return (
    <MainLayout>
      <div className="flex-grow flex items-center justify-center px-margin-mobile md:px-margin-desktop py-stack-lg w-full">
        <div className="w-full max-w-[480px] bg-surface-container-lowest rounded-xl shadow-[0px_4px_20px_rgba(15,23,42,0.05)] p-stack-lg border border-outline-variant/30 animate-fade-in">
          {formError && (
            <div className="mb-6 p-4 bg-error-container text-on-error-container rounded-lg border border-error/30 text-sm flex items-center gap-3 animate-fade-in">
              <span className="material-symbols-outlined text-error">error</span>
              <span>{formError}</span>
            </div>
          )}

          {successMessage && (
            <div className="mb-6 p-4 bg-green-100 text-green-800 rounded-lg border border-green-300 text-sm flex items-center gap-3 animate-fade-in">
              <span className="material-symbols-outlined text-green-600">check_circle</span>
              <span>{successMessage}</span>
            </div>
          )}

          {step === 1 && (
            <>
              <div className="flex flex-col items-center text-center mb-stack-lg">
                <div className="w-16 h-16 bg-primary-fixed rounded-full flex items-center justify-center mb-stack-md">
                  <span className="material-symbols-outlined text-primary text-[32px]">lock_reset</span>
                </div>
                <h1 className="font-headline-lg text-headline-lg text-on-background mb-unit font-bold">
                  Forgot Password - Step 1
                </h1>
                <p className="font-body-lg text-body-lg text-on-surface-variant max-w-[320px]">
                  Enter your email to start the password recovery process.
                </p>
              </div>

              <form onSubmit={handleRequestOtp} className="space-y-stack-md">
                <div className="space-y-unit">
                  <label className="block font-label-md text-label-md text-on-surface-variant ml-unit font-medium" htmlFor="email">
                    Email address
                  </label>
                  <div className="relative">
                    <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                      mail
                    </span>
                    <input
                      className="w-full pl-12 pr-4 py-3 bg-surface border border-outline-variant rounded-lg font-body-lg text-body-lg focus:ring-2 focus:ring-primary/20 focus:border-primary transition-all outline-none"
                      id="email"
                      placeholder="username@example.com"
                      required
                      type="email"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      disabled={loading}
                    />
                  </div>
                </div>

                <button
                  type="submit"
                  disabled={loading}
                  className="w-full bg-primary text-on-primary font-headline-md text-headline-md font-bold py-4 rounded-lg hover:opacity-90 active:scale-[0.98] transition-all flex items-center justify-center gap-stack-sm disabled:opacity-50 shadow-sm"
                >
                  {loading ? (
                    <>
                      <span className="material-symbols-outlined animate-spin">progress_activity</span>
                      <span>Sending Code...</span>
                    </>
                  ) : (
                    <>
                      <span>Send Verification Code</span>
                      <span className="material-symbols-outlined">arrow_forward</span>
                    </>
                  )}
                </button>
              </form>

              <div className="mt-stack-lg pt-stack-md border-t border-outline-variant/30 text-center">
                <p className="font-body-sm text-body-sm text-on-surface-variant">
                  Remember your password?{' '}
                  <Link className="text-primary font-bold hover:underline ml-unit" to="/login">
                    Back to Login
                  </Link>
                </p>
              </div>
            </>
          )}

          {step === 2 && (
            <>
              <div className="text-center mb-stack-lg">
                <div className="inline-flex items-center justify-center w-16 h-16 bg-primary-container rounded-full mb-stack-md text-on-primary">
                  <span className="material-symbols-outlined text-[32px]">verified_user</span>
                </div>
                <h1 className="font-headline-lg text-headline-lg text-primary mb-2 font-bold">
                  Forgot Password - OTP Verification
                </h1>
                <p className="font-body-lg text-body-lg text-on-surface-variant">
                  A verification code has been sent to your email{' '}
                  <span className="font-bold text-error">{email}</span>
                </p>
              </div>

              <form onSubmit={handleVerifyOtp} className="space-y-stack-lg">
                <div className="flex justify-center gap-2 sm:gap-3 mb-stack-lg">
                  {otp.map((digit, index) => (
                    <input
                      key={index}
                      ref={(el) => (inputRefs.current[index] = el)}
                      className="w-10 h-12 sm:w-12 sm:h-14 text-center text-xl sm:text-2xl font-bold rounded-lg border border-outline-variant focus:border-primary focus:ring-2 focus:ring-primary/20 bg-surface transition-all outline-none"
                      maxLength={1}
                      type="text"
                      value={digit}
                      onChange={(e) => handleOtpChange(index, e.target.value)}
                      onKeyDown={(e) => handleOtpKeyDown(index, e)}
                      disabled={loading}
                    />
                  ))}
                </div>

                <div className="flex flex-col items-center gap-stack-md pt-stack-sm">
                  <button
                    type="submit"
                    disabled={loading}
                    className="w-full py-4 bg-primary text-on-primary rounded-lg font-headline-md text-headline-md font-bold hover:opacity-90 transition-all shadow-[0px_4px_20px_rgba(15,23,42,0.05)] flex items-center justify-center active:scale-[0.98] disabled:opacity-50"
                  >
                    {loading ? (
                      <>
                        <span className="material-symbols-outlined animate-spin">progress_activity</span>
                        <span>Verifying...</span>
                      </>
                    ) : (
                      <span>Confirm OTP</span>
                    )}
                  </button>

                  <div className="flex items-center gap-2 font-body-sm text-body-sm text-on-surface-variant">
                    <span>Didn't receive the code?</span>
                    <button
                      type="button"
                      onClick={handleResendOtp}
                      disabled={countdown > 0 || loading}
                      className={`flex items-center gap-1 font-bold transition-all group ${countdown > 0 ? 'text-outline cursor-not-allowed' : 'text-primary hover:underline'}`}
                    >
                      <span>Resend OTP</span>
                      <span className="font-mono text-label-md bg-secondary-container text-on-secondary-container px-2 py-0.5 rounded-full group-disabled:bg-surface-variant">
                        {countdown > 0 ? `${countdown}s` : 'Available'}
                      </span>
                    </button>
                  </div>
                </div>
              </form>

              <div className="mt-stack-lg pt-stack-lg border-t border-outline-variant/30">
                <div className="flex items-center gap-stack-md p-stack-md bg-surface-variant/20 rounded-lg">
                  <div className="flex-shrink-0">
                    <span className="material-symbols-outlined text-secondary">security</span>
                  </div>
                  <div className="flex-1">
                    <h4 className="font-label-md text-label-md text-on-surface font-bold">Account Security</h4>
                    <p className="text-[11px] leading-tight text-on-surface-variant mt-1">
                      This verification code is only valid for 5 minutes. Do not share this code with anyone to protect your privacy.
                    </p>
                  </div>
                </div>
              </div>

              <div className="mt-8 text-center">
                <button
                  type="button"
                  onClick={() => setStep(1)}
                  className="inline-flex items-center gap-2 text-secondary hover:text-primary transition-colors font-label-md text-label-md font-medium"
                >
                  <span className="material-symbols-outlined text-[18px]">arrow_back</span>
                  Back to Email Input
                </button>
              </div>
            </>
          )}

          {step === 3 && (
            <>
              <div className="flex flex-col items-center text-center mb-stack-lg">
                <div className="w-16 h-16 bg-secondary-container text-primary rounded-full flex items-center justify-center mb-stack-md">
                  <span className="material-symbols-outlined text-[32px]">lock_reset</span>
                </div>
                <h1 className="font-headline-lg text-headline-lg text-on-surface mb-unit font-bold">Reset Password</h1>
                <p className="font-body-lg text-body-lg text-on-surface-variant">
                  Please enter a new password for your account to complete the recovery process.
                </p>
              </div>

              <form onSubmit={handleResetPassword} className="space-y-stack-md">
                <div className="space-y-unit">
                  <label className="block font-label-md text-label-md text-on-surface-variant ml-1 font-medium">New Password</label>
                  <div className="relative">
                    <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                      lock
                    </span>
                    <input
                      className="w-full pl-12 pr-12 py-3 bg-surface border border-outline-variant rounded-lg font-body-lg text-body-lg focus:outline-none focus:ring-2 focus:ring-primary focus:border-primary transition-all placeholder:text-outline"
                      placeholder="Enter new password"
                      required
                      type={showNewPassword ? 'text' : 'password'}
                      value={newPassword}
                      onChange={(e) => setNewPassword(e.target.value)}
                      disabled={loading}
                    />
                    <button
                      className="absolute right-4 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors flex items-center justify-center"
                      type="button"
                      onClick={() => setShowNewPassword(!showNewPassword)}
                    >
                      <span className="material-symbols-outlined text-[20px]">
                        {showNewPassword ? 'visibility_off' : 'visibility'}
                      </span>
                    </button>
                  </div>
                  <p className="font-label-md text-label-md text-outline px-1">Password must be at least 8 characters long.</p>
                </div>

                <div className="space-y-unit">
                  <label className="block font-label-md text-label-md text-on-surface-variant ml-1 font-medium">Confirm New Password</label>
                  <div className="relative">
                    <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                      verified_user
                    </span>
                    <input
                      className="w-full pl-12 pr-12 py-3 bg-surface border border-outline-variant rounded-lg font-body-lg text-body-lg focus:outline-none focus:ring-2 focus:ring-primary focus:border-primary transition-all placeholder:text-outline"
                      placeholder="Confirm new password"
                      required
                      type={showConfirmPassword ? 'text' : 'password'}
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      disabled={loading}
                    />
                    <button
                      className="absolute right-4 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors flex items-center justify-center"
                      type="button"
                      onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                    >
                      <span className="material-symbols-outlined text-[20px]">
                        {showConfirmPassword ? 'visibility_off' : 'visibility'}
                      </span>
                    </button>
                  </div>
                </div>

                <div className="grid grid-cols-2 gap-stack-sm pt-unit">
                  <div className={`flex items-center gap-unit ${hasUpperAndLower ? 'text-primary font-bold' : 'text-outline'}`}>
                    <span className="material-symbols-outlined text-[16px]">
                      {hasUpperAndLower ? 'check_circle' : 'radio_button_unchecked'}
                    </span>
                    <span className="font-label-md text-label-md">Upper &amp; lower case</span>
                  </div>
                  <div className={`flex items-center gap-unit ${hasSpecialChar ? 'text-primary font-bold' : 'text-outline'}`}>
                    <span className="material-symbols-outlined text-[16px]">
                      {hasSpecialChar ? 'check_circle' : 'radio_button_unchecked'}
                    </span>
                    <span className="font-label-md text-label-md">Special character</span>
                  </div>
                </div>

                <button
                  type="submit"
                  disabled={loading}
                  className="w-full mt-stack-md bg-primary text-on-primary py-4 rounded-lg font-headline-md text-headline-md font-bold hover:opacity-90 transition-colors shadow-sm disabled:opacity-50 flex items-center justify-center gap-2"
                >
                  {loading ? (
                    <>
                      <span className="material-symbols-outlined animate-spin">progress_activity</span>
                      <span>Resetting...</span>
                    </>
                  ) : (
                    <span>Reset Password</span>
                  )}
                </button>

                <div className="text-center pt-stack-sm">
                  <Link className="font-label-md text-label-md text-primary hover:underline flex items-center justify-center gap-unit font-medium" to="/login">
                    <span className="material-symbols-outlined text-[16px]">arrow_back</span>
                    Back to login
                  </Link>
                </div>
              </form>
            </>
          )}

          <div className="mt-stack-lg text-center border-t border-outline-variant/30 pt-6">
            <p className="font-body-sm text-body-sm text-on-surface-variant">
              Having trouble? <a className="text-primary font-semibold hover:underline" href="#">Contact support</a>
            </p>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};

export default ForgotPasswordPage;
