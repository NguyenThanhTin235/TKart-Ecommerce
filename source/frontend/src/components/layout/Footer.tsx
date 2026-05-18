import React from 'react';
import { Link } from 'react-router-dom';

const Footer: React.FC = () => {
  return (
    <footer className="bg-surface-container-low border-t border-outline-variant mt-stack-lg">
      <div className="w-full py-stack-lg px-margin-mobile md:px-margin-desktop mt-auto flex flex-col md:flex-row justify-between items-start max-w-container-max mx-auto gap-8">
        <div className="max-w-xs">
          <Link to="/" className="font-display text-headline-md text-secondary opacity-50 block mb-4">
            Tkart
          </Link>
          <p className="font-body-sm text-body-sm text-on-surface-variant">
            Elevating the multi-channel shopping experience with academic precision and soft aesthetics.
          </p>
        </div>
        <div className="grid grid-cols-2 md:grid-cols-3 gap-stack-lg flex-grow">
          <div>
            <h4 className="font-label-caps text-label-caps mb-4">Explore</h4>
            <ul className="space-y-2 font-body-sm text-body-sm text-on-surface-variant">
              <li>
                <a className="hover:text-primary transition-all" href="#">
                  New Arrivals
                </a>
              </li>
              <li>
                <a className="hover:text-primary transition-all" href="#">
                  Featured Designers
                </a>
              </li>
              <li>
                <a className="hover:text-primary transition-all" href="#">
                  Boutiques
                </a>
              </li>
            </ul>
          </div>
          <div>
            <h4 className="font-label-caps text-label-caps mb-4">Company</h4>
            <ul className="space-y-2 font-body-sm text-body-sm text-on-surface-variant">
              <li>
                <a className="hover:text-primary transition-all" href="#">
                  About Us
                </a>
              </li>
              <li>
                <a className="hover:text-primary transition-all" href="#">
                  Careers
                </a>
              </li>
              <li>
                <a className="hover:text-primary transition-all" href="#">
                  Terms of Service
                </a>
              </li>
            </ul>
          </div>
          <div className="col-span-2 md:col-span-1">
            <h4 className="font-label-caps text-label-caps mb-4">Connect</h4>
            <div className="flex gap-4">
              <a className="w-8 h-8 flex items-center justify-center rounded-full bg-surface-variant hover:bg-primary hover:text-white transition-all" href="#">
                <span className="material-symbols-outlined text-sm">language</span>
              </a>
              <a className="w-8 h-8 flex items-center justify-center rounded-full bg-surface-variant hover:bg-primary hover:text-white transition-all" href="#">
                <span className="material-symbols-outlined text-sm">alternate_email</span>
              </a>
              <a className="w-8 h-8 flex items-center justify-center rounded-full bg-surface-variant hover:bg-primary hover:text-white transition-all" href="#">
                <span className="material-symbols-outlined text-sm">share</span>
              </a>
            </div>
          </div>
        </div>
      </div>
      <div className="w-full px-margin-mobile md:px-margin-desktop py-6 border-t border-outline-variant max-w-container-max mx-auto flex flex-col md:flex-row justify-between items-center gap-4">
        <p className="font-body-sm text-body-sm text-on-surface-variant">
          © 2024 Tkart Marketplace. Academic Modernism Precision.
        </p>
        <p className="font-label-caps text-label-caps opacity-50 uppercase tracking-widest text-[10px]">
          Academic Modernism Framework v1.0
        </p>
      </div>
    </footer>
  );
};

export default Footer;
