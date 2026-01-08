import type { ReactNode } from 'react';
import clsx from 'clsx';

interface CardProps {
  className?: string;
  children: ReactNode;
}

export const Card = ({ className, children }: CardProps) => (
  <div className={clsx('bg-white shadow-sm rounded-md p-4', className)}>{children}</div>
);