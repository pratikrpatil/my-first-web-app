package com.webapp.firstwebapp.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
//	@Autowired
//	private AuthenticationService authenticationService;
//	
//	@RequestMapping("login")
//	public String goToLoginPage(@RequestParam String name, ModelMap model) {
//		model.put("name", name);
//		return "login";
//	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String goToWelcomePage(ModelMap model) {
		
		model.put("name", getLoggedinUsername());
		return "welcome";
	}
	
	private String getLoggedinUsername() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return authentication.getName();
		
	}
	
//	@RequestMapping(value="login", method = RequestMethod.POST)
//	public String goToWelcomePage(@RequestParam String name,
//			@RequestParam String password,
//			ModelMap model) {
//		
//		if(authenticationService.authenticateUserCredentials(name, password)) {
//			model.put("name", name);
//			return "welcome";
//		}
//		
//		model.put("errorMsg", "Invalid Credetials ! Please try again.");
//		return "login";
//	}
}
