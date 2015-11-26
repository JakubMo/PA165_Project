package cz.muni.fi.pa165.project.service;

import cz.muni.fi.pa165.project.dao.ServiceCheckDao;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.entity.Vehicle;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Implementation of {@link ServiceCheckService} interface. Provides implementation of the business logic.
 *
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 26.11.2015
 */
@Service
public class ServiceCheckServiceImpl implements ServiceCheckService {

	@Autowired
	private ServiceCheckDao serviceCheckDao;

	@Override
	public void createServiceCheck(ServiceCheck serviceCheck) throws DataAccessException {
		if(serviceCheck == null){
			throw new IllegalArgumentException("serviceCheck is null!");
		}

		this.serviceCheckDao.create(serviceCheck);
	}

	@Override
	public void deleteServiceCheck(ServiceCheck serviceCheck) throws DataAccessException {
		if(serviceCheck == null){
			throw new IllegalArgumentException("serviceCheck is null!");
		}

		this.serviceCheckDao.delete(serviceCheck);
	}

	@Override
	public void updateReport(ServiceCheck serviceCheck, String report) throws DataAccessException {
		if(serviceCheck == null){
			throw new IllegalArgumentException("serviceCheck is null!");
		}
		if(report == null){
			throw new IllegalArgumentException("report is null!");
		}

		serviceCheck.setReport(report);
		this.serviceCheckDao.update(serviceCheck);
	}

	@Override
	public void updateServiceCheckDate(ServiceCheck serviceCheck, Date serviceCheckDate) throws DataAccessException {
		if(serviceCheck == null){
			throw new IllegalArgumentException("serviceCheck is null!");
		}
		if(serviceCheckDate == null){
			throw new IllegalArgumentException("serviceCheckDate is null!");
		}

		serviceCheck.setServiceCheckDate(serviceCheckDate);
		this.serviceCheckDao.update(serviceCheck);
	}

	@Override
	public void updateServiceEmployee(ServiceCheck serviceCheck, String serviceEmployee) throws DataAccessException {
		if(serviceCheck == null){
			throw new IllegalArgumentException("serviceCheck is null!");
		}
		if(serviceEmployee == null){
			throw new IllegalArgumentException("serviceEmployee is null!");
		}

		serviceCheck.setServiceEmployee(serviceEmployee);
		this.serviceCheckDao.update(serviceCheck);
	}

	@Override
	public void updateServiceCheckStatus(ServiceCheck serviceCheck, ServiceCheckStatus serviceCheckStatus) throws DataAccessException {
		if(serviceCheck == null){
			throw new IllegalArgumentException("serviceCheck is null!");
		}
		if(serviceCheckStatus == null){
			throw new IllegalArgumentException("serviceCheckStatus is null!");
		}

		serviceCheck.setStatus(serviceCheckStatus);
		this.serviceCheckDao.update(serviceCheck);
	}

	@Override
	public ServiceCheck getById(Long id) throws DataAccessException {
		if(id == null){
			throw new IllegalArgumentException("id is null!");
		}

		return this.serviceCheckDao.get(id);
	}

	@Override
	public List<ServiceCheck> getAll() throws DataAccessException {
		return this.serviceCheckDao.getAll();
	}

	@Override
	public List<ServiceCheck> getAllByVehicle(Vehicle vehicle) throws DataAccessException {
		if(vehicle == null){
			throw new IllegalArgumentException("vehicle is null!");
		}
		return this.serviceCheckDao.getAllByVehicle(vehicle);
	}

	@Override
	public List<ServiceCheck> getAllByServiceCheckStatus(ServiceCheckStatus serviceCheckStatus) throws DataAccessException {
		if(serviceCheckStatus == null){
			throw new IllegalArgumentException("serviceCheckStatus is null!");
		}

		return this.serviceCheckDao.getAllByStatus(serviceCheckStatus);
	}

	@Override
	public List<ServiceCheck> getAllByTimeInterval(Date startTime, Date endTime) throws DataAccessException {
		if(startTime == null){
			throw new IllegalArgumentException("startTime is null!");
		}
		if(endTime == null){
			throw new IllegalArgumentException("endTime is null!");
		}
		if(endTime.before(startTime)){
			throw new IllegalArgumentException("endTime is before startTime!");
		}

		return this.serviceCheckDao.getAllByTimeInterval(startTime, endTime);
	}
}
