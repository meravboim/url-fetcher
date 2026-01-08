import React from 'react';
import clsx from 'clsx';

export const Card = ({ className, children }: { className?: string; children: React.ReactNode }) => {
  return (
    <div className={clsx('bg-white shadow-sm rounded-md p-4', className)}>{children}</div>
  );
};

