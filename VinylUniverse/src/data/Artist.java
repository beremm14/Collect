package data;

/**
 *
 * @author emil
 */
public class Artist {
    
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

    public Artist(String name, String country, String genre) {
        this.name = name;
        this.country = country;
        this.genre = genre;
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

}
