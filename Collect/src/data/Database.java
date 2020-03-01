package data;

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
    
    private final List<LP> lps = new ArrayList<>();
    private final List<Movie> movies = new ArrayList<>();

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
