package data;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author emil
 */
public class Song implements JsonObjAble {
    
    private final String name;
    private final int pos;
    private final int posOnSide;
    private final int side;

    public Song(String name, int pos, int posOnSide, int side) {
        this.name = name;
        this.pos = pos;
        this.posOnSide = posOnSide;
        this.side = side;
    }
    
    public Song(JsonObject input) {
        this.name = input.getString("Name");
        this.pos = input.getInt("Pos");
        this.posOnSide = input.getInt("PosOnSide");
        this.side = input.getInt("Side");
    }

    public String getName() {
        return name;
    }

    public int getPos() {
        return pos;
    }

    public int getPosOnSide() {
        return posOnSide;
    }

    public int getSide() {
        return side;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObjectBuilder b = Json.createObjectBuilder();
        
        b.add("Name", name);
        b.add("Pos", pos);
        b.add("PosOnSide", posOnSide);
        b.add("Side", side);
        
        return b.build();
    }

}