package data;

import data.book.Book;
import data.movie.Movie;
import data.vinyl.LP;
import java.util.HashMap;

/**
 *
 * @author emil
 */
public class Stats {
    
    private static Stats instance;
    
    public static Stats getInstance() {
        if (instance == null) {
            instance = new Stats();
        }
        return instance;
    }
    
    private final HashMap<String, Integer> vinylGenres = new HashMap<>();
    private final HashMap<String, Integer> vinylAlbumsOfInterpret = new HashMap<>();
    private final HashMap<String, Integer> vinylCountries = new HashMap<>();
    private final HashMap<String, Integer> vinylLabels = new HashMap<>();
    private int vinylAverageYear;
    private int vinylAverageSongCount;
    private double vinylPercentageActive;
    
    private final HashMap<String, Integer> bookAuthors = new HashMap<>();
    private final HashMap<String, Integer> bookGenres = new HashMap<>();
    private int bookAverageYear;
    
    private final HashMap<String, Integer> movieGenres = new HashMap<>();
    private final HashMap<String, Integer> movieDirectors = new HashMap<>();
    private final HashMap<String, Integer> movieCountries = new HashMap<>();
    private int movieAverageYear;
    private double moviePercentageSeries;
    
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
    
    public void updateInstance() {
        updateVinyls();
        updateBooks();
        updateMovies();
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
    
}
