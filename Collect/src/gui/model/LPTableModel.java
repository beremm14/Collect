package gui.model;

import data.Database;
import data.vinyl.LP;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author emil
 */
public class LPTableModel extends AbstractTableModel {
    
    private final String[] columns = {"Name", "Interpret", "Genre", "Year", "Label", "Composer", "Country"};

    @Override
    public int getRowCount() {
        return Database.getInstance().getLps().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LP lp = Database.getInstance().getLps().get(rowIndex);
        
        switch(columnIndex) {
            case 0: return lp.getName();
            case 1: return lp.getInterpret();
            case 2: return lp.getGenre();
            case 3: return lp.getYear();
            case 4: return lp.getLabel();
            case 5: return lp.getComposer();
            case 6: return lp.getInterpret().getCountry();
            default: throw new RuntimeException("wrong column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}
