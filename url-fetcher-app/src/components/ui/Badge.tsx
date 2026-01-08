import React from 'react';
import clsx from 'clsx';

export const Badge = ({ children, className }: { children: React.ReactNode; className?: string }) => (
  <span className={clsx('inline-flex items-center px-2 py-0.5 rounded-full text-xs font-medium', className)}>{children}</span>
);

