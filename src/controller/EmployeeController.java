package controller;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import dao.EmployeeDAO;
import model.FlatPaidEmployee;
import model.HourlyPaidEmployee;

public class EmployeeController {

	Logger logger = Logger.getLogger(EmployeeController.class);
	@Inject
	EmployeeDAO employeeDAO;

	@PostConstruct
	public void init() {
	}

	public void modify(int idEmp, String paymentMethod, String postalAddress, String bankAccount) {
		employeeDAO.modifyByEmployee(idEmp, paymentMethod, postalAddress, bankAccount);
	}

	public HourlyPaidEmployee findHourlyPaidEmployeeById(int idEmp) {
		HourlyPaidEmployee hourlyPaidEmployee = employeeDAO.findHourlyPaidEmployeeById(idEmp);
		return hourlyPaidEmployee;
	}

	public FlatPaidEmployee findFlatPaidEmployeeById(int idEmp) {
		FlatPaidEmployee flatPaidEmployee = employeeDAO.findFlatPaidEmployeeById(idEmp);
		return flatPaidEmployee;
	}
	
	public String findEmployeeContractType(int idEmp){
		return employeeDAO.getEmployeeType(idEmp);
	}

}
