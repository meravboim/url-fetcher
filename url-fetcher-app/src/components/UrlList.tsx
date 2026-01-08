import React from 'react';
import type { UrlStatus } from '../types';
import { Card } from './ui/Card';
import { Badge } from './ui/Badge';

interface UrlListProps {
  items: UrlStatus[];
  selectedUrl?: string | null;
  onSelect: (url: string) => void;
}

export default function UrlList({ items, onSelect, selectedUrl }: UrlListProps) {
  return (
    <Card className="h-full overflow-auto">
      <h2 className="font-semibold mb-3">Tracked URLs</h2>
      <ul className="space-y-2">
        {items.length === 0 && <li className="text-sm text-gray-500">No URLs submitted yet</li>}
        {items.map(item => (
          <li
            key={item.url}
            onClick={() => onSelect(item.url)}
            className={`p-2 rounded cursor-pointer flex justify-between items-start hover:bg-gray-50 ${selectedUrl === item.url ? 'bg-blue-50' : ''}`}
          >
            <div className="flex-1">
              <div className="text-sm font-medium text-gray-900 break-all">{item.url}</div>
              <div className="text-xs text-gray-500">{item.snippet || item.lastUpdated || ''}</div>
            </div>
            <div className="ml-3">
              <Badge className={`${item.status === 'PENDING' ? 'bg-yellow-100 text-yellow-800' : item.status === 'SUCCESS' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}`}>
                {item.status}
              </Badge>
            </div>
          </li>
        ))}
      </ul>
    </Card>
  );
}
