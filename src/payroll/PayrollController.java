package payroll;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.DepositDAO;
import dao.EmployeeDAO;
import dao.SalesCardDAO;
import dao.TimeCardDAO;
import model.Employee;

@Stateless
public class PayrollController {

	@Inject
	EmployeeDAO employeeDAO;

	@Inject
	SalesCardDAO salesDAO;

	@Inject
	TimeCardDAO cardsDAO;

	@Inject
	DepositDAO depositDAO;

	@Inject
	PayrollFlatPaidEmployee payrollFlatPaidEmployee;

	@Inject
	PayrollHourlyPaidEmployee payrollHourlyPaidEmployee;

	private PayrollEmployee payrollEmployee;

	private int countEmployeesToPay;

	public int runPayroll(Date date) {
		List<Employee> employeesToPay = employeeDAO.findAlltoPay(date);
		countEmployeesToPay = employeesToPay.size();
		for (Employee employee : employeesToPay) {

			String contractType = employee.getContractType();

			if (contractType.equals("flat")) {
				payrollEmployee = payrollFlatPaidEmployee;
			} else {
				payrollEmployee = payrollHourlyPaidEmployee;
			}
			payrollEmployee.runPayroll(employee.getId(), date, contractType);
		}

		return countEmployeesToPay;
	}
}
