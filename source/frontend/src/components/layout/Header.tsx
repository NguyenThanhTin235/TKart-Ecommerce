import React, { useState, useRef, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '@/redux/store';
import { logout } from '@/redux/authSlice';
import { authApi } from '@/api/authApi';

const Header: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { user, isAuthenticated } = useSelector((state: RootState) => state.auth);
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsDropdownOpen(false);
      }
    };
    document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, []);

  const handleLogout = async (e: React.MouseEvent) => {
    e.stopPropagation();
    try {
      await authApi.logout();
    } catch (err) {
      console.error('Logout API error', err);
    } finally {
      dispatch(logout());
      setIsDropdownOpen(false);
      navigate('/login');
    }
  };

  return (
    <header className="bg-surface-container-lowest shadow-[0px_4px_20px_rgba(15,23,42,0.05)] sticky top-0 z-50 font-medium border-b border-outline-variant/20">
      <div className="flex justify-between items-center w-full px-margin-mobile md:px-margin-desktop py-4 max-w-container-max mx-auto gap-8">
        <div className="flex items-center gap-8">
          <Link className="font-display text-headline-md text-primary tracking-tight font-extrabold flex items-center gap-2" to="/">
            Tkart
          </Link>
          <nav className="hidden md:flex gap-6">
            <Link className="text-sm font-medium text-secondary hover:text-primary transition-colors" to="/">
              Home
            </Link>
            <Link className="text-sm font-medium text-secondary hover:text-primary transition-colors" to="/search">
              Shop
            </Link>
            <a className="text-sm font-medium text-secondary hover:text-primary transition-colors" href="#">
              Promotions
            </a>
            <a className="text-sm font-medium text-secondary hover:text-primary transition-colors" href="#">
              Blog
            </a>
            <a className="text-sm font-medium text-secondary hover:text-primary transition-colors" href="#">
              Support
            </a>
          </nav>
        </div>
        <div className="flex items-center gap-4">
          <Link to="/cart" className="p-2 hover:bg-surface-container-low rounded-full transition-all duration-200 relative text-on-surface-variant">
            <span className="material-symbols-outlined">shopping_cart</span>
            <span className="absolute top-1 right-1 w-4 h-4 bg-primary text-[10px] text-white flex items-center justify-center rounded-full font-bold">
              0
            </span>
          </Link>

          {isAuthenticated ? (
            <div 
              ref={dropdownRef}
              className="relative group flex items-center gap-2 cursor-pointer p-1.5 hover:bg-surface-container-low rounded-lg transition-all"
              onMouseEnter={() => setIsDropdownOpen(true)}
              onMouseLeave={() => setIsDropdownOpen(false)}
              onClick={() => setIsDropdownOpen(!isDropdownOpen)}
            >
              <span className="material-symbols-outlined text-on-surface-variant text-[24px]">account_circle</span>
              <span className="text-sm font-bold text-on-surface hidden md:inline truncate max-w-[120px]">
                {user?.fullName || user?.email || 'User'}
              </span>
              <span className={`material-symbols-outlined text-on-surface-variant text-[18px] transition-transform duration-200 ${isDropdownOpen ? 'rotate-180' : ''}`}>
                expand_more
              </span>

              {/* Dropdown Container with invisible bridge (pt-2) */}
              <div className={`absolute right-0 top-full pt-2 w-56 z-50 transition-all duration-200 origin-top-right ${isDropdownOpen ? 'opacity-100 scale-100 visible' : 'opacity-0 scale-95 invisible'}`}>
                <div className="bg-surface-container-lowest border border-outline-variant/50 rounded-2xl shadow-[0_12px_40px_rgba(0,0,0,0.12)] py-2 backdrop-blur-md overflow-hidden">
                  <div className="px-4 py-3 border-b border-outline-variant/30 bg-surface-container-lowest/50">
                    <span className="block font-bold text-on-surface text-sm mb-0.5 truncate">{user?.fullName || 'User'}</span>
                    <span className="block text-xs text-on-surface-variant truncate opacity-80">{user?.email}</span>
                  </div>
                  <Link 
                    to="/profile" 
                    className="flex items-center gap-3 px-4 py-2.5 text-sm text-on-surface hover:bg-surface-container-low transition-colors mt-1"
                    onClick={() => setIsDropdownOpen(false)}
                  >
                    <span className="material-symbols-outlined text-[18px] text-primary">person</span>
                    Profile
                  </Link>
                  <button
                    onClick={handleLogout}
                    className="w-full flex items-center gap-3 px-4 py-2.5 text-sm text-error hover:bg-error-container/20 transition-colors"
                  >
                    <span className="material-symbols-outlined text-[18px]">logout</span>
                    Logout
                  </button>
                </div>
              </div>
            </div>
          ) : (
            <Link to="/login" className="flex items-center gap-2 px-4 py-2 bg-primary text-white hover:bg-primary/90 rounded-full transition-all text-sm font-bold shadow-sm hover:shadow">
              <span className="material-symbols-outlined text-[18px]">login</span>
              <span>Login</span>
            </Link>
          )}
        </div>
      </div>
    </header>
  );
};

export default Header;
