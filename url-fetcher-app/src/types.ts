export type FetchStatus = 'PENDING' | 'SUCCESS' | 'FAIL';

export interface UrlStatus {
  url: string;
  status: FetchStatus;
  lastUpdated?: string; // ISO timestamp
  snippet?: string; // short preview of content or error
}

export interface UrlDetail {
  url: string;
  status: FetchStatus;
  content?: string;
  headers?: Record<string, string>;
}

export interface SubmitResponse {
  success: boolean;
  submitted: string[];
}

