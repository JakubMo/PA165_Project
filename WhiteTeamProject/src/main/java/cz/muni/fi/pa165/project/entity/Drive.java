package cz.muni.fi.pa165.project.entity;

import cz.muni.fi.pa165.project.enums.DriveStatus;
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
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 10/26/2015
 */

@Entity
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @NotNull
    private Long vehicleVin;
    
    @ManyToOne
    @NotNull
    private Long employeeId;
    
    @Column(name = "start_date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Column(name = "end_date")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(name = "km")
    private BigDecimal km;
    
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

    public Long getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(Long vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
                ", vehicleVin=" + vehicleVin + 
                ", employeeId=" + employeeId + 
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
        result += (employeeId == null) ? 0 : (int) (employeeId ^ (employeeId >>> 32));
        result += (vehicleVin == null) ? 0 : (int) (vehicleVin ^ (vehicleVin >>> 32));
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
        //employeeId
        if (employeeId == null){
            if(other.getEmployeeId() != null)
                return false;
        } else if (!employeeId.equals(other.getEmployeeId()))
            return false;
        //vehicleVin
        if (vehicleVin == null){
            if(other.getVehicleVin() != null)
                return false;
        } else if (vehicleVin.equals(other.getVehicleVin()))
            return false;
        //startDate
        if (startDate == null){
            if(other.getStartDate() != null)
                return false;
        } else if (!startDate.equals(other.getStartDate()))
            return false;
        //endDate
        if (employeeId == null){
            if(other.getEmployeeId() != null)
                return false;
        } else if (!employeeId.equals(other.getEmployeeId()))
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
