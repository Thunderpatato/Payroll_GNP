package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "HourlyPaidEmployee")
@DiscriminatorValue("hourly")
public class HourlyPaidEmployee extends Employee {
	private float hourRate;

	public HourlyPaidEmployee(String firstName, String lastName, float hourRate) {
		super(firstName, lastName);
		this.setHourRate(hourRate);
	}

	public HourlyPaidEmployee() {
	}

	public float getHourRate() {
		return hourRate;
	}

	public void setHourRate(float hourRate) {
		this.hourRate = hourRate;
	}

}
