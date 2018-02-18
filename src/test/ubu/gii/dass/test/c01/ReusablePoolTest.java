/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author alumno
 *
 */
public class ReusablePoolTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool reusablePool = ReusablePool.getInstance();
		assertNotNull(reusablePool);
		assertEquals(reusablePool, ReusablePool.getInstance());
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * @throws NotFreeInstanceException 
	 */
	@Test (expected = NotFreeInstanceException.class)
	public void testAcquireReusable() throws NotFreeInstanceException {
		 ReusablePool pool = ReusablePool.getInstance();
		 Reusable r1 = pool.acquireReusable();
		 assertNotNull(r1);
		 assertTrue( r1 instanceof Reusable);
		 
		 while(true) {
			 pool.acquireReusable();
		 }
		 
	}
	
	// Es una forma de manejar excepciones en los test de JUnit
	@Rule
	public ExpectedException dupicatedInstanceException = ExpectedException.none();
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws DuplicatedInstanceException 
	 */
	@Test //(expected = DuplicatedInstanceException.class) // Esta es otra forma, descomentar para probar
	public void testReleaseReusable() throws DuplicatedInstanceException, NotFreeInstanceException {
		
		ReusablePool pool = ReusablePool.getInstance();
		Reusable r1 = pool.acquireReusable();

		pool.releaseReusable(r1); //No deberia dar error
		
		dupicatedInstanceException.expect(DuplicatedInstanceException.class); //Comentar para probar la otra forma
		pool.releaseReusable(r1); //Deberia saltar una excepcion
	}

}
