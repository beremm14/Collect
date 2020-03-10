package data;

import data.book.Book;
import data.movie.Movie;
import data.vinyl.LP;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author emil
 */
public class Database implements JsonExport {
    
    private static Database instance;
    
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    private Database() {}
    
    private final ArrayList<LP> lps = new ArrayList<>();
    private final ArrayList<Movie> movies = new ArrayList<>();
    private final ArrayList<Book> books = new ArrayList<>();

    public ArrayList<LP> getLps() {
        return lps;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        Collections.sort(lps);
        Collections.sort(movies);
        Collections.sort(books);
        
        JsonArrayBuilder abl = Json.createArrayBuilder();
        for (LP lp : lps) {
            abl.add(lp.toJsonObject());
        }
        
        JsonArrayBuilder abm = Json.createArrayBuilder();
        for (Movie m : movies) {
            abm.add(m.toJsonObject());
        }
        
        JsonArrayBuilder abb = Json.createArrayBuilder();
        for (Book b : books) {
            abb.add(b.toJsonObject());
        }
        
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("Vinyls", abl.build());
        ob.add("Books", abb.build());
        ob.add("Movies", abm.build());
        
        w.write(ob.build().toString());
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        lps.clear();
        books.clear();
        movies.clear();
        
        JsonObject obj;
        try (JsonReader jsonReader = Json.createReader(fis)) {
            obj = jsonReader.readObject();
        }
        
        JsonArray jLps = obj.getJsonArray("Vinyls");
        for (JsonValue v : jLps) {
            lps.add(new LP(v.asJsonObject()));
        }
        
        JsonArray jBooks = obj.getJsonArray("Books");
        for (JsonValue v : jBooks) {
            books.add(new Book(v.asJsonObject()));
        }
        
        JsonArray jMovies = obj.getJsonArray("Movies");
        for (JsonValue v : jMovies) {
            movies.add(new Movie(v.asJsonObject()));
        }
        
        Collections.sort(lps);
        Collections.sort(movies);
        Collections.sort(books);
    }
    
}
