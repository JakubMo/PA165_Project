package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.VehicleCreateDTO;
import cz.muni.fi.pa165.project.dto.VehicleDTO;
import cz.muni.fi.pa165.project.facade.DriveFacade;
import cz.muni.fi.pa165.project.facade.ServiceCheckFacade;
import cz.muni.fi.pa165.project.facade.VehicleFacade;
import cz.muni.fi.pa165.project.util.DataAccessExceptionImpl;
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
    
    @Autowired
    private ServiceCheckFacade serviceCheckFacade;
    
    @Autowired
    private DriveFacade driveFacade;
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("vehicles", vehicleFacade.getAll());
        }
        catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/";
        }
        
        return "vehicle/list";
    }
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            VehicleDTO vehicle = vehicleFacade.getById(id);
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("serviceChecks", serviceCheckFacade.getAllByVehicle(vehicle));
            model.addAttribute("drives", driveFacade.findAllByVehicle(vehicle));
        }
        catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/vehicle/list";
        }
        
        return "vehicle/detail";
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newVehicle(Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("vehicleCreate", new VehicleCreateDTO());
        }
        catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/vehicle/list";
        }
        
        return "vehicle/new";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("vehicleCreate") VehicleCreateDTO vehicleCreateDTO, BindingResult bindingResult, 
                            Model model, RedirectAttributes redirectAttributes) {
        try {
            if(!bindingResult.hasErrors()) {
                VehicleDTO vehicle = null;
                try {
                    vehicle = vehicleFacade.getByVin(vehicleCreateDTO.getVin());
                }
                catch(DataAccessExceptionImpl ex) {
                    // OK
                }
                if(vehicle != null) {
                    model.addAttribute("vin_error", "Vehicle with such VIN already exists (ID " + vehicle.getId() + ").");
                    return "vehicle/new";
                }
                
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
        }
        catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
        }
        
        return "redirect:/vehicle/list";
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            vehicleFacade.deleteVehicle(id);
            redirectAttributes.addFlashAttribute("alert_success", "Vehicle with ID " + id + " was deleted.");
        }
        catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
        }
        
        return "redirect:/vehicle/list";
    }
    
    /*@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("vehicleEdit") VehicleDTO vehicleDTO, BindingResult bindingResult,
                        Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriComponentsBuilder) {
        
    }*/
    
    @RequestMapping(value = "/*")
    public String notFound(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alert_warning", "Requested page not found.");
        return "redirect:/vehicle/list";
    }
}
