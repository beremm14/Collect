package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;

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
    
    private Config() {}
    
    private boolean vinyl;
    private boolean movie;
    private boolean book;
    
    private String directory;
    
    private boolean dark;

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

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        JsonObjectBuilder ob = Json.createObjectBuilder();
        
        ob.add("Vinyl Active", vinyl);
        ob.add("Book Active", book);
        ob.add("Movie Active", movie);
        ob.add("Directory", directory);
        ob.add("Dark", dark);
        
        w.write(ob.build().toString());
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        JsonObject obj;
        try (JsonReader jsonReader = Json.createReader(fis)) {
            obj = jsonReader.readObject();
        }
        
        vinyl = obj.getBoolean("Vinyl Active");
        book = obj.getBoolean("Book Active");
        movie = obj.getBoolean("Movie Active");
        directory = obj.getString("Directory");
        dark = obj.getBoolean("Dark");
    }
    
}
