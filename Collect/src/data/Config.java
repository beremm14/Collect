package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author emil
 */
public class Config implements JsonExport {
    
    private static Config instance;
    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    private boolean vinyl;
    private boolean movie;
    private boolean book;
    
    private String directory;

    public boolean isVinyl() {
        return vinyl;
    }

    public void setVinyl(boolean vinyl) {
        this.vinyl = vinyl;
    }

    public boolean isMovie() {
        return movie;
    }

    public void setMovie(boolean movie) {
        this.movie = movie;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
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
