package cz.muni.fi.pa165.project.dto;

import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import java.util.Date;

/**
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/23/2015
 */
public class ServiceCheckDTO {

    /**
     * Service check's id
     */
    private Long id;

	/**
	 * Service check's status
	 */
    private ServiceCheckStatus status;

	/**
	 * Date when check is been realized (or is planned)
	 */
    private Date serviceCheckDate;

	/**
	 * vehicle, which was checked (or will be)
	 */
    private VehicleDTO vehicle;

	/**
	 * employee of service
	 */
    private String serviceEmployee;

	/**
	 * service check's report (optional)
	 */
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

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
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
                ", id=" + id +
                ", status=" + status + 
                ", date=" + serviceCheckDate + 
                ", vehicle=" + vehicle + 
                ", serviceEmployeeId=" + serviceEmployee + 
                ", report=" + report + 
                '}';
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
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ServiceCheckDTO)) {
            return false;
        }
        
        final ServiceCheckDTO other = (ServiceCheckDTO) obj;
        return this.getId().equals(other.getId()) && 
                (this.getStatus() == other.getStatus()) && 
                ( ((this.getServiceCheckDate() == null) && (other.getServiceCheckDate() == null)) || this.getServiceCheckDate().equals(other.getServiceCheckDate()) ) &&    // both are null or they are equal
                this.getVehicle().equals(other.getVehicle()) && 
                this.getServiceEmployee().equals(other.getServiceEmployee()) && 
                this.getReport().equals(other.getReport());
    }
}
