package cz.muni.fi.pa165.project.util;

import org.springframework.dao.DataAccessException;

/**
 * Exception for errors in methods of DAO classes.
 *
 * @author Marek
 */
public class DataAccessExceptionImpl extends DataAccessException {
    public DataAccessExceptionImpl(String message) {
        super(message);
    }

    public DataAccessExceptionImpl(String message, Throwable cause) {
        super(message, cause);
    }
}
