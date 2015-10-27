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

	@Column(name = "service_interval")
	@NotNull
	private Date serviceInterval;

	@Column(name = "max_mileage")
	@NotNull
	private Long maxMileage;

	@ManyToOne
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
		return serviceInterval;
	}

	public void setServiceInterval(Date serviceInterval) {
		this.serviceInterval = serviceInterval;
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
		return drives;
	}

	public void setDrives(List<Drive> drives) {
		this.drives = drives;
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
				", serviceInterval=" + serviceInterval +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Vehicle vehicle = (Vehicle) o;

		return !(vin != null ? !vin.equals(vehicle.vin) : vehicle.vin != null);

	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = prime * this.vin.hashCode();

		return result;
	}
}
