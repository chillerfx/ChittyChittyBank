package com.ChittyBankService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ChittyBankService.Model.Employee;
import com.ChittyBankService.Repository.BranchRepo;
import com.ChittyBankService.Repository.EmployeeRepo;


@RestController
@RequestMapping("/employee")
public class EmployeeController implements ErrorController {

	@Autowired
	BranchRepo branchRepo;
	@Autowired
	EmployeeRepo employeeRepo;
	@RequestMapping()
	public @ResponseBody List<Employee> employee(){
		List<Employee> employees = employeeRepo.findAll();
		return employees;
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
