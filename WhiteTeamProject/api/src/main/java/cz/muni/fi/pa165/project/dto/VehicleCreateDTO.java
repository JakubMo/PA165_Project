package cz.muni.fi.pa165.project.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/23/2015
 */
public class VehicleCreateDTO {
    
    /**
     * Vehicle vin (17 characters long).
     */
    @NotNull
    @Size(min=17, max=17)
    private String vin;
    
    /**
     * Brand of vehicle.
     */
    @NotNull
    @Size(min=2, max=20)
    private String brand;
    
    /**
     * Model of vehicle.
     */
    @NotNull
    @Size(min=2, max=30)
    private String model;
    
    /**
     * Type pf vehicle.
     */
    @NotNull
    @Size(min=2, max=10)
    private String type;
    
    /**
     * Construction year of vehicle.
     */
    @NotNull
    @Min(1900L)
    private int yearOfProduction;
    
    /**
     * Engine type of vehicle.
     */
    @NotNull
    @Size(min=3, max=10)
    private String engineType;
    
    /**
     * Distance driven.
     */
    @NotNull
    @Min(0L)
    private Long mileage;
    
    /**
     * Service check interval in months. Maximum is 12 months (min is 1 month)
     */
    @NotNull
    @Min(1L)
    @Max(12L)
    private int serviceCheckInterval;
    
    /**
     * Maximum mileage for vehicle. When mileage is higher,
     * the vehicle is not visible in system. The lowest allowed
     * value is 100 000.
     */
    @NotNull
    @Min(100000L)
    private Long maxMileage;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Long getMaxMileage() {
        return maxMileage;
    }

    public void setMaxMileage(Long maxMileage) {
        this.maxMileage = maxMileage;
    }
    
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + (this.getVin() != null ? this.getVin().hashCode() : 0);

        return result;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (!(o instanceof VehicleCreateDTO)) {
            return false;
        }

        final VehicleCreateDTO vehicle = (VehicleCreateDTO) o;
        if (this.getVin() == null) {
            if (vehicle.getVin() != null) {
                return false;
            }
        } else if (!this.getVin().equals(vehicle.getVin())) {
            return false;
        }

        return true;
    }
    
    @Override
    public String toString() {
        return "Vehicle{"
                + "vin='" + vin + '\''
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", type='" + type + '\''
                + ", yearOfProduction=" + yearOfProduction
                + ", engineType='" + engineType + '\''
                + ", mileage=" + mileage
                + ", serviceCheckInterval=" + serviceCheckInterval
                + '}';
    }
}
