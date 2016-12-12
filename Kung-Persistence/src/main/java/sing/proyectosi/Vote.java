package sing.proyectosi;

import javax.persistence.Entity;
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
	private int idProduction;
	private String email;
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="email")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="idproduction")
	private Production production;
	
	//constructor
	public Vote(){	}
	
	
	public int getIdVoto() {
		return idVoto;
	}
	
	public int getIdproduction() {
		return idProduction;
	}
	public void setIdproduction(int idproduction) {
		this.idProduction = idproduction;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	
	//useer
	public void setUser(User d) {
		if (this.user != null) {
			this.user.internalRemoveVote(this);
		}
		
		this.user = d;
		
		if (this.user != null) {
			this.user.internalAddVote(this);
		}
	}

	public User getUser() {
		return this.user;
	}
	
	//production
	//useer
		public void setProduction(Production  d) {
			if (this.production != null) {
				this.production.internalRemoveVote(this);
			}
			
			this.production = d;
			
			if (this.production != null) {
				this.production.internalAddVote(this);
			}
		}

		public Production getProduction() {
			return this.production;
		}

	
}
