package com.umang;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.umang.dao.Diseasedao;
import com.umang.dao.Medicinedao;
import com.umang.dao.Userdao;
import com.umang.model.Feedback;
import com.umang.model.Medicine;
import com.umang.model.Orders;
import com.umang.model.User;


@Controller
public class LoginController {
	
	@Autowired
	public Userdao userdao;
	
	@Autowired
	public Diseasedao diseasedao;
	
	@Autowired
	public Medicinedao medicinedao;
	
	
	
	@RequestMapping("/admin")
	public String admin(Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		model.addAttribute("user", loggedInUserName);
		model.addAttribute("name", "Spring Security Custom Login Demo");
		model.addAttribute("description", "Protected page !");
		return "admin";
	}
	
	@RequestMapping("/user")
	public String user(Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		model.addAttribute("user", loggedInUserName);
		model.addAttribute("name", "Spring Security USER Custom Login Demo");
		model.addAttribute("description", "Protected page for user !");
		return "user";
	}
	
	
	@RequestMapping("/emp")
	public String emp(Model model, Principal principal) {

		String loggedInUserName = principal.getName();
		model.addAttribute("user", loggedInUserName);
		model.addAttribute("name", "Spring Security USER Custom Login Demo");
		model.addAttribute("description", "Protected page for user !");
		return "emp";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,ModelMap model,@AuthenticationPrincipal Principal principal,
			Authentication authentication,
			@ModelAttribute("error1") String error1,
			@ModelAttribute("error2") String error2,
			@ModelAttribute("error3") String error3,
			@ModelAttribute("message") String message) {
	
		
		if(principal!=null)
		{
			String CurrentUser = principal.getName();
			Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			if(CurrentUser!=null)
			{
		        if (authorities.contains("ROLE_ADMIN")) 
		        {
		    		String loggedInUserName = principal.getName();
		    		model.addAttribute("user", loggedInUserName);
		    		model.addAttribute("users",userdao.getAllEmp());
		    		model.addAttribute("firstname",userdao.getCurrentEmp(loggedInUserName).get(0).getFirstname());
		    		model.addAttribute("lastname",userdao.getCurrentEmp(loggedInUserName).get(0).getLastname());
		    		model.addAttribute("error1",error1);
		    		model.addAttribute("error2",error2);
		    		model.addAttribute("name", "Spring Security Custom Login Demo");
		    		model.addAttribute("description", "Protected page !");
		        	return "/admin";
		        } 
		        else if (authorities.contains("ROLE_USER")) 
		        {
		    		String loggedInUserName = principal.getName();
		    		model.addAttribute("user", loggedInUserName);
		    		model.addAttribute("orders", new Orders());
		    		model.addAttribute("feedback", new Feedback());
		    		model.addAttribute("name", "Spring Security USER Custom Login Demo");
		    		model.addAttribute("description", "Protected page for user !");
		    		model.addAttribute("firstname",userdao.getCurrentUser(loggedInUserName).get(0).getFirstname());
		    		model.addAttribute("lastname",userdao.getCurrentUser(loggedInUserName).get(0).getLastname());
		    		model.addAttribute("medicines",medicinedao.getAllMedicine());
		    		return "/user";
		        } 
		        else if(authorities.contains("ROLE_EMP"))
		        {
		        	
		        	
		    		List<Medicine> medicine_id = medicinedao.getExpiredMedicineId();
		    		if(medicine_id!=null && medicine_id.size()!=0)
		    		{
		    			medicinedao.updateStockToZero(medicine_id);
		    		}
		    		List<String> med_name = new ArrayList<String> ();
		    		for(int i=0;i<medicine_id.size();i++)
		    		{
		    			med_name.add(medicine_id.get(i).getName());
		    		}
		        	
		        	
		        	
		    		String loggedInUserName = principal.getName();
		    		model.addAttribute("firstname",userdao.getCurrentEmp(loggedInUserName).get(0).getFirstname());
		    		model.addAttribute("lastname",userdao.getCurrentEmp(loggedInUserName).get(0).getLastname());
		    		model.addAttribute("user", loggedInUserName);
		    		model.addAttribute("diseases",diseasedao.getAllDiseases());
		    		model.addAttribute("error1",error1);
		    		model.addAttribute("medicines",medicinedao.getAllMedicine());
		    		model.addAttribute("error2",error2);
		    		model.addAttribute("error3",error3);
		    		model.addAttribute("expiredMedicines",med_name);
		    		model.addAttribute("users",userdao.getAllEmp());
		    		model.addAttribute("name", "Spring Security USER Custom Login Demo");
		    		model.addAttribute("description", "Protected page for user !");
		        	return "/emp";
		        }
		        else
		        {
		            throw new IllegalStateException();
		        }
			}
			else
			{
				model.addAttribute("message",message);
				return "login";
			}
		}
		else
		{
			return "login";
		}
		
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/loginError", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";

	}
	
	
    @RequestMapping(value="/forgetPassword", method=RequestMethod.GET)
    public String forgetPassword(Model model) {
        return "forgetPassword";
    }
    
    
    @RequestMapping(value="/forgetPassword", method=RequestMethod.POST)
    public String forgetPassword1(Model model,HttpServletRequest request) {
    	
    	int cnt = userdao.checkEmailAndUsername(request.getParameter("email"),request.getParameter("username"));
        if (cnt==1 ) {
        	
        	String tokken = UUID.randomUUID().toString();
        	
        	userdao.updateToken(request.getParameter("username"),tokken);
        	
        	
    		ApplicationContext context =new ClassPathXmlApplicationContext("Spring-Mail.xml");
           	MailMail mm = (MailMail) context.getBean("mailMail");
          
           	String mess = "To complete the password reset process, please click here:"+"http://localhost:8080/MedicalCompany/confirm-reset?token="+tokken;
            mm.sendMail("umangjain5000@gmail.com",
           		   request.getParameter("email"),
           		   "Complete Password Reset!", 
           		   mess);
               
        

            model.addAttribute("message", "Request to reset password received. Check your inbox for the reset link.");
            return "redirect:/login";
        } else 
        {
        	model.addAttribute("message", "This email address does not exist!");
        	return "forgetPassword";
        }
    }
    
    
    
    
    
    @RequestMapping(value="/confirm-reset", method= {RequestMethod.GET, RequestMethod.POST})
    public String validateResetToken(Model model,@RequestParam("token") String Token) {
    	User user = userdao.getUserByToken(Token);

        if (user != null) {
        	model.addAttribute("user",user);
        	model.addAttribute("username",user.getUsername());
        	return "reset-password";
        } 
        else 
        {
        	model.addAttribute("message", "The link is invalid or broken!");
        	return "redirect:/login";
        }
    }

    @RequestMapping(value = "/reset-password", method = RequestMethod.POST)
    public String resetUserPassword(Model model,HttpServletRequest request,@Valid @ModelAttribute("user") User user) {
        if (user.getUsername() != null) 
        {
        	if(user.getPassword().equals(user.getMpassword()))
        	{
        		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        		userdao.updatePassword(user.getUsername(),passwordEncoder.encode(user.getPassword()));
                model.addAttribute("message", "Password successfully reset. You can now log in with the new credentials.");
                return "redirect:/login";

        	}
        	else
        	{
        		System.out.println(user.getPassword());
        		System.out.println(user.getMpassword());
            	model.addAttribute("user",user);
            	model.addAttribute("username",user.getUsername());
            	model.addAttribute("message", "Password do not match");
            	return "reset-password";
        	}
        	

        } 
        else 
        {
        	model.addAttribute("message", "The link is invalid or broken!");
        	return "redirect:/login";
        }
    }
    
    
    
    
    
	
    
	
}
