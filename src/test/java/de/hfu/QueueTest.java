package de.hfu;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class QueueTest 
{
	private Queue neueQueue;
    @Before
    public void erzeugeQueue() {
    	neueQueue = new Queue(3);
    }
    @Test
    public void testQueueEnqueue() {
        neueQueue.enqueue(1);
        neueQueue.enqueue(2);
        neueQueue.enqueue(3);
        neueQueue.enqueue(4);
        neueQueue.dequeue();
        neueQueue.dequeue();
        assertEquals("Ueberschreiben", 4, neueQueue.dequeue());
    }
    @Test
    public void testQueueDequeue() {
    	try {
    		//neueQueue.enqueue(1);
    		neueQueue.dequeue();
    		fail("Darf nicht kommen");
    	}catch(IllegalStateException e) {
    		System.out.println(e.getMessage());
    	}
    }
}