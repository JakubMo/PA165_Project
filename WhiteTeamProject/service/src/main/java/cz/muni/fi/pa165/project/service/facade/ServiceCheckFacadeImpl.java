package cz.muni.fi.pa165.project.service.facade;

import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.project.service.BeanMappingService;
import cz.muni.fi.pa165.project.service.ServiceCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Date;

/**
 * Implementation of {@link ServiceCheckFacade} interface
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 25.11.2015
 */
@Service
@Transactional
public class ServiceCheckFacadeImpl implements ServiceCheckFacade {

	final static Logger log = LoggerFactory.getLogger(ServiceCheckFacadeImpl.class);

	@Inject
	private ServiceCheckService serviceCheckService;

	@Autowired
	private BeanMappingService beanMappingService;

	@Override
	public void createServiceCheck(ServiceCheckCreateDTO s) throws DataAccessException {
		if (s == null){
			throw new IllegalArgumentException("service check is null!");
		}

		ServiceCheck serviceCheck = this.beanMappingService.mapTo(s, ServiceCheck.class);
		this.serviceCheckService.createServiceCheck(serviceCheck);
	}

	@Override
	public void deleteServiceCheck(Long checkId) throws DataAccessException {
		if(checkId == null){
			throw new IllegalArgumentException("checkId is null!");
		}

		ServiceCheck serviceCheck = this.serviceCheckService.getById(checkId);
		this.serviceCheckService.deleteServiceCheck(serviceCheck);
	}

	@Override
	public void updateReport(Long checkId, String report) throws DataAccessException {
		if(checkId == null){
			throw new IllegalArgumentException("checkId is null!");
		}

		if(report == null){
			throw new IllegalArgumentException("report is null!");
		}

		ServiceCheck serviceCheck = this.serviceCheckService.getById(checkId);
		this.serviceCheckService.updateReport(serviceCheck, report);
	}

	@Override
	public void updateServiceCheckDate(Long checkId, Date serviceCheckDate) throws DataAccessException {
		if(checkId == null){
			throw new IllegalArgumentException("checkId is null!");
		}
		if(serviceCheckDate == null){
			throw new IllegalArgumentException("serviceCheckDate is null!");
		}

		ServiceCheck serviceCheck = this.serviceCheckService.getById(checkId);
		this.serviceCheckService.updateServiceCheckDate(serviceCheck, serviceCheckDate);
	}

	@Override
	public void updateServiceEmployee(Long checkId, String serviceEmployee) throws DataAccessException {
		if(checkId == null){
			throw new IllegalArgumentException("checkId is null!");
		}
		if(serviceEmployee == null){
			throw new IllegalArgumentException("serviceEmployee is null!");
		}

		ServiceCheck serviceCheck = this.serviceCheckService.getById(checkId);
		this.serviceCheckService.updateServiceEmployee(serviceCheck, serviceEmployee);
	}

	@Override
	public void updateServiceCheckStatus(Long checkId, ServiceCheckStatus status) throws DataAccessException {
		if(checkId == null){
			throw new IllegalArgumentException("checkId is null!");
		}
		if(status == null){
			throw new IllegalArgumentException("status is null!");
		}

		ServiceCheck serviceCheck = this.serviceCheckService.getById(checkId);
		this.serviceCheckService.updateServiceCheckStatus(serviceCheck, status);
	}

	@Override
	public Collection<ServiceCheckDTO> getAll() throws DataAccessException {
		return this.beanMappingService.mapTo(this.serviceCheckService.getAll(), ServiceCheckDTO.class);
	}

	@Override
	public Collection<ServiceCheckDTO> getAllByVehicle(VehicleDTO vehicleDTO) throws DataAccessException {
		if(vehicleDTO == null){
			throw new IllegalArgumentException("vehicleDTO is null!");
		}

		Vehicle vehicle = this.beanMappingService.mapTo(vehicleDTO, Vehicle.class);
		Collection<ServiceCheck> serviceChecks = this.serviceCheckService.getAllByVehicle(vehicle);
		return this.beanMappingService.mapTo(serviceChecks, ServiceCheckDTO.class);
	}

	@Override
	public Collection<ServiceCheckDTO> getAllByServiceCheckStatus(ServiceCheckStatus status) throws DataAccessException {
		if(status == null){
			throw new IllegalArgumentException("status is null!");
		}

		Collection<ServiceCheck> serviceChecks = this.serviceCheckService.getAllByServiceCheckStatus(status);
		return this.beanMappingService.mapTo(serviceChecks, ServiceCheckDTO.class);
	}

	@Override
	public Collection<ServiceCheckDTO> getAllByTimeInterval(Date startDate, Date endDate) throws DataAccessException {
		if(startDate == null){
			throw new IllegalArgumentException("starDate is null!");
		}
		if(endDate == null){
			throw new IllegalArgumentException("endDate is null!");
		}
		if(endDate.before(startDate)){
			throw new IllegalArgumentException("end date is before start date!");
		}

		Collection<ServiceCheck> serviceChecks = this.serviceCheckService.getAllByTimeInterval(startDate, endDate);
		return this.beanMappingService.mapTo(serviceChecks, ServiceCheckDTO.class);
	}

	@Override
	public ServiceCheckDTO get(Long id) throws DataAccessException {
		if(id == null){
			throw new IllegalArgumentException("id is null!");
		}

		ServiceCheck serviceCheck = this.serviceCheckService.getById(id);
		return this.beanMappingService.mapTo(serviceCheck, ServiceCheckDTO.class);
	}
}
