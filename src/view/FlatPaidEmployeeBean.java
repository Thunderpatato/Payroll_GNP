package view;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import controller.EmployeeController;
import controller.SalesCardController;
import model.FlatPaidEmployee;
import model.SalesCard;

@Named("flatPaidEmployeeBean")
@SessionScoped
@ManagedBean
public class FlatPaidEmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EmployeeController flatPaidEmployeeController;

	@Inject
	SalesCardController salesCardController;

	private SalesCard salesCard;

	private FlatPaidEmployee flatPaidEmployee;

	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		int id = (Integer) session.getAttribute("id");
		flatPaidEmployee = flatPaidEmployeeController.findFlatPaidEmployeeById(id);
		salesCard = new SalesCard();
	}

	public String updateInfo(){
		try{
			int idEmp = flatPaidEmployee.getId();
			String paymentMethod = flatPaidEmployee.getPaymentMethod();
			String postalAddress = flatPaidEmployee.getPostalAddress();
			String bankAccount = flatPaidEmployee.getBankAccount();
			flatPaidEmployeeController.modify(idEmp, paymentMethod, postalAddress, bankAccount);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Updated"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String submitTimeCard() {
		try {
			salesCard.setFlatPaidEmployee(flatPaidEmployee);
			salesCardController.submitSalesCard(salesCard);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("Posted", new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Posted"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public SalesCard getSalesCard() {
		return salesCard;
	}

	public void setSalesCard(SalesCard salesCard) {
		this.salesCard = salesCard;
	}

	public FlatPaidEmployee getFlatPaidEmployee() {
		return flatPaidEmployee;
	}

	public void setFlatPaidEmployee(FlatPaidEmployee flatPaidEmployee) {
		this.flatPaidEmployee = flatPaidEmployee;
	}

}
