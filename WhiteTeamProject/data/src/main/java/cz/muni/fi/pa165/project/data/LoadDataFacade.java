package cz.muni.fi.pa165.project.data;

import java.io.IOException;

/**
 * Loads data to database
 * 
 * @author jakub
 */
public interface LoadDataFacade {
    
    /**
     * Method loads all data to database
     * @throws IOException 
     */
    void load() throws IOException;
}
