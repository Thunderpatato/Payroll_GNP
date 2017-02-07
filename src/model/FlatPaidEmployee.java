package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "FlatPaidEmployee")
@DiscriminatorValue("flat")
public class FlatPaidEmployee extends Employee {
	private Float monthlySalary;
	private Float commissionRate;

	public FlatPaidEmployee() {
	}

	public FlatPaidEmployee(String firstName, String lastName, Float monthlySalary, Float commissionRate) {
		super(firstName, lastName);
		this.monthlySalary = monthlySalary;
		this.commissionRate = commissionRate;
	}

	public Float getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Float monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Float getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Float commissionRate) {
		this.commissionRate = commissionRate;
	}

}
