package com.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.Employee;
import com.spring.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeViewController {
	
	@Autowired
	private EmployeeService empService;

	@GetMapping("/list")
	public String getAllEmployees(ModelMap mm, @RequestParam(name = "fName", required = false) String fname,
			@RequestParam(name = "designation", required = false) String designation) {

		Set<String> designations = empService.getAllDesignations();

		List<Employee> allEmployees = new ArrayList<Employee>();

		if (fname == null && designation == null)
			allEmployees = empService.getAllEmployees();
		if (fname != null)
			allEmployees = empService.findEmployeeByFname(fname);
		if (designation != null)
			allEmployees = empService.findEmployeesByDesignation(designation);

		mm.addAttribute("employees", allEmployees);
		mm.addAttribute("designations", designations);
		return "list-employees";
	}

	@GetMapping("/add")
	public String addEmployee(@ModelAttribute("employee") Employee e) {
		return "add-employee";
	}

	@PostMapping("/save")
	public String saveEmployee(Employee e) {
		empService.addEmployee(e);
		return "redirect:/employee/list";
	}

	@PostMapping("/delete")
	String deleteEmployeeById(@RequestParam("empId") int bidd) {
		empService.deleteEmployee(bidd);
		return "redirect:/employee/list";
	}

	@PostMapping("/update")
	String updateEmployee(@RequestParam("empId") int id, Model theModel) {
		Employee emp = empService.getEmployeeById(id);
		theModel.addAttribute("employee", emp);
		return "update-employee";
	}

	@PostMapping("/save-update")
	public String saveUpdatedEmployee(@ModelAttribute("employee") Employee e) {
		empService.updateEmployee(e);
		return "redirect:/employee/list";
	}

}
