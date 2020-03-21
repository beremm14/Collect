package gui.model;

import data.Database;
import data.vinyl.LP;
import data.vinyl.Song;
import data.vinyl.SongLpPair;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author emil
 */
public class SongTableModel extends AbstractTableModel {
    
    private final String[] columns = {"Nr.", "NoS", "Side", "Title", "Album", "Intepret", "Year", "Country", "Label", "Favorite"};
    private final ArrayList<SongLpPair> songs = new ArrayList<>();

    @Override
    public int getRowCount() {
        return Database.getInstance().getSongCount();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SongLpPair s = songs.get(rowIndex);
        switch(columnIndex) {
            case 0: return s.getSong().getPos();
            case 1: return s.getSong().getPosOnSide();
            case 2: return s.getSong().getSide();
            case 3: return s.getSong().getName();
            case 4: return s.getLp().getName();
            case 5: return s.getLp().getInterpret().getName();
            case 6: return s.getLp().getYear();
            case 7: return s.getLp().getInterpret().getCountry();
            case 8: return s.getLp().getLabel();
            case 9: return s.getSong().isFavorite();
            default: throw new RuntimeException("wrong column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void updateSongList() {
        songs.clear();
        for (LP lp : Database.getInstance().getLps()) {
            for (Song song : lp.getSongs()) {
                songs.add(new SongLpPair(song, lp));
            }
        }
        Collections.sort(songs);
    }
    
}
