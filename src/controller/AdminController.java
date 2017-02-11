package controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.AuthenticationDAO;
import dao.EmployeeDAO;
import model.Authentication;
import model.Employee;
import model.FlatPaidEmployee;

@Stateless
public class AdminController {
	@Inject
	EmployeeDAO employeeDAO;
	
	@Inject
	AuthenticationDAO authenticationDAO;
	
	@Inject
	DateController dateController;
	
	@PostConstruct
	public void init(){
	}
	
	public List<Employee> addEmployee(Employee employee) {
		if (employee.isInUnion()==Boolean.FALSE) {
			employee.setWeeklyDues(0);
			employee.setTotalDues(0);
		}
		if (employee.getLastPayment() == null) {
			employee.setLastPayment(dateController.yesterday());
		}
		if (employee.getNextPayment() == null) {
			if (employee.getClass().equals(FlatPaidEmployee.class)) {
				employee.setNextPayment(dateController.endDateOfThisMonth());
			} else {
				employee.setNextPayment(dateController.nextFriday());
			}
		}
		employeeDAO.add(employee);
		addAuthentication(employee);
		return findAll();
	}
	
	public void modifyEmployeeInfo(Employee employee) {
		if (employee.isInUnion()==Boolean.FALSE) {
			employee.setWeeklyDues(0);
			employee.setTotalDues(0);
		}
		if (employee.getLastPayment() == null) {
			employee.setLastPayment(dateController.yesterday());
		}
		employeeDAO.modifyByAdmin(employee);
	}
	
	public List<Employee> removeEmployee(Employee employee)  {
		employeeDAO.remove(employee);
		return employeeDAO.findAllEmployee();
	}
	
	private void addAuthentication(Employee employee) {
		Authentication authentication = new Authentication(employee, "password" + employee.getId(), false);
		authenticationDAO.add(authentication);
	}
	
	public List<Employee> findAll() {
		return employeeDAO.findAllEmployee();
	}
	
	public List<Employee> findAllEmployeesInUnion() {
		return employeeDAO.findAllEmployeesInUnion();
	}
}
