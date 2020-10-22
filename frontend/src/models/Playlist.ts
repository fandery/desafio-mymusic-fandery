
/**
 * Playlist model
 */

import User from "./User";
import Music from "./Music";

export default interface Playlist {
	id: string;
	user: User[];
	musics: Music[];
}
