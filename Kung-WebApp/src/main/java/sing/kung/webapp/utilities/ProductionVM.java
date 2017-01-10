package sing.kung.webapp.utilities;

import java.util.List;
import javax.persistence.EntityManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import sing.proyectosi.Production;
import sing.proyectosi.TransactionUtils;
import sing.kung.webapp.utilities.DesktopEntityManagerManager;

public class ProductionVM {

	public ProductionVM() {
		// TODO Auto-generated constructor stub
	}	
	
	private Production currentProduction= null;

	
	public Production getCurrentProduction() {
		return currentProduction;
	}
	
	
	public List<Production> getProductions() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("SELECT * FROM Production", Production.class).getResultList();
	}

	
	@Command
	@NotifyChange("production")
	public void delete(@BindingParam("e") Production production) {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.remove(production);
		});
	}
	
	@Command
	@NotifyChange("currentProduction")
	public void newProduction() {
		this.currentProduction = new Production();
	}
	
	
	@Command
	@NotifyChange({"producitons", "currentProduction"})
	public void save() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.persist(this.currentProduction);
		});
		this.currentProduction = null;
	}
	
	@Command
	@NotifyChange("currentProduction")
	public void cancel() {
		this.currentProduction = null;
	}
	
	@Command
	@NotifyChange("currentProduction")
	public void edit(@BindingParam("e") Production production) {
		this.currentProduction = production;
	}
	

}



