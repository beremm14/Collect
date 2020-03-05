package data;

import data.book.Book;
import data.movie.Movie;
import data.vinyl.LP;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author emil
 */
public class Stats implements JsonObjAble {
    
    private static Stats instance;
    
    public static Stats getInstance() {
        if (instance == null) {
            instance = new Stats();
        }
        return instance;
    }
    
    public Stats createInstance(JsonObject input) {
        if (instance == null) {
            instance = new Stats(input);
        }
        return instance;
    }
    
    private Stats() {}
    
    private Stats(JsonObject input) {
        clearEverything();
        
        this.vinylAverageSongCount = input.getInt("Vinyl Average Song Count");
        this.vinylAverageYear = input.getInt("Vinyl Average Year");
        this.vinylPercentageActive = input.getJsonNumber("Vinyl Percentage Active").doubleValue();
        
        this.bookAverageYear = input.getInt("Book Average Year");
        
        this.movieAverageYear = input.getInt("Movie Average Year");
        this.moviePercentageSeries = input.getJsonNumber("Movie Percentage Series").doubleValue();
        
        fillHashMaps(input.getJsonArray("Vinyl Genres"), vinylGenres);
        fillHashMaps(input.getJsonArray("Vinyl Albums Of Interpret"), vinylAlbumsOfInterpret);
        fillHashMaps(input.getJsonArray("Vinyl Countries"), vinylCountries);
        fillHashMaps(input.getJsonArray("Vinyl Labels"), vinylLabels);
        
        fillHashMaps(input.getJsonArray("Book Authors"), bookAuthors);
        fillHashMaps(input.getJsonArray("Book Genres"), bookGenres);
        
        fillHashMaps(input.getJsonArray("Movie Genres"), movieGenres);
        fillHashMaps(input.getJsonArray("Movie Directors"), movieDirectors);
        fillHashMaps(input.getJsonArray("Movie Countries"), movieCountries);
        
        updateDatasets();
    }
    
    private final HashMap<String, Integer> vinylGenres = new HashMap<>();
    private final HashMap<String, Integer> vinylAlbumsOfInterpret = new HashMap<>();
    private final HashMap<String, Integer> vinylCountries = new HashMap<>();
    private final HashMap<String, Integer> vinylLabels = new HashMap<>();
    
    private final DefaultPieDataset vinylGenresSet = new DefaultPieDataset();
    private final DefaultPieDataset vinylAlbumsOfInterpretSet = new DefaultPieDataset();
    private final DefaultPieDataset vinylCountriesSet = new DefaultPieDataset();
    private final DefaultPieDataset vinylLabelsSet = new DefaultPieDataset();
    
    private int vinylAverageYear;
    private int vinylAverageSongCount;
    private double vinylPercentageActive;
    
    private final HashMap<String, Integer> bookAuthors = new HashMap<>();
    private final HashMap<String, Integer> bookGenres = new HashMap<>();
    
    private final DefaultPieDataset bookAuthorsSet = new DefaultPieDataset();
    private final DefaultPieDataset bookGenresSet = new DefaultPieDataset();
    
    private int bookAverageYear;
    
    private final HashMap<String, Integer> movieGenres = new HashMap<>();
    private final HashMap<String, Integer> movieDirectors = new HashMap<>();
    private final HashMap<String, Integer> movieCountries = new HashMap<>();
    
    private final DefaultPieDataset movieGenresSet = new DefaultPieDataset();
    private final DefaultPieDataset movieDirectorsSet = new DefaultPieDataset();
    private final DefaultPieDataset movieCountriesSet = new DefaultPieDataset();
    
    private int movieAverageYear;
    private double moviePercentageSeries;
    
    private void convertToDataset(HashMap<String, Integer> input, DefaultPieDataset output) {
        for (Map.Entry<String,Integer> entry : input.entrySet()) {
            output.setValue(entry.getKey(), entry.getValue());
        }
    }
    
    private void updateVinyls() {
        int avYear = 0;
        int avSongCount = 0;
        double percActive = 0;
        for (LP lp : Database.getInstance().getLps()) {
            if(!vinylGenres.containsKey(lp.getGenre()))
                vinylGenres.put(lp.getGenre(), 1);
            else
                vinylGenres.replace(lp.getGenre(), vinylGenres.get(lp.getGenre()) + 1);
            
            if(!vinylAlbumsOfInterpret.containsKey(lp.getInterpret().getName()))
                vinylAlbumsOfInterpret.put(lp.getInterpret().getName(), 1);
            else
                vinylAlbumsOfInterpret.remove(lp.getInterpret().getName(), vinylAlbumsOfInterpret.get(lp.getInterpret().getName()) + 1);
            
            if(!vinylCountries.containsKey(lp.getInterpret().getCountry()))
                vinylCountries.put(lp.getInterpret().getCountry(), 1);
            else
                vinylCountries.replace(lp.getInterpret().getCountry(), vinylCountries.get(lp.getInterpret().getCountry()) + 1);
            
            if(!vinylLabels.containsKey(lp.getLabel()))
                vinylLabels.put(lp.getLabel(), 1);
            else
                vinylLabels.replace(lp.getLabel(), vinylLabels.get(lp.getLabel()) + 1);
            
            if(lp.getInterpret().isActive())
                percActive++;
            
            avYear += lp.getYear();
            avSongCount += lp.getSongs().size();
        }
        vinylPercentageActive = 100. * percActive / (double)Database.getInstance().getLps().size();
        vinylAverageYear = avYear / Database.getInstance().getLps().size();
        vinylAverageSongCount = avSongCount / Database.getInstance().getLps().size();
    }
    
    private void updateBooks() {
        int avYear = 0;
        for (Book b : Database.getInstance().getBooks()) {
            if(!bookAuthors.containsKey(b.getAuthor()))
                bookAuthors.put(b.getAuthor(), 1);
            else
                bookAuthors.replace(b.getAuthor(), bookAuthors.get(b.getAuthor()) + 1);
            
            if(!bookGenres.containsKey(b.getGenre()))
                bookGenres.put(b.getGenre(), 1);
            else
                bookGenres.replace(b.getGenre(), bookGenres.get(b.getGenre()) + 1);
            
            avYear += b.getYear();
        }
        bookAverageYear = avYear / Database.getInstance().getBooks().size();
    }
    
    private void updateMovies() {
        int avYear = 0;
        double percSeries = 0;
        for (Movie m : Database.getInstance().getMovies()) {
            if(!movieGenres.containsKey(m.getGenre()))
                movieGenres.put(m.getGenre(), 1);
            else
                movieGenres.replace(m.getGenre(), movieGenres.get(m.getGenre()) + 1);
            
            if(!movieDirectors.containsKey(m.getDirector()))
                movieDirectors.put(m.getDirector(), 1);
            else
                movieDirectors.replace(m.getDirector(), movieDirectors.get(m.getDirector()) + 1);
            
            if(!movieCountries.containsKey(m.getCountry())) 
                movieCountries.put(m.getCountry(), 1);
            else
                movieCountries.replace(m.getCountry(), movieCountries.get(m.getCountry()) + 1);
            
            avYear += m.getYear();
            
            if(m.isSeries())
                percSeries++;
        }
        
        movieAverageYear = avYear / Database.getInstance().getMovies().size();
        moviePercentageSeries = 100. * percSeries / (double)Database.getInstance().getMovies().size();
    }
    
    public void clearEverything() {
        vinylAlbumsOfInterpret.clear();
        vinylAlbumsOfInterpretSet.clear();
        vinylCountries.clear();
        vinylCountriesSet.clear();
        vinylGenres.clear();
        vinylGenresSet.clear();
        vinylLabels.clear();
        vinylLabelsSet.clear();
        
        bookAuthors.clear();
        bookAuthorsSet.clear();
        bookGenres.clear();
        bookGenresSet.clear();
        
        movieCountries.clear();
        movieCountriesSet.clear();
        movieDirectors.clear();
        movieDirectorsSet.clear();
        movieGenres.clear();
        movieGenresSet.clear();
    }
    
    private void updateDatasets() {
        convertToDataset(vinylGenres, vinylGenresSet);
        convertToDataset(vinylAlbumsOfInterpret, vinylAlbumsOfInterpretSet);
        convertToDataset(vinylCountries, vinylCountriesSet);
        convertToDataset(vinylLabels, vinylLabelsSet);
        
        convertToDataset(bookAuthors, bookAuthorsSet);
        convertToDataset(bookGenres, bookGenresSet);
        
        convertToDataset(movieCountries, movieCountriesSet);
        convertToDataset(movieDirectors, movieDirectorsSet);
        convertToDataset(movieGenres, movieGenresSet);
    }
    
    public void updateInstance() {
        clearEverything();
        
        updateVinyls();
        updateBooks();
        updateMovies();
        
        updateDatasets();
    }

    public HashMap<String, Integer> getVinylGenres() {
        return vinylGenres;
    }

    public HashMap<String, Integer> getVinylAlbumsOfInterpret() {
        return vinylAlbumsOfInterpret;
    }

    public HashMap<String, Integer> getVinylCountries() {
        return vinylCountries;
    }

    public HashMap<String, Integer> getVinylLabels() {
        return vinylLabels;
    }

    public int getVinylAverageYear() {
        return vinylAverageYear;
    }

    public int getVinylAverageSongCount() {
        return vinylAverageSongCount;
    }

    public double getVinylPercentageActive() {
        return vinylPercentageActive;
    }

    public HashMap<String, Integer> getBookAuthors() {
        return bookAuthors;
    }

    public HashMap<String, Integer> getBookGenres() {
        return bookGenres;
    }

    public int getBookAverageYear() {
        return bookAverageYear;
    }

    public HashMap<String, Integer> getMovieGenres() {
        return movieGenres;
    }

    public HashMap<String, Integer> getMovieDirectors() {
        return movieDirectors;
    }

    public HashMap<String, Integer> getMovieCountries() {
        return movieCountries;
    }

    public int getMovieAverageYear() {
        return movieAverageYear;
    }

    public double getMoviePercentageSeries() {
        return moviePercentageSeries;
    }

    public DefaultPieDataset getVinylGenresSet() {
        return vinylGenresSet;
    }

    public DefaultPieDataset getVinylAlbumsOfInterpretSet() {
        return vinylAlbumsOfInterpretSet;
    }

    public DefaultPieDataset getVinylCountriesSet() {
        return vinylCountriesSet;
    }

    public DefaultPieDataset getVinylLabelsSet() {
        return vinylLabelsSet;
    }

    public DefaultPieDataset getBookAuthorsSet() {
        return bookAuthorsSet;
    }

    public DefaultPieDataset getBookGenresSet() {
        return bookGenresSet;
    }

    public DefaultPieDataset getMovieGenresSet() {
        return movieGenresSet;
    }

    public DefaultPieDataset getMovieDirectorsSet() {
        return movieDirectorsSet;
    }

    public DefaultPieDataset getMovieCountriesSet() {
        return movieCountriesSet;
    }
    
    private JsonArray convertToJsonArray(HashMap<String, Integer> input) {
        JsonArrayBuilder ab = Json.createArrayBuilder();
        for (Map.Entry<String,Integer> entry : input.entrySet()) {
            JsonObjectBuilder ob = Json.createObjectBuilder();
            ob.add("Key", entry.getKey());
            ob.add("Value", entry.getValue());
            ab.add(ob.build());
        }
        return ab.build();
    }
    
    private void fillHashMaps(JsonArray input, HashMap<String, Integer> output) {
        for (JsonValue v : input) {
            JsonObject o = v.asJsonObject();
            output.put(o.getString("Key"), o.getInt("Value"));
        }
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Vinyl Average Year", vinylAverageYear);
        b.add("Vinyl Average Song Count", vinylAverageSongCount);
        b.add("Vinyl Percentage Active", vinylPercentageActive);
        
        b.add("Book Average Year", bookAverageYear);
        
        b.add("Movie Average Year", movieAverageYear);
        b.add("Movie Percentage Series", moviePercentageSeries);
        
        b.add("Vinyl Genres", convertToJsonArray(vinylGenres));
        b.add("Vinyl Albums Of Interpret", convertToJsonArray(vinylAlbumsOfInterpret));
        b.add("Vinyl Countries", convertToJsonArray(vinylCountries));
        b.add("Vinyl Labels", convertToJsonArray(vinylLabels));
        
        b.add("Book Authors", convertToJsonArray(bookAuthors));
        b.add("Book Genres", convertToJsonArray(bookGenres));
        
        b.add("Movie Genres", convertToJsonArray(movieGenres));
        b.add("Movie Directors", convertToJsonArray(movieDirectors));
        b.add("Movie Countries", convertToJsonArray(movieCountries));
        
        return b.build();
    }
    
}
