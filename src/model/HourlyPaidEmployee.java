package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "HourlyPaidEmployee")
@DiscriminatorValue("hourly")
public class HourlyPaidEmployee extends Employee {
	
	private static final long serialVersionUID = 1L;
	
	private Float hourRate;

	public HourlyPaidEmployee() {
	}

	public HourlyPaidEmployee(String firstName, String lastName, Float hourRate) {
		super(firstName, lastName);
		this.hourRate = hourRate;
	}

	public Float getHourRate() {
		return hourRate;
	}

	public void setHourRate(Float hourRate) {
		this.hourRate = hourRate;
	}

}
