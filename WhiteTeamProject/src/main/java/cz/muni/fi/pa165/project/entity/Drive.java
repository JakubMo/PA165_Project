package cz.muni.fi.pa165.project.entity;

import cz.muni.fi.pa165.project.enums.DriveStatus;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    @NotNull
    private Vehicle vehicle;
    
    /**
     * Employee who used the vehicle.
     */
    @ManyToOne
    @NotNull
    private Employee employee;
    
    /**
     * Date when the drive starts.
     */
    @Column(name = "start_date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    /**
     * Date when the drive ends
     */
    @Column(name = "end_date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    /**
     * Kilometers driven during the drive
     */
    @Column(name = "km")
    private BigDecimal km;
    
    /**
     * Current status of the drive
     */
    @Enumerated
    @NotNull
    @Column (name = "state")
    private DriveStatus state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getKm() {
        return km;
    }

    public void setKm(BigDecimal km) {
        this.km = km;
    }

    public DriveStatus getState() {
        return state;
    }

    public void setState(DriveStatus state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Drive{" + "id=" + id + 
                ", vehicle=" + vehicle + 
                ", employee=" + employee + 
                ", startDate=" + startDate + 
                ", endDate=" + endDate + 
                ", km=" + km + 
                ", state=" + state + 
                '}';
    }
    
    @Override
    public int hashCode(){
        int base = 31;
        int result = 1;
        
        result += (km == null) ? 0 : km.intValue();
        result += (state == null) ? 0 : state.ordinal();
        result += (startDate == null) ? 0 : startDate.hashCode();
        result += (endDate == null) ? 0 : endDate.hashCode();
        result += (employee == null) ? 0 : employee.hashCode();
        result += (vehicle == null) ? 0 : vehicle.hashCode();
        result = base * result;
        
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
        
        Drive other = (Drive) obj;
        //employee
        if (employee == null){
            if(other.getEmployee() != null)
                return false;
        } else if (!employee.equals(other.getEmployee()))
            return false;
        //vehicle
        if (vehicle == null){
            if(other.getVehicle() != null)
                return false;
        } else if (vehicle.equals(other.getVehicle()))
            return false;
        //startDate
        if (startDate == null){
            if(other.getStartDate() != null)
                return false;
        } else if (!startDate.equals(other.getStartDate()))
            return false;
        //endDate
        if (endDate == null){
            if(other.getEndDate()!= null)
                return false;
        } else if (!endDate.equals(other.getEndDate()))
            return false;
        //km
        if (km == null){
            if(other.getKm() != null)
                return false;
        } else if (!km.equals(other.getKm()))
            return false;
        //state
        if (state == null){
            if(other.getState() != null)
                return false;
        } else if (!state.equals(other.getState()))
            return false;
        
        return true;
    }
}
