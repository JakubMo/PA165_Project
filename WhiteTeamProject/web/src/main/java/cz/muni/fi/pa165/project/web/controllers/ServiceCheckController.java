package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.ServiceCheckCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.entity.ServiceCheck;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.enums.ServiceCheckStatus;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
import cz.muni.fi.pa165.project.web.utils.EmployeeDTOEditor;
import cz.muni.fi.pa165.project.web.utils.VehicleDTOEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	@Inject
	private VehicleDTOEditor vehicleDTOEditor;

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
		binder.registerCustomEditor(VehicleDTO.class, vehicleDTOEditor);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, RedirectAttributes redirectAttributes) {
		log.info("Listing service checks");
		try {
			model.addAttribute("services", serviceCheckFacade.getAll());
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
			return "redirect:/";
		}
		return "servicecheck/list";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		log.info("Detail of service check with id: " + id);
		try {
			model.addAttribute("serviceCheck", serviceCheckFacade.get(id));
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
			return "redirect:/servicecheck/list";
		}
		return "servicecheck/detail";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newVehicle(Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		log.info("New service check");
		try {
			model.addAttribute("statuses", ServiceCheckStatus.values());
			model.addAttribute("vehicles", vehicleFacade.getAll());
			model.addAttribute("servicecheckCreate", new ServiceCheckCreateDTO());
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
			return "redirect:" + uriComponentsBuilder.path("/servicecheck/new").toUriString();
		}
		return "servicecheck/new";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("serviceCheckCreate") ServiceCheckCreateDTO serviceCheckCreateDTO, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		log.info("Creating service check");
		try{
			if(!bindingResult.hasErrors()) {
				serviceCheckFacade.createServiceCheck(serviceCheckCreateDTO);
				redirectAttributes.addFlashAttribute("alert_success", "Service check was created");
			} else {
				bindingResult.getGlobalErrors().stream().forEach((ObjectError oe) -> {
					log.trace("ObjectError: {}", oe);
				});

				bindingResult.getFieldErrors().stream().forEach((FieldError fe) -> {
					log.trace("FieldError: {}", fe);
					redirectAttributes.addFlashAttribute("alert_danger", "invalid " + fe.getField());
				});
				return "redirect:" + uriComponentsBuilder.path("/servicecheck/new").toUriString();
			}
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
			return "redirect:" + uriComponentsBuilder.path("/servicecheck/new").toUriString();
		}
		return "redirect:" + uriComponentsBuilder.path("/servicecheck/list").toUriString();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		log.info("Deleting service check with id: " + id);
		try {
			serviceCheckFacade.deleteServiceCheck(id);
			redirectAttributes.addFlashAttribute("alert_success", "Service check with ID " + id + " was deleted.");
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
		}
		return "redirect:/servicecheck/list";
	}
}
