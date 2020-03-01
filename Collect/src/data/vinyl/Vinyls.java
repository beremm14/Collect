package data.vinyl;

import data.JsonExport;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emil
 */
public class Vinyls implements JsonExport {
    
    private final List<LP> lps = new ArrayList<>();

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
