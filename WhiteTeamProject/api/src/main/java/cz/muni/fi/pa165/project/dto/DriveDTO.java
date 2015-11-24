package cz.muni.fi.pa165.project.dto;

import cz.muni.fi.pa165.project.enums.DriveStatus;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Marek
 */
public class DriveDTO {    
    /**
     * ID of drive.
     */
    private Long id;
    
    /**
     * Vehicle used.
     */
    private VehicleDTO vehicle;
    
    /**
     * Employee who used vehicle.
     */
    private EmployeeDTO employee;
    
    /**
     * Date when the drive starts.
     */
    private Date startDate;
    
    /**
     * Date when the drive ends.
     */
    private Date endDate;
    
    /**
     * Amount of driven kilometers in drive.
     */
    private BigDecimal km;
    
    /**
     * Current status of drive.
     */
    private DriveStatus driveStatus;

    /**
     * Getter for {@link #id}.
     * 
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for {@link #id}.
     * 
     * @param id ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for {@link #vehicle}.
     * 
     * @return {@link #vehicle}
     */
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    /**
     * Setter for {@link #vehicle}.
     * 
     * @param vehicle vehicle to be set
     */
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Getter for {@link #employee}.
     * 
     * @return {@link #employee}
     */
    public EmployeeDTO getEmployee() {
        return employee;
    }

    /**
     * Setter for {@link #employee}.
     * 
     * @param employee employee to be set
     */
    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    /**
     * Getter for {@link #startDate}.
     * 
     * @return {@link #startDate}
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter for {@link #startDate}.
     * 
     * @param startDate start date to be set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for {@link #endDate}.
     * 
     * @return {@link #endDate}
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter for {@link #endDate}.
     * 
     * @param endDate end date to be set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for {@link #km}.
     * 
     * @return {@link #km}
     */
    public BigDecimal getKm() {
        return km;
    }

    /**
     * Setter for {@link #km}.
     * 
     * @param km amount of kilometers to be set
     */
    public void setKm(BigDecimal km) {
        this.km = km;
    }

    /**
     * Getter of {@link #driveStatus}.
     * 
     * @return {@link #driveStatus}
     */
    public DriveStatus getDriveStatus() {
        return driveStatus;
    }

    /**
     * Setter of {@link #driveStatus}.
     * 
     * @param driveStatus status to be set
     */
    public void setDriveStatus(DriveStatus driveStatus) {
        this.driveStatus = driveStatus;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + id.hashCode();
        hash = 53 * hash + vehicle.hashCode();
        hash = 53 * hash + employee.hashCode();
        hash = 53 * hash + startDate.hashCode();
        hash = 53 * hash + endDate.hashCode();
        hash = 53 * hash + km.hashCode();
        hash = 53 * hash + driveStatus.getValue();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DriveDTO)) {
            return false;
        }
        
        final DriveDTO other = (DriveDTO) obj;
        
        return this.getId().equals(other.getId()) &&
                this.getVehicle().equals(other.getVehicle()) &&
                this.getEmployee().equals(other.getEmployee()) && 
                this.getStartDate().equals(other.getStartDate()) &&
                this.getEndDate().equals(other.getEndDate()) &&
                this.getKm().equals(other.getKm()) &&
                (this.getDriveStatus() == other.getDriveStatus());
    }

    @Override
    public String toString() {
        return "DriveDTO{" + 
                "id=" + id + 
                ", vehicle=" + vehicle + 
                ", employee=" + employee + 
                ", startDate=" + startDate + 
                ", endDate=" + endDate + 
                ", km=" + km + 
                ", driveStatus=" + driveStatus + 
                '}';
    }
}
