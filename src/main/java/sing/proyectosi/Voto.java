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
	private int idUser;
	private int votacion;
	
		
	@ManyToMany(mappedBy="voto")
	private Set<Project> projects = new HashSet<>();
	
	public void setVotacion(int votacion) {
		this.votacion = votacion;
	}
	
	public int getVotacion() {
		return votacion;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public int getId() {
		return idVoto;
	}

	
}
