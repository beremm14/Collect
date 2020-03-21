package data.vinyl;

/**
 *
 * @author emil
 */
public class SongLpPair implements Comparable<SongLpPair> {
    
    private final Song song;
    private final LP lp;

    public SongLpPair(Song song, LP lp) {
        this.song = song;
        this.lp = lp;
    }

    public Song getSong() {
        return song;
    }

    public LP getLp() {
        return lp;
    }

    @Override
    public int compareTo(SongLpPair o) {
        return this.getSong().getName().compareTo(o.getSong().getName());
    }
    
}
