package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.Hibernate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class VoteTest extends SQLBasedTest {
	
	private static EntityManagerFactory emf;
	
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
		//jdbcConnection.setAutoCommit(false);
		//jdbcConnection.rollback();
		super.renewConnection();
		
	}
	
	
	
	/*@Test
	public void testCreateVoto() throws SQLException {
		final Vote vo = new Vote();
		final Production pro = new Production();	
		final User user = new User();
		
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
		
		int idPro = statPro.RETURN_GENERATED_KEYS;	
		
		Statement statUser = jdbcConnection.createStatement();
		doTransaction(emf, u -> {			
			    user.setEmail("email@email.com");	
			    user.setLastName("prueba");
			    user.setName("probeta");
			    user.setPassword("buah"); 
				u.persist(user);
		});
		int iduser= statUser.RETURN_GENERATED_KEYS;
		
		Statement statVote = jdbcConnection.createStatement();
		doTransaction(emf, v -> {			
			vo.setProduction( pro);
			vo.setRating(1);		
			vo.setUser( user);
			v.persist(vo);
	    });
		int idVote= statVote.RETURN_GENERATED_KEYS;	

		
		Statement statement = jdbcConnection.createStatement();
		int id = statement.executeUpdate(
				"INSERT INTO vote(rating,idProduction,email_user) "
				+ "values("+ vo.getRating() + "," + vo.getIdproduction()+ ",'" +vo.getEmail()+"')", 
				Statement.RETURN_GENERATED_KEYS);
	
		Statement statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM vote WHERE idvoto = " + idVote);
		rs.next();
		
		assertEquals(1, rs.getInt("total"));

	}*/
	
	
	@Test
	public void testDeleteVote() throws SQLException {
		
		final Vote vo = new Vote();
		final Production pro = new Production();	
		final User user = new User();
		
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
		
		int idPro = statPro.RETURN_GENERATED_KEYS;	
		
		Statement statUser = jdbcConnection.createStatement();
		doTransaction(emf, u -> {			
			    user.setEmail("email@email.com");	
			    user.setLastName("prueba");
			    user.setName("probeta");
			    user.setPassword("buah"); 
				u.persist(user);
		});
		int idUser= statUser.RETURN_GENERATED_KEYS;
		
		Statement statVote = jdbcConnection.createStatement();
		doTransaction(emf, v -> {			
			vo.setProduction( pro);
			vo.setRating(1);		
			vo.setUser( user);
			v.persist(vo);
	    });
		int idVote= statVote.RETURN_GENERATED_KEYS;	
		
		
		doTransaction(emf, em -> {
			Production e = em.find(Production.class, idPro);
			em.remove(e);
		});
		
		doTransaction(emf, em -> {
			User us = em.find(User.class, idUser);
			em.remove(us);
		});
		
		doTransaction(emf, em -> {		
			Vote vot = em.find(Vote.class, idVote);
			em.remove(vot);
		});
		
		//check
		Statement statement = jdbcConnection.createStatement();
		statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM Production WHERE idproduction = "+idPro);
		rs.next();
		
		assertEquals(0, rs.getInt("total"));
	}

}
