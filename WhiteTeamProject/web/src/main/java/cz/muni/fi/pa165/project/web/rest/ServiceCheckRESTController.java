package cz.muni.fi.pa165.project.web.rest;

import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.service.ServiceCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;

/**
 * @author Tomas Borcin | tborcin@redhat.com | created: 12/18/15.
 */
@Controller
@RequestMapping("/rest/servicecheck")
public class ServiceCheckRESTController {

	@Autowired
	private View jsonView;

	@Autowired
	@Qualifier(value = "serviceCheckService")

	private ServiceCheckService serviceCheckService;
	private static final String DATA_FIELD = "serviceCheck";
	private static final String LIST_FIELD = "listData";

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView getTour(@PathVariable("id") String serviceCheckId) {
		Long id;
		ServiceCheck serviceCheck;
		id = Long.parseLong(serviceCheckId);
		serviceCheck = serviceCheckService.getById(id);
		if (serviceCheck == null) {
			throw new IllegalArgumentException();
		}
		return new ModelAndView(jsonView, DATA_FIELD, serviceCheck);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView getAllTours() {
		List<ServiceCheck> serviceChecks;
		serviceChecks = serviceCheckService.getAll();
		return new ModelAndView(jsonView, LIST_FIELD, serviceChecks);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public ModelAndView createTour(@RequestBody ServiceCheck serviceCheck) {
		ServiceCheck createdServiceCheck;
		serviceCheckService.createServiceCheck(serviceCheck);
		createdServiceCheck = serviceCheckService.getById(serviceCheck.getId());
		return new ModelAndView(jsonView, DATA_FIELD, createdServiceCheck);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView updateTour(@RequestBody ServiceCheck serviceCheck) {
		ServiceCheck updatedServiceCheck;
		serviceCheckService.updateServiceEmployee(serviceCheck, serviceCheck.getServiceEmployee());
		updatedServiceCheck = serviceCheckService.getById(serviceCheck.getId());
		return new ModelAndView(jsonView, DATA_FIELD, updatedServiceCheck);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public ModelAndView deleteTour(@PathVariable("id") String serviceCheckId) {
		Long id = Long.parseLong(serviceCheckId);
		ServiceCheck serviceCheck = serviceCheckService.getById(id);
		if (serviceCheck == null) {
			throw new IllegalArgumentException();
		}
		serviceCheckService.deleteServiceCheck(serviceCheck);
		return new ModelAndView(jsonView, DATA_FIELD, null);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Service check not found")
	public void notFound() {
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Error during DB access")
	public void notAccessible() {
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ID can't be parsed")
	public void badFormat() {
	}
}