package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.*;
import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.enums.DriveStatus;
import cz.muni.fi.pa165.project.facade.DriveFacade;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

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
	public String list(Model model)
	{
		model.addAttribute("drives", this.driveFacade.findAll());
		return "drive/list";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newDrive(Model model, RedirectAttributes redirectAttributes)
	{
		try{
			DriveCreateDTO driveCreateDTO = new DriveCreateDTO();
			driveCreateDTO.setDriveStatus(DriveStatus.REQUESTED);
			//TODO: set logged employee
			Collection<EmployeeDTO> empl = this.employeeFacade.getAll();
			if(empl.size() > 0) {
				driveCreateDTO.setEmployee(empl.iterator().next());
			}
			model.addAttribute("driveCreate", driveCreateDTO);
			model.addAttribute("vehicles", this.vehicleFacade.getAll());
		} catch (Exception e) {
			log.trace(e.getMessage());
			redirectAttributes.addFlashAttribute("alert_danger", e.getLocalizedMessage());
		}

		return "drive/new";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable long id, Model model)
	{
		model.addAttribute("drive", this.driveFacade.findById(id));
		return "drive/detail";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model) {
		model.addAttribute("drive", this.driveFacade.findById(id));
		return "drive/detail";
	}

	@RequestMapping(value = "/complete/{id}", method = RequestMethod.POST)
	public String complete(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {

		model.addAttribute("drive", this.driveFacade.findById(id));
		redirectAttributes.addFlashAttribute("alert_success", "The drive was completed");
		return "drive/detail";
	}

	@RequestMapping(value = "/cancel/{id}", method = RequestMethod.POST)
	public String cancel(@PathVariable long id, RedirectAttributes redirectAttributes, Model model) {
		this.driveFacade.cancelDrive(id);
		redirectAttributes.addFlashAttribute("alert_success", "The drive was canceled");
		model.addAttribute("drive", this.driveFacade.findById(id));
		return "drive/detail";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("driveCreate") DriveCreateDTO driveCreateDTO, BindingResult bindingResult,
						 Model model, RedirectAttributes redirectAttributes) {
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

}
