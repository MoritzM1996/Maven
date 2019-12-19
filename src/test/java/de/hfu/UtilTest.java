package de.hfu;
import static org.junit.Assert.*;
import org.junit.Test;

public class UtilTest 
{
    @Test
    public void testUtilIn() {
    	assertEquals("Erstes Halbjahr = true", true, Util.istErstesHalbjahr(1));
    }
    @Test
    public void testUtilOut() {
    	assertEquals("Erstes Halbjahr = false", false, Util.istErstesHalbjahr(12));
    }
    @Test
    public void testUtilMid() {
    	assertEquals("Erstes Halbjahr = false", false, Util.istErstesHalbjahr(7));
    }
    @Test
    public void testUtilNull() {
    	try{
    		Util.istErstesHalbjahr(0);
    		fail("FAIL");
    	}catch(IllegalArgumentException e) {
    		System.out.println(e.getMessage());
    	}
    }
    @Test
    public void testUtilOut_of_range() {
    	try{
    		Util.istErstesHalbjahr(13);
    		fail("FAIL");
    	}catch(IllegalArgumentException e) {
    		System.out.println(e.getMessage());
    	}
    }
}