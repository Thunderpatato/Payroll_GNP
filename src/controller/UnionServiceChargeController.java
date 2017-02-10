package controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.EmployeeDAO;
import dao.UnionServiceChargeDAO;
import model.UnionServiceCharge;

@Stateless
public class UnionServiceChargeController {
	
	@Inject
	UnionServiceChargeDAO unionServiceChargeDAO;
	
	@Inject
	EmployeeDAO employeeDAO;
	
	@Inject
	DateController dateController;
	
	@PostConstruct
	public void init() {
	}
	
	public void submitUnionServiceCharge(UnionServiceCharge unionServiceCharge){
		float previousDues = unionServiceCharge.getEmployee().getTotalDues();
		float currentDues = previousDues + unionServiceCharge.getDues();	
		employeeDAO.modifyDues(unionServiceCharge.getEmployee().getId(), currentDues);
		unionServiceCharge.setDateOfCharge(dateController.today());
		unionServiceChargeDAO.submitUnionServiceCharge(unionServiceCharge);
	}
}
