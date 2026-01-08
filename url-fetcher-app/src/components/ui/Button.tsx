import { type ComponentProps } from 'react';
import clsx from 'clsx';

interface ButtonProps extends ComponentProps<'button'> {
  variant?: 'default' | 'ghost' | 'outline';
}

export const Button = ({ className, variant = 'default', ...props }: ButtonProps) => {
  const base = 'inline-flex items-center justify-center rounded-md text-sm font-medium transition-colors focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-60';
  const variants: Record<string, string> = {
    default: 'bg-blue-600 text-white hover:bg-blue-700 focus:ring-blue-500 px-4 py-2',
    ghost: 'bg-transparent text-gray-700 hover:bg-gray-100 px-3 py-1',
    outline: 'border border-gray-200 bg-white text-gray-700 px-4 py-2 hover:bg-gray-50',
  };

  return <button className={clsx(base, variants[variant], className)} {...props} />;
};
