package sing.kung.webapp.utilities;

import java.util.List;
import javax.persistence.EntityManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import sing.proyectosi.User;
import sing.proyectosi.TransactionUtils;
import sing.kung.webapp.utilities.DesktopEntityManagerManager;


public class UserVM {

	public UserVM() {
		// TODO Auto-generated constructor stub
	}

	
	private User currentUser = null;

	
	public User getCurrentUser() {
		return currentUser;
	}
	
	
	public List<User> getUser() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("SELECT * FROM user", User.class).getResultList();
	}

	
	@Command
	@NotifyChange("user")
	public void delete(@BindingParam("e") User user) {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.remove(user);
		});
	}
	
	@Command
	@NotifyChange("currentUser")
	public void newUser() {
		this.currentUser = new User();
	}
	
	
	@Command
	@NotifyChange({"user", "currentUser"})
	public void save() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.persist(this.currentUser);
		});
		this.currentUser = null;
	}
	
	@Command
	@NotifyChange("currentUser")
	public void cancel() {
		this.currentUser = null;
	}
	
	@Command
	@NotifyChange("currentUser")
	public void edit(@BindingParam("e") User user) {
		this.currentUser = user;
	}
}
