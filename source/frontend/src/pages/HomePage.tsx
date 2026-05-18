import React from 'react';
import { Link } from 'react-router-dom';
import MainLayout from '@/components/layout/MainLayout';
import { useSelector } from 'react-redux';
import { RootState } from '@/redux/store';

const HomePage: React.FC = () => {
  const { user, isAuthenticated } = useSelector((state: RootState) => state.auth);

  return (
    <MainLayout>
      <div className="flex-grow bg-background">
        {/* Hero Section */}
        <section className="bg-surface-container-low border-b border-outline-variant/30 py-16 md:py-24 animate-fade-in">
          <div className="max-w-container-max mx-auto px-margin-mobile md:px-margin-desktop text-center">
            <span className="bg-primary-fixed text-on-primary-fixed font-label-caps text-label-caps px-3 py-1 rounded-full mb-6 inline-block font-bold">
              Academic Modernism Design System
            </span>
            <h1 className="font-display text-display text-on-background mb-6 font-extrabold max-w-4xl mx-auto tracking-tight">
              Institutional Precision Meets Digital E-Commerce
            </h1>
            <p className="font-body-lg text-body-lg text-on-surface-variant max-w-2xl mx-auto mb-10 leading-relaxed">
              Explore the next generation of online marketplace solutions. Engineered with clean geometry, robust JWT security, and state-of-the-art minimalist aesthetics.
            </p>

            <div className="flex flex-wrap justify-center gap-4">
              {isAuthenticated ? (
                <Link
                  to="/profile"
                  className="bg-primary text-on-primary px-8 py-4 rounded-lg font-headline-md text-headline-md font-bold hover:opacity-90 transition-all shadow-[0px_4px_20px_rgba(15,23,42,0.05)] active:scale-[0.98]"
                >
                  Welcome back, {user?.fullName || user?.email || 'User'}
                </Link>
              ) : (
                <>
                  <Link
                    to="/login"
                    className="bg-primary text-on-primary px-8 py-4 rounded-lg font-headline-md text-headline-md font-bold hover:opacity-90 transition-all shadow-[0px_4px_20px_rgba(15,23,42,0.05)] active:scale-[0.98]"
                  >
                    Explore Platform Login
                  </Link>
                  <Link
                    to="/register"
                    className="bg-surface-container-lowest text-primary border border-outline-variant px-8 py-4 rounded-lg font-headline-md text-headline-md font-bold hover:bg-surface-container-low transition-all active:scale-[0.98]"
                  >
                    Create Scholar Account
                  </Link>
                </>
              )}
            </div>
          </div>
        </section>

        {/* Quick Navigation Cards */}
        <section className="max-w-container-max mx-auto px-margin-mobile md:px-margin-desktop py-16 animate-fade-in">
          <div className="mb-12 text-center md:text-left">
            <h2 className="font-headline-lg text-headline-lg text-on-background font-bold mb-2">Authentication Flows</h2>
            <p className="font-body-lg text-body-lg text-on-surface-variant">
              Quick access to all frontend authentication modules verified under the Academic Modernism specification.
            </p>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <Link
              to="/login"
              className="bg-surface-container-lowest border border-outline-variant/50 rounded-xl p-8 shadow-[0px_4px_20px_rgba(15,23,42,0.05)] hover:shadow-[0px_10px_30px_rgba(15,23,42,0.1)] hover:-translate-y-1 transition-all duration-200 group"
            >
              <div className="w-12 h-12 bg-primary-fixed rounded-lg flex items-center justify-center mb-6 group-hover:bg-primary group-hover:text-on-primary transition-colors">
                <span className="material-symbols-outlined text-primary group-hover:text-on-primary text-[24px]">login</span>
              </div>
              <h3 className="font-headline-md text-headline-md text-on-background font-bold mb-2 group-hover:text-primary transition-colors">
                Login Portal
              </h3>
              <p className="font-body-sm text-body-sm text-on-surface-variant mb-6">
                Secure JWT authentication interface with standardized input states and interactive error handling.
              </p>
              <span className="font-label-md text-label-md text-primary font-bold flex items-center gap-1">
                View Module <span className="material-symbols-outlined text-[16px]">arrow_forward</span>
              </span>
            </Link>

            <Link
              to="/register"
              className="bg-surface-container-lowest border border-outline-variant/50 rounded-xl p-8 shadow-[0px_4px_20px_rgba(15,23,42,0.05)] hover:shadow-[0px_10px_30px_rgba(15,23,42,0.1)] hover:-translate-y-1 transition-all duration-200 group"
            >
              <div className="w-12 h-12 bg-secondary-fixed rounded-lg flex items-center justify-center mb-6 group-hover:bg-primary group-hover:text-on-primary transition-colors">
                <span className="material-symbols-outlined text-secondary group-hover:text-on-primary text-[24px]">person_add</span>
              </div>
              <h3 className="font-headline-md text-headline-md text-on-background font-bold mb-2 group-hover:text-primary transition-colors">
                Student Registration
              </h3>
              <p className="font-body-sm text-body-sm text-on-surface-variant mb-6">
                Two-column academic onboarding flow with automatic OTP dispatch and password verification.
              </p>
              <span className="font-label-md text-label-md text-primary font-bold flex items-center gap-1">
                View Module <span className="material-symbols-outlined text-[16px]">arrow_forward</span>
              </span>
            </Link>

            <Link
              to="/forgot-password"
              className="bg-surface-container-lowest border border-outline-variant/50 rounded-xl p-8 shadow-[0px_4px_20px_rgba(15,23,42,0.05)] hover:shadow-[0px_10px_30px_rgba(15,23,42,0.1)] hover:-translate-y-1 transition-all duration-200 group"
            >
              <div className="w-12 h-12 bg-tertiary-fixed rounded-lg flex items-center justify-center mb-6 group-hover:bg-primary group-hover:text-on-primary transition-colors">
                <span className="material-symbols-outlined text-tertiary group-hover:text-on-primary text-[24px]">lock_reset</span>
              </div>
              <h3 className="font-headline-md text-headline-md text-on-background font-bold mb-2 group-hover:text-primary transition-colors">
                Password Recovery
              </h3>
              <p className="font-body-sm text-body-sm text-on-surface-variant mb-6">
                Multi-step secure recovery engine with 6-digit OTP verification and real-time password strength validation.
              </p>
              <span className="font-label-md text-label-md text-primary font-bold flex items-center gap-1">
                View Module <span className="material-symbols-outlined text-[16px]">arrow_forward</span>
              </span>
            </Link>
          </div>
        </section>
      </div>
    </MainLayout>
  );
};

export default HomePage;
