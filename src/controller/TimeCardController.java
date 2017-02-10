package controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import dao.TimeCardDAO;
import date.DateUtils;
import model.TimeCard;

@Stateless
public class TimeCardController {

	@Inject
	TimeCardDAO timeCardDAO;

	@Inject
	DateUtils dateUtils;

	Logger logger = Logger.getLogger(TimeCardController.class);

	@PostConstruct
	public void init() {

	}

	public void submitTimeCard(TimeCard timeCard){
		Date date = dateUtils.today();
		timeCard.setSelectedDate(date);
		List<TimeCard> timeCards = timeCardDAO.findAllTheTimeCardsBetweenDates(timeCard.getHourlyPaidEmployee().getId(), date, date);
		if (timeCards.isEmpty()){
			timeCardDAO.submitTimeCard(timeCard);
		}
	}
}
