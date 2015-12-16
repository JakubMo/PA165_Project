package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import javax.validation.Valid;
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

/**
 * Controller for administering vehicle.
 *
 * @author Marek
 */
@Controller
@RequestMapping("/vehicle")
public class VehicleController {
    
    final static Logger log = LoggerFactory.getLogger(VehicleController.class);
    
    @Autowired
    private VehicleFacade vehicleFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("vehicles", vehicleFacade.getAll());
        return "vehicle/list";
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("vehicle", vehicleFacade.getById(id));
        return "vehicle/detail";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newVehicle(Model model) {
        model.addAttribute("vehicleCreate", new VehicleCreateDTO());
        return "vehicle/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("vehicleCreate") VehicleCreateDTO vehicleCreateDTO, BindingResult bindingResult, 
                            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
        if(!bindingResult.hasErrors()) {
            vehicleFacade.createVehicle(vehicleCreateDTO);
            redirectAttributes.addFlashAttribute("alert_success", "Vehicle was created.");
        }
        else {
            bindingResult.getGlobalErrors().stream().forEach((ObjectError oe) -> {
                log.trace("ObjectError: {}", oe);
            });
            
            bindingResult.getFieldErrors().stream().forEach((FieldError fe) -> {
                log.trace("FieldError: {}", fe);
                model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
            });
            return "vehicle/new";
        }
        
        return "rediret:" + uriComponentsBuilder.path("/vehicle/list").toUriString();
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
        vehicleFacade.deleteVehicle(id);
        redirectAttributes.addFlashAttribute("alert_success", "Vehicle with ID " + id + " was deleted.");
        return "redirect:" + uriComponentsBuilder.path("vehicle/list").toUriString();
    }
    
    /*@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("vehicleEdit") VehicleDTO vehicleDTO, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
        
    }*/
}
