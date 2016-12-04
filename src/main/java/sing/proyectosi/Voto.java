package sing.proyectosi;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Voto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVoto;	
	
	private int idproduction;
	private int email;
	private int rating;
	
	
	public int getIdVoto() {
		return idVoto;
	}
	
	public int getIdproduction() {
		return idproduction;
	}
	public void setIdproduction(int idproduction) {
		this.idproduction = idproduction;
	}
	public int getEmail() {
		return email;
	}
	public void setEmail(int email) {
		this.email = email;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
		


	
}
