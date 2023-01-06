package com.prog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.prog.entities.Employee;
import com.prog.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService service;

	@GetMapping("/")
	public String home(Model m) {
		List<Employee> emp = service.getAllEmployee();
		m.addAttribute("emp", emp);
		return "index";           //index.html
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {
		System.out.println("data = " + e);
		service.addEmp(e);
		session.setAttribute("msg", "Employee added successfully");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editEmp(@PathVariable int id, Model m) {
		
		Employee e = service.getEmployeeById(id);
		m.addAttribute("emp", e);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		service.addEmp(e);
		session.setAttribute("msg", "Employee Updtaed Scuucessfully..");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		service.deleteEmployeeById(id);
		session.setAttribute("msg", "Employee Deleted Scuucessfully..");
		return "redirect:/";
	}
	
}
