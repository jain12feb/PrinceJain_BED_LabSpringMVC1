package com.spring.service;

import java.util.List;
import java.util.Set;

import com.spring.entity.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee emp);

	Employee getEmployeeById(long id);

	List<Employee> getAllEmployees();

	Employee updateEmployee(long id, Employee emp);

	void deleteEmployee(long id);

	List<Employee> findEmployeeByFname(String fname);

	List<Employee> findEmployeesByDesignation(String designation);

	Set<String> getAllDesignations();

	void updateEmployee(Employee e);

}
