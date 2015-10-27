package cz.muni.fi.pa165.project.entity;

import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 * Provides entity for service check.
 * 
 * @author Marek
 */
@Entity
@Table(name = "serviceCheck")
public class ServiceCheck implements Serializable {    
    /**
     * ID of service check.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Current status of service check.
     */
    @Column(name = "status")
    private ServiceCheckStatus status;
    
    /**
     * Date when the service check was done.
     */
    @Temporal(DATE)
    @Column(name = "service_check_date")
    private Date serviceCheckDate;
    
    /**
     * ID of vehicle that goes to service check.
     */
    @Column(name = "vehicle_id")
    private Long vehicleId;
    
    /**
     * Employee who did the service check.
     */
    @Column(name = "service_employee")
    private String serviceEmployee;
    
    /**
     * Optional report message of service check.
     */
    @Column(name = "report")
    private String report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceCheckStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceCheckStatus status) {
        this.status = status;
    }

    public Date getServiceCheckDate() {
        return serviceCheckDate;
    }

    public void setServiceCheckDate(Date serviceCheckDate) {
        this.serviceCheckDate = serviceCheckDate;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getServiceEmployee() {
        return serviceEmployee;
    }

    public void setServiceEmployee(String serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "ServiceCheck{" + 
                "id=" + id + 
                ", status=" + status + 
                ", date=" + serviceCheckDate + 
                ", vehicleId=" + vehicleId + 
                ", serviceEmployeeId=" + serviceEmployee + 
                ", report=" + report + 
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ServiceCheck)) {
            return false;
        }
        
        final ServiceCheck other = (ServiceCheck) obj;
        return this.getId().equals(other.getId()) && 
                (this.getStatus() == other.getStatus()) && 
                this.getServiceCheckDate().equals(other.getServiceCheckDate()) && 
                this.getVehicleId().equals(other.getVehicleId()) && 
                this.getServiceEmployee().equals(other.getServiceEmployee()) && 
                this.getReport().equals(other.getReport());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 97 * hash + this.status.getValue();
        hash = 97 * hash + (this.serviceCheckDate != null ? this.serviceCheckDate.hashCode() : 0);
        hash = 97 * hash + (this.vehicleId != null ? this.vehicleId.hashCode() : 0);
        hash = 97 * hash + (this.serviceEmployee != null ? this.serviceEmployee.hashCode() : 0);
        hash = 97 * hash + (this.report != null ? this.report.hashCode() : 0);
        return hash;
    }
}