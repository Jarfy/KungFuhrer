package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonsTest extends SQLBasedTest {
	
	private static EntityManagerFactory emf;
	
	class MiObjeto{		
		private int idPerson;
		private int idPro;
		
		private MiObjeto( ){}
		
		private MiObjeto(int idPerson){
			this.idPerson = idPerson;
		}
		
		private MiObjeto(int idPerson,int idPro){
			this.idPerson = idPerson;	
			this.idPro = idPro;
		}
	
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("si-database");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if(emf!=null && emf.isOpen()) emf.close();
	}
	
	@After
	public void renewConnectionAfterTest() throws ClassNotFoundException, SQLException {
		super.renewConnection();
			
	}
	
	
	
	private MiObjeto crearEntidades() throws SQLException {			
		final Production pro = new Production();	
		final Persons per = new Persons();	
		
		Set<Production> productions = new HashSet<Production>();
		
		
		
		Statement statPro= jdbcConnection.createStatement();
		doTransaction(emf, p -> {
				pro.setTitle("prueba de insertar");
				pro.setEpisode(1);
				pro.setGenre("destruccion");
				pro.setLanguaje("arameo");
				pro.setPlot("resumen de como destruyo el mundo con el metal de fondo");
				pro.setRelease("6666");
				pro.setRuntime(666);
				pro.setTotalSeason(6);
				pro.setType("serie");
				pro.setYear(null);
				p.persist(pro);
		});
		
		productions.add(pro);
		int idPro = statPro.RETURN_GENERATED_KEYS;	
		
		
		for (Production i : productions ){
			System.out.println("hola: " + i.getGenre());
		}	
		
		Statement statUser = jdbcConnection.createStatement();
		doTransaction(emf, u -> {			
			per.setLastName("mierda escoria");	
			per.setName("josito");
//			per.setProductions(productions);		
			per.setProductions(null);
			u.persist(per);
		});
		int idPerson = statUser.RETURN_GENERATED_KEYS;	
		
		
		final MiObjeto miObj = new MiObjeto(idPerson,idPro);
		
		return  miObj;
	}
	
	@Test
	public void testCreatePerson() throws SQLException {	
		
		final MiObjeto miObj = this.crearEntidades();	
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM persons WHERE idperson = "+ miObj.idPerson);
		rs.next();
		
		assertEquals(1, rs.getInt("total"));

	}
	
	
	@Test
	public void testUpdateProduction() throws SQLException {
		
		final MiObjeto miObj = this.crearEntidades();	
		
		Statement statement = jdbcConnection.createStatement();
		
		doTransaction(emf, em -> {
			Persons e = em.find(Persons.class, miObj.idPerson);
			e.setName("la hostia de muerte");			
		});
		
		//check
		statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT * FROM persons WHERE idPerson = '" + miObj.idPerson + "'");
		rs.next();
		
		assertEquals("la hostia de muerte", rs.getString("name"));
		
	}
	
	
	@Test
	public void testDeleteUser() throws SQLException {
		//prepare database for test
		final MiObjeto miObj = this.crearEntidades();		
		
		
		Statement statement = jdbcConnection.createStatement();
		doTransaction(emf, em -> {
			Persons e = em.find(Persons.class, miObj.idPerson);
			em.remove(e);
		});
		
		//check
		statement = jdbcConnection.createStatement();
		Statement statVote = jdbcConnection.createStatement();
		ResultSet rs = statVote.executeQuery(
				"SELECT COUNT(*) as total FROM persons WHERE idPerson= "+miObj.idPerson );
		rs.next();
		
		assertEquals(0, rs.getInt("total"));
	}

	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	