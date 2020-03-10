package data.vinyl;

import data.JsonObjAble;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Song implements JsonObjAble, Comparable<Song> {
    
    private final String name;
    private final Integer pos;
    private final int posOnSide;
    private final int side;
    private final boolean favorite;

    public Song(String name, int pos, int posOnSide, int side, boolean favorite) {
        this.name = name;
        this.pos = pos;
        this.posOnSide = posOnSide;
        this.side = side;
        this.favorite = favorite;
    }
    
    public Song(JsonObject input) {
        this.name = input.getString("Name");
        this.pos = input.getInt("Pos");
        this.posOnSide = input.getInt("PosOnSide");
        this.side = input.getInt("Side");
        this.favorite = input.getBoolean("Favorite");
    }

    public String getName() {
        return name;
    }

    public Integer getPos() {
        return pos;
    }

    public int getPosOnSide() {
        return posOnSide;
    }

    public int getSide() {
        return side;
    }

    public boolean isFavorite() {
        return favorite;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Name", name);
        b.add("Pos", pos);
        b.add("PosOnSide", posOnSide);
        b.add("Side", side);
        b.add("Favorite", favorite);
        
        return b.build();
    }

    @Override
    public int compareTo(Song o) {
        return this.getPos().compareTo(o.getPos());
    }

}
