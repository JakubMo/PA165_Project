package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.entity.Drive;
import cz.muni.fi.pa165.project.facade.DriveFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

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

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model)
	{
		model.addAttribute("drives", this.driveFacade.findAll());
		return "drive/list";
	}

}
