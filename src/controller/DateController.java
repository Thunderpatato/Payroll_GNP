package controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import javax.ejb.Stateless;

@Stateless
public class DateController {

	private LocalDate endOfThisMonth(LocalDate date) {
		date = date.with(TemporalAdjusters.lastDayOfMonth());
		if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			date = date.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		}
		return date;
	}

	public Date endDateOfThisMonth() {
		LocalDate date = LocalDate.now();
		return Date.valueOf(endOfThisMonth(date));

	}

	public Date endDateOfNextMonth() {
		LocalDate date = LocalDate.now();
		date.plusMonths(1);
		return Date.valueOf(endOfThisMonth(date));
	}

	public Date today() {
		LocalDate date = LocalDate.now();
		return Date.valueOf(date);
	}

	public Date yesterday() {
		LocalDate date = LocalDate.now();
		return Date.valueOf(date.minusDays(1));
	}

	public Date nextFriday() {
		LocalDate date = LocalDate.now();
		return Date.valueOf(date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
	}

	public Date nextNextFriday() {
		LocalDate date = LocalDate.now();
		return Date.valueOf(
				date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
	}

}
