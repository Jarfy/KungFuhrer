package sing.proyectosi;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Production")
public class Production {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idproduction")
	private int idProduction;	
	private String title;
	private Date year;
	private String genre;
	private String released;
	private int runtime;
	private String plot;
	private String languaje;
	//type of product
	private String type;
	private int totalSeason;
	private int episode;
	
	@OneToMany(mappedBy="production",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Vote> votes = new HashSet<>();
	
	@ManyToMany(mappedBy="productions")	
	private Set<Persons> persons = new HashSet<>();
		
	public int getIdProduction() {
		return idProduction;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRelease() {
		return released;
	}

	public void setRelease(String release) {
		this.released = release;
	}

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getLanguaje() {
		return languaje;
	}

	public void setLanguaje(String languaje) {
		this.languaje = languaje;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTotalSeason() {
		return totalSeason;
	}

	public void setTotalSeason(int totalSeason) {
		this.totalSeason = totalSeason;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}


	public String getReleased() {
		return released;
	}


	public void setReleased(String released) {
		this.released = released;
	}

	
	//metodos de relaciones
	public Set<Vote> getVotes() {
		return votes;
	}


	public void setVotes(Set<Vote> votes) {
		this.votes = votes;
	}


	public Set<Persons> getPersons() {
		return persons;
	}


	public void setPersons(Set<Persons> persons) {
		this.persons = persons;
	}
	
}
