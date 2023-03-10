//constructor und alles für Song, theoretisch bräuchte man Song.java nicht
export default class Song {
    constructor(title, author, length, version) {
        this.title = title;
        this.author = author;
        //this.genre = genre;
        this.length = length;
        this.version = version;
    }
}