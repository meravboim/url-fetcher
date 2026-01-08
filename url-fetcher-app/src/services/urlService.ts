import axios from 'axios';
import type { UrlStatus, UrlDetail, SubmitResponse } from '../types';
import { SERVER_URL } from '../config/constants';

export const URL_ENDPOINT = `${SERVER_URL}/urls`

export const urlService = {
  submitUrls: async (urls: string[]): Promise<SubmitResponse> => {
    const response = await axios.post(URL_ENDPOINT, { urls });
    return response.data as SubmitResponse;
  },

  fetchAllStatuses: async (): Promise<UrlStatus[]> => {
    const response = await axios.get(`${URL_ENDPOINT}`);
    return response.data as UrlStatus[];
  },

  fetchUrlDetail: async (url: string): Promise<UrlDetail> => {
    const response = await axios.get(`${URL_ENDPOINT}/content`, {
      params: { url }
    });
    return response.data as UrlDetail;
  }
};