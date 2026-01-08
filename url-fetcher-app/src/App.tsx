import UrlForm from './components/UrlForm';
import UrlResultsViewer from './components/UrlResultsViewer';
import { Toaster } from 'react-hot-toast';

function App() {
  return (
    <div className="h-screen w-screen overflow-hidden bg-gray-100 flex flex-col">
      <header className="px-6 py-5 text-center flex-shrink-0">
        <h1 className="text-3xl font-extrabold text-gray-800">URL Fetcher</h1>
        <p className="text-gray-500">Submit URLs and track their fetch status in real-time</p>
      </header>

      <div className="px-6 flex-shrink-0 mb-4">
        <UrlForm />
      </div>

      <div className="px-6 pb-6 flex-grow overflow-hidden">
        <UrlResultsViewer />
      </div>

      <Toaster position="top-right" />
    </div>
  );
}

export default App;
