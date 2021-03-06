package dev.ls.service;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import dev.ls.entity.Employee;

@Transactional
public class EmployeeService {
	
	@PersistenceContext
	//(name = persistence context name is not required since there is only a single persistence unit)
	//otherwise could have provided a name property
	// here, providing the persistence context name is redundant
	EntityManager entityManager;
	
	public Employee createEmployee(Employee employee) {
		entityManager.persist(employee);
		return employee;
	}
	
	public Employee updateEmployee(Employee employee) {
		entityManager.merge(employee);
		return employee;
	}
	
	public Employee findEmployeeById(Integer empId) {
		return entityManager.find(Employee.class, empId);
	}
	
	public List<Employee> getEmployees(){
		return entityManager.createQuery("SELECT e from Employee e",Employee.class).getResultList();
	}	
	@PostConstruct	
	private void showPostConstrct() {
		System.out.println("After Construction.");
	}
	@PreDestroy
	private void showPreDestroy() {
		System.out.println("Before Ddestruction.");
	}	
}
