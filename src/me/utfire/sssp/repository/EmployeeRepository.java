package me.utfire.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.utfire.sssp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	Employee getByLastName(String lastName);
}
