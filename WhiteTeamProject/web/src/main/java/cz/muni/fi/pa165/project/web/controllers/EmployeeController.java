package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.enums.Category;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import static cz.muni.fi.pa165.project.web.controllers.VehicleController.log;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(value= "/list",method = RequestMethod.GET)
    public String list(Model model, RedirectAttributes redirectAttributes){
        try{
            model.addAttribute("employees", employeeFacade.getAll());
        } catch (Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/";
        }
        return "employee/list";
    }
    
    @RequestMapping(value= "/new", method = RequestMethod.GET)
    public String newEmployee(Model model, RedirectAttributes redirectAttributes) {
        try{
            model.addAttribute("employeeRegister", new EmployeeDTO());
            String[] roles = {"USER", "ADMIN"};
            model.addAttribute("roles", roles);
            model.addAttribute("categories", Category.values());
        } catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
            return "redirect:/employee/list";
        }
        return "employee/new";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("employeeRegister") EmployeeDTO employeeDTO, 
                        @RequestParam(value = "password", required = true) String password,
                        BindingResult bindingResult,
                        Model model, 
                        RedirectAttributes redirectAttributes) {
        
        try{
            
            if(!bindingResult.hasErrors()) {
                EmployeeDTO oldEmp = null;
                try{
                   oldEmp = employeeFacade.getByEmail(employeeDTO.getEmail());
                }catch (DataAccessException ex) {
                    //found nothing == ok
                }
                
                if(oldEmp != null) {
                    model.addAttribute("email_error", "Employee with such email is already registered! Please register with different email.");
                    return "employee/new";
                }
                
                employeeFacade.register(employeeDTO, password);
                redirectAttributes.addFlashAttribute("alert_success", "New employee '" + employeeDTO.getEmail() + "' was registered.");
                
            } else {
                bindingResult.getGlobalErrors().stream().forEach((ObjectError oe) -> {
                    log.trace("ObjectError: {}", oe);
                });
                
                bindingResult.getFieldErrors().stream().forEach((FieldError fe) -> {
                    log.trace("FieldError: {}", fe);
                    model.addAttribute(fe.getField() + "_error", fe.getDefaultMessage());
                });
                return "employee/new";
            }
        } catch(Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
        }
        
        return "redirect:/employee/list";
    }
    
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
