package model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Deposit")
public class Deposit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private float grossProfit;
	private float netWorth;
	private float dues;
	private Date date;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmp", referencedColumnName = "id")
	private Employee employee;

	public Deposit() {
	}

	public float getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(float grossProfit) {
		this.grossProfit = grossProfit;
	}

	public float getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(float netWorth) {
		this.netWorth = netWorth;
	}

	public float getDues() {
		return dues;
	}

	public void setDues(float dues) {
		this.dues = dues;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
