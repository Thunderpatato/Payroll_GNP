package payroll;

import java.sql.Date;

import javax.ejb.Stateless;

@Stateless
public abstract class PayrollEmployee {
	
	public abstract void addDeposit(int idEmp, Date date, float grossProfit, float dues);
	
	public abstract void setDates(int idEmp, String contractType);
	
	public abstract float getCards(int idEmp, Date date);
	
	public abstract float getSalary(int idEmp);

	public abstract float getDues(int id, Date date);
	
	public abstract void setup(int id);

	private float grossProfit;
	private float dues;
	
	// template method
	public final void runPayroll(int id, Date date, String contract) {

		setup(id);
		
		grossProfit = getCards(id, date) + getSalary(id);
		
		dues = getDues(id, date);
		
		addDeposit(id, date, grossProfit, dues);
		
		setDates(id, contract);
				
	}

}
