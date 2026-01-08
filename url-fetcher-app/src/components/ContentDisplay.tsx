import React from 'react';
import type { UrlDetail } from '../types';
import { Card } from './ui/Card';

interface ContentDisplayProps {
  data: UrlDetail | null;
  isLoading?: boolean;
}

export default function ContentDisplay({ data, isLoading }: ContentDisplayProps) {
  if (isLoading) {
    return (
      <Card className="h-full flex items-center justify-center min-h-0">
        <div className="text-gray-600">Loading content...</div>
      </Card>
    );
  }

  if (!data) {
    return (
      <Card className="h-full min-h-0">
        <div className="text-gray-500">Select a URL to view fetched content</div>
      </Card>
    );
  }

  return (
    <Card className="h-full flex flex-col min-h-0">
      <div className="flex-shrink-0 flex justify-between items-start mb-4 gap-4">
        <div className="min-w-0">
          <h3 className="font-semibold break-all">{data.url}</h3>
          <div className="text-sm text-gray-500">Status: {data.status}</div>
        </div>

        <div className="flex items-center gap-2">
          {data.content && (
            <a className="text-sm text-blue-600 hover:underline ml-4" href={data.url} target="_blank" rel="noreferrer">Open raw</a>
          )}
        </div>
      </div>

      {/* Always-expanded scrollable content area (internal vertical & horizontal scroll only) */}
      <div className={`flex-1 overflow-auto border-t border-gray-100 pt-3 min-h-0`}>
        <pre className="m-0 whitespace-pre text-sm text-gray-800 min-w-max">
          {data.content ? data.content : <span className="text-gray-500">No content available for this URL.</span>}
        </pre>
      </div>
    </Card>
  );
}
