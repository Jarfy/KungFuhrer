package sing.kung.webapp.utilities;

import java.util.List;
import javax.persistence.EntityManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import sing.proyectosi.Persons;
import sing.proyectosi.Production;
import sing.proyectosi.TransactionUtils;
import sing.kung.webapp.utilities.DesktopEntityManagerManager;

public class PersonsVM {

	public PersonsVM() {
		// TODO Auto-generated constructor stub
	}

	
	private Persons currentPerson= null;

	
	public Persons getCurrentPerson() {
		return currentPerson;
	}
	
	
	public List<Persons> getPersons() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("SELECT * FROM persons", Persons.class).getResultList();
	}

	
	@Command
	@NotifyChange("persons")
	public void delete(@BindingParam("e") Persons persons) {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.remove(persons);
		});
	}
	
	@Command
	@NotifyChange("currentPersons")
	public void newPerson() {
		this.currentPerson = new Persons();
	}
	
	
	@Command
	@NotifyChange({"persons", "currentPerson"})
	public void save() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.persist(this.currentPerson);
		});
		this.currentPerson = null;
	}
	
	@Command
	@NotifyChange("currentPerson")
	public void cancel() {
		this.currentPerson = null;
	}
	
	@Command
	@NotifyChange("currentPerson")
	public void edit(@BindingParam("e") Persons persons) {
		this.currentPerson = persons;
	}
}
