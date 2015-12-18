package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Service check controller.
 *
 * @author Tomas Borcin | tborcin@redhat.com | created: 12/17/15.
 */
@Controller
@RequestMapping("/servicecheck")
public class ServiceCheckController {

	final static Logger log = LoggerFactory.getLogger(ServiceCheckController.class);

	@Autowired
	private ServiceCheckFacade serviceCheckFacade;

	@Autowired
	private VehicleFacade vehicleFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("services", serviceCheckFacade.getAll());
		log.info("Model: " + model);
		return "servicecheck/list";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id, Model model) {
		model.addAttribute("serviceCheck", serviceCheckFacade.get(id));
		return "servicecheck/detail";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newVehicle(Model model) {
		model.addAttribute("statuses", DriveStatus.values());
		model.addAttribute("vehicles", vehicleFacade.getAll());
		model.addAttribute("servicecheckCreate", new ServiceCheckCreateDTO());
		return "servicecheck/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("serviceCheckCreate") ServiceCheckCreateDTO serviceCheckCreateDTO, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		if (!bindingResult.hasErrors()) {
			serviceCheckFacade.createServiceCheck(serviceCheckCreateDTO);
			redirectAttributes.addFlashAttribute("alert_success", "Service check was created.");
		} else {
			bindingResult.getGlobalErrors().stream().forEach((ObjectError oe) -> {
				log.trace("ObjectError: {}", oe);
			});

			bindingResult.getFieldErrors().stream().forEach((FieldError fe) -> {
				log.trace("FieldError: {}", fe);
				model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
			});
			return "servicecheck/new";
		}

		return "redirect:" + uriComponentsBuilder.path("/servicecheck/list").toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		serviceCheckFacade.deleteServiceCheck(id);
		redirectAttributes.addFlashAttribute("alert_success", "Service check with ID " + id + " was deleted.");
		return "redirect:" + uriComponentsBuilder.path("/servicecheck/list").toUriString();
	}
}
