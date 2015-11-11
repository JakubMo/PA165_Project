package cz.muni.fi.pa165.project.entity;

import cz.muni.fi.pa165.project.enums.DriveStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;


/**
 * Provides entity for drive record
 * 
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/26/2015
 */
@Entity
public class Drive implements Serializable{
    /**
     * ID of drive record
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Vehicle used.
     */
    @ManyToOne
    private Vehicle vehicle;
    
    /**
     * Employee who used the vehicle.
     */
    @ManyToOne
    private Employee employee;
    
    /**
     * Date when the drive starts.
     */
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    /**
     * Date when the drive ends
     */
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    /**
     * Kilometers driven during the drive
     */
    @Column(name = "km", nullable = false)
    private BigDecimal km;
    
    /**
     * Current status of the drive
     */
    @Enumerated
    @Column (name = "driveStatus")
    private DriveStatus driveStatus;

    /**
     * Constructor without parameters, does not set any default values to attributes.
     */
    public Drive() {
    }
    
    /**
     * Constructor for setting all attributes.
     * 
     * @param id ID of drive record
     * @param vehicle vehicle used
     * @param employee employee who used the vehicle
     * @param startDate date when the drive starts
     * @param endDate date when the drive ends
     * @param km kilometers driven during the drive
     * @param driveStatus current status of drive
     */
    public Drive(Long id, Vehicle vehicle, Employee employee, Date startDate, Date endDate, BigDecimal km, DriveStatus driveStatus){
        this.id = id;
        this.vehicle = vehicle;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.km = km;
        this.driveStatus = driveStatus;
    }
    
    /**
     * Getter for {@link #id}.
     * 
     * @return {@link  #id}
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Setter for {@link #id}.
     * 
     * @param id id to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for {@link #vehicle}.
     * 
     * @return {@link  #vehicle}
     */
    public Vehicle getVehicle() {
        return vehicle;
    }
    
    /**
     * Setter for {@link #vehicle}.
     * 
     * @param vehicle vehicle used to be set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Getter for {@link #employee}.
     * 
     * @return {@link  #employee}
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Setter for {@link #employee}.
     * 
     * @param employee employee to be set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Getter for {@link #startDate}.
     * 
     * @return {@link  #startDate}
     */
    public Date getStartDate() {
        return new Date(this.startDate.getTime());
    }

    /**
     * Setter for {@link #startDate}.
     * 
     * @param startDate startDate of drive to be set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for {@link #endDate}.
     * 
     * @return {@link  #endDate}
     */
    public Date getEndDate() {
        return new Date(this.endDate.getTime());
    }

    /**
     * Setter for {@link #endDate}.
     * 
     * @param endDate endDate of drive to be set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for {@link #km}.
     * 
     * @return {@link  #km}
     */
    public BigDecimal getKm() {
        return km;
    }

    /**
     * Setter for {@link #km}.
     * 
     * @param km kilometers driven to be set
     */
    public void setKm(BigDecimal km) {
        this.km = km;
    }

    /**
     * Getter for {@link #driveStatus}.
     * 
     * @return {@link  #driveStatus}
     */
    public DriveStatus getDriveStatus() {
        return driveStatus;
    }

    /**
     * Setter for {@link #driveStatus}.
     * 
     * @param driveStatus  current status of drive to be set
     */
    public void setDriveStatus(DriveStatus driveStatus) {
        this.driveStatus = driveStatus;
    }

    @Override
    public String toString() {
        return "Drive{" + "id=" + id + 
                ", vehicle=" + vehicle + 
                ", employee=" + employee + 
                ", startDate=" + startDate + 
                ", endDate=" + endDate + 
                ", km=" + km + 
                ", status=" + driveStatus + 
                '}';
    }
    
    @Override
    public int hashCode(){
        int result = 31;
        result = 8 * result + this.getId().hashCode();
        result = 8 * result + this.getKm().intValue();
        result = 8 * result + this.getDriveStatus().getValue(); 
        result = 8 * result + ((this.getStartDate() != null) ? this.getStartDate().hashCode() : 0);
        result = 8 * result + ((this.getEndDate() != null) ? this.getEndDate().hashCode() : 0);
        result = 8 * result + this.getEmployee().hashCode();
        result = 8 * result + this.getVehicle().hashCode();
        return result;
    }
    
    @Override
    public boolean equals(Object obj){
        //equivalence
        if (this == obj)
            return true;
        if (this == null)
            return false;
        if (!(obj instanceof Drive))
            return false;
        
        final Drive other = (Drive) obj;
        return this.getId().equals(other.getId()) &&
                this.getEmployee().equals(other.getEmployee()) &&
                this.getVehicle().equals(other.getVehicle()) &&
                this.getDriveStatus().equals(other.getDriveStatus());
    }
}
