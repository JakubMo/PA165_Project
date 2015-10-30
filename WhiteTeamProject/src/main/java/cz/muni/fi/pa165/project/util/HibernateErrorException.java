package cz.muni.fi.pa165.project.util;

/**
 * Exception for working with hibernate and sessions in DAO classes.
 *
 * @author Marek
 */
public class HibernateErrorException extends Exception {
    public HibernateErrorException(String message) {
        super(message);
    }

    public HibernateErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public HibernateErrorException(Throwable cause) {
        super(cause);
    }

    public HibernateErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}
