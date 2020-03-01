package data;

/**
 *
 * @author emil
 */
public class Song {
    
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

}
