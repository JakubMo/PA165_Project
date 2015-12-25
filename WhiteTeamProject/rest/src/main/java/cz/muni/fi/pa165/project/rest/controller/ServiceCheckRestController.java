package cz.muni.fi.pa165.project.rest.controller;

import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.ServiceCheckDTO;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

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

	@RequestMapping(value = "/updateemployee/{id}/{employeeName}", method = RequestMethod.PUT)
	void updateEmployee(@PathVariable Long id, @PathVariable String employeeName) {
		serviceCheckFacade.updateServiceEmployee(id, employeeName);
	}

	@RequestMapping(value = "/updatedate/{id}/{date}", method = RequestMethod.PUT)
	void updateDate(@PathVariable Long id, @PathVariable String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		serviceCheckFacade.updateServiceCheckDate(id, df.parse(date));
	}

	@RequestMapping(value = "/updatestatus/{id}/{status}", method = RequestMethod.PUT)
	void updateStatus(@PathVariable Long id, @PathVariable ServiceCheckStatus status) {
		serviceCheckFacade.updateServiceCheckStatus(id, status);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	void deleteServiceCheck(@PathVariable Long id) {
		serviceCheckFacade.deleteServiceCheck(id);
	}
}
