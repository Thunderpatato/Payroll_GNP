package view;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import controller.AuthenticationController;

@Named("authenticationBean")
@SessionScoped
@ManagedBean
public class AuthenticationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	AuthenticationController authenticationController;

	int idEmp;
	String password;
	String userType;

	@PostConstruct
	private void Init() {
	}

	public String login() {
		try {
			userType = authenticationController.validateUser(idEmp, password);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("id", idEmp);
			System.out.println(userType);
			return userType;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public String logout() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
