/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ws.chojnacki.timetable.rxmldata;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pawel
 */
public class InMemorySequenceManagerTest {

    public InMemorySequenceManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of adjust method, of class InMemorySequenceManager.
     */
    @Test
    public void testAdjust() {
        System.out.println("adjust");
        long values[] = {5L, 225L, 100000L, 10000000000L};
        InMemorySequenceManager instance = new InMemorySequenceManager();
        
        for (long value : values) {
            instance.adjust(value);
            assertEquals(value+1, instance.current());
            assertEquals(value+1, instance.next());

        }
        
    }

    /**
     * Test of change method, of class InMemorySequenceManager.
     */
    @Test
    public void testChange() {
        System.out.println("change");
        long value = 10L;
        InMemorySequenceManager instance = new InMemorySequenceManager();
        instance.change(value);
        assertEquals(value, instance.current());
        
    }

    /**
     * Test of next method, of class InMemorySequenceManager.
     */
    @Test
    public void testNext() {
        System.out.println("next");
        InMemorySequenceManager instance = new InMemorySequenceManager();
        long expResult = 0L;
        long result = instance.next();
        assertEquals(expResult, result);
        assertEquals(1L,instance.next());
        assertEquals(2L,  instance.next());
        
    }

    /**
     * Test of current method, of class InMemorySequenceManager.
     */
    @Test
    public void testCurrent() {
        System.out.println("current");
        InMemorySequenceManager instance = new InMemorySequenceManager();
        long expResult = 0L;

        long result = instance.current();
        assertEquals(expResult, result);

        long newValue = 10L;
        instance.change(newValue);
        result = instance.current();
        assertEquals(newValue, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

}