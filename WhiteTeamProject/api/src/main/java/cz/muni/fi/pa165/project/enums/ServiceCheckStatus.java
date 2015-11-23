package cz.muni.fi.pa165.project.enums;

/**
 * Provides information about status of service check.
 *
 * @author Marek
 */
public enum ServiceCheckStatus {
    /**
     * Service check was not done.
     */
    NOT_DONE(0),
    
    /**
     * Service check was done and everything is ok.
     */
    DONE_OK(1),
    
    /**
     * Service check was done but something is wrong and needs to be repaired / changed.
     */
    DONE_NOT_OK(2);
    
    /**
     * Integer representation of enum item.
     */
    private final int value;
    
    /**
     * Private constructor for setting integer representation of enum item.
     * 
     * @param value Integer value to be set.
     */
    private ServiceCheckStatus(int value) {
        this.value = value;
    }
    
    /**
     * Obtains integer representation of enum item.
     * 
     * @return Integer representation of enum item.
     */
    public int getValue() {
        return value;
    }
}
