package payroll;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import controller.DateController;
import dao.EmployeeDAO;
import dao.TimeCardDAO;
import dao.DepositDAO;
import model.Deposit;
import model.HourlyPaidEmployee;
import model.TimeCard;

@Stateless
public class PayrollHourlyPaidEmployee extends PayrollEmployee {

	@Inject
	EmployeeDAO employeeDAO;

	@Inject
	TimeCardDAO timeCardDAO;

	@Inject
	DepositDAO depositDAO;

	@Inject
	DateController dateController;

	private Deposit deposit;
	private HourlyPaidEmployee hourlyPaidEmployee;

	@Override
	public void setup(int idEmp) {
		hourlyPaidEmployee = employeeDAO.findHourlyPaidEmployeeById(idEmp);
	}

	@Override
	public void addDeposit(int id, Date date, float grossProfit, float dues) {
		deposit = new Deposit();
		deposit.setDate(date);
		deposit.setGrossProfit(grossProfit);
		deposit.setDues(dues);
		deposit.setNetWorth(grossProfit - dues);
		deposit.setEmployee(hourlyPaidEmployee);
		depositDAO.add(deposit);

	}

	@Override
	public float getCards(int idEmp, Date date) {

		float hours = 8;
		float extraCommission = (float) 1.5;
		float total = 0;

		List<TimeCard> timeCards = timeCardDAO.findAllTheTimeCardsBetweenDates(idEmp,
				hourlyPaidEmployee.getLastPayment(), date);
		for (TimeCard timeCard : timeCards) {
			float extraHours = 0;
			float hoursOfWork = 0;
			if (timeCard.getWorkedHours() > hours) {
				extraHours = timeCard.getWorkedHours() - hours;
				hoursOfWork = hours;
			} else {
				hoursOfWork = timeCard.getWorkedHours();
			}
			total = total + (hoursOfWork * hourlyPaidEmployee.getHourRate())
					+ (extraHours * extraCommission * hourlyPaidEmployee.getHourRate());
		}
		return total;
	}

	@Override
	public float getDues(int idEmp, Date date) {
		float monthlyDues = 0;
		if (hourlyPaidEmployee.isInUnion() == Boolean.TRUE) {
			monthlyDues = (hourlyPaidEmployee.getWeeklyDues() * 4) + hourlyPaidEmployee.getTotalDues();
			employeeDAO.modifyDues(idEmp, 0);
		}
		return monthlyDues;
	}

	@Override
	public void setDates(int idEmp, String contractType) {
		hourlyPaidEmployee.setNextPayment(dateController.nextFriday());
		hourlyPaidEmployee.setLastPayment(dateController.today());
		employeeDAO.modifyByAdmin(hourlyPaidEmployee);
	}

	@Override
	public float getSalary(int idEmp) {
		return 0;
	}

}
