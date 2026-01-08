import axios from 'axios';
import type { UrlStatus, UrlDetail, SubmitResponse } from '../types';

const API_BASE_URL = "http://localhost:8080/urls";

export const urlService = {
  submitUrls: async (urls: string[]): Promise<SubmitResponse> => {
    const response = await axios.post(API_BASE_URL, { urls });
    return response.data as SubmitResponse;
  },

  fetchAllStatuses: async (): Promise<UrlStatus[]> => {
    const response = await axios.get(`${API_BASE_URL}`);
    return response.data as UrlStatus[];
  },

  fetchUrlDetail: async (url: string): Promise<UrlDetail> => {
    const response = await axios.get(`${API_BASE_URL}/content`, {
      params: { url }
    });
    return response.data as UrlDetail;
  }
};