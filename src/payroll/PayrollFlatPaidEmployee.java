package payroll;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controller.DateController;
import dao.EmployeeDAO;
import dao.SalesCardDAO;
import dao.DepositDAO;
import model.Deposit;
import model.FlatPaidEmployee;
import model.SalesCard;

@Stateless
public class PayrollFlatPaidEmployee extends PayrollEmployee {

	@Inject
	SalesCardDAO salesCardDAO;

	@Inject
	EmployeeDAO employeeDAO;

	@Inject
	DepositDAO depositDAO;

	@Inject
	DateController dateController;

	private Deposit deposit;

	private FlatPaidEmployee flatPaidEmployee;

	@Override
	public void addDeposit(int idEmp, Date date, float grossProfit, float dues) {
		deposit = new Deposit();
		deposit.setDate(date);
		deposit.setDues(dues);
		deposit.setGrossProfit(grossProfit);
		deposit.setNetWorth(grossProfit - dues);
		deposit.setEmployee(flatPaidEmployee);
		depositDAO.add(deposit);
	}

	@Override
	public void setDates(int idEmp, String contractType) {
		if (dateController.nextNextFriday().before(dateController.endDateOfThisMonth())) {
			flatPaidEmployee.setNextPayment(dateController.nextNextFriday());
		} else {
			flatPaidEmployee.setNextPayment(dateController.endDateOfThisMonth());
		}

		flatPaidEmployee.setLastPayment(dateController.today());

		employeeDAO.modifyByAdmin(flatPaidEmployee);
	}

	@Override
	public float getCards(int idEmp, Date date) {
		float totalSales = 0;
		List<SalesCard> receipts = salesCardDAO.findAllTheSalesCards(idEmp, flatPaidEmployee.getLastPayment(), date);
		for (SalesCard salesCard : receipts) {
			totalSales = totalSales + salesCard.getSale() * flatPaidEmployee.getCommissionRate();
		}
		return totalSales;
	}

	@Override
	public float getSalary(int idEmp) {
		return flatPaidEmployee.getMonthlySalary();
	}

	@Override
	public float getDues(int idEmp, Date date) {
		float monthlyDues = 0;
		if (flatPaidEmployee.isInUnion() == Boolean.TRUE) {
			monthlyDues = (flatPaidEmployee.getWeeklyDues() * 4) + flatPaidEmployee.getTotalDues();
			employeeDAO.modifyDues(idEmp, 0);
		}
		return monthlyDues;
	}

	@Override
	public void setup(int idEmp) {
		flatPaidEmployee = employeeDAO.findFlatPaidEmployeeById(idEmp);
	}

}
