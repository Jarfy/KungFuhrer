package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProductionTest extends SQLBasedTest {
	
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
		super.renewConnection();
			
	}
	
	
	@Test
	public void testCreateProduction() throws SQLException {
		final Production pro = new Production();	
		Statement statement = jdbcConnection.createStatement();
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
			
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM Production WHERE idproduction = "+pro.getIdProduction());
		rs.next();
		
		assertEquals(1, rs.getInt("total"));

	}
	
	
	@Test
	public void testUpdateProduction() throws SQLException {
		//prepare database for test
		final Production pro = new Production();			
		
		pro.setTitle("prueba modificar");
		pro.setEpisode(1);
		pro.setGenre("destruccion");
		pro.setLanguaje("arameo");
		pro.setPlot("resumen de como destruyo el mundo con el metal de fondo");
		pro.setRelease("6666");
		pro.setRuntime(666);
		pro.setTotalSeason(6);
		pro.setType("serie");
		pro.setYear(null);
				
		Statement statement = jdbcConnection.createStatement();
		statement.executeUpdate(
				"INSERT INTO Production(title,genre,released,runtime,plot,languaje,type,totalSeason,episode) "
				+ "values('"+pro.getTitle() + "','" +pro.getGenre() + "','" +pro.getRelease() +
				"','" + pro.getRuntime()+"','" + pro.getPlot()+"','" + pro.getLanguaje()+ "','" + pro.getType()+ 
				"','" + pro.getTotalSeason()+ "','" +  pro.getEpisode()+"')", 
				Statement.RETURN_GENERATED_KEYS);
		
		int id = getLastInsertedId(statement);
		
		doTransaction(emf, em -> {
			Production e = em.find(Production.class, id);
			e.setTitle("prueba modificar modificada");			
		});
		
		//check
		statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT * FROM Production WHERE idproduction = "+id);
		rs.next();
		
		assertEquals("prueba modificar modificada", rs.getString("title"));
		assertEquals(id, rs.getInt("idproduction"));
		
	}
	
	
	@Test
	public void testDeleteProduction() throws SQLException {
		//prepare database for test
		final Production pro = new Production();			
		
		pro.setTitle("prueba eliminar");
		pro.setEpisode(1);
		pro.setGenre("destruccion");
		pro.setLanguaje("arameo");
		pro.setPlot("resumen de como destruyo el mundo con el metal de fondo");
		pro.setRelease("6666");
		pro.setRuntime(666);
		pro.setTotalSeason(6);
		pro.setType("serie");
		pro.setYear(null);
				
		Statement statement = jdbcConnection.createStatement();
		statement.executeUpdate(
				"INSERT INTO Production(title,genre,released,runtime,plot,languaje,type,totalSeason,episode) "
				+ "values('"+pro.getTitle() + "','" +pro.getGenre() + "','" +pro.getRelease() +
				"','" + pro.getRuntime()+"','" + pro.getPlot()+"','" + pro.getLanguaje()+ "','" + pro.getType()+ 
				"','" + pro.getTotalSeason()+ "','" +  pro.getEpisode()+"')", 
				Statement.RETURN_GENERATED_KEYS);
		
		int id = getLastInsertedId(statement);
		
		doTransaction(emf, em -> {
			Production e = em.find(Production.class, id);
			em.remove(e);
		});
		
		//check
		statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM Production WHERE idproduction = "+id);
		rs.next();
		
		assertEquals(0, rs.getInt("total"));
	}
	
	@Test
	public void testListProduction() throws SQLException {
		//prepare database for test
		final Production pro1 = new Production();			
		
		pro1.setTitle("prueba para listar 1");
		pro1.setEpisode(1);
		pro1.setGenre("destruccion");
		pro1.setLanguaje("arameo");
		pro1.setPlot("resumen de como destruyo el mundo con el metal de fondo");
		pro1.setRelease("6666");
		pro1.setRuntime(666);
		pro1.setTotalSeason(6);
		pro1.setType("serie");
		pro1.setYear(null);
		
		//production 2
		final Production pro2 = new Production();			
		
		pro2.setTitle("prueba para listar 2");
		pro2.setEpisode(1);
		pro2.setGenre("destruccion");
		pro2.setLanguaje("arameo");
		pro2.setPlot("resumen de como destruyo el mundo con el metal de fondo");
		pro2.setRelease("6666");
		pro2.setRuntime(666);
		pro2.setTotalSeason(6);
		pro2.setType("serie");
		pro2.setYear(null);
				
		Statement statement1 = jdbcConnection.createStatement();
		statement1.executeUpdate(
				"INSERT INTO Production(title,genre,released,runtime,plot,languaje,type,totalSeason,episode) "
				+ "values('"+pro1.getTitle() + "','" +pro1.getGenre() + "','" +pro1.getRelease() +
				"','" + pro1.getRuntime()+"','" + pro1.getPlot()+"','" + pro1.getLanguaje()+ "','" + pro1.getType()+ 
				"','" + pro1.getTotalSeason()+ "','" +  pro1.getEpisode()+"')", 
				Statement.RETURN_GENERATED_KEYS);
		
		Statement statement2 = jdbcConnection.createStatement();
		statement2.executeUpdate(
				"INSERT INTO Production(title,genre,released,runtime,plot,languaje,type,totalSeason,episode) "
				+ "values('"+pro2.getTitle() + "','" +pro2.getGenre() + "','" +pro2.getRelease() +
				"','" + pro2.getRuntime()+"','" + pro2.getPlot()+"','" + pro2.getLanguaje()+ "','" + pro2.getType()+ 
				"','" + pro2.getTotalSeason()+ "','" +  pro2.getEpisode()+"')", 
				Statement.RETURN_GENERATED_KEYS);
		
		List<Production> produc = emf.createEntityManager()
			.createQuery("SELECT e FROM Production e ORDER BY e.title", Production.class)
			.getResultList();
		
		//check
		assertEquals(2, produc.size());
		assertEquals("prueba para listar 1", produc.get(0).getTitle());
		assertEquals("prueba para listar 2", produc.get(1).getTitle());
	}
	
	

}
