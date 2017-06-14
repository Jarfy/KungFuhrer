package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	
	@Test
	public void tesCrearVoteRelacion() throws SQLException {		
		final Vote vo = new Vote();
		final Production pro = new Production();	
		final User user = new User();		
		
		Statement statement= jdbcConnection.createStatement();
		
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
		
		
		statement = jdbcConnection.createStatement();
		ResultSet resultado = statement.executeQuery(
				"SELECT count(*) as total FROM Production WHERE idProduction = "+ pro.getIdProduction());
		resultado.next();
		
		
		assertEquals(1, resultado.getInt("total"));
		
		doTransaction(emf, u -> {			
		    user.setEmail("email@email.com");	
		    user.setLastName("prueba");
		    user.setName("probeta");
		    user.setPassword("buah"); 
			u.persist(user);
		});
		
		resultado  = statement.executeQuery(
				"SELECT count(*) as total FROM user WHERE email = '" + user.getEmail() +"'");
		resultado.next();
		
		
		assertEquals(1, resultado.getInt("total"));
		
		doTransaction(emf, v -> {			
			vo.setProduction( pro);
			vo.setRating(1);		
			vo.setUser( user);
			v.persist(vo);
	    });
		
		
		
		resultado  = statement.executeQuery(
				"SELECT count(*) as total FROM vote WHERE idVoto = " + vo.getIdVoto() + " and email_user = '" + user.getEmail() + "' and idProduction = "+ pro.getIdProduction());
		resultado.next();
		
		assertEquals(1, resultado.getInt("total"));
		
	} 
	
	@Test
	public void tesUpdateVote() throws SQLException {		
		final Vote vo = new Vote();
		final Production pro = new Production();	
		final User user = new User();		
		
		Statement statement= jdbcConnection.createStatement();
		
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
		
		
		statement = jdbcConnection.createStatement();
		ResultSet resultado = statement.executeQuery(
				"SELECT count(*) as total FROM Production WHERE idProduction = "+ pro.getIdProduction());
		resultado.next();
		
		doTransaction(emf, u -> {			
		    user.setEmail("prueba@email.com");	
		    user.setLastName("prueba");
		    user.setName("probeta");
		    user.setPassword("buah"); 
			u.persist(user);
		});
		
		resultado  = statement.executeQuery(
				"SELECT count(*) as total FROM user WHERE email = '" + user.getEmail() +"'");
		resultado.next();
		
		doTransaction(emf, v -> {			
			vo.setProduction( pro);
			vo.setRating(1);		
			vo.setUser( user);
			v.persist(vo);
	    });
				
		resultado  = statement.executeQuery(
				"SELECT count(*) as total FROM vote WHERE idVoto = " + vo.getIdVoto() + " and email_user = '" + user.getEmail() + "' and idProduction = "+ pro.getIdProduction());
		resultado.next();		
		
		doTransaction(emf, em -> {
			Vote e = em.find(Vote.class, vo.getIdVoto());
			e.setRating(999);			
		});
		
		resultado = statement.executeQuery(
				"SELECT * FROM vote WHERE idvoto = "+  vo.getIdVoto());
		resultado.next();
		
		assertEquals("999", resultado.getString("rating"));
		
	} 
	
	
	@Test
	public void testCreateVote() throws SQLException {		
		final Vote vo = new Vote();	
		
		Statement statement= jdbcConnection.createStatement();
		
		doTransaction(emf, v -> {			
			vo.setProduction( null);
			vo.setRating(1);		
			vo.setUser( null);
			v.persist(vo);
	    });
				
		ResultSet resultado  = statement.executeQuery(
				"SELECT count(*) as total FROM vote WHERE idVoto = " + vo.getIdVoto() );
		resultado.next();			
		
		
		assertEquals(1, resultado.getInt("total"));
		
	} 
	
	@Test
	public void testDeleteVote() throws SQLException {		
		final Vote vo = new Vote();	
		
		Statement statement= jdbcConnection.createStatement();
		
		doTransaction(emf, v -> {			
			vo.setProduction( null);
			vo.setRating(1);		
			vo.setUser( null);
			v.persist(vo);
	    });	
		
		doTransaction(emf, em -> {
			Vote vote = em.find(Vote.class, vo.getIdVoto());
			em.remove(vote);
		});
		
		ResultSet resultado = statement.executeQuery(
				"SELECT COUNT(*) as total FROM vote WHERE idVoto = " + vo.getIdVoto());
		resultado.next();		
		
		assertEquals(0, resultado.getInt("total"));
		
	} 

}
