package TestAppendFifoQueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> q1;
	private FifoQueue<Integer> q2;
	
	@Before
	public void setUp() {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}
	
	@After
	public void tearDown() {
		q1 = null;
		q2 = null;
	}
	
	@Test
	public void testTwoEmpty() {
		q1.append(q2);
		assertTrue("Skall vara tom", q1.isEmpty());
		assertTrue("Skall vara tom", q2.isEmpty());
	}
	
	@Test
	public void testEmptyToNonEmpty() {
		q1.offer(1);
		q1.append(q2);
		assertEquals("Skall finnas en 1:a", (Integer)1, q1.poll());
		assertTrue("Skall vara tom", q1.isEmpty());
	}
	
	@Test
	public void testNonEmptyToEmpty() {
		q2.offer(1);
		q1.append(q2);
		assertEquals("Skall finnas en 1:a", (Integer)1, q1.poll());
		assertTrue("Skall vara tom", q1.isEmpty());
	}
	
	@Test
	public void testTwoNonEmpty() {
		q2.offer(1);
		q1.offer(3);
		q1.offer(2);
		q1.append(q2);
		assertEquals("Skall finnas en 1:a", (Integer)3, q1.poll());
		assertEquals("Skall finnas en 1:a", (Integer)2, q1.poll());
		assertEquals("Skall finnas en 1:a", (Integer)1, q1.poll());
		assertTrue("Skall vara tom", q1.isEmpty());
	}
}
