package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import static cz.muni.fi.pa165.project.web.controllers.VehicleController.log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for employee pages administration
 * @author jakub
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeFacade employeeFacade;
    
    @RequestMapping(value = "/detail/principal", method = RequestMethod.GET)
    public String detailPrincipal(Model model, RedirectAttributes redirectAttributes) {
        try{
           String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
           EmployeeDTO employeeDTO = employeeFacade.getByEmail(principal);
           model.addAttribute("employee", employeeDTO);
        } catch (Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/";
        }
        
        return "employee/detail";
    } 
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        try{
           EmployeeDTO employeeDTO = employeeFacade.getById(id);
           model.addAttribute("employee", employeeDTO);
        } catch (Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/";
        }
        
        return "employee/detail";
    } 
}
