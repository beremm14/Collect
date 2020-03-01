package data;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 *
 * @author emil
 */
public class LP implements JsonObjAble {
    
    private final String name;
    private final String genre;
    private final Artist interpret;
    private final Artist composer;
    private final int year;
    private final String label;
    private final List<Song> songs = new ArrayList<>();

    public LP(String name, String genre, Artist interpret, Artist composer, int year, String label) {
        this.name = name;
        this.genre = genre;
        this.interpret = interpret;
        this.composer = composer;
        this.year = year;
        this.label = label;
    }
    
    public LP(JsonObject input) {
        this.name = input.getString("Name");
        this.genre = input.getString("Genre");
        this.interpret = new Artist(input.getJsonObject("Interpret"));
        this.composer = new Artist(input.getJsonObject("Composer"));
        this.year = input.getInt("Year");
        this.label = input.getString("Label");
        
        JsonArray array = input.getJsonArray("Songs");
        for (JsonValue v : array) {
            songs.add(new Song(v.asJsonObject()));
        }
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

    public String getLabel() {
        return label;
    }

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Name", name);
        b.add("Genre", genre);
        b.add("Interpret", interpret.toJsonObject());
        b.add("Composer", composer.toJsonObject());
        b.add("Year", year);
        b.add("Label", label);
        
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Song s : songs) {
            ab.add(s.toJsonObject());
        }
        
        b.add("Songs", ab.build());
        
        return b.build();
    }


    
}
