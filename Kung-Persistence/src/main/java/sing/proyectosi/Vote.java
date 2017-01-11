package sing.proyectosi;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne; 
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVoto;		
	private int rating;

	//@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@ManyToOne()
	@JoinColumn(name="email_user")
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="idProduction")
	private Production production;
	
	public int getIdVoto() {
		return idVoto;
	}
	
	public int getIdproduction() {
		return production.getIdProduction();
	}
	

	public String getEmail() {
		return user.getEmail();
	}	
	/*
	public void setEmail(String email) {
		this.user.setEmail(email); 
	}*/
		
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
	

	public Production getProduction() {
		return production;
	}


	public void setProduction(Production production) {
		this.production = production;
	}

	


	
}
