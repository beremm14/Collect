package gui.model;

import data.Database;
import data.book.Book;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author emil
 */
public class BookTableModel extends AbstractTableModel {
    
    private final String[] columns = {"Title", "Author", "Genre", "Type", "Year"};

    @Override
    public int getRowCount() {
        return Database.getInstance().getBooks().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = Database.getInstance().getBooks().get(rowIndex);
        
        switch(columnIndex) {
            case 0: return book.getTitle();
            case 1: return book.getAuthor();
            case 2: return book.getGenre();
            case 3: return book.getType().toString();
            case 4: return book.getYear();
            default: throw new RuntimeException("Wrong Column Index");
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
}
