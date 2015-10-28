package cz.muni.fi.pa165.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@Column(name = "vin")
	@NotNull
	private String vin;

	@Column(name = "brand")
	@NotNull
	private String brand;

	@Column(name = "model")
	@NotNull
	private String model;

	@Column(name = "type")
	@NotNull
	private String type;

	@Column(name = "year_of_production")
	@NotNull
	private Date yearOfProduction;

	@Column(name = "engine_type")
	@NotNull
	private String engineType;

	@Column(name = "mileage")
	@NotNull
	private Long mileage;

	@Column(name = "service_check_interval")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date serviceCheckInterval;

//	@Column(name = "service_check_mileage_interval")
//	@NotNull
//	private long serviceCheckMileageInterval;

	@Column(name = "max_mileage")
	@NotNull
	private Long maxMileage;

	@OneToMany(mappedBy = "vehicle")
	private List<ServiceCheck> serviceChecks;

	@OneToMany(mappedBy="vehicle")
	private List<Drive> drives;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Date yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public String getEngineType() {
		return engineType;
	}

	public void setEngineType(String engineType) {
		this.engineType = engineType;
	}

	public Long getMileage() {
		return mileage;
	}

	public void setMileage(Long mileage) {
		this.mileage = mileage;
	}

	public Date getServiceInterval() {
		return this.serviceCheckInterval;
	}

	public void setServiceInterval(Date serviceCheckInterval) {
		this.serviceCheckInterval = serviceCheckInterval;
	}


//	public long getServiceCheckMileageInterval() {
//		return serviceCheckMileageInterval;
//	}
//
//	public void setServiceCheckMileageInterval(long serviceCheckMileageInterval) {
//		this.serviceCheckMileageInterval = serviceCheckMileageInterval;
//	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getMaxMileage() {
		return maxMileage;
	}

	public void setMaxMileage(Long maxMileage) {
		this.maxMileage = maxMileage;
	}

	public List<Drive> getDrives() {
		return drives;
	}

	public void setDrives(List<Drive> drives) {
		this.drives = drives;
	}

	public List<ServiceCheck> getServiceChecks() {
		return serviceChecks;
	}

	public void setServiceChecks(List<ServiceCheck> serviceChecks) {
		this.serviceChecks = serviceChecks;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"vin='" + vin + '\'' +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", type='" + type + '\'' +
				", yearOfProduction=" + yearOfProduction +
				", engineType='" + engineType + '\'' +
				", mileage=" + mileage +
				", serviceCheckInterval=" + serviceCheckInterval +
//				", serviceCheckMileageInterval=" + serviceCheckMileageInterval +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null) return false;

		if(!(o instanceof Vehicle)) return false;

		Vehicle vehicle = (Vehicle)o;
		if(this.vin == null){
			if(vehicle.vin != null){
				return false;
			}
		} else if (!this.vin.equals(vehicle.vin)){
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (this.vin != null ? this.vin.hashCode() : 0);

		return result;
	}
}
