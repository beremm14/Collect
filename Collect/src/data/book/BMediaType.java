package data.book;

/**
 *
 * @author emil
 */
public enum BMediaType {
    
    Book, iBooks, Kindle, Online, notDefined;

    public static BMediaType parseString(String input) {
        switch(input) {
            case "Book": return Book;
            case "iBooks": return iBooks;
            case "Kindle": return Kindle;
            case "Online": return Online;
            default: return notDefined;
        }
    }
    
}
