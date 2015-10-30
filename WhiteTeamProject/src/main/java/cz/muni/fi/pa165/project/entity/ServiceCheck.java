package cz.muni.fi.pa165.project.entity;

import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Current status of service check.
     */
    @Enumerated
    @Column(name = "status")
    private ServiceCheckStatus status;
    
    /**
     * Date when the service check was done.
     */
    @Temporal(DATE)
    @Column(name = "service_check_date")
    private Date serviceCheckDate;
    
    /**
     * Vehicle that goes to service check.
     */
    @ManyToOne
    @NotNull
    private Vehicle vehicle;
    
    /**
     * Employee who did the service check.
     */
    @NotNull
    @Column(name = "service_employee")
    private String serviceEmployee;
    
    /**
     * Optional report message of service check.
     */
    @NotNull
    @Column(name = "report")
    private String report;

    /**
     * Constructor without parameters, does not set any default values to attributes.
     */
    public ServiceCheck() {
    }

    /**
     * Constructor for setting all attributes.
     * 
     * @param id id of service check
     * @param status status of service check
     * @param serviceCheckDate date when was service check done
     * @param vehicle vehicle that goes to service check
     * @param serviceEmployee employee who did the service check
     * @param report optional report message of service check
     */
    public ServiceCheck(Long id, ServiceCheckStatus status, Date serviceCheckDate, Vehicle vehicle, String serviceEmployee, String report) {
        this.id = id;
        this.status = status;
        this.serviceCheckDate = serviceCheckDate;
        this.vehicle = vehicle;
        this.serviceEmployee = serviceEmployee;
        this.report = report;
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
     * Getter for {@link #status}.
     * 
     * @return {@link #status}
     */
    public ServiceCheckStatus getStatus() {
        return status;
    }

    /**
     * Setter for {@link #status}.
     * 
     * @param status status to be set
     */
    public void setStatus(ServiceCheckStatus status) {
        this.status = status;
    }

    /**
     * Getter for {@link #serviceCheckDate}.
     * 
     * @return {@link #serviceCheckDate}
     */
    public Date getServiceCheckDate() {
        return serviceCheckDate;
    }

    /**
     * Setter for {@link #serviceCheckDate}.
     * 
     * @param serviceCheckDate date of service check to be set
     */
    public void setServiceCheckDate(Date serviceCheckDate) {
        this.serviceCheckDate = serviceCheckDate;
    }

    /**
     * Getter for {@link #vehicle}.
     * 
     * @return {@link #vehicle}
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Setter for {@link #vehicle}.
     * 
     * @param vehicle vehicle to be set
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Getter for {@link #serviceEmployee}.
     * 
     * @return {@link #serviceEmployee}
     */
    public String getServiceEmployee() {
        return serviceEmployee;
    }

    /**
     * Setter for {@link #serviceEmployee}.
     * 
     * @param serviceEmployee employee of service to be set
     */
    public void setServiceEmployee(String serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    /**
     * Getter for {@link #report}.
     * 
     * @return {@link #report}
     */
    public String getReport() {
        return report;
    }

    /**
     * Setter for {@link #report}.
     * 
     * @param report report to be set
     */
    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "ServiceCheck{" + 
                "id=" + id + 
                ", status=" + status + 
                ", date=" + serviceCheckDate + 
                ", vehicle=" + vehicle + 
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
                ( ((this.getServiceCheckDate() == null) && (other.getServiceCheckDate() == null)) || this.getServiceCheckDate().equals(other.getServiceCheckDate()) ) &&    // both are null or they are equal
                this.getVehicle().equals(other.getVehicle()) && 
                this.getServiceEmployee().equals(other.getServiceEmployee()) && 
                this.getReport().equals(other.getReport());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id.hashCode();
        hash = 97 * hash + this.status.getValue();
        hash = 97 * hash + (this.serviceCheckDate != null ? this.serviceCheckDate.hashCode() : 0);
        hash = 97 * hash + this.vehicle.hashCode();
        hash = 97 * hash + this.serviceEmployee.hashCode();
        hash = 97 * hash + this.report.hashCode();
        return hash;
    }
}
