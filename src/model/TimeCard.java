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
@Table(name = "TimeCard")
public class TimeCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idEmp", referencedColumnName = "id")
	private HourlyPaidEmployee hourlyPaidEmployee;

	private Date selectedDate;
	private float workedHours;
	
	public TimeCard() {
	}

	public float getWorkedHours() {
		return workedHours;
	}

	public void setWorkedHours(float workedHours) {
		this.workedHours = workedHours;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public HourlyPaidEmployee getHourlyPaidEmployee() {
		return hourlyPaidEmployee;
	}

	public void setHourlyPaidEmployee(HourlyPaidEmployee hourlyPaidEmployee) {
		this.hourlyPaidEmployee = hourlyPaidEmployee;
	}

}
