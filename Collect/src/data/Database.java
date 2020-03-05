package data;

import data.book.Book;
import data.movie.Movie;
import data.vinyl.LP;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
