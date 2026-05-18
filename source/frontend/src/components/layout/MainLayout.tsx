import React from 'react';
import Header from './Header';
import Footer from './Footer';
import BottomNavBar from './BottomNavBar';

interface MainLayoutProps {
  children: React.ReactNode;
}

const MainLayout: React.FC<MainLayoutProps> = ({ children }) => {
  return (
    <div className="flex flex-col min-h-screen bg-background text-on-surface font-body-lg selection:bg-primary-fixed selection:text-on-primary-fixed">
      <Header />
      <main className="flex-grow flex flex-col pb-16 md:pb-0">
        {children}
      </main>
      <Footer />
      <BottomNavBar />
    </div>
  );
};

export default MainLayout;
