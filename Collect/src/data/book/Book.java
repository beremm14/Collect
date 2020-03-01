package data.book;

import data.JsonObjAble;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Book implements JsonObjAble {
    
    private final String title;
    private final String author;
    private final String genre;
    private final BMediaType type;
    private final int year;

    public Book(String title, String author, String genre, BMediaType type, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.type = type;
        this.year = year;
    }
    
    public Book(JsonObject input) {
        this.title = input.getString("Title");
        this.author = input.getString("Author");
        this.genre = input.getString("Genre");
        this.type = BMediaType.parseString(input.getString("Type"));
        this.year = input.getInt("Year");
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public BMediaType getType() {
        return type;
    }

    public int getYear() {
        return year;
    }
    
    

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Title", title);
        b.add("Author", author);
        b.add("Genre", genre);
        b.add("Type", type.toString());
        b.add("Year", year);
        
        return b.build();
    }

}
