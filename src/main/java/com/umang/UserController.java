package com.umang;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umang.dao.Addressdao;
import com.umang.dao.Billdao;
import com.umang.dao.Cancuredao;
import com.umang.dao.Containsdao;
import com.umang.dao.Diseasedao;
import com.umang.dao.Feedbackdao;
import com.umang.dao.Locationdao;
import com.umang.dao.MedicineOrdereddao;
import com.umang.dao.MedicinePurchaseddao;
import com.umang.dao.Medicinedao;
import com.umang.dao.Ordersdao;
import com.umang.dao.Partnerdao;
import com.umang.dao.Patientdao;
import com.umang.dao.Supplierdao;
import com.umang.dao.Userdao;
import com.umang.model.Address;
import com.umang.model.CONSTANTS;
import com.umang.model.Feedback;
import com.umang.model.Medicine;
import com.umang.model.Medicine_ordered;
import com.umang.model.Orders;
import com.umang.model.User;

@Controller
public class UserController {
	
	@Autowired
	Userdao userdao;
	
	@Autowired
	Addressdao addressdao;
	
	@Autowired
	Medicinedao medicinedao;
	
	@Autowired
	Supplierdao supplierdao;
	
	@Autowired
	Locationdao locationdao;
	
	@Autowired
	Cancuredao cancuredao;
	@Autowired
	Diseasedao diseasedao;
	@Autowired
	Containsdao containsdao;
	@Autowired
	Partnerdao partnerdao;
	@Autowired
	Patientdao patientdao;
	
	@Autowired
	Billdao billdao;
	
	@Autowired
	Ordersdao ordersdao;
	
	@Autowired
	Feedbackdao feedbackdao;
	
	@Autowired
	MedicineOrdereddao medicineOrdereddao;
	
	@Autowired
	MedicinePurchaseddao medicinePurchasedao;
	
	
	@RequestMapping(value="/registerUser",method=RequestMethod.GET)
	public String registerUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registerUser";
	}
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String registerUserProcess(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,HttpServletRequest request) {
		if(result.hasErrors()) {
			return "registerUser";
		}
		else {
				if(!user.getPassword().equals(user.getMpassword())) {
					model.addAttribute("error","passwords dont match");
					return "registerUser";
				}
				if(userdao.getUser(user.getUsername())!=null) {
					model.addAttribute("error", "username exists");
					return "registerUser";
				}
				
				 
				
					
				int house_no = Integer.parseInt(request.getParameter("house_no"));
				if(house_no==0) {
					model.addAttribute("error", "house no can't be 0 ");
					return "registerUser";
				}
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String colony_name = request.getParameter("colony_name");
				int pincode = Integer.parseInt(request.getParameter("pincode"));
				int shop_no=0;
				
				Address add = new Address();
				add.setCity(city);
				add.setColony_name(colony_name);
				add.setHouse_no(house_no);
				add.setPincode(pincode);
				add.setShop_no(shop_no);
				add.setState(state);
				
				
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			
				userdao.registerUser(user);
				int add_id = addressdao.updateAddress(add);
				userdao.setAddress(user.getUsername(), add_id);
				return "redirect:/login";
				
		}
	}
	
	
	@RequestMapping(value="/user/updateProfile",method=RequestMethod.GET)
	public String updateProfileShowPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "updateProfile";
	}
	
	@RequestMapping(value="/user/updateProfile",method=RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute("user") User user,BindingResult result,Model model,Principal principal) {
		if(result.hasErrors()) {
			return "updateProfile";
		}
		else {
				user.setUsername(principal.getName());
				userdao.updateEmpHimself(user);
				return "redirect:/login";
		}
	}
	
	
	@RequestMapping(value="/user/showAllMedicine",method=RequestMethod.GET)
	public String showAllMedicinePage(Model model)
	{
		List<Medicine> medicines = medicinedao.getAllMedicine();
		model.addAttribute("medicines",medicines);
		return "showAllMedicineForUser";
	}
	
	
	
	@RequestMapping(value="/user/updateAddress",method=RequestMethod.GET)
	public String updateAddressShowPage(Model model) {
		Address add = new Address();
		model.addAttribute("add", add);
		model.addAttribute("is_supplier",false);
		return "updateAddress";
	}
	
	@RequestMapping(value="/user/updateAddress",method=RequestMethod.POST)
	public String updateAddress(@Valid @ModelAttribute("add") Address add,BindingResult result,Model model,Principal principal) {
		if(result.hasErrors()) {
			return "updateAddress";
		}
		else if(add.getHouse_no()==0)
		{
			model.addAttribute("error","House no can't be 0");
			return "updateAddress";
		}
		else 
		{
				add.setShop_no(0);
				int add_id = addressdao.updateAddress(add);
				userdao.setAddress(principal.getName(),add_id);
				return "redirect:/login";
		}
	}
	
	
	@RequestMapping(value="/user/viewProfile",method=RequestMethod.GET)
	public String viewCurrentEmp(Model model,Principal principal)
	{
		List<User> users = userdao.getCurrentUser(principal.getName());
		model.addAttribute("users",users);
		model.addAttribute("is_single_emp",true);
		return "allUsers";
	}
	
	
	

	
	
	@RequestMapping(value="/user/addorder",method=RequestMethod.POST)
	public String addorder(@Valid @ModelAttribute("orders") Orders orders,BindingResult result,Model model,Principal principal,HttpServletRequest request) {
		if(result.hasErrors())
		{
			return "redirect:/login";
		}
		else if(Integer.parseInt(request.getParameter("cnt"))<=-1)
		{
			return "redirect:/login";
		}
		if(orders.getOrdered_by()<=0)
		{
			
			return "redirect:/login";
		}
		else 
		{
			int total=0;
			
			List<Medicine_ordered> medicineOrdered = new ArrayList<Medicine_ordered>();
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			for(int i=0;i<=cnt;i++)
			{
				Medicine_ordered mp = new Medicine_ordered();
				mp.setMedicine_id(Integer.parseInt(request.getParameter("medicine"+i)));
				mp.setQuantity(Integer.parseInt(request.getParameter("quantity"+i)));
				total+=medicinedao.getMedicineById(mp.getMedicine_id()).get(0).getPrice()*mp.getQuantity();
				medicineOrdered.add(mp);
			}
			
			CONSTANTS cont = new CONSTANTS();
			
			total = (int) ((total*(100+cont.getCgst()+cont.getSgst()))/100);
			
			System.out.println(orders.getOrdered_by());
			int order_id = ordersdao.add(orders);
			for(int i=0;i<=cnt;i++)
			{
				Medicine_ordered mp  = medicineOrdered.get(i);
				mp.setOrder_id(order_id);
			}
			
			medicineOrdereddao.add(medicineOrdered);
			
			return "redirect:/login";
		}
	}
	
	
	
	@RequestMapping(value="/user/addFeedback",method=RequestMethod.POST)
	public String addFeedback(Model model,Principal principal,HttpServletRequest request)
	{
		Feedback fb=new Feedback();
		fb.setPartner_id(Integer.parseInt(request.getParameter("partner_id")));
		fb.setFeedback(request.getParameter("feedback"));
		feedbackdao.add(fb);
		return "redirect:/login";
	}

	
	
	
	
	
	
	
	
	
	

}
