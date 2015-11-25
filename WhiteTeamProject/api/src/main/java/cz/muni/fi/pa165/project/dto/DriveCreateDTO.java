package cz.muni.fi.pa165.project.dto;

import cz.muni.fi.pa165.project.enums.DriveStatus;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marek
 */
public class DriveCreateDTO {
    /**
     * Vehicle used.
     */
    @NotNull
    private VehicleDTO vehicle;
    
    /**
     * Employee who used vehicle.
     */
    @NotNull
    private EmployeeDTO employee;
    
    /**
     * Date when the drive starts.
     */
    @NotNull
    private Date startDate;
    
    /**
     * Date when the drive ends.
     */
    @NotNull
    private Date endDate;
    
    /**
     * Amount of driven kilometers in drive.
     */
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal km;
    
    /**
     * Current status of drive.
     */
    @NotNull
    private DriveStatus driveStatus;

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
     * Getter for {@link #driveStatus}.
     * 
     * @return {@link #driveStatus}
     */
    public DriveStatus getDriveStatus() {
        return driveStatus;
    }

    /**
     * Setter for {@link #driveStatus}.
     * 
     * @param driveStatus drive status to be set
     */
    public void setDriveStatus(DriveStatus driveStatus) {
        this.driveStatus = driveStatus;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + vehicle.hashCode();
        hash = 67 * hash + employee.hashCode();
        hash = 67 * hash + startDate.hashCode();
        hash = 67 * hash + endDate.hashCode();
        hash = 67 * hash + km.hashCode();
        hash = 67 * hash + driveStatus.getValue();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DriveCreateDTO)) {
            return false;
        }
        
        final DriveCreateDTO other = (DriveCreateDTO) obj;
        
        return this.getVehicle().equals(other.getVehicle()) &&
                this.getEmployee().equals(other.getEmployee()) && 
                this.getStartDate().equals(other.getStartDate()) && 
                this.getEndDate().equals(other.getEndDate()) && 
                this.getKm().equals(other.getKm()) && 
                (this.getDriveStatus() == other.getDriveStatus());
    }

    @Override
    public String toString() {
        return "DriveCreateDTO{" + 
                "vehicle=" + vehicle + 
                ", employee=" + employee + 
                ", startDate=" + startDate + 
                ", endDate=" + endDate + 
                ", km=" + km + 
                ", driveStatus=" + driveStatus + 
                '}';
    }
    
    @AssertTrue(message = "Start date must be before end date.")
    public boolean isStartDateBeforeEndDate() {
        if(startDate == null) {
            throw new IllegalStateException("start date is null");
        }
        if(endDate == null) {
            throw new IllegalStateException("end date is null");
        }
        
        return startDate.before(endDate);
    }
}
