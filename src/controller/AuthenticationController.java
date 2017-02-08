package controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import dao.AuthenticationDAO;
import dao.EmployeeDAO;

@Stateless
public class AuthenticationController {

	@Inject
	AuthenticationDAO authenticationDAO;

	@Inject
	EmployeeDAO employeeDAO;

	Logger logger = Logger.getLogger(AuthenticationController.class);

	@PostConstruct
	public void init() {
		logger.info("Authentication Controller is up!");
	}

	public String validateUser(int idEmp, String password) {

		String userType = authenticationDAO.validate(idEmp, password);

		if (!userType.equals("admin")) {
			userType = employeeDAO.getEmployeeType(idEmp);
		}

		return userType;
	}

}
