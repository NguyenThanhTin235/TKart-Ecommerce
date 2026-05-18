import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/redux/store';
import { setLoading, setError, loginSuccess } from '@/redux/authSlice';
import { authApi } from '@/api/authApi';
import MainLayout from '@/components/layout/MainLayout';
import AlertStatus from '@/components/common/AlertStatus';

declare global {
  interface Window {
    google: any;
  }
}

const LoginPage: React.FC = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [formError, setFormError] = useState<string | null>(null);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { loading } = useSelector((state: RootState) => state.auth);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setFormError(null);

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      setFormError('Please enter a valid email address');
      return;
    }

    if (!password.trim()) {
      setFormError('Please enter your password');
      return;
    }

    dispatch(setLoading(true));

    try {
      const response = await authApi.login({ email, password });
      if (response.data) {
        dispatch(loginSuccess({
          token: response.data.token,
          user: response.data.user,
        }));
        navigate('/');
      }
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
      dispatch(setError(errorMessage));
    } finally {
      dispatch(setLoading(false));
    }
  };

  const handleGoogleResponse = async (response: any) => {
    try {
      dispatch(setLoading(true));
      setFormError(null);
      const res = await authApi.googleLogin({ tokenId: response.credential });
      if (res.data) {
        dispatch(loginSuccess({
          token: res.data.token,
          user: res.data.user,
        }));
        navigate('/');
      }
    } catch (err: any) {
      const errorMessage = typeof err === 'string' ? err : err?.message || err?.error || JSON.stringify(err);
      setFormError(errorMessage);
      dispatch(setError(errorMessage));
    } finally {
      dispatch(setLoading(false));
    }
  };

  useEffect(() => {
    const loadGoogleScript = () => {
      const script = document.createElement('script');
      script.src = 'https://accounts.google.com/gsi/client';
      script.async = true;
      script.defer = true;
      script.onload = () => {
        if (window.google) {
          window.google.accounts.id.initialize({
            client_id: import.meta.env.VITE_GOOGLE_CLIENT_ID || '408847171120-dldrof0cc90f3nn19qsl7amrl7fpc9jc.apps.googleusercontent.com',
            callback: handleGoogleResponse,
          });
          const container = document.getElementById('googleButtonContainer');
          if (container) {
            window.google.accounts.id.renderButton(container, {
              theme: 'outline',
              size: 'large',
              type: 'standard',
            });
          }
        }
      };
      document.body.appendChild(script);
    };
    loadGoogleScript();
  }, []);

  const handleGoogleLoginClick = () => {
    if (window.google) {
      window.google.accounts.id.prompt((notification: any) => {
        if (notification.isNotDisplayed() || notification.isSkippedMoment()) {
          const container = document.getElementById('googleButtonContainer');
          if (container) {
            const button = container.querySelector('div[role="button"]') as HTMLElement;
            if (button) {
              button.click();
            } else {
              setFormError('Unable to open Google login. Please check your popup blocker settings.');
            }
          }
        }
      });
    } else {
      setFormError('Google login service is loading. Please try again in a moment.');
    }
  };

  return (
    <MainLayout>
      <div className="flex-grow flex items-center justify-center px-margin-mobile md:px-0 py-stack-lg w-full">
        <div className="w-full max-w-[440px] mx-auto">
          {/* Brand Header */}
          <div className="text-center mb-stack-lg">
            <Link to="/" className="font-display text-headline-lg text-primary tracking-tighter font-extrabold">
              Tkart
            </Link>
          </div>

          {/* Login Card */}
          <div className="login-card bg-surface-container-lowest rounded-xl p-stack-lg border border-outline-variant shadow-[0px_4px_20px_rgba(15,23,42,0.05)]">
            <div className="mb-stack-lg">
              <h1 className="font-headline-lg text-headline-lg text-on-surface font-bold">Login</h1>
              <p className="font-body-sm text-body-sm text-on-surface-variant mt-unit">
                Welcome back to the Tkart community.
              </p>
            </div>

            <AlertStatus
              type="error"
              message={formError}
              onClose={() => setFormError(null)}
            />

            <form onSubmit={handleSubmit} className="space-y-stack-md">
              {/* Email Field */}
              <div className="space-y-unit">
                <label className="font-label-md text-label-md text-on-surface-variant block px-unit font-medium" htmlFor="email">
                  Email address
                </label>
                <div className="relative">
                  <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-on-surface-variant text-[20px]">
                    mail
                  </span>
                  <input
                    className="w-full pl-11 pr-4 py-3 bg-surface rounded-lg border border-outline-variant focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all outline-none font-body-lg text-body-lg"
                    id="email"
                    name="email"
                    placeholder="name@example.com"
                    required
                    type="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    disabled={loading}
                  />
                </div>
              </div>

              {/* Password Field */}
              <div className="space-y-unit">
                <div className="flex justify-between items-center px-unit">
                  <label className="font-label-md text-label-md text-on-surface-variant font-medium" htmlFor="password">
                    Password
                  </label>
                  <Link className="font-label-md text-label-md text-primary hover:underline transition-all font-medium" to="/forgot-password">
                    Forgot Password?
                  </Link>
                </div>
                <div className="relative">
                  <span className="material-symbols-outlined absolute left-4 top-1/2 -translate-y-1/2 text-on-surface-variant text-[20px]">
                    lock
                  </span>
                  <input
                    className="w-full pl-11 pr-11 py-3 bg-surface rounded-lg border border-outline-variant focus:border-primary focus:ring-2 focus:ring-primary/20 transition-all outline-none font-body-lg text-body-lg"
                    id="password"
                    name="password"
                    placeholder="••••••••"
                    required
                    type={showPassword ? 'text' : 'password'}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    disabled={loading}
                  />
                  <button
                    className="absolute right-4 top-1/2 -translate-y-1/2 text-on-surface-variant hover:text-primary transition-colors"
                    type="button"
                    onClick={() => setShowPassword(!showPassword)}
                  >
                    <span className="material-symbols-outlined text-[20px]">
                      {showPassword ? 'visibility_off' : 'visibility'}
                    </span>
                  </button>
                </div>
              </div>

              {/* Primary Action */}
              <div className="pt-stack-sm">
                <button
                  className="w-full py-3 px-stack-md bg-primary text-on-primary font-headline-md text-headline-md font-bold rounded-lg shadow-sm hover:opacity-90 active:scale-[0.98] transition-all flex justify-center items-center gap-stack-sm disabled:opacity-50"
                  type="submit"
                  disabled={loading}
                >
                  {loading ? (
                    <>
                      <span className="material-symbols-outlined animate-spin">progress_activity</span>
                      <span>Logging in...</span>
                    </>
                  ) : (
                    <span>Login</span>
                  )}
                </button>
               </div>
            </form>

            {/* Divider */}
            <div className="flex items-center gap-stack-md my-stack-lg">
              <div className="h-px bg-outline-variant flex-grow"></div>
              <span className="font-label-md text-label-md text-outline">or login with</span>
              <div className="h-px bg-outline-variant flex-grow"></div>
            </div>

            {/* Social Options */}
            <div className="space-y-gutter relative">
              <button
                type="button"
                onClick={handleGoogleLoginClick}
                className="w-full flex items-center justify-center gap-stack-sm py-stack-sm border border-outline-variant rounded-lg hover:bg-surface-container-low transition-colors font-medium text-on-surface"
              >
                <img alt="Google" className="w-5 h-5" src="https://www.gstatic.com/images/branding/product/1x/gsa_512dp.png" />
                <span className="font-label-md text-label-md">Continue with Google</span>
              </button>
              <div id="googleButtonContainer" className="hidden"></div>
            </div>

            {/* Secondary Option */}
            <div className="mt-stack-lg text-center">
              <p className="font-body-sm text-body-sm text-on-surface-variant">
                Don't have an account?{' '}
                <Link className="text-primary font-bold hover:underline" to="/register">
                  Sign up
                </Link>
              </p>
            </div>
          </div>

          {/* Security/Trust Badges */}
          <div className="mt-stack-lg flex justify-center items-center gap-stack-lg opacity-60">
            <div className="flex items-center gap-unit">
              <span className="material-symbols-outlined text-[16px]">verified_user</span>
              <span className="font-label-md text-label-md uppercase tracking-widest font-bold">SSL SECURED</span>
            </div>
            <div className="flex items-center gap-unit">
              <span className="material-symbols-outlined text-[16px]">shopping_bag</span>
              <span className="font-label-md text-label-md uppercase tracking-widest font-bold">GENUINE PRODUCTS</span>
            </div>
          </div>
        </div>
      </div>
    </MainLayout>
  );
};

export default LoginPage;
