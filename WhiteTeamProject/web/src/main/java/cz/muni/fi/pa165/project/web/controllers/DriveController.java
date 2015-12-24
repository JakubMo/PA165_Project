package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.*;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.facade.DriveFacade;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

/**
 * SpringMVC Controller for drives
 * @author Mario Kudolani | mariok@mail.muni.cz | created: 26.11.2015
 */
@Controller
@RequestMapping("/drive")
public class DriveController {

	final static Logger log = LoggerFactory.getLogger(DriveController.class);

	@Inject
	private DriveFacade driveFacade;

	@Inject
	private VehicleFacade vehicleFacade;

	@Inject
	private EmployeeFacade employeeFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, RedirectAttributes redirectAttributes)
	{
		log.trace("list()");
		try {
			EmployeeDTO employeeDTO = this.getLoggedInEmployee();
			model.addAttribute("drives", this.driveFacade.findAllByEmployee(employeeDTO));
			model.addAttribute("actualDate", Date.from(Instant.now()));
			model.addAttribute("showAdminButtons", false);
		} catch (Exception ex){
			log.trace(ex.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
			return "redirect:/";
		}
		return "drive/list";
	}

	@RequestMapping(value = "/list/all", params = "requested", method = RequestMethod.GET)
	public String listAllRequested(@RequestParam("requested") Object requested, Model model, RedirectAttributes redirectAttributes)
	{
		log.trace("listAllRequested()");
		try{
			model.addAttribute("actualDate", Date.from(Instant.now()));
			model.addAttribute("drives", this.driveFacade.findAllByStatus(DriveStatus.REQUESTED));
			model.addAttribute("showAdminButtons", true);
			return "/drive/list";
		} catch (Exception ex) {
			log.trace(ex.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/list/all", method = RequestMethod.GET)
	public String listAll(Model model, RedirectAttributes redirectAttributes)
	{
		log.trace("listAll()");
		try{
			model.addAttribute("drives", this.driveFacade.findAll());
			model.addAttribute("actualDate", Date.from(Instant.now()));
			model.addAttribute("showAdminButtons", true);
			return "drive/list";
		} catch (Exception ex) {
			log.trace(ex.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
			return "redirect:/";
		}
	}


	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newDrive(Model model, RedirectAttributes redirectAttributes)
	{
		log.trace("new()");
		try{
			DriveCreateDTO driveCreateDTO = new DriveCreateDTO();
			driveCreateDTO.setDriveStatus(DriveStatus.REQUESTED);

			EmployeeDTO employeeDTO = this.getLoggedInEmployee();
			driveCreateDTO.setEmployee(employeeDTO);
			model.addAttribute("driveCreate", driveCreateDTO);
			model.addAttribute("vehicles", this.vehicleFacade.getAll());
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
			return "redirect:/drive/list";
		}

		return "drive/new";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes)
	{
		log.trace("detail()");
		try {
			model.addAttribute("drive", this.driveFacade.findById(id));
		} catch (Exception ex){
			log.trace(ex.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
			return "redirect:/drive/list";
		}
		return "drive/detail";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
		log.trace("edit()");
		try {
			model.addAttribute("drive", this.driveFacade.findById(id));
		} catch (Exception ex){
			log.trace(ex.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
			return "redirect:/drive/list";
		}
		return "drive/detail";
	}

	@RequestMapping(value = "/complete/{id}", method = RequestMethod.POST)
	public String complete(@PathVariable long id, @ModelAttribute("km")BigDecimal km, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		log.trace("complete()");
		this.driveFacade.changeEndDate(id, Date.from(Instant.now()));
		this.driveFacade.changeDrivenKilometers(id, km);
		this.driveFacade.completeDrive(id);
		return "redirect:" + uriComponentsBuilder.path("/drive/list").toUriString();
	}

	@RequestMapping(value = "/complete/{id}", method = RequestMethod.GET)
	public String complete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes)
	{
		log.trace("complete()");
		try {
			model.addAttribute("drive", this.driveFacade.findById(id));
			return "drive/complete";
		} catch (Exception ex) {
			log.trace(ex.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
			return "/drive/list";
		}
	}

	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
	public String cancel(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
		log.trace("cancel()");
		this.driveFacade.cancelDrive(id);
		return "redirect:" + uriComponentsBuilder.path("/drive/list").toUriString();
	}

	@RequestMapping(value = "/approve/{id}", method = RequestMethod.POST)
	public String approve(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder)
	{
		log.trace("approve()");
		this.driveFacade.approveDrive(id);
		return "redirect:" + uriComponentsBuilder.path("/drive/list/all").toUriString();
	}

	@RequestMapping(value = "/reject/{id}", method = RequestMethod.POST)
	public String reject(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder)
	{
		log.trace("reject()");
		this.driveFacade.cancelDrive(id);
		return "redirect:" + uriComponentsBuilder.path("/drive/list").toUriString();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("driveCreate") DriveCreateDTO driveCreateDTO, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes) {
		log.trace("create()");
		try{
			if(!bindingResult.hasErrors()) {
				this.driveFacade.createDrive(driveCreateDTO);
				redirectAttributes.addFlashAttribute("alert_success", "Drive was created");
			} else {
				bindingResult.getGlobalErrors().stream().forEach((ObjectError oe) -> {
					log.trace("ObjectError: {}", oe);
				});

				bindingResult.getFieldErrors().stream().forEach((FieldError fe) -> {
					log.trace("FieldError: {}", fe);
					model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
				});
				return "vehicle/new";
			}
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
		}

		return "redirect:drive/list";
	}

	/**
	 * Returns logged employee dto
	 * @return {@link EmployeeDTO} of logged user
	 */
	private EmployeeDTO getLoggedInEmployee()
	{
		String loggedUserPrincipal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return this.employeeFacade.getByEmail(loggedUserPrincipal);
	}

}
