package dao;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.FlatPaidEmployee;
import model.SalesCard;

@Stateless
public class SalesCardDAO {

	@PersistenceContext
	EntityManager entityManager;

	public void submitSalesCard(SalesCard salesCard) {
		FlatPaidEmployee flatPaidEmployee = entityManager.find(FlatPaidEmployee.class,
				salesCard.getFlatPaidEmployee().getId());
		salesCard.setFlatPaidEmployee(flatPaidEmployee);
		entityManager.persist(salesCard);
		entityManager.flush();
	}

	public List<SalesCard> findAllSalesCards() {
		List<SalesCard> salesCards = entityManager.createQuery("select e from Sales e", SalesCard.class)
				.getResultList();
		return salesCards;
	}

	public List<SalesCard> findAllTheSalesCards(int idEmp) {
		List<SalesCard> salesCards = entityManager
				.createQuery("select e from Sales e where idEmp = " + idEmp, SalesCard.class).getResultList();
		return salesCards;
	}

	public List<SalesCard> findAllTheSalesCards(int idEmp, Date startingDate, Date endingDate) {
		List<SalesCard> salesCards = entityManager.createQuery("select e from Sales e where idEmp = " + idEmp
				+ " and (selectedDate between '" + startingDate + "' and '" + endingDate + "')", SalesCard.class)
				.getResultList();
		return salesCards;
	}
}
