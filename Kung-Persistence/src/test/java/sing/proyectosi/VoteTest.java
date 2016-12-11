package sing.proyectosi;


import static org.junit.Assert.assertEquals;
import static sing.proyectosi.TransactionUtils.doTransaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
		super.renewConnection();
	}
	
	
	@Test
	public void testCreateVoto() throws SQLException {
		final Vote vo = new Vote();	
		/*Statement statement = jdbcConnection.createStatement();
		doTransaction(emf, v -> {			
			    vo.setIdproduction(1);
				vo.setEmail("muerte@muerte.com");
				vo.setRating(3);				
				v.persist(vo);
		});*/
		vo.setEmail("mmmmmmmm");
		vo.setIdproduction(2);
		vo.setRating(1);		
		
		Statement statement = jdbcConnection.createStatement();
		int id = statement.executeUpdate(
				"INSERT INTO vote(idproduction,rating,email) "
				+ "values("+ vo.getIdproduction() + "," + vo.getRating()+ ",'" +vo.getEmail()+"')", 
				Statement.RETURN_GENERATED_KEYS);
	
			
		ResultSet rs = statement.executeQuery(
				"SELECT COUNT(*) as total FROM vote WHERE idvoto = " + vo.getIdVoto());
		rs.next();
		
		assertEquals(1, rs.getInt("total"));

	}
	
	
	

}
