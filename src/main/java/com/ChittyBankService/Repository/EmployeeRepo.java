package com.ChittyBankService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChittyBankService.Model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	@SuppressWarnings("unchecked")
	public Employee save(Employee employee);

}

