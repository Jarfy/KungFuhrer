package sing.kung.webapp.utilities;

import java.util.List;
import javax.persistence.EntityManager;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import sing.proyectosi.Vote;
import sing.proyectosi.Production;
import sing.proyectosi.TransactionUtils;
import sing.kung.webapp.utilities.DesktopEntityManagerManager;

import sing.proyectosi.Persons;

public class VoteVM {
	
	private int rating;
	private int idUser;
	private int idProduction;
	private Vote currentVote = null;

	public VoteVM() {
		// TODO Auto-generated constructor stub
	}

	public Vote getCurrentVote() {
		return currentVote;
	}
	
	
	public List<Vote> getVote() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		return em.createQuery("SELECT * FROM vote", Vote.class).getResultList();
	}	
	
	@Command
	@NotifyChange("currentVote")
	public void newVote() {
		this.currentVote = new Vote();
	}
	
	@Command
	@NotifyChange({"rating", "idUser", "idProduction"})
	public void save() {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		
		Vote vote = new Vote();
		
		vote.setRating(this.rating);
		vote.setIdProduction(this.idProduction); 
		vote.setIdUser(this.idUser); 
		
		
		TransactionUtils.doTransaction(em, __ -> {
			em.persist(vote);
		});
		
		this.rating = 0;
		this.idProduction = 0;
		this.idUser = 0;		
	}
	
	
	@Command
	@NotifyChange("votes")
	public void delete(@BindingParam("e") Vote vote) {
		EntityManager em = DesktopEntityManagerManager.getDesktopEntityManager();
		TransactionUtils.doTransaction(em, __ -> {
			em.remove(vote);
		});
	}
	
	@Command
	@NotifyChange("currentVote")
	public void edit(@BindingParam("e") Vote vote) {
		this.currentVote = vote;
	}
	
	
	//metodos
	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public int getIdProduction() {
		return idProduction;
	}


	public void setIdProduction(int idProduction) {
		this.idProduction = idProduction;
	}
	
	
}
