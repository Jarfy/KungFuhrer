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



public class UserTest extends SQLBasedTest {

	private static EntityManagerFactory emf;

	class MiObjeto {
		private String iduser;

		private MiObjeto() {
		}

		private MiObjeto(String iduser) {
			this.iduser = iduser;
		}

	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("si-database");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (emf != null && emf.isOpen())
			emf.close();
	}

	@After
	public void renewConnectionAfterTest() throws ClassNotFoundException, SQLException {
		super.renewConnection();

	}

	private MiObjeto crearEntidades(String mail) throws SQLException {

		final User user = new User();

		Statement statUser = jdbcConnection.createStatement();
		doTransaction(emf, u -> {
			user.setEmail(mail);
			user.setLastName("prueba");
			user.setName("probeta");
			user.setPassword("buah");
			u.persist(user);
		});
		String idUser = user.getEmail();

		final MiObjeto miObj = new MiObjeto(idUser);

		return miObj;
	}

	@Test
	public void testCreateUser() throws SQLException {

		final MiObjeto miObj = this.crearEntidades("prueba3@gmail.com");

		Statement statVote = jdbcConnection.createStatement();
		ResultSet rs = statVote.executeQuery("SELECT COUNT(*) as total FROM user WHERE email= '" + miObj.iduser + "'");
		rs.next();

		assertEquals(1, rs.getInt("total"));

	}

	@Test
	public void testDeleteUser() throws SQLException {
		// prepare database for test
		final MiObjeto miObj = this.crearEntidades("prueba1@gmail.com");

		Statement statement = jdbcConnection.createStatement();
		doTransaction(emf, em -> {
			User e = em.find(User.class, miObj.iduser);
			em.remove(e);
		});

		// check
		//statement = jdbcConnection.createStatement();
		//Statement statVote = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT COUNT(*) as total FROM user WHERE email= '" + miObj.iduser + "'");
		rs.next();

		assertEquals(0, rs.getInt("total"));
	}

	@Test
	public void testUpdateProduction() throws SQLException {

		final MiObjeto miObj = this.crearEntidades("prueba2@gmail.com");

		Statement statement = jdbcConnection.createStatement();

		doTransaction(emf, em -> {
			User e = em.find(User.class, miObj.iduser);
			e.setName("la hostia de muerte");
		});

		// check
		statement = jdbcConnection.createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE email = '" + miObj.iduser + "'");
		rs.next();

		assertEquals("la hostia de muerte", rs.getString("name"));
	}

}
