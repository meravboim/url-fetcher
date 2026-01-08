import React from 'react';
import UrlForm from './components/UrlForm';
import UrlList from './components/UrlList';
import ContentDisplay from './components/ContentDisplay';
import { useUrlFetcher } from './hooks/useUrlFetcher';

function App() {
  const { statuses, selectedDetail, isLoadingDetail, submit, select } = useUrlFetcher(3000);

  return (
    <div className="h-screen w-screen overflow-hidden bg-gray-100">
      <div className="h-full w-full flex flex-col">
        <header className="px-6 py-5 text-center flex-shrink-0">
          <h1 className="text-3xl font-extrabold text-gray-800">URL Fetcher</h1>
          <p className="text-gray-500">Submit URLs and track their fetch status in real-time</p>
        </header>

        <div className="px-6 flex-shrink-0">
          <UrlForm onSubmit={submit} />
        </div>

        <main className="flex-1 px-6">
          <div className="h-full grid grid-cols-1 lg:grid-cols-3 gap-6">
            <div className="lg:col-span-1 h-full overflow-auto min-h-0">
              <UrlList
                items={statuses}
                onSelect={select}
                selectedUrl={selectedDetail?.url}
              />
            </div>

            <div className="lg:col-span-2 h-full min-h-0">
              <div className="h-full overflow-auto min-h-0">
                <ContentDisplay data={selectedDetail} isLoading={isLoadingDetail} />
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}

export default App;
