import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/redux/store';
import { setLoading, setError, setOtpEmail } from '@/redux/authSlice';
import { authApi } from '@/api/authApi';
import MainLayout from '@/components/layout/MainLayout';
import AlertStatus from '@/components/common/AlertStatus';

const RegisterPage: React.FC = () => {
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);
  const [agreeTerms, setAgreeTerms] = useState(false);
  const [formError, setFormError] = useState<string | null>(null);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading } = useSelector((state: RootState) => state.auth);

  const hasLength = password.length >= 8;
  const hasUpperAndLower = /[a-z]/.test(password) && /[A-Z]/.test(password);
  const hasNumber = /\d/.test(password);
  const hasSpecialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormError(null);

    if (fullName.trim().length < 2) {
      setFormError('Full name must be at least 2 characters long');
      return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      setFormError('Please enter a valid email address');
      return;
    }

    if (!hasLength || !hasUpperAndLower || !hasNumber || !hasSpecialChar) {
      setFormError('Password must meet all security requirements');
      return;
    }

    if (password !== confirmPassword) {
      setFormError('Passwords do not match');
      return;
    }

    if (!agreeTerms) {
      setFormError('You must agree to the Terms of Service and Privacy Policy');
      return;
    }

    dispatch(setLoading(true));

    try {
      await authApi.sendRegistrationOtp({ email });
      // Store registration data temporarily for OtpVerificationPage
      localStorage.setItem('reg_fullName', fullName);
      localStorage.setItem('reg_email', email);
      localStorage.setItem('reg_password', password);
      dispatch(setOtpEmail(email));
      navigate('/verify-otp');
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
      <div className="flex-grow flex items-center justify-center py-stack-lg px-margin-mobile w-full">
        <div className="w-full max-w-[1000px] grid md:grid-cols-2 bg-surface-container-lowest rounded-xl shadow-[0px_4px_20px_rgba(15,23,42,0.05)] overflow-hidden border border-outline-variant/30">
          {/* Left Side: Visual/Context */}
          <div className="hidden md:flex relative overflow-hidden bg-primary-container p-margin-desktop flex-col justify-end text-on-primary-container">
            <div className="absolute inset-0 z-0">
              <img
                className="w-full h-full object-cover opacity-40 mix-blend-overlay"
                alt="University campus marketplace"
                src="https://lh3.googleusercontent.com/aida-public/AB6AXuCMydRTsBmspK2mLaDDclXxglmNfSFiYJlOJJhhYsgX0ndssV51gbCEKZwCSF0vYf5mHHoyXyT6PqqTUAqOMTfnXplybz3mgeJR0EqLEhw4Wfnk7ttAtrlxmdAPQrAdLhbNiXc7ggbIxwEr5pOttI7I4ywnfB7hBnVLd5Y5MLv4EYk_kQOGz2fkwgQmPcd9nBwfglTaEd-aTLHa52JxdGqFwMJD41MURNeHsVd8aWqui1tdm_-4PQXa4y0CP6J0ZYI1dd-MmNBfXAo"
              />
            </div>
            <div className="relative z-10 space-y-4">
              <h2 className="font-headline-lg text-headline-lg text-on-primary-container font-bold">
                Join the Tkart Community
              </h2>
              <p className="font-body-lg text-body-lg opacity-90">
                Connecting students and faculty through the most modern and sophisticated academic shopping experiences.
              </p>
              <div className="flex gap-4 pt-4 items-center">
                <div className="flex -space-x-3">
                  <div className="w-10 h-10 rounded-full border-2 border-primary-container bg-surface-variant overflow-hidden">
                    <img
                      alt="User"
                      className="w-full h-full object-cover"
                      src="https://lh3.googleusercontent.com/aida-public/AB6AXuBE-tlrqomVlDje5USwvo0JdnA17t6S8Q_zFN1RhLCAQkJDLaFqMTkf2jmdVQsqlodiDvLzCckdip04_r8xVNUaXeziloZMaxPD-Xelw_uvKdOFSxYXQ70JSvhFuQGYubbuj4PBd28-6kNbOom9T3J-V1MnOCtRcuud5DWxVh5DVxckWZtf0hPG-CTcYW8naQm-qJH3UW-gv_fhpkmtuXrYd9DXCbAbAHEIlX_RuoLKpwvM2Dq2vE0TOQWMQp4MWvvK7kNY64E4FsA"
                    />
                  </div>
                  <div className="w-10 h-10 rounded-full border-2 border-primary-container bg-surface-variant overflow-hidden">
                    <img
                      alt="User"
                      className="w-full h-full object-cover"
                      src="https://lh3.googleusercontent.com/aida-public/AB6AXuCpvbjg_MrGDiK0kFfCl7JsJr_MCUq9DpXLDGiCxPMPcOWrc-l9SWWVOVp1BLf-y2nNLXf9FMnxqMsxU6KRGb7xdqyhjedG0CWiYLLPcckkhwxDbJF3J8Bskeq_NlARXdZjVrgXwNMH8DahchW8Cs8QB4JJ3cXd6JTF0zJ-Kt2zhUZwp7TwlhT-45rjDDzgu2v8wRCE0zaaIF6ShBrr5CHzj4Rja6j8c-sfApxg5zrhaLhKW2MB6_IdlLCOMPjtmLvcSREYZnrXLz8"
                    />
                  </div>
                  <div className="w-10 h-10 rounded-full border-2 border-primary-container bg-surface-variant overflow-hidden">
                    <img
                      alt="User"
                      className="w-full h-full object-cover"
                      src="https://lh3.googleusercontent.com/aida-public/AB6AXuBylUv_z5hONdbePey2rTYz4TODmXSaKXKbBv3hWXKE894pkJy8h1KTzeL9y3q0UnNEhlkJqjQbTfXm16x_K3vtnOH_xijQT45qa6jO-UXRjrgla8IgMQq-XT_-sGEJTYMIzzewUT3rH9kamyMZC7IOT7fUhpJU_z3rWJ9W3NdlJrp7ec8WiIl69gnYOibIjQmUh_K3OaH31ffrt8sf9v0kk-GH6YiU3y9A6BY0gaETQzQuy9j1BOwNr0LwT-40i7apXDoE73Ooa-g"
                    />
                  </div>
                </div>
                <p className="text-label-md font-label-md font-bold">More than 5000+ members joined</p>
              </div>
            </div>
          </div>

          {/* Right Side: Form */}
          <div className="p-8 md:p-12">
            <div className="mb-10">
              <h1 className="font-headline-lg text-headline-lg text-primary mb-2 font-bold">Create Account</h1>
              <p className="font-body-sm text-body-sm text-on-surface-variant">
                Please fill in the information below to start shopping at Tkart marketplace.
              </p>
            </div>

            <AlertStatus
              type="error"
              message={formError}
              onClose={() => setFormError(null)}
            />

            <form onSubmit={handleSubmit} className="space-y-6">
              <div className="space-y-2">
                <label className="font-label-md text-label-md text-secondary font-medium" htmlFor="fullname">
                  Full Name
                </label>
                <div className="relative">
                  <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                    person
                  </span>
                  <input
                    className="w-full pl-10 pr-4 py-3 bg-white border border-outline-variant rounded-lg font-body-sm text-body-sm focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition-all"
                    id="fullname"
                    placeholder="John Doe"
                    required
                    type="text"
                    value={fullName}
                    onChange={(e) => setFullName(e.target.value)}
                    disabled={loading}
                  />
                </div>
              </div>

              <div className="space-y-2">
                <label className="font-label-md text-label-md text-secondary font-medium" htmlFor="email">
                  Email address
                </label>
                <div className="relative">
                  <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                    mail
                  </span>
                  <input
                    className="w-full pl-10 pr-4 py-3 bg-white border border-outline-variant rounded-lg font-body-sm text-body-sm focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition-all"
                    id="email"
                    placeholder="example@student.hcmute.edu.vn"
                    required
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    disabled={loading}
                  />
                </div>
              </div>

              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div className="space-y-2">
                  <label className="font-label-md text-label-md text-secondary font-medium" htmlFor="password">
                    Password
                  </label>
                  <div className="relative">
                    <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                      lock
                    </span>
                    <input
                      className="w-full pl-10 pr-10 py-3 bg-white border border-outline-variant rounded-lg font-body-sm text-body-sm focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition-all"
                      id="password"
                      placeholder="••••••••"
                      required
                      type={showPassword ? 'text' : 'password'}
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                      disabled={loading}
                    />
                    <button
                      className="absolute right-3 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors flex items-center justify-center"
                      type="button"
                      onClick={() => setShowPassword(!showPassword)}
                    >
                      <span className="material-symbols-outlined text-[20px]">
                        {showPassword ? 'visibility_off' : 'visibility'}
                      </span>
                    </button>
                  </div>
                </div>

                <div className="space-y-2">
                  <label className="font-label-md text-label-md text-secondary font-medium" htmlFor="confirm-password">
                    Confirm Password
                  </label>
                  <div className="relative">
                    <span className="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-outline text-[20px]">
                      lock_reset
                    </span>
                    <input
                      className="w-full pl-10 pr-10 py-3 bg-white border border-outline-variant rounded-lg font-body-sm text-body-sm focus:outline-none focus:ring-2 focus:ring-primary/20 focus:border-primary transition-all"
                      id="confirm-password"
                      placeholder="••••••••"
                      required
                      type={showConfirmPassword ? 'text' : 'password'}
                      value={confirmPassword}
                      onChange={(e) => setConfirmPassword(e.target.value)}
                      disabled={loading}
                    />
                    <button
                      className="absolute right-3 top-1/2 -translate-y-1/2 text-outline hover:text-primary transition-colors flex items-center justify-center"
                      type="button"
                      onClick={() => setShowConfirmPassword(!showConfirmPassword)}
                    >
                      <span className="material-symbols-outlined text-[20px]">
                        {showConfirmPassword ? 'visibility_off' : 'visibility'}
                      </span>
                    </button>
                  </div>
                </div>
              </div>

              {/* Password Requirements UI */}
              <div className="bg-surface-container-lowest p-4 rounded-lg border border-outline-variant/40 space-y-2 mt-2">
                <p className="font-label-md text-label-md text-on-surface font-bold">Password Security Requirements:</p>
                <div className="grid grid-cols-1 sm:grid-cols-2 gap-2 pt-1">
                  <div className={`flex items-center gap-2 ${hasLength ? 'text-primary font-bold' : 'text-outline'}`}>
                    <span className="material-symbols-outlined text-[16px]">
                      {hasLength ? 'check_circle' : 'radio_button_unchecked'}
                    </span>
                    <span className="font-label-md text-label-md">At least 8 characters</span>
                  </div>
                  <div className={`flex items-center gap-2 ${hasUpperAndLower ? 'text-primary font-bold' : 'text-outline'}`}>
                    <span className="material-symbols-outlined text-[16px]">
                      {hasUpperAndLower ? 'check_circle' : 'radio_button_unchecked'}
                    </span>
                    <span className="font-label-md text-label-md">Upper &amp; lower case</span>
                  </div>
                  <div className={`flex items-center gap-2 ${hasNumber ? 'text-primary font-bold' : 'text-outline'}`}>
                    <span className="material-symbols-outlined text-[16px]">
                      {hasNumber ? 'check_circle' : 'radio_button_unchecked'}
                    </span>
                    <span className="font-label-md text-label-md">At least one number</span>
                  </div>
                  <div className={`flex items-center gap-2 ${hasSpecialChar ? 'text-primary font-bold' : 'text-outline'}`}>
                    <span className="material-symbols-outlined text-[16px]">
                      {hasSpecialChar ? 'check_circle' : 'radio_button_unchecked'}
                    </span>
                    <span className="font-label-md text-label-md">Special character (!@#$...)</span>
                  </div>
                </div>
              </div>

              <div className="flex items-start gap-3 pt-2">
                <input
                  className="mt-1 w-4 h-4 rounded border-outline-variant text-primary focus:ring-primary/20"
                  id="terms"
                  type="checkbox"
                  checked={agreeTerms}
                  onChange={(e) => setAgreeTerms(e.target.checked)}
                  disabled={loading}
                />
                <label className="font-body-sm text-body-sm text-on-surface-variant" htmlFor="terms">
                  I agree to the{' '}
                  <a className="text-primary hover:underline font-medium" href="#">
                    Terms of Service
                  </a>{' '}
                  and{' '}
                  <a className="text-primary hover:underline font-medium" href="#">
                    Privacy Policy
                  </a>
                  .
                </label>
              </div>

              <button
                className="w-full bg-primary-container text-on-primary-container py-3.5 rounded-lg font-headline-md text-headline-md font-bold shadow-sm hover:opacity-90 active:scale-[0.98] transition-all flex justify-center items-center gap-2 disabled:opacity-50"
                type="submit"
                disabled={loading}
              >
                {loading ? (
                  <>
                    <span className="material-symbols-outlined animate-spin">progress_activity</span>
                    <span>Sending OTP...</span>
                  </>
                ) : (
                  <span>Register Now</span>
                )}
              </button>
            </form>

            <div className="mt-8 pt-8 border-t border-outline-variant text-center">
              <p className="font-body-sm text-body-sm text-on-surface-variant">
                Already have an account?{' '}
                <Link className="text-primary font-bold hover:underline" to="/login">
                  Login
                </Link>
              </p>
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};

export default RegisterPage;
