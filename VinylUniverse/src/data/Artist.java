package data;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Artist implements JsonObjAble {
    
    private final String name;
    private final String country;
    private final String genre;
    private boolean active;

    public Artist(String name, String country, String genre, boolean active) {
        this.name = name;
        this.country = country;
        this.genre = genre;
        this.active = active;
    }
    
    public Artist(JsonObject input) {
        this.name = input.getString("Name");
        this.country = input.getString("Country");
        this.genre = input.getString("Genre");
        this.active = input.getBoolean("Active");
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Name", name);
        b.add("Country", country);
        b.add("Genre", genre);
        b.add("Active", active);
        
        return b.build();
    }

}
