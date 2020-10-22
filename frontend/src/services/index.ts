/**
 * Desafio MyMusic 
 * Author: Felipe 
 */

import axios, { AxiosRequestConfig } from 'axios';
import 'axios-response-logger';
import Music from '../models/Music';
import Playlist from '../models/Playlist';

/**
 * Axios configuration.
 */
export const config: AxiosRequestConfig = {
	baseURL: process.env.REACT_APP_API_URL_MUSIC_SERVICE,
	responseType: 'json',
	headers: { accept: 'application/json', 'content-type': 'application/json'},
	timeout: 40000,
	withCredentials: false,
	maxRedirects: 5,
	validateStatus: (status: number) => status >= 200 && status <= 500,
	transformResponse: (response) => {				
		if(response && response.error){
			console.log(response.error);
		}
		return response;
	},
};

export const config2: AxiosRequestConfig = {
	baseURL: process.env.REACT_APP_API_URL_PLAYLIST_SERVICE,
	responseType: 'json',
	headers: { accept: 'application/json', 'content-type': 'application/json'},
	timeout: 40000,
	withCredentials: false,
	maxRedirects: 5,
	validateStatus: (status: number) => status >= 200 && status <= 500,
	transformResponse: (response) => {				
		if(response && response.error){
			console.log(response.error);
		}
		return response;
	},
};

/**
 * Select musics by filter
 * @param nome
 * @returns instance {Promise<AxiosResponse<Music[]>>}
 * @link https://github.com/axios/axios
 */
export const selectMusicsByFilter = async (nome: string) => {	
	config.url = `/musicas?filter=${nome}`;
	config.method = 'get';
	return axios.request<Music[]>(config);
};

/**
 * Select musics by user
 * @param nome
 * @returns instance {Promise<AxiosResponse<Playlist[]>>}
 * @link https://github.com/axios/axios
 */
export const selectPlaylistByUser = async (nome: string) => {	
	config2.url = `/playlists?user=${nome}`;
	config2.method = 'get';
	return axios.request<Playlist>(config2);
};

/**
 * Add musics in user playlist
 * @param playlistId
 * @returns instance {Promise<AxiosResponse<Playlist[]>>}
 * @link https://github.com/axios/axios
 */
export const addMusics = async (playlistId: string, musics: Music[]) => {	
	config2.url = `/playlists/${playlistId}/musicas`;
	config2.method = 'put';
	config2.data = musics;
	return axios.request<Playlist>(config2);
};

/**
 * Remove musics in user playlist
 * @param playlistId
 * @returns instance {Promise<AxiosResponse<Playlist[]>>}
 * @link https://github.com/axios/axios
 */
export const removeMusic = async (playlistId: string, musicId: string) => {	
	config2.url = `/playlists/${playlistId}/musicas/${musicId}`;
	config2.method = 'delete';	
	return axios.request<Playlist>(config2);
};



