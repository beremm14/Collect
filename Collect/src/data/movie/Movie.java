package data.movie;

import data.JsonObjAble;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Movie implements JsonObjAble, Comparable<Movie> {
    
    private final String title;
    private final String genre;
    private final String director;
    private final String country;
    private final int year;
    private final int length;
    private final MMediaType type;
    private final boolean series;

    public Movie(String name, String genre, String director, String country, int year, int length, MMediaType type, boolean series) {
        this.title = name;
        this.genre = genre;
        this.director = director;
        this.country = country;
        this.year = year;
        this.length = length;
        this.type = type;
        this.series = series;
    }
    
    public Movie(JsonObject input) {
        this.title = input.getString("Title");
        this.genre = input.getString("Genre");
        this.director = input.getString("Director");
        this.country = input.getString("Country");
        this.year = input.getInt("Year");
        this.length = input.getInt("Length");
        this.type = MMediaType.parseString(input.getString("Type"));
        this.series = input.getBoolean("Series");
    }

    public String getName() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
    }

    public MMediaType getType() {
        return type;
    }

    public boolean isSeries() {
        return series;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Title", title);
        b.add("Genre", genre);
        b.add("Director", director);
        b.add("Country", country);
        b.add("Year", year);
        b.add("Length", length);
        b.add("Type", type.toString());
        b.add("Series", series);
        
        return b.build();
    }

    @Override
    public int compareTo(Movie o) {
        return this.getName().compareTo(o.getName());
    }
    
}
