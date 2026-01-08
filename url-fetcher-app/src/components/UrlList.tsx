import { useEffect, useState } from 'react';
import type { FetchStatus, UrlStatus } from '../types';
import { Card } from './ui/Card';
import { Badge } from './ui/Badge';
import { urlService } from '../services/urlService';
import { POLL_INTERVAL } from '../config/constants';

interface UrlListProps {
  selectedUrl?: string | null;
  onSelect: (url: string) => void;
}

const urlStatusesStyle: Record<FetchStatus, string> = {
  PENDING: 'bg-yellow-100 text-yellow-800',
  SUCCESS: 'bg-green-100 text-green-800',
  FAILED: 'bg-red-100 text-red-800',
};

function UrlList({ onSelect, selectedUrl }: UrlListProps) {
  const [urlStatuses, setUrlStatuses] = useState<UrlStatus[]>([]);

  useEffect(() => {
    const refresh = async () => {
      try {
        const data = await urlService.fetchAllStatuses();
        setUrlStatuses(data);
      } catch (err) {
        console.error('Failed to fetch statuses', err);
      }
    }

    refresh();
    const timerId = setInterval(refresh, POLL_INTERVAL);

    return () => { clearInterval(timerId); };
  }, []);

  return (
    <Card className='h-full overflow-auto'>
      <h2 className='font-semibold mb-3'>Tracked URLs</h2>
      <ul className='space-y-2'>
        {urlStatuses.length === 0 && (<li className='text-sm text-gray-500'>No URLs submitted yet</li>)}
        {urlStatuses.map((item) => (
          <li
            key={item.url}
            onClick={() => onSelect(item.url)}
            className={`p-2 rounded cursor-pointer flex justify-between items-start hover:bg-gray-50 ${selectedUrl === item.url ? 'bg-blue-50' : ''
              }`}
          >
            <div className='flex-1 text-sm font-medium text-gray-900 break-all'>
              {item.url}
            </div>
            <div className='ml-3'>
              <Badge className={urlStatusesStyle[item.status]}>{item.status}</Badge>
            </div>
          </li>
        ))}
      </ul>
    </Card>
  );
}

export default UrlList;
