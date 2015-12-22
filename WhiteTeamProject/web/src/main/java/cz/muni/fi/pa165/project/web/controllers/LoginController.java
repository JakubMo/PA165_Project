package cz.muni.fi.pa165.project.web.controllers;

import cz.muni.fi.pa165.project.dto.EmployeeDTO;
import cz.muni.fi.pa165.project.facade.EmployeeFacade;
import static cz.muni.fi.pa165.project.web.controllers.VehicleController.log;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Provides the interface for home page
 * 
 * @author jakub
 */
@Controller
public class LoginController {
    
    final static Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    EmployeeFacade employeeFacade;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(Model model, RedirectAttributes redirectAttributes) {
        try{
            String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            EmployeeDTO employeeDTO = employeeFacade.getByEmail(principal);
            if(employeeDTO != null) {
                model.addAttribute("principal", employeeDTO);
            }
        } catch (Exception ex) {
            log.trace(ex.getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", ex.getLocalizedMessage());
        }
        return "index";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes redirectAttributes) {

        log.debug("login page mapping");
        
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null){
              new SecurityContextLogoutHandler().logout(request, response, auth);
            model.addObject("msg", "You've been logged out successfully.");
            }
        }

        model.setViewName("login");
        return model;

    }
    
    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        log.debug("403 page mapping");
        
        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }
}
