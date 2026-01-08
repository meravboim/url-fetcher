import UrlList from './UrlList';
import { useState } from 'react';
import { urlService } from '../services/urlService';
import type { UrlDetail } from '../types';
import UrlContentDetails from './UrlContentDetails';


function UrlResult() {
    const [selectedDetail, setSelectedDetail] = useState<UrlDetail | null>(null);
    const [isLoadingDetail, setIsLoadingDetail] = useState(false);

    const select = async (url: string) => {
        setIsLoadingDetail(true);
        try {
            const detail = await urlService.fetchUrlDetail(url);
            setSelectedDetail(detail);
        } catch (err) {
            console.error('Failed to fetch detail', err);
        } finally {
            setIsLoadingDetail(false);
        }
    };


    return (
        <main className="flex-1 px-6 h-full grid grid-cols-1 lg:grid-cols-3 gap-6">
            <div className="lg:col-span-1 h-full overflow-auto min-h-0">
                <UrlList onSelect={select} selectedUrl={selectedDetail?.url} />
            </div>
            <div className="h-full overflow-auto min-h-0 lg:col-span-2">
                <UrlContentDetails data={selectedDetail} isLoading={isLoadingDetail} />
            </div>
        </main>
    );
}

export default UrlResult;