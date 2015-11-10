package cz.muni.fi.pa165.project.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

/**
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 27.10.2015
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * Vehicle's vin
	 */
	@Column(nullable = false, unique = true)
	private String vin;

	/**
	 * Vehicle's brand
	 */
	@Column(nullable = false)
	private String brand;

	/**
	 * Vehicle's model
	 */
	@Column(nullable = false)
	private String model;

	/**
	 * Type of the vehicle
	 */
	@Column(nullable = false)
	private String type;

	/**
	 * Vehicle's year of production
	 */
	@Column(name = "year_of_production", nullable = false)
	private int yearOfProduction;

	/**
	 * Vehicle's engine type
	 */
	@Column(name = "engine_type", nullable = false)
	private String engineType;

	/**
	 * Current vehicle's mileage
	 */
	@Column(name = "mileage", nullable = false)
	private Long mileage;

	/**
	 * Number of days between service checks of the vehicle
	 */
	@Column(name = "service_check_interval", nullable = false)
	private int serviceCheckInterval;

	/**
	 * Max approved mileage, after that the vehicle will not be available
	 */
	@Column(name = "max_mileage", nullable = false)
	private Long maxMileage;

	/**
	 * List of the vehicle's service checks
	 */
	@OneToMany(mappedBy = "vehicle")
	private List<ServiceCheck> serviceChecks;

	/**
	 * List of the vehicle's drives
	 */
	@OneToMany(mappedBy="vehicle")
	private List<Drive> drives;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public int getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(int yearOfProduction) {
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

	public int getServiceCheckInterval() {
		return serviceCheckInterval;
	}

	public void setServiceCheckInterval(int serviceCheckInterval) {
		this.serviceCheckInterval = serviceCheckInterval;
	}

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
		return Collections.unmodifiableList(this.drives);
	}

	public void setDrives(List<Drive> drives) {
		this.drives = drives;
	}

	public List<ServiceCheck> getServiceChecks() {
		return Collections.unmodifiableList(this.serviceChecks);
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
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(o == null) return false;

		if(!(o instanceof Vehicle)) return false;

		final Vehicle vehicle = (Vehicle)o;
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
