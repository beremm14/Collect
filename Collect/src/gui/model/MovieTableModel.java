package gui.model;

import data.Database;
import data.movie.Movie;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author emil
 */
public class MovieTableModel extends AbstractTableModel {
    
    private final String[] columns = {"Title", "Genre", "Director", "Year", "Length", "Country", "Type", "Series"};

    @Override
    public int getRowCount() {
        return Database.getInstance().getMovies().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Movie m = Database.getInstance().getMovies().get(rowIndex);
        switch(columnIndex) {
            case 0: return m.getName();
            case 1: return m.getGenre();
            case 2: return m.getDirector();
            case 3: return m.getYear();
            case 4: return m.getLength();
            case 5: return m.getCountry();
            case 6: return m.getType().toString();
            case 7: return m.isSeries();
            default: throw new RuntimeException("wrong column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}
