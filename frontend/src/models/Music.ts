/**
 * Music model
 */

import Artist from "./Artist";

export default interface Music {
	id: string;
	nome: string;
	artista: Artist;	
}
