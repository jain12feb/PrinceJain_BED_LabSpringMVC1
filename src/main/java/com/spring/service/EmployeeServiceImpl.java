package com.spring.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Employee;
import com.spring.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;
	
	@Override
	public Employee addEmployee(Employee emp) {
		Boolean isEmpExist = empRepo.existsByEmail(emp.getEmail());
		if (isEmpExist)
			throw new RuntimeException("Employee Already Exists");
		return empRepo.save(emp);
	}

	@Override
	public Employee getEmployeeById(long id) {
		return empRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee not found with this id " + id));
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee updateEmployee(long id, Employee emp) {
		Employee empFromDb = empRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee not found with this id " + id));

		empFromDb.setFName(emp.getFName());
		empFromDb.setLName(emp.getLName());
		empFromDb.setEmail(emp.getEmail());
		empFromDb.setSalary(emp.getSalary());
		empFromDb.setDesignation(emp.getDesignation());

		return empRepo.save(empFromDb);
	}

	@Override
	public void deleteEmployee(long id) {
		Employee empFromDb = empRepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee not found with this id " + id));
		empRepo.delete(empFromDb);
	}

	@Override
	public List<Employee> findEmployeeByFname(String name) {
		return empRepo.findByfNameOrlNameContainingIgnoreCase(name, name);
	}

	@Override
	public List<Employee> findEmployeesByDesignation(String designation) {
		return empRepo.findByDesignationContaining(designation);
	}

	@Override
	public Set<String> getAllDesignations() {
		return empRepo.findAllDistinctDesignations();
	}

	@Override
	public void updateEmployee(Employee e) {

		Employee emp = empRepo.findById(e.getId())
				.orElseThrow(() -> new NoSuchElementException("Employee not found with this id " + e.getId()));

		emp.setFName(e.getFName());
		emp.setLName(e.getLName());
		emp.setEmail(e.getEmail());
		emp.setSalary(e.getSalary());
		emp.setDesignation(e.getDesignation());

		empRepo.save(emp);
	}

}
