package cz.muni.fi.pa165.project.rest.controller;

import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Tomas Borcin | tborcin@redhat.com | created: 12/24/15.
 */
@RestController
@RequestMapping("/servicecheck")
public class ServiceCheckRestController {

	@Autowired
	private ServiceCheckFacade serviceCheckFacade;

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	ServiceCheckDTO getServiceCheck(@PathVariable Long id) {
		return serviceCheckFacade.get(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	Collection<ServiceCheckDTO> getServiceChecks() {
		return serviceCheckFacade.getAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	void createServiceCheck(@RequestBody ServiceCheckCreateDTO serviceCheckCreateDTO) {
		serviceCheckFacade.createServiceCheck(serviceCheckCreateDTO);
	}

	@RequestMapping(value = "/updatereport/{id}/{report}", method = RequestMethod.PUT)
	void updateServiceCheck(@PathVariable Long id, @PathVariable String report) {
		serviceCheckFacade.updateReport(id, report);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	void deleteServiceCheck(@PathVariable Long id) {
		serviceCheckFacade.deleteServiceCheck(id);
	}

	@RequestMapping(value = "/updateemployee/{id}/{employeeName}", method = RequestMethod.PUT)
	void updateEmployee(@PathVariable Long id, @PathVariable String employeeName) {
		serviceCheckFacade.updateServiceEmployee(id, employeeName);
	}
}
