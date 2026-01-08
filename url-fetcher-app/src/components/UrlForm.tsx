import { useState, type FormEvent } from 'react';
import { Card } from './ui/Card';
import { Button } from './ui/Button';
import { urlService } from '../services/urlService';
import toast from 'react-hot-toast';

function UrlForm() {
  const [input, setInput] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  const parseInput = (raw: string) => raw.split(/[,\n\r]+/).map((s) => s.trim()).filter(Boolean);

  const handleSubmit = async (e: FormEvent) => {
    e.preventDefault();

    const urls = parseInput(input);

    try {
      setIsSubmitting(true);
      await urlService.submitUrls(urls);
      setInput('');
    } catch (err) {
      console.error('Submit failed', err);
      toast.error('Failed to submit URLs. Please try again.');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <Card className='mb-6'>
      <form onSubmit={handleSubmit} className='space-y-3'>
        <label className='block text-sm font-medium text-gray-700'>
          Enter one or more URLs (comma or newline separated)
        </label>
        <div className='flex gap-2'>
          <textarea
            rows={3}
            className='flex-1 border border-gray-300 p-2 rounded resize-none'
            placeholder='https://example.com, https://another.com'
            value={input}
            onChange={(e) => setInput(e.target.value)}
          />
          <Button type='submit' disabled={isSubmitting || input.trim() === ''}>
            {isSubmitting ? 'Submitting...' : 'Fetch'}
          </Button>
        </div>
      </form>
    </Card>
  );
}

export default UrlForm;