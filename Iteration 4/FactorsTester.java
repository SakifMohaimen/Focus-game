import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class FactorsTester {

    @Test
    void testPerfect1()
    {
        // TEST 1: should throw the exception because the parameter value is less than 1
        assertThrows(IllegalArgumentException.class, () -> FactorsUtility.perfect(0));
    }

    @Test
    void testPerfect2()
    {
        // TEST 2: should succeed because 1 is a valid parameter value, but is not a perfect number
        assertFalse(FactorsUtility.perfect(1));
    }

    @Test
    void testPerfect3()
    {
        // TEST 3: should succeed because 6 is a valid parameter value, and is a perfect number
        assertTrue(FactorsUtility.perfect(6));
    }

    @Test
    void testPerfect4()
    {
        // TEST 4: should succeed because 7 is a valid parameter value, but is not a perfect number
        // I've coded this using assertEquals to show that there's often more than one way to write a test
        boolean expected = false;
        assertEquals(expected, FactorsUtility.perfect(7));
    }

    @Test
    void testgetFactors1(){
        int index = 1;
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(index);
        assertEquals(expected,FactorsUtility.getFactors(2));
    }

    @Test
    void testgetFactors2(){
        ArrayList<Integer> expected = new ArrayList<Integer>();
        assertEquals(expected,FactorsUtility.getFactors(1));
    }

    @Test
    void testgetFactors3(){
        ArrayList<Integer> expected = new ArrayList<Integer>();
        assertEquals(expected,FactorsUtility.getFactors(0));
    }

    @Test
    void testgetFactors4(){
        assertThrows(IllegalArgumentException.class, () -> FactorsUtility.getFactors(-1));
    }

    @Test
    void testgetFactors5(){
        ArrayList<Integer> expected = new ArrayList<Integer>();
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(6);

        assertEquals(expected,FactorsUtility.getFactors(12));
    }

    @Test
    void testFactor1(){
        assertFalse(FactorsUtility.factor(10,3));
    }

    @Test
    void testFactor2(){
        assertTrue(FactorsUtility.factor(6,1));
    }

    @Test
    void testFactor3(){
        assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(1,0));
    }

    @Test
    void testFactor4(){
        assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(-1,1));
    }

    @Test
    void testFactor5(){
        assertThrows(IllegalArgumentException.class, () -> FactorsUtility.factor(1,-1));
    }

}