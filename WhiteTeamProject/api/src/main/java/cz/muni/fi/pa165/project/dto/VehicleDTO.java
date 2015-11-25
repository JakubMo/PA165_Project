package cz.muni.fi.pa165.project.dto;

/**
 *
 * @author Jakub Mozucha | j.mozucha@gmail.com | created: 11/23/2015
 */
public class VehicleDTO {
    
    /**
     * Vehicle id.
     */
    private Long id;
    
    /**
     * Vehicle vin (17 characters long).
     */
    private String vin;
    
    /**
     * Brand of vehicle.
     */
    private String brand;
    
    /**
     * Model of vehicle.
     */
    private String model;
    
    /**
     * Type pf vehicle.
     */
    private String type;
    
    /**
     * Construction year of vehicle.
     */
    private int yearOfProduction;
    
    /**
     * Engine type of vehicle.
     */
    private String engineType;
    
    /**
     * Distance driven.
     */
    private Long mileage;
    
    /**
     * Service check interval in months.
     */
    private int serviceCheckInterval;
    
    /**
     * Maximum mileage for vehicle. When mileage is higher,
     * the vehicle is not visible in system. The lowest allowed
     * value is 100 000.
     */
    private Long maxMileage;

    /**
     * Getter for {@link #id}
     * 
     * @return {@link #id} of vehicle.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for {@link #id}.
     * 
     * @param id to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for {@link #vin}
     * 
     * @return {@link #vin} of vehicle.
     */
    public String getVin() {
        return vin;
    }

    /**
     * Setter for {@link #vin}.
     * 
     * @param vin to be set
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * Getter for {@link #brand}
     * 
     * @return {@link #brand} of vehicle.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for {@link #brand}.
     * 
     * @param brand to be set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for {@link #model}
     * 
     * @return {@link #model} of vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Setter for {@link #model}.
     * 
     * @param model to be set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Getter for {@link #type}
     * 
     * @return {@link #type} of vehicle.
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for {@link #type}.
     * 
     * @param type to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for {@link #yearOfProduction}
     * 
     * @return {@link #yearOfProduction} of vehicle.
     */
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    /**
     * Setter for {@link #yearOfProduction}.
     * 
     * @param yearOfProduction to be set
     */
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    /**
     * Getter for {@link #engineType}
     * 
     * @return {@link #engineType} of vehicle.
     */
    public String getEngineType() {
        return engineType;
    }

    /**
     * Setter for {@link #engineType}.
     * 
     * @param engineType to be set
     */
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    /**
     * Getter for {@link #mileage}
     * 
     * @return {@link #mileage} of vehicle.
     */
    public Long getMileage() {
        return mileage;
    }

    /**
     * Setter for {@link #mileage}.
     * 
     * @param mileage to be set
     */
    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    /**
     * Getter for {@link #serviceCheckInterval}
     * 
     * @return {@link #serviceCheckInterval} of vehicle.
     */
    public int getServiceCheckInterval() {
        return serviceCheckInterval;
    }

    /**
     * Setter for {@link #serviceCheckInterval}.
     * 
     * @param serviceCheckInterval to be set
     */
    public void setServiceCheckInterval(int serviceCheckInterval) {
        this.serviceCheckInterval = serviceCheckInterval;
    }

    /**
     * Getter for {@link #maxMileage}
     * 
     * @return {@link #maxMileage} of vehicle.
     */
    public Long getMaxMileage() {
        return maxMileage;
    }

    /**
     * Setter for {@link #maxMileage}.
     * 
     * @param maxMileage to be set
     */
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

        if (!(o instanceof VehicleDTO)) {
            return false;
        }

        final VehicleDTO vehicle = (VehicleDTO) o;
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
                + "id='" + id + '\''
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
