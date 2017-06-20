package sing.kung.webapp;

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
		return em.createQuery("select p FROM Production p", Production.class).getResultList();
	}

	
	@Command
	@NotifyChange("productions")
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
	@NotifyChange({"productions", "currentProduction"})
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
	
	
	@Command
	@NotifyChange("currentProduction")
	public void productionDetail(@BindingParam("e") Production production) {
		this.currentProduction = production;
	}
	
	//funcion buscador simple
		public List<Production> getSearchSimple(String ptitle, String pGenre, String pLanguaje, String pType ) {
			EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
			return em.createQuery("select p FROM Production p where title like :title", Production.class)
					.setParameter("title", "'%" + ptitle + "%'")				
					.getResultList();
		}
	
	//funcion buscador  avanzado
	public List<Production> getSearchAdvanced(String ptitle, String pGenre, String pLanguaje, String pType ) {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("select p FROM Production p where title like :title or genre like :genre or languaje like :languaje or type like :type", Production.class)
				.setParameter("title", "'%" + ptitle + "%'")
				.setParameter("genre", "'%" + pGenre + "%'")
				.setParameter("languaje", "'%" + pLanguaje + "%'")
				.setParameter("type", "'%" + pType + "%'")
				.getResultList();
	}

}



