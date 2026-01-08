export type FetchStatus = 'PENDING' | 'SUCCESS' | 'FAILED';

export interface UrlStatus {
  url: string;
  status: FetchStatus;
}

export interface UrlDetail {
  url: string;
  content?: string;
}

export interface SubmitResponse {
  success: boolean;
  submitted: string[];
}

