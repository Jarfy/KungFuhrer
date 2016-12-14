package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class VoteTest extends SQLBasedTest {
	
	class MiObjeto{
		
		private int idPro;
		private String iduser;
		private int idVote;
		
		
		private MiObjeto( ){}
		
		private MiObjeto(int idPro,String iduser,int idVote ){
			this.idPro = idPro;
			this.iduser = iduser;
			this.idVote = idVote;
		
		}
	
	}
	
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
	
	private MiObjeto crearEntidades() throws SQLException {
		
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
		String idUser = user.getEmail();
		
		
		Statement statVote = jdbcConnection.createStatement();
		doTransaction(emf, v -> {			
			vo.setProduction( pro);
			vo.setRating(1);		
			vo.setUser( user);
			v.persist(vo);
	    });
		int idVote= statVote.RETURN_GENERATED_KEYS;	
		
		
		
		
		final MiObjeto miObj = new MiObjeto(idPro,idUser,idVote);
		
		return  miObj;
	}
	
	
//	@Test
//	public void testCreateVoto() throws SQLException {
//		final MiObjeto miobj = this.crearEntidades();	
//	
//	
//		Statement statement = jdbcConnection.createStatement();
//		ResultSet rs = statement.executeQuery(
//				"SELECT COUNT(*) as total FROM vote WHERE idvoto = " + miobj.idVote);
//		rs.next();
//		
//		assertEquals(1, rs.getInt("total"));
//
//	}
	
	
//	@Test
//	public void testDeleteVote() throws SQLException {
//		
//		final MiObjeto miobj = this.crearEntidades();
//		
//		
//		doTransaction(emf, em -> {
//			Production e = em.find(Production.class,miobj.idPro );
//			em.remove(e);
//		});
//		System.out.println("idpro: " + miobj.idPro);
//		
//		doTransaction(emf, use -> {
//			User us = use.find(User.class, miobj.iduser);
//			use.remove(us);
//		});
//		//System.out.println("idUser: " + idUser);
//		
//		
//		/*doTransaction(emf, emv -> {		
//			Vote vot = emv.find(Vote.class, idVote);
//			emv.remove(vot);
//		});
//		System.out.println("idVote: " + idVote);*/
//		
//		//check
//		Statement statement1 = jdbcConnection.createStatement();
//		statement1 = jdbcConnection.createStatement();
//		ResultSet rs1 = statement1.executeQuery(
//				"SELECT COUNT(*) as total FROM Production WHERE idproduction = "+ miobj.idPro);
//		rs1.next();
//		
//		Statement statement2 = jdbcConnection.createStatement();
//		statement2 = jdbcConnection.createStatement();
//		ResultSet rs2 = statement2.executeQuery(
//				"SELECT COUNT(*) as total FROM vote WHERE idvoto = "+ miobj.idVote);
//		rs2.next();
//		
//		Statement statement3 = jdbcConnection.createStatement();
//		statement3 = jdbcConnection.createStatement();
//		ResultSet rs3 = statement3.executeQuery(
//				"SELECT COUNT(*) as total FROM user WHERE email = '"+ miobj.iduser +"'");
//		rs3.next();
//		
//		assertEquals(0, rs1.getInt("total"));
//		assertEquals(0, rs2.getInt("total"));
//		assertEquals(0, rs3.getInt("total"));
//	}
	
	
//	@Test
//	public void testUpdateVote() throws SQLException {
//		
//		final MiObjeto miObj = this.crearEntidades();
//		
//		doTransaction(emf, em -> {
//			Vote e = em.find(Vote.class, miObj.idVote);
//			e.setRating(22);			
//		});
//		
//		//check
//		Statement statement = jdbcConnection.createStatement();
//		statement = jdbcConnection.createStatement();
//		ResultSet rs = statement.executeQuery(
//				"SELECT * FROM vote WHERE idvoto = "+miObj.idVote);
//		rs.next();
//		
//		assertEquals("22", rs.getString("rating"));
//		
//		
//	}
	
	
	
	
	
	
	
	
	

}
