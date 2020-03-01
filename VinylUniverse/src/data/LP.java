package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emil
 */
public class LP {
    
    private final String name;
    private final String genre;
    private final Artist interpret;
    private final Artist composer;
    private final int year;
    private final List<Song> songs = new ArrayList<>();

    public LP(String name, String genre, Artist interpret, Artist composer, int year) {
        this.name = name;
        this.genre = genre;
        this.interpret = interpret;
        this.composer = composer;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Artist getInterpret() {
        return interpret;
    }

    public Artist getComposer() {
        return composer;
    }

    public int getYear() {
        return year;
    }

    public List<Song> getSongs() {
        return songs;
    }
    
    

}
