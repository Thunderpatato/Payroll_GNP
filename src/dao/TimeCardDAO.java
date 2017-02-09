package dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.HourlyPaidEmployee;
import model.TimeCard;

@Stateless
public class TimeCardDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void submitTimeCard(TimeCard timeCard) {
		HourlyPaidEmployee hourlyPaidEmployee = entityManager.find(HourlyPaidEmployee.class,
				timeCard.getHourlyPaidEmployee().getId());
		timeCard.setHourlyPaidEmployee(hourlyPaidEmployee);
		entityManager.persist(timeCard);
		entityManager.flush();
	}

	public List<TimeCard> findAllTimeCards() {
		List<TimeCard> timeCards = entityManager.createQuery("select e from TimeCard e", TimeCard.class)
				.getResultList();
		return timeCards;
	}

	public List<TimeCard> findAllTheTimeCards(int idEmp) {
		List<TimeCard> timeCards = entityManager
				.createQuery("select e from TimeCard e where idEmp= " + idEmp, TimeCard.class).getResultList();
		return timeCards;
	}

	public List<TimeCard> findAllTheTimeCardsBetweenDates(int idEmp, Date startingDate, Date endingDate) {
		List<TimeCard> timeCards = entityManager.createQuery("select e from TimeCard e where idEmp = " + idEmp
				+ " and (selectedDate between '" + startingDate + "' and '" + endingDate + "')", TimeCard.class)
				.getResultList();
		return timeCards;
	}
}
