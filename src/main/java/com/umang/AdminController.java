package com.umang;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umang.dao.Addressdao;
import com.umang.dao.Diseasedao;
import com.umang.dao.Feedbackdao;
import com.umang.dao.Managesdao;
import com.umang.dao.Partnerdao;
import com.umang.dao.Patientdao;
import com.umang.dao.Userdao;
import com.umang.model.Address;
import com.umang.model.Feedback;
import com.umang.model.Partner;
import com.umang.model.Patient;
import com.umang.model.User;



@Controller
public class AdminController {
	
	
	@Autowired
	public Userdao userdao;
	@Autowired
	public Addressdao addressdao;
	@Autowired
	public Managesdao managesdao;
	@Autowired
	public Diseasedao diseasedao;
	@Autowired
	public Partnerdao partnerdao;
	@Autowired
	public Patientdao patientdao;
	@Autowired
	public Feedbackdao feedbackdao;
	
	@RequestMapping(value="/admin/addEmp",method=RequestMethod.GET)
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registerEmp";
	}
	
	@RequestMapping(value="/admin/addEmp",method=RequestMethod.POST)
	public String registerProcess(@Valid @ModelAttribute("user") User user,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "registerEmp";
		}
		else {
				if(!user.getPassword().equals(user.getMpassword())) {
					model.addAttribute("error","passwords dont match");
					return "registerEmp";
				}
				if(userdao.getUser(user.getUsername())!=null) {
					model.addAttribute("error", "username exists");
					return "registerEmp";
				}
				if(user.getJoining_date().compareTo(user.getDOB())<0){
					model.addAttribute("error", "joining date can't be before birth");
					return "registerEmp";
				}
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userdao.registerEmp(user);
				return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/admin/showManagerForm",method=RequestMethod.GET)
	public String addManagerPage(Model model)
	{
		List<User> users = userdao.getAllEmp();
		model.addAttribute("users",users);
		return "showAddManager";
	}
	
	@RequestMapping(value="/admin/addManager",method=RequestMethod.GET)
	public String addManager(Model model,HttpServletRequest request,HttpServletResponse response)
	{
		String manager_id = request.getParameter("manager_id");
		String emp_id     = request.getParameter("emp_id");

		if(manager_id==null || emp_id==null)
		{
			List<User> users = userdao.getAllEmp();
			model.addAttribute("users",users);
			model.addAttribute("error", "select both manager and employee");
			return "showAddManager";
		}
		
		if(manager_id.equals(emp_id))
		{
			List<User> users = userdao.getAllEmp();
			model.addAttribute("users",users);
			model.addAttribute("error", "One can't be Manager of itself");
			return "showAddManager";
		}
		
		User if_existing_manager = managesdao.getManager(emp_id);
		if(if_existing_manager!=null)
		{
			List<User> users = userdao.getAllEmp();
			model.addAttribute("users",users);
			model.addAttribute("error", "Employee "+ emp_id +" is already Managed By "+if_existing_manager.getUsername());
			return "showAddManager";
		}
		
		managesdao.addManager(manager_id, emp_id);
		return "redirect:/login";
	}
	
	
	@RequestMapping(value="/admin/findManagerForEmp",method=RequestMethod.GET)
	public String findMangerForEmp(Model model,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
	{
		String emp_id = request.getParameter("emp_id");
		/* System.out.println(emp_id); */

		if(emp_id==null)
		{
			redirectAttributes.addFlashAttribute("error1","select Some Employee");
			return "redirect:/login";
		}
		
		List<User> managers = new ArrayList<User> ();
		User manager_id = managesdao.getManager(emp_id);
		managers.add(manager_id);
		model.addAttribute("users",managers);
		return "allemps";
	}
	
	
	@RequestMapping(value="/admin/findEmpForManager",method=RequestMethod.GET)
	public String findEmpForManager(Model model,HttpServletRequest request,HttpServletResponse response,RedirectAttributes redirectAttributes)
	{
		String manager_id = request.getParameter("manager_id");

		if(manager_id==null)
		{
			redirectAttributes.addFlashAttribute("error2","select Some Employee");
			return "redirect:/login";
		}
		
		List<User> emps =  managesdao.getManagesEmp(manager_id);
		model.addAttribute("users",emps);
		return "allemps";
	}
	
	
	@RequestMapping(value="/admin/viewAllEmp",method=RequestMethod.GET)
	public String viewAllEmp(Model model)
	{
		List<User> users = userdao.getAllEmp();
		model.addAttribute("users",users);
		model.addAttribute("role","admin");
		return "allemps";
	}
	
	@RequestMapping(value="/admin/viewAllEmpSorted",method=RequestMethod.GET)
	public String viewAllEmpSorted(Model model,HttpServletRequest request,HttpServletResponse response)
	{
		String income = request.getParameter("income");
		String joiningdate = request.getParameter("joining_date");
		
		List<User> users = new ArrayList<User>();
		if( income!=null && joiningdate!=null && income.equals("true") && joiningdate.equals("true") ){
			users = userdao.getEmpSortedByIncomeAndJoining();
		}
		else if(income!=null  && income.equals("true")){
			users = userdao.getEmpSortedByIncome();
		}
		else if(joiningdate!=null && joiningdate.equals("true")){
			users = userdao.getEmpSortedByJoining();
		}
		else{
			users = userdao.getAllEmp();
		}
		model.addAttribute("users",users);
		model.addAttribute("role","admin");
		return "allemps";
	}
	
	
	
	@RequestMapping(value="/admin/findAddress/{add_id}",method=RequestMethod.GET)
	public String findAddressByID(Model model,@PathVariable("add_id") int add_id)
	{
		Address add  = addressdao.getAddressById(add_id);
		List<Address> adds  = new ArrayList<Address> ();
		adds.add(add);
		model.addAttribute("adds",adds);
		model.addAttribute("who","emp");
		return "showAddress";
	}
	
	

	
	
	@RequestMapping(value="/admin/showMostValuablePartners",method=RequestMethod.GET)
	public String showMostValuablePartners(Model model) {
		List<Partner> partners = partnerdao.getMostValuablePartnersSorted();
		for(int i=0;i<partners.size();i++)
		{
			Partner partner = partners.get(i);
			partner.setTotal(partnerdao.getTotalBillOfPartner(partner.getId()).get(0));
		}
		model.addAttribute("partners",partners);
		model.addAttribute("showTotal","yes");
		return "showPartners";
	}
	
	
	@RequestMapping(value="/admin/showMostValuablePatients",method=RequestMethod.GET)
	public String showMostValuablePatients(Model model) {
		List<Patient> patients = patientdao.getMostValuablePatientSorted();
		for(int i=0;i<patients.size();i++)
		{
			Patient patient = patients.get(i);
			patient.setTotal(patientdao.getTotalBillOfPatient(patient.getId()).get(0));
		}
		model.addAttribute("patients",patients);
		model.addAttribute("showTotal","yes");
		return "showPatients";
	}
	
	@RequestMapping(value="/admin/deleteEmployee/{username}",method=RequestMethod.GET)
	public String deleteEmployee(Model model,@PathVariable("username") String username)
	{
		userdao.delete(username);
		return "redirect:/admin/viewAllEmp";
	}
	
	
	@RequestMapping(value="/admin/updateEmployee/{username}",method=RequestMethod.GET)
	public String updateEmployee(Model model,@PathVariable("username") String username)
	{
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("username",username);
		System.out.println("param1 is "+username);
		return "updateEmp";
	}
	
	
	@RequestMapping(value="/admin/updateEmployee/{username}",method=RequestMethod.POST)
	public String updateEmployee(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,@PathVariable("username") String username) {
		if(result.hasErrors()) {
			model.addAttribute("username",username);
			return "updateEmp";
		}
		else {
				user.setUsername(username);
				System.out.println("param is "+username);
				userdao.updateEmp(user);
				return "redirect:/admin/viewAllEmp";
		}
	}
	
	
	@RequestMapping(value="/admin/showFeedback",method=RequestMethod.GET)
	public String showFeedback(Model model) {
		List<Feedback> fbs = feedbackdao.getAllIncompletedFeedback();
		model.addAttribute("feedbacks",fbs);
		return "showFeedback";
	}
	
	
	@RequestMapping(value="/admin/setFeedbackSeen/{id}",method=RequestMethod.GET)
	public String setFeedbackSeen(Model model,@PathVariable("id") int id) {
		feedbackdao.setComplete(id);
		return "redirect:/admin/showFeedback";
	}
	
	
	@RequestMapping(value="/justtry",method=RequestMethod.GET)
	public String justtry(Model model) {
    	@SuppressWarnings("resource")
		ApplicationContext context = 
                new ClassPathXmlApplicationContext("Spring-Mail.xml");
       	 
       	MailMail mm = (MailMail) context.getBean("mailMail");
           mm.sendMail("umangjain5000@gmail.com",
       		   "umang.jain.cse17@itbhu.ac.in",
       		   "Testing123", 
       		   "Testing only \n\n Hello Spring Email Sender");
           
		return "redirect:/login";
	}
	
	
	
	
	
	
}
