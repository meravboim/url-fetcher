import React, { useState } from 'react';
import { Card } from './ui/Card';
import { Button } from './ui/Button';

interface UrlFormProps {
  onSubmit: (urls: string[]) => void | Promise<void>;
}

export default function UrlForm({ onSubmit }: UrlFormProps) {
  const [input, setInput] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  const parseInput = (raw: string) => {
    return raw
      .split(/[,\n\r]+/) // split by comma or newlines
      .map(s => s.trim())
      .filter(Boolean);
  };

  const handleSubmit = async (e?: React.FormEvent) => {
    e?.preventDefault();
    const urls = parseInput(input);
    if (urls.length === 0) return;
    try {
      setIsSubmitting(true);
      await onSubmit(urls);
      setInput('');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <Card className="mb-6">
      <form onSubmit={handleSubmit} className="space-y-3">
        <label className="block text-sm font-medium text-gray-700">Enter one or more URLs (comma or newline separated)</label>
        <div className="flex gap-2">
          <textarea
            rows={3}
            className="flex-1 border border-gray-300 p-2 rounded resize-none"
            placeholder="https://example.com, https://another.com"
            value={input}
            onChange={(e) => setInput(e.target.value)}
          />
          <Button type="submit" disabled={isSubmitting || input.trim() === ''}>
            {isSubmitting ? 'Submitting...' : 'Fetch'}
          </Button>
        </div>
      </form>
    </Card>
  );
}