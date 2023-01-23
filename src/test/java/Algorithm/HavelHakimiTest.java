package Algorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HavelHakimiTest {
    HavelHakimi hk = new HavelHakimi();
    @Test
    void testNullInput()
    {
        assertEquals(new ArrayList<Integer>(), hk.isRealizable(null));
    }

    @Test
    void testDifferentInputs()
    {
        assertEquals(List.of(0,0,0), hk.isRealizable(2,2,3,4,2,2,1));
        assertEquals(List.of(0),hk.isRealizable(2,2,2));
        assertEquals(List.of(),hk.isRealizable());
        assertEquals(List.of(0,0,0,0,0),hk.isRealizable(2,2,2,2,2,2,2,2,2,2,2,2,2,2));
        assertEquals(List.of(0,0,0,0,0,0,0),hk.isRealizable(6,6,5,4,3,3,3,3,2,1,0,0,0));
    }

}
