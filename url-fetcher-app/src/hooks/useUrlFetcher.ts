import { useEffect, useRef, useState, useCallback } from 'react';
import { urlService } from '../services/urlService';
import type { UrlStatus, UrlDetail } from '../types';

export function useUrlFetcher(pollInterval = 3000) {
  const [statuses, setStatuses] = useState<UrlStatus[]>([]);
  const [selectedDetail, setSelectedDetail] = useState<UrlDetail | null>(null);
  const [isLoadingDetail, setIsLoadingDetail] = useState(false);
  const mounted = useRef(true);
  const timer = useRef<number | null>(null);

  const refresh = useCallback(async () => {
    try {
      const data = await urlService.fetchAllStatuses();
      if (mounted.current) setStatuses(data);
    } catch (err) {
      console.error('Failed to fetch statuses', err);
    }
  }, []);

  useEffect(() => {
    mounted.current = true;
    refresh();
    timer.current = window.setInterval(refresh, pollInterval);
    return () => {
      mounted.current = false;
      if (timer.current) window.clearInterval(timer.current);
    };
  }, [refresh, pollInterval]);

  const submit = useCallback(async (urls: string[]) => {
    try {
      await urlService.submitUrls(urls);
      await refresh();
    } catch (err) {
      console.error('Submit failed', err);
      throw err;
    }
  }, [refresh]);

  const select = useCallback(async (url: string) => {
    setIsLoadingDetail(true);
    try {
      const detail = await urlService.fetchUrlDetail(url);
      setSelectedDetail(detail);
    } catch (err) {
      console.error('Failed to fetch detail', err);
    } finally {
      setIsLoadingDetail(false);
    }
  }, []);

  return {
    statuses,
    selectedDetail,
    isLoadingDetail,
    submit,
    select,
    refresh
  } as const;
}

