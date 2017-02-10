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
import controller.TimeCardController;
import model.HourlyPaidEmployee;
import model.TimeCard;

@Named("hourlyPaidEmployeeBean")
@SessionScoped
@ManagedBean
public class HourlyPaidEmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EmployeeController employeeController;

	@Inject
	TimeCardController timeCardController;

	private TimeCard timeCard;

	private HourlyPaidEmployee hourlyPaidEmployee;
	
	@PostConstruct
	public void init() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		int id = (int) session.getAttribute("id");
		hourlyPaidEmployee = employeeController.findHourlyPaidEmployeeById(id);
		timeCard = new TimeCard();
	}

	public void updateInfo() {
		try {
			int idEmp = hourlyPaidEmployee.getId();
			String paymentMethod = hourlyPaidEmployee.getPaymentMethod();
			String postalAddress = hourlyPaidEmployee.getPostalAddress();
			String bankAccount = hourlyPaidEmployee.getBankAccount();
			employeeController.modify(idEmp, paymentMethod, postalAddress, bankAccount);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "success", "Updated"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submitTimeCard() {
		try {
			timeCard.setHourlyPaidEmployee(hourlyPaidEmployee);
			timeCardController.submitTimeCard(timeCard);
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("Posted", new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Posted"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public TimeCard getTimeCard() {
		return timeCard;
	}

	public void setTimeCard(TimeCard timeCard) {
		this.timeCard = timeCard;
	}

	public HourlyPaidEmployee getHourlyPaidEmployee() {
		return hourlyPaidEmployee;
	}

	public void setHourlyPaidEmployee(HourlyPaidEmployee hourlyPaidEmployee) {
		this.hourlyPaidEmployee = hourlyPaidEmployee;
	}

}
