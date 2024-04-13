package com.spring.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("SELECT e FROM Employee e WHERE lower(e.fName) LIKE %:fName% or lower(e.lName) LIKE %:lName%")
    List<Employee> findByfNameOrlNameContainingIgnoreCase(String fName, String lName);

	Boolean existsByEmail(String email);

	List<Employee> findByDesignationContaining(String designation);

	@Query("SELECT DISTINCT e.designation FROM Employee e")
	Set<String> findAllDistinctDesignations();

}
