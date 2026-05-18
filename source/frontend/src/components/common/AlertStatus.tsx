import React from 'react';
import { motion, AnimatePresence } from 'framer-motion';

interface AlertStatusProps {
  type: 'success' | 'error' | 'warning' | 'info';
  message: string | null;
  onClose?: () => void;
}

const AlertStatus: React.FC<AlertStatusProps> = ({ type, message, onClose }) => {
  if (!message) return null;

  const config = {
    success: {
      bg: 'bg-emerald-50/90 dark:bg-emerald-950/40',
      border: 'border-emerald-500/30 dark:border-emerald-500/20',
      text: 'text-emerald-800 dark:text-emerald-200',
      icon: 'check_circle',
      iconColor: 'text-emerald-600 dark:text-emerald-400',
      accent: 'bg-emerald-500',
    },
    error: {
      bg: 'bg-rose-50/90 dark:bg-rose-950/40',
      border: 'border-rose-500/30 dark:border-rose-500/20',
      text: 'text-rose-800 dark:text-rose-200',
      icon: 'error',
      iconColor: 'text-rose-600 dark:text-rose-400',
      accent: 'bg-rose-500',
    },
    warning: {
      bg: 'bg-amber-50/90 dark:bg-amber-950/40',
      border: 'border-amber-500/30 dark:border-amber-500/20',
      text: 'text-amber-800 dark:text-amber-200',
      icon: 'warning',
      iconColor: 'text-amber-600 dark:text-amber-400',
      accent: 'bg-amber-500',
    },
    info: {
      bg: 'bg-blue-50/90 dark:bg-blue-950/40',
      border: 'border-blue-500/30 dark:border-blue-500/20',
      text: 'text-blue-800 dark:text-blue-200',
      icon: 'info',
      iconColor: 'text-blue-600 dark:text-blue-400',
      accent: 'bg-blue-500',
    },
  }[type];

  return (
    <AnimatePresence>
      <motion.div
        initial={{ opacity: 0, y: -16, scale: 0.98 }}
        animate={{ opacity: 1, y: 0, scale: 1 }}
        exit={{ opacity: 0, y: -16, scale: 0.98 }}
        transition={{ duration: 0.3, ease: [0.16, 1, 0.3, 1] }}
        className={`relative mb-6 overflow-hidden rounded-xl border ${config.border} ${config.bg} backdrop-blur-md shadow-[0_8px_30px_rgb(0,0,0,0.08)] transition-all`}
      >
        {/* Left Accent Bar */}
        <div className={`absolute left-0 top-0 bottom-0 w-1.5 ${config.accent}`} />

        <div className="flex items-start gap-3.5 p-4 pl-5">
          {/* Animated Icon */}
          <motion.div
            initial={{ scale: 0.8 }}
            animate={{ scale: [0.8, 1.2, 1] }}
            transition={{ duration: 0.4, delay: 0.1 }}
            className="flex-shrink-0 mt-0.5"
          >
            <span className={`material-symbols-outlined text-[22px] font-bold ${config.iconColor}`}>
              {config.icon}
            </span>
          </motion.div>

          {/* Message Content */}
          <div className="flex-1 pr-2">
            <p className={`font-body-md text-sm font-medium leading-relaxed ${config.text}`}>
              {message}
            </p>
          </div>

          {/* Optional Close Button */}
          {onClose && (
            <button
              onClick={onClose}
              type="button"
              className={`flex-shrink-0 p-1 rounded-lg transition-colors hover:bg-black/5 dark:hover:bg-white/10 ${config.text}`}
              aria-label="Close alert"
            >
              <span className="material-symbols-outlined text-[18px]">close</span>
            </button>
          )}
        </div>
      </motion.div>
    </AnimatePresence>
  );
};

export default AlertStatus;
