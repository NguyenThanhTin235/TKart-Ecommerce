import React from 'react';
import { Link, useLocation } from 'react-router-dom';

const BottomNavBar: React.FC = () => {
  const location = useLocation();

  const isActive = (path: string) => location.pathname === path;

  return (
    <nav className="fixed bottom-0 left-0 w-full z-50 flex justify-around items-center px-4 py-2 pb-safe bg-surface-container-lowest border-t border-outline-variant md:hidden">
      <Link to="/" className={`flex flex-col items-center justify-center ${isActive('/') ? 'text-primary font-bold' : 'text-secondary'}`}>
        <span className="material-symbols-outlined">home</span>
        <span className="font-label-md text-[10px]">Home</span>
      </Link>
      <Link to="/search" className={`flex flex-col items-center justify-center ${isActive('/search') ? 'text-primary font-bold' : 'text-secondary'}`}>
        <span className="material-symbols-outlined">storefront</span>
        <span className="font-label-md text-[10px]">Products</span>
      </Link>
      <Link to="/cart" className={`flex flex-col items-center justify-center ${isActive('/cart') ? 'bg-primary text-white font-bold' : 'bg-primary-container text-on-primary-container'} rounded-full px-4 py-1 transition-all`}>
        <span className="material-symbols-outlined">shopping_cart</span>
        <span className="font-label-md text-[10px]">Cart</span>
      </Link>
      <Link to="/profile" className={`flex flex-col items-center justify-center ${isActive('/profile') ? 'text-primary font-bold' : 'text-secondary'}`}>
        <span className="material-symbols-outlined">person</span>
        <span className="font-label-md text-[10px]">Account</span>
      </Link>
    </nav>
  );
};

export default BottomNavBar;
