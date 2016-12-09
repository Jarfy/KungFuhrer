package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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
				pro.setTitle("serie muerte 1");
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
		/*pro.setTitle("serie muerte 1");
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
		int id = statement.executeUpdate(
				"INSERT INTO Production(title,year,genre,released,runtime,plot,languaje,type,totalSeason,episode) "
				+ "values('"+pro.getTitle() + "," + pro.getYear() + "," +pro.getGenre() + "," +pro.getRelease() +
				"," + pro.getRuntime()+"," + pro.getPlot()+"," + pro.getLanguaje()+ "," + pro.getType()+ 
				"," + pro.getTotalSeason()+ "," +  pro.getEpisode()+"')", 
				Statement.RETURN_GENERATED_KEYS);*/
		
			
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM Production WHERE idproduction = "+pro.getIdProduction());
		rs.next();
		
		assertEquals(1, rs.getInt("total"));

	}
	
	

}
