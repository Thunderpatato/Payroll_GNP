package controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import dao.SalesCardDAO;
import date.DateUtils;
import model.SalesCard;

@Stateless
public class SalesCardController {
	@Inject
	SalesCardDAO salesCardDAO;

	@Inject
	DateUtils dateUtils;

	Logger logger = Logger.getLogger(SalesCardController.class);

	@PostConstruct
	public void init() {

	}

	public void submitSalesCard(SalesCard salesCard) {
		salesCard.setSelectedData(dateUtils.today());
		salesCardDAO.submitSalesCard(salesCard);
	}
}
