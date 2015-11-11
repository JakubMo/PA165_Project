package cz.muni.fi.pa165.project.enums;

/**
 * Current status of {@link cz.muni.fi.pa165.project.entity.Drive}.
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/26/2015
 */
public enum DriveStatus {
    /**
     * Drive in requested state, waiting for approval
     */
    REQUESTED(0), 
    
    /**
     * Drive in approved state, waiting for the date of drive
     */
    APPROVED(1), 
    
    /**
     * Drive finished
     */
    COMPLETED(2), 
    
    /**
     * Drive has been cancelled
     */
    CANCELLED(3);
    
    private final int value;
    
    /**
     * Private constructor for setting integer representation of enum item.
     * 
     * @param value Integer value to be set.
     */
    private DriveStatus(int value) {
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
