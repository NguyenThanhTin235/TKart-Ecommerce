import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/redux/store';
import { setLoading, setError, loginSuccess } from '@/redux/authSlice';
import { authApi } from '@/api/authApi';
import MainLayout from '@/components/layout/MainLayout';
import AlertStatus from '@/components/common/AlertStatus';

const OtpVerificationPage: React.FC = () => {
  const [otp, setOtp] = useState<string[]>(['', '', '', '', '', '']);
  const [countdown, setCountdown] = useState(60);
  const [formError, setFormError] = useState<string | null>(null);
  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const inputRefs = useRef<(HTMLInputElement | null)[]>([]);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading, otpEmail } = useSelector((state: RootState) => state.auth);

  useEffect(() => {
    if (!otpEmail) {
      // If no email in state/localStorage, redirect to login
      navigate('/login');
    }
  }, [otpEmail, navigate]);

  useEffect(() => {
    if (countdown > 0) {
      const timer = setTimeout(() => setCountdown(countdown - 1), 1000);
      return () => clearTimeout(timer);
    }
  }, [countdown]);

  const handleChange = (index: number, value: string) => {
    if (isNaN(Number(value))) return;

    const newOtp = [...otp];
    newOtp[index] = value;
    setOtp(newOtp);

    // Auto advance focus
    if (value !== '' && index < 5) {
      inputRefs.current[index + 1]?.focus();
    }
  };

  const handleKeyDown = (index: number, e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Backspace' && otp[index] === '' && index > 0) {
      inputRefs.current[index - 1]?.focus();
    }
  };

  const handleResend = async () => {
    if (!otpEmail || countdown > 0) return;
    setFormError(null);
    setSuccessMessage(null);
    dispatch(setLoading(true));

    try {
      await authApi.sendRegistrationOtp({ email: otpEmail });
      setCountdown(60);
      setSuccessMessage('A new OTP has been sent to your email.');
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
    } finally {
      dispatch(setLoading(false));
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormError(null);
    setSuccessMessage(null);

    const otpCode = otp.join('');
    if (otpCode.length < 6) {
      setFormError('Please enter the 6-digit OTP code');
      return;
    }

    if (!otpEmail) {
      setFormError('Session expired. Please try registering again.');
      return;
    }

    dispatch(setLoading(true));

    try {
      const regFullName = localStorage.getItem('reg_fullName');
      const regPassword = localStorage.getItem('reg_password');

      let response;
      if (regFullName && regPassword) {
        // Complete full registration flow
        response = await authApi.register({
          fullName: regFullName,
          email: otpEmail,
          password: regPassword,
          otpCode: otpCode,
        });
      } else {
        // Fallback or standard OTP verification flow
        response = await authApi.verifyOtp({
          email: otpEmail,
          otp: otpCode,
        });
      }

      if (response.data) {
        setSuccessMessage('Registration successful! Redirecting to login page...');
        // Clean up temp storage
        localStorage.removeItem('reg_fullName');
        localStorage.removeItem('reg_email');
        localStorage.removeItem('reg_password');
        setTimeout(() => {
          navigate('/login');
        }, 2000);
      }
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
      <div className="flex-grow flex items-center justify-center px-margin-mobile py-stack-lg w-full">
        <div className="max-w-[480px] w-full mx-auto">
          {/* Verification Card */}
          <div className="bg-surface-container-lowest rounded-[24px] p-8 md:p-12 shadow-[0px_4px_20px_rgba(15,23,42,0.05)] border border-outline-variant/30 text-center">
            {/* Illustration/Icon */}
            <div className="mb-8 flex justify-center">
              <div className="w-16 h-16 bg-primary-container/10 rounded-full flex items-center justify-center">
                <span className="material-symbols-outlined text-[32px] text-primary">verified_user</span>
              </div>
            </div>

            <h1 className="font-headline-lg text-headline-lg text-on-surface mb-stack-sm font-bold">
              OTP Verification
            </h1>
            <p className="font-body-sm text-body-sm text-on-surface-variant mb-stack-lg">
              Please enter the 6-digit code sent to your email{' '}
              <span className="font-bold text-on-surface">{otpEmail}</span> to complete the security process.
            </p>

            <AlertStatus
              type="error"
              message={formError}
              onClose={() => setFormError(null)}
            />

            <AlertStatus
              type="success"
              message={successMessage}
              onClose={() => setSuccessMessage(null)}
            />

            {/* OTP Input Cluster */}
            <form onSubmit={handleSubmit} className="space-y-stack-lg">
              <div className="flex justify-center gap-2 sm:gap-3 mb-stack-lg">
                {otp.map((digit, index) => (
                  <input
                    key={index}
                    ref={(el) => (inputRefs.current[index] = el)}
                    className="w-10 h-12 sm:w-12 sm:h-14 text-center text-xl sm:text-2xl font-bold border border-outline-variant rounded-xl bg-surface transition-all focus:border-primary focus:ring-2 focus:ring-primary/20 outline-none"
                    maxLength={1}
                    type="text"
                    value={digit}
                    onChange={(e) => handleChange(index, e.target.value)}
                    onKeyDown={(e) => handleKeyDown(index, e)}
                    disabled={loading}
                  />
                ))}
              </div>

              <button
                className="w-full bg-primary-container text-on-primary font-headline-md text-headline-md font-bold py-4 rounded-xl shadow-sm hover:opacity-90 active:scale-[0.98] transition-all flex justify-center items-center gap-2 disabled:opacity-50"
                type="submit"
                disabled={loading}
              >
                {loading ? (
                  <>
                    <span className="material-symbols-outlined animate-spin">progress_activity</span>
                    <span>Verifying...</span>
                  </>
                ) : (
                  <span>Verify Code</span>
                )}
              </button>
            </form>

            <div className="mt-8 pt-8 border-t border-outline-variant/50">
              <p className="font-body-sm text-body-sm text-on-surface-variant mb-2">Didn't receive the code?</p>
              <div className="flex items-center justify-center gap-2">
                <button
                  type="button"
                  onClick={handleResend}
                  disabled={countdown > 0 || loading}
                  className={`font-bold transition-all ${countdown > 0 ? 'text-secondary opacity-50 cursor-not-allowed' : 'text-primary hover:underline'}`}
                >
                  Resend Code
                </button>
                <span className="text-secondary opacity-60">•</span>
                <span className="text-secondary font-label-md text-label-md">
                  {countdown > 0 ? `00:${countdown < 10 ? '0' : ''}${countdown}s` : 'Available'}
                </span>
              </div>
            </div>
          </div>

          {/* Back Link */}
          <div className="mt-8 text-center">
            <Link className="inline-flex items-center gap-2 text-secondary hover:text-primary transition-colors font-label-md text-label-md font-medium" to="/login">
              <span className="material-symbols-outlined text-[18px]">arrow_back</span>
              Back to Login
            </Link>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};

export default OtpVerificationPage;
