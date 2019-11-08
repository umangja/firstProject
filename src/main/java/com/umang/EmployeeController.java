package com.umang;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umang.dao.Addressdao;
import com.umang.dao.Billdao;
import com.umang.dao.Cancuredao;
import com.umang.dao.Containsdao;
import com.umang.dao.Diseasedao;
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
import com.umang.model.Bill;
import com.umang.model.CONSTANTS;
import com.umang.model.Cancure;
import com.umang.model.Contains;
import com.umang.model.Disease;
import com.umang.model.Locationnew;
import com.umang.model.Medicine;
import com.umang.model.Medicine_ordered;
import com.umang.model.Medicine_purchased;
import com.umang.model.Orders;
import com.umang.model.Partner;
import com.umang.model.Patient;
import com.umang.model.Supplier;
import com.umang.model.User;

@Controller
public class EmployeeController {
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
	MedicineOrdereddao medicineOrderdao;
	
	@Autowired
	MedicinePurchaseddao medicinePurchasedao;
	
	@RequestMapping(value="/emp/updateProfile",method=RequestMethod.GET)
	public String updateProfileShowPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "updateProfile";
	}
	
	@RequestMapping(value="/emp/updateProfile",method=RequestMethod.POST)
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
	
	
	@RequestMapping(value="/emp/updateAddress",method=RequestMethod.GET)
	public String updateAddressShowPage(Model model) {
		Address add = new Address();
		model.addAttribute("add", add);
		model.addAttribute("is_supplier",false);
		return "updateAddress";
	}
	
	@RequestMapping(value="/emp/updateAddress",method=RequestMethod.POST)
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
	
	
	@RequestMapping(value="/emp/addMedicine",method=RequestMethod.GET)
	public String addMedicineShowPage(Model model) {
		Medicine med = new Medicine();
		List<Supplier> suppliers = supplierdao.getAllSup();
		List<Locationnew> locations = locationdao.getAllLocation();
		model.addAttribute("med", med);
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("locations", locations);
		return "addMedicine";
	}
	
	@RequestMapping(value="/emp/addMedicine",method=RequestMethod.POST)
	public String addMedicine(@Valid @ModelAttribute("med") Medicine med,BindingResult result,Model model,Principal principal,HttpServletRequest request,HttpServletResponse response) {
		if(result.hasErrors())
		{
			return "addMedicine";
		}
		else if(med.getPrice()==0)
		{
			List<Supplier> suppliers = supplierdao.getAllSup();
			List<Locationnew> locations = locationdao.getAllLocation();
			model.addAttribute("suppliers", suppliers);
			model.addAttribute("locations", locations);
			model.addAttribute("error","Price can't be 0");
			return "addMedicine";
		}
		else 
		{
			medicinedao.addMedicine(med);
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value="/emp/addLocation",method=RequestMethod.GET)
	public String addLocationShowPage(Model model) {
		Locationnew location = new Locationnew();
		model.addAttribute("location", location);
		return "addLocation";
	}
	
	
	@RequestMapping(value="/emp/addLocation",method=RequestMethod.POST)
	public String addLocation(@Valid @ModelAttribute("location") Locationnew loc,BindingResult result,Model model,Principal principal) {
		if(result.hasErrors())
		{
			return "addLocation";
		}
		else 
		{
			locationdao.addLocation(loc);
			return "redirect:/login";
		}
	}
	
	
	@RequestMapping(value="/emp/addSupplier",method=RequestMethod.GET)
	public String addSupplierShowPage(Model model) {
		Supplier sup = new Supplier();
		model.addAttribute("supplier", sup);
		return "addSupplier";
	}
	
	
	@RequestMapping(value="/emp/addSupplier",method=RequestMethod.POST)
	public String addLocation(@Valid @ModelAttribute("supplier") Supplier sup,BindingResult result,Model model,Principal principal,HttpServletRequest request) {
		if(result.hasErrors())
		{
			return "addSupplier";
		}
		else if(sup.getPhone_no().length()!=10)
		{
			model.addAttribute("error","Phone number has to contain 10 digits");
			return "addSupplier";
		}
		else 
		{
			
			int shop_no = Integer.parseInt(request.getParameter("shop_no"));
			if(shop_no==0) {shop_no=1;}
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String colony_name = request.getParameter("colony_name");
			int pincode = Integer.parseInt(request.getParameter("pincode"));
			int house_no=0;
			
			Address add = new Address();
			add.setCity(city);
			add.setColony_name(colony_name);
			add.setHouse_no(house_no);
			add.setPincode(pincode);
			add.setShop_no(shop_no);
			add.setState(state);
			
			
			
			String supplier_id = String.valueOf(supplierdao.addSupplier(sup));
			int add_id = addressdao.updateAddress(add);
			supplierdao.setAddress(supplier_id,add_id);
			return "redirect:/login";
		}
	}
	
	
	@RequestMapping(value="/emp/canCure",method=RequestMethod.GET)
	public String addcanCureShowPage(Model model) {
		Cancure cancure = new Cancure();
		List<Medicine> medicines = medicinedao.getAllMedicinesId();
		List<Disease> diseases = diseasedao.getAllDiseasesIdAndName();
		model.addAttribute("medicines",medicines);
		model.addAttribute("diseases",diseases);
		model.addAttribute("cancure", cancure);
		return "addCancure";
	}
	
	
	@RequestMapping(value="/emp/canCure",method=RequestMethod.POST)
	public String addLocation(@Valid @ModelAttribute("cancure") Cancure cancure,BindingResult result,Model model,Principal principal,HttpServletRequest request,HttpServletResponse response) {
		if(result.hasErrors())
		{
			List<Medicine> medicines = medicinedao.getAllMedicinesId();
			List<Disease> diseases = diseasedao.getAllDiseasesIdAndName();
			model.addAttribute("medicines",medicines);
			model.addAttribute("diseases",diseases);
			model.addAttribute("cancure", cancure);
			return "addCancure";
		}
		else if(request.getParameter("medicine_id")==null || request.getParameter("disease_id")==null)
		{
			model.addAttribute("error","select both items");
			List<Medicine> medicines = medicinedao.getAllMedicinesId();
			List<Disease> diseases = diseasedao.getAllDiseasesIdAndName();
			model.addAttribute("medicines",medicines);
			model.addAttribute("diseases",diseases);
			model.addAttribute("cancure", cancure);
			return "addCancure";
		}
		else if(cancuredao.isExisting(request.getParameter("medicine_id"),request.getParameter("disease_id"))!=0)
		{
			model.addAttribute("error","Combination Already Exist");
			List<Medicine> medicines = medicinedao.getAllMedicinesId();
			List<Disease> diseases = diseasedao.getAllDiseasesIdAndName();
			model.addAttribute("medicines",medicines);
			model.addAttribute("diseases",diseases);
			model.addAttribute("cancure", cancure);
			return "addCancure";
		}
		else 
		{
			cancuredao.addCancure(cancure);
			return "redirect:/login";
		}
	}
	
	
	@RequestMapping(value="/emp/addContain",method=RequestMethod.GET)
	public String addContainShowPage(Model model) {
		Contains contain = new Contains();
		List<Medicine> medicines = medicinedao.getAllMedicinesId();
		model.addAttribute("medicines",medicines);
		model.addAttribute("contain", contain);
		return "addContain";
	}
	
	
	@RequestMapping(value="/emp/addContain",method=RequestMethod.POST)
	public String addContain(@Valid @ModelAttribute("contain") Contains contain,BindingResult result,Model model,Principal principal,HttpServletRequest request,HttpServletResponse response) {
		if(result.hasErrors())
		{
			List<Medicine> medicines = medicinedao.getAllMedicinesId();
			model.addAttribute("medicines",medicines);
			model.addAttribute("contain", contain);
			return "addContain";
		}
		else if(contain.getPercentage()<=0 ||contain.getPercentage()>=100 )
		{
			model.addAttribute("error","Percentage show be between 0 and 100 exclusive");
			List<Medicine> medicines = medicinedao.getAllMedicinesId();
			model.addAttribute("medicines",medicines);
			model.addAttribute("contain", contain);
			return "addContain";
		}
		else if(containsdao.isExisting(contain.getMedicine_id(),contain.getDrug_name())!=0)
		{
			model.addAttribute("error","Medicine Already Contain This Drug");
			List<Medicine> medicines = medicinedao.getAllMedicinesId();
			model.addAttribute("medicines",medicines);
			model.addAttribute("contain", contain);
			return "addContain";
		}
		else 
		{
			containsdao.addcontain(contain);
			return "redirect:/login";
		}
	}
	
	
	@RequestMapping(value="/emp/viewProfile",method=RequestMethod.GET)
	public String viewCurrentEmp(Model model,Principal principal)
	{
		List<User> users = userdao.getCurrentEmp(principal.getName());
		model.addAttribute("users",users);
		model.addAttribute("is_single_emp",true);
		return "allemps";
	}
	
	
	@RequestMapping(value="/emp/findAddress/{add_id}",method=RequestMethod.GET)
	public String findAddressByID(Model model,@PathVariable("add_id") int add_id)
	{
		Address add  = addressdao.getAddressById(add_id);
		List<Address> adds = new ArrayList<Address> ();
		adds.add(add);
		model.addAttribute("adds",adds);
		if(add.getHouse_no()==0)
		{
			model.addAttribute("who","sup");
		}
		else
		{
			model.addAttribute("who","emp");
		}
		return "showAddress";
	}
	
	
	@RequestMapping(value="/emp/showAllMedicine",method=RequestMethod.GET)
	public String showAllMedicinePage(Model model)
	{
		List<Medicine> medicines = medicinedao.getAllMedicine();
		model.addAttribute("medicines",medicines);
		return "showAllMedicines";
	}
	
	
	@RequestMapping(value="/emp/showLocationOfMedicine/{medicine_id}",method=RequestMethod.GET)
	public String showLocations(Model model,@PathVariable("medicine_id") int medicine_id)
	{
		List<Locationnew> locations = locationdao.getLocationofMedicine(medicine_id);
		model.addAttribute("locations",locations);
		return "showLocations";
	}
	
	@RequestMapping(value="/emp/showSupplierforMedicine/{medicine_id}",method=RequestMethod.GET)
	public String showSuppliers(Model model,@PathVariable("medicine_id") int medicine_id)
	{
		List<Supplier> suppliers = supplierdao.getSupplierForMedicine(medicine_id);
		model.addAttribute("suppliers",suppliers);
		return "showSuppliers";
	}
	
	@RequestMapping(value="/emp/showDiseaseforMedicine/{id}/{name}",method=RequestMethod.GET)
	public String showDiseasesForMedicines(Model model,Medicine medicine,@PathVariable("id") int id,@PathVariable("name") String name)
	{
		List<Disease> diseases = cancuredao.getDiseasesForMedicine(id);
		model.addAttribute("diseases",diseases);
		model.addAttribute("medicine_name",name);
		return "showDiseaseForMedicine";
	}

	@RequestMapping(value="/emp/showDrugforMedicine/{id}/{name}",method=RequestMethod.GET)
	public String showDrugForMedicines(Model model,Medicine medicine,@PathVariable("id") int id,@PathVariable("name") String name)
	{
		List<Contains> drugs = containsdao.getDrugsForMedicine(id);
		model.addAttribute("drugs",drugs);
		model.addAttribute("medicine_name",name);
		return "showDrugForMedicine";
	}
	
	
	@RequestMapping(value="emp/MedicineThatCanCure",method=RequestMethod.GET)
	public String MedicineThatCanCure(Model model,HttpServletRequest request)
	{
		int dis_id =Integer.parseInt( request.getParameter("disease_id")); 
		List<Medicine> medicines = cancuredao.getMedicineThatCanCure(dis_id);
		model.addAttribute("medicines",medicines);
		return "showAllMedicines";
	}
	
	@RequestMapping(value="emp/diseaseForMedicine",method=RequestMethod.GET)
	public String diseaseForMedicine(Model model,HttpServletRequest request)
	{
		String[] idAndName = request.getParameter("medicine").split(" ");
		int med_id =Integer.parseInt( idAndName[0]);
		String name = idAndName[1];
		List<Disease> diseases = cancuredao.getDiseasesForMedicine(med_id);
		model.addAttribute("diseases",diseases);
		model.addAttribute("medicine_name",name);
		return "showDiseaseForMedicine";
	}
	
	
	
	@RequestMapping(value="emp/showFilteredMedicine",method=RequestMethod.POST)
	public String showFilteredMedicine(Model model,HttpServletRequest request)
	{
		
		
		int price =100000;
		if(request.getParameter("price")!=null && request.getParameter("price").length()!=0)
		{
			price=Integer.parseInt( request.getParameter("price"));
		}
		int in_stock =100000;
		if(request.getParameter("in_stock")!=null && request.getParameter("in_stock").length()!=0)
		{
			in_stock=Integer.parseInt( request.getParameter("in_stock"));
		}
		
		String expiration_date = "2100:12:30";
		if(request.getParameter("expiration_date")!=null && request.getParameter("expiration_date").length()!=0)
		{
			expiration_date=request.getParameter("expiration_date");
		}
		
		String company = "selectall";
		if(request.getParameter("company")!=null && request.getParameter("company").length()!=0)
		{
			company=request.getParameter("company");
		}
		
		List<Medicine> medicines=null;
		if(company!="selectall")
		{
			medicines = medicinedao.getAllMedicineFiltered(company,price,in_stock,expiration_date);
		}
		else
		{
			medicines = medicinedao.getAllMedicineFiltered(price,in_stock,expiration_date);
		}
		
		model.addAttribute("medicines",medicines);
		return "showAllMedicines";
		
	}
	
	
	
	
	
	
	@RequestMapping(value="/emp/addSupplierAddress",method=RequestMethod.GET)
	public String updateSupplierAddressShowPage(Model model) {
		Address add = new Address();
		model.addAttribute("add", add);
		model.addAttribute("is_supplier", true);
		
		List<Supplier> suppliers = supplierdao.getAllSup();
		model.addAttribute("suppliers",suppliers);
		return "updateAddress";
	}
	
	@RequestMapping(value="/emp/addSupplierAddress",method=RequestMethod.POST)
	public String updateSupplierAddress(HttpServletRequest request,HttpServletResponse response,@Valid @ModelAttribute("add") Address add,BindingResult result,Model model,Principal principal) {
		if(result.hasErrors()) {
			model.addAttribute("is_supplier", true);
			List<Supplier> suppliers = supplierdao.getAllSup();
			model.addAttribute("suppliers",suppliers);
			return "updateAddress";
		}
		else if(add.getShop_no()==0)
		{
			model.addAttribute("error","Shop no can't be 0");
			model.addAttribute("is_supplier", true);
			List<Supplier> suppliers = supplierdao.getAllSup();
			model.addAttribute("suppliers",suppliers);
			return "updateAddress";
		}
		else 
		{
				add.setHouse_no(0);
				int add_id = addressdao.updateAddress(add);
				supplierdao.setAddress(request.getParameter("supplier_id"),add_id);
				return "redirect:/login";
		}
	}
	
	
	
	@RequestMapping(value="/emp/addPartner",method=RequestMethod.GET)
	public String addPartnerShowPage(Model model) {
		Partner sup = new Partner();
		model.addAttribute("partner", sup);
		return "addPartner";
	}
	
	
	@RequestMapping(value="/emp/addPartner",method=RequestMethod.POST)
	public String addPartner(@Valid @ModelAttribute("partner") Partner partner,BindingResult result,Model model,Principal principal,HttpServletRequest request) {
		if(result.hasErrors())
		{
			return "addPartner";
		}
		else if(partner.getPhone_no().length()!=10)
		{
			model.addAttribute("error","Phone number has to contain 10 digits");
			return "addPartner";
		}
		else 
		{
			int shop_no = Integer.parseInt(request.getParameter("shop_no"));
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String colony_name = request.getParameter("colony_name");
			int pincode = Integer.parseInt(request.getParameter("pincode"));
			int house_no=0;
			
			Address add = new Address();
			add.setCity(city);
			add.setColony_name(colony_name);
			add.setHouse_no(house_no);
			add.setPincode(pincode);
			add.setShop_no(shop_no);
			add.setState(state);
			
			int add_id = addressdao.updateAddress(add);
			partner.setAddress_id(add_id);
			
			partnerdao.addPartner(partner);
			return "redirect:/login";
		}
	}
	
	
	
	
	
	@RequestMapping(value="/emp/addPatient",method=RequestMethod.GET)
	public String addPatientshowpage(Model model) {
		Patient sup = new Patient();
		model.addAttribute("patient", sup);
		return "addPatient";
	}
	
	
	@RequestMapping(value="/emp/addPatient",method=RequestMethod.POST)
	public String addPatient(@Valid @ModelAttribute("patient") Patient patient,BindingResult result,Model model,Principal principal,HttpServletRequest request) {
		if(result.hasErrors())
		{
			return "addPatient";
		}
		else if(patient.getPhone_no().length()!=10)
		{
			model.addAttribute("error","Phone number has to contain 10 digits");
			return "addPatient";
		}
		else 
		{
			patientdao.addPatient(patient);
			return "redirect:/login";
		}
	}
	
	
	
	@RequestMapping(value="/emp/addBill",method=RequestMethod.GET)
	public String addBillshowpage(Model model) {
		Bill sup = new Bill();
		model.addAttribute("bill", sup);
		model.addAttribute("patients",patientdao.getAll());
		model.addAttribute("partners",partnerdao.getAll());
		model.addAttribute("medicines",medicinedao.getAllMedicine());
		return "addBill";
	}
	
	
	@RequestMapping(value="/emp/addBill",method=RequestMethod.POST)
	public String addBill(@Valid @ModelAttribute("bill") Bill bill,BindingResult result,Model model,Principal principal,HttpServletRequest request) {
		if(result.hasErrors())
		{
			model.addAttribute("patients",patientdao.getAll());
			model.addAttribute("partners",partnerdao.getAll());
			model.addAttribute("medicines",medicinedao.getAllMedicine());
			return "addBill";
		}
		else if(Integer.parseInt(request.getParameter("cnt"))<=-1)
		{
			model.addAttribute("patients",patientdao.getAll());
			model.addAttribute("partners",partnerdao.getAll());
			model.addAttribute("medicines",medicinedao.getAllMedicine());
			model.addAttribute("error","select some medicine and quantity");
			return "addBill";
		}
		else if(bill.getDiscount_offered()<0 ||bill.getDiscount_offered()>51 )
		{
			model.addAttribute("patients",patientdao.getAll());
			model.addAttribute("partners",partnerdao.getAll());
			model.addAttribute("medicines",medicinedao.getAllMedicine());
			model.addAttribute("error","Discount can be betwwen 0 and 51");
			return "addBill";
		}
		else if(bill.getTransaction_id()<0)
		{
			model.addAttribute("patients",patientdao.getAll());
			model.addAttribute("partners",partnerdao.getAll());
			model.addAttribute("medicines",medicinedao.getAllMedicine());
			model.addAttribute("error","tranasction Id can't be less than 0");
			return "addBill";
		}
		else 
		{
			String forwho = request.getParameter("for_who");
			if(forwho==null || forwho=="" || forwho.equals("-"))
			{
				model.addAttribute("patients",patientdao.getAll());
				model.addAttribute("partners",partnerdao.getAll());
				model.addAttribute("medicines",medicinedao.getAllMedicine());
				model.addAttribute("error","select for who");
				return "addBill";
			}
			int bil_id=0;
			int total=0;
			
			List<Medicine_purchased> medicinePurchased = new ArrayList<Medicine_purchased>();
			int cnt = Integer.parseInt(request.getParameter("cnt"));
			for(int i=0;i<=cnt;i++)
			{
				Medicine_purchased mp = new Medicine_purchased();
				mp.setMedicine_id(Integer.parseInt(request.getParameter("medicine"+i)));
				mp.setQuantity(Integer.parseInt(request.getParameter("quantity"+i)));
				total+=medicinedao.getMedicineById(mp.getMedicine_id()).get(0).getPrice()*mp.getQuantity();
				medicinePurchased.add(mp);
			}
			
			CONSTANTS cont = new CONSTANTS();
			if(total>cont.getMagic_value())
			{
				total = (int) ((total*(100-cont.getDiscount()))/100);
			}
			
			total = (int) ((total*(100-bill.getDiscount_offered()))/100);
			total = (int) ((total*(100+cont.getCgst()+cont.getSgst()))/100);;
			
			
			bill.setTotal(total);
			if(request.getParameter("for_who").equals("Partner"))
			{
				bill.setSupplied_to(Integer.parseInt(request.getParameter("partner_id")));
				bill.setEmployee_issuing(principal.getName());
				bil_id=billdao.addBillOfPartner(bill);
			}
			else
			{
				bill.setPurchased_by(Integer.parseInt(request.getParameter("patient_id")));
				bill.setEmployee_issuing(principal.getName());
				bil_id=billdao.addBillOfPatient(bill);
			}
			
			for(int i=0;i<=cnt;i++)
			{
				Medicine_purchased mp  = medicinePurchased.get(i);
				mp.setBill_id(bil_id);
				medicinedao.updateIn_stock(mp.getMedicine_id(),mp.getQuantity());
			}
			
			medicinePurchasedao.add(medicinePurchased);
			
//			return "redirect:/login";
			String redirect_url = "emp/downloadbill/"+bil_id;
			return "redirect:/"+redirect_url;
		}
	}
	
	
	
	@RequestMapping(value="/emp/showBills",method=RequestMethod.GET)
	public String showBills(Model model) {
		List<Bill> bills = billdao.getBills();
		model.addAttribute("bills", bills);
		return "showBills";
	}
	
	
	@RequestMapping(value="/emp/showPatients/{patient_id}",method=RequestMethod.GET)
	public String showPatients(Model model,@PathVariable("patient_id") int patient_id)
	{
		List<Patient> Patients = patientdao.getPatientById(patient_id);
		model.addAttribute("patients",Patients);
		return "showPatients";
	}
	
	@RequestMapping(value="/emp/showPartners/{patient_id}",method=RequestMethod.GET)
	public String showPartners(Model model,@PathVariable("patient_id") int patient_id)
	{
		List<Partner> Partners = partnerdao.getPartnerById(patient_id);
		model.addAttribute("partners",Partners);
		return "showPartners";
	}
	
	@RequestMapping(value="/emp/showMedicinesForBill/{bill_id}",method=RequestMethod.GET)
	public String showMedicinesForBill(Model model,@PathVariable("bill_id") int bill_id)
	{
		List<Medicine_purchased> medicine_purchased = medicinePurchasedao.getMedicinesForBill(bill_id);
		model.addAttribute("meds",medicine_purchased);
		return "showMedicine_purchased";
	}
	
	
	@RequestMapping(value="/emp/showFilteredBill",method=RequestMethod.POST)
	public String showFilteredBill(Model model,HttpServletRequest request,RedirectAttributes redirectAttributes)
	{
		

		
		int total =-1;
		if(request.getParameter("total")!=null && request.getParameter("total").length()!=0)
		{
			total=Integer.parseInt( request.getParameter("total"));
		}
		
		int discount_offered =-1;
		if(request.getParameter("discount_offered")!=null && request.getParameter("discount_offered").length()!=0)
		{
			discount_offered=Integer.parseInt( request.getParameter("discount_offered"));
		}
		
		String employee_issuing = "";
		if(request.getParameter("employee_issuing")!=null && request.getParameter("employee_issuing").length()!=0)
		{
			employee_issuing=request.getParameter("employee_issuing");
		}
		
		String datetime = "2100:12:30";
		if(request.getParameter("datetime")!=null && request.getParameter("datetime").length()!=0)
		{
			datetime=request.getParameter("datetime");
		}
		
		
		List<Bill> bills=null;
		
		
		if(employee_issuing.equals("all"))
		{
			bills = billdao.getAllBillsFiltered(total,discount_offered,datetime);
		}
		else
		{
			bills = billdao.getAllBillsFiltered(total,discount_offered,datetime,employee_issuing);
		}
		
		model.addAttribute("bills", bills);
		return "showBills";
	}
	
	@RequestMapping(value="/checkExpiration",method=RequestMethod.GET)
	public String checkExpiration(final RedirectAttributes redirectAttributes)
	{
		return "redirect:/login";
	}
	
	@RequestMapping(value="/emp/FindMedicine",method=RequestMethod.GET)
	public String FindMedicineByName(Model model,HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		String med_name = request.getParameter("medicine");
		if(med_name==null || med_name.equals(""))
		{
			redirectAttributes.addFlashAttribute("error3", "select some medicine");
			return "redirect:/login";
		}
		else
		{
			List<Medicine> medicines  = medicinedao.getMedicineByName(med_name);
			if(medicines==null || medicines.size()==0)
			{
				redirectAttributes.addFlashAttribute("error3","medicine do not exist");
				return "redirect:/login";
			}
			model.addAttribute("medicines",medicines);
		    return "showAllMedicines"; 
		  
		}
		
	}
	
	
	@RequestMapping(value="/emp/downloadbill/{bill_id}",method=RequestMethod.GET)
	public ModelAndView downloadBillForBill(@PathVariable("bill_id") int bill_id)
	{
		 List<Bill> bills = billdao.getBillById(bill_id);
		 Bill bill = null;
		 if(bills!=null)
		 {
			 bill = bills.get(0);
		 }
		
		 List<Medicine_purchased> medicine_purchased =medicinePurchasedao.getMedicinesForBill(bill_id);
		 List<Medicine> medicines =new ArrayList<Medicine> ();
		 for(int i=0;i<medicine_purchased.size();i++) {
			 List<Medicine> temp = medicinedao.getMedicineById(medicine_purchased.get(i).getMedicine_id());
			 if(temp!=null)
			 {
				 medicines.add(temp.get(0));
			 }
			  
		 }
		 
		 Address adds = null;
		 String cust_name;
		 if(bill.getPurchased_by()!=0)
		 {
			 cust_name = patientdao.getPatientById(bill.getPurchased_by()).get(0).getName();
		 }
		 else
		 {
			 cust_name = partnerdao.getPartnerById(bill.getSupplied_to()).get(0).getName();
			 int add_id = partnerdao.getPartnerById(bill.getSupplied_to()).get(0).getAddress_id();
			 adds = addressdao.getAddressById(add_id);
		 }
		 
		 
		 
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("download");
		 mv.addObject("medicines",medicines);
		 mv.addObject("bill", bill);
		 mv.addObject("bill_id",bill_id);
		 mv.addObject("address",adds);
		 mv.addObject("cust_name",cust_name);
		 mv.addObject("medicine_purchased",medicine_purchased);
		return mv;
	}
	
	
	
	@RequestMapping(value="/emp/showOrders",method=RequestMethod.GET)
	public String showOrders(Model model) {
		List<Orders> orders = ordersdao.getIncompleteOrders();
		model.addAttribute("orders", orders);
		return "showOrders";
	}
	
	
	@RequestMapping(value="/emp/showMedicinesForOrder/{order_id}",method=RequestMethod.GET)
	public String showMedicinesForOrder(Model model,@PathVariable("order_id") int order_id)
	{
		List<Medicine_ordered> medicine_ordered = medicineOrderdao.getMedicinesForOrder(order_id);
		model.addAttribute("meds",medicine_ordered);
		return "showMedicine_purchased";
	}
	
	
	
	@RequestMapping(value="/emp/completeOrder/{order_id}",method=RequestMethod.GET)
	public String completeOrder(Model model,@PathVariable("order_id") int order_id)
	{
//		List<Medicine_ordered> medicine_ordered = medicineOrderdao.getMedicinesForOrder(order_id);
		
//		for(int i=0;i<medicine_ordered.size();i++)
//		{
//			Medicine_ordered med = medicine_ordered.get(i);
//			medicinedao.updateIn_stock(med.getMedicine_id(), med.getQuantity());
//		}
		medicineOrderdao.updateToComplete(order_id);
		return "redirect:/emp/showOrders";
		
	}
	
	
	@RequestMapping(value="/emp/addDisease",method=RequestMethod.GET)
	public String addDiseaseShowPage(Model model) {
		Disease disease = new Disease();
		model.addAttribute("disease", disease);
		return "addDisease";
	}
	
	
	@RequestMapping(value="/emp/addDisease",method=RequestMethod.POST)
	public String addDisease(@Valid @ModelAttribute("disease") Disease disease,BindingResult result,Model model,Principal principal) {
		if(result.hasErrors())
		{
			return "addDisease";
		}
		else if(disease.getHow_rare()>10)
		{
			model.addAttribute("error","rare level should be less than 10");
			return "addDisease";
		}
		else if(disease.getDanger_level()>10)
		{
			model.addAttribute("error","danger level should be less than 10");
			return "addDisease";
		}
		else 
		{
			diseasedao.addDisease(disease);
			return "redirect:/login";
		}
	}
	
	
	
	
	
	
	
	
	
}
