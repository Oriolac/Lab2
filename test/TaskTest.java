import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class TaskTest {

    List expectedList = new ArrayList();
    List l = new ArrayList();

    @Test
    public void withinRangeIntegers() {
        List l = Task.withinRange(null, 2, 0);
        assertArrayEquals(expectedList.toArray(),l.toArray());
        l = Arrays.asList(-4, -1, 0, 2, 3, 34, 125);
        expectedList = Arrays.asList(-1,0);
        assertArrayEquals(expectedList.toArray(),Task.withinRange(l,2,-1).toArray());
        expectedList = Arrays.asList(125);
        assertArrayEquals(expectedList.toArray(),Task.withinRange(l,143,123).toArray());

        expectedList = Arrays.asList(-1,0,2,3);
        assertArrayEquals(expectedList.toArray(),Task.withinRange(l, 4, -2).toArray());

        expectedList = Arrays.asList(-4);
        l = Arrays.asList(-4, null, 0);
        assertArrayEquals(expectedList.toArray(),Task.withinRange(l,3,-5).toArray());

        expectedList = new ArrayList();
        l = new ArrayList();
        for(int i = -5; i < 6; i++){
            l.add(i);
            if(i >= -2 && i < 4){
                expectedList.add(i);
            }
        }
        assertArrayEquals(expectedList.toArray(),Task.withinRange(l, 4, -2).toArray());
    }

    @Test
    public void withInRangeComparable(){
        l = Arrays.asList(-24L, -23L, -12L, 0L, 12L, 42L, 43L);
        expectedList = Arrays.asList(-23L, -12L, 0L, 12L, 42L);
        assertEquals(expectedList, Task.withinRange(l, 43L, -23L));

        l = Arrays.asList(72L,43L,12L,48L,-24L,-23L,42L);
        expectedList = Arrays.asList(12L, -23L, 42L);
        assertEquals(expectedList,Task.withinRange(l,43L, -23L));

        assertEquals(Arrays.asList(false, false, false), Task.withinRange(Arrays.asList(false, false, true, false),true,false));
        assertEquals(Arrays.asList(), Task.withinRange(Arrays.asList(true),true,true));
        assertEquals(Arrays.asList(), Task.withinRange(Arrays.asList(null, false),true,false));
    }

    @Test
    public void withInRangeComparator(){
        l = Arrays.asList(1L, -3L);
        assertEquals(Arrays.asList(1L),Task.withinRange(Comparator.naturalOrder(), (List<Long>) l,3L,-2L));
        assertEquals(Arrays.asList(false, false, false), Task.withinRange(Comparator.naturalOrder(), Arrays.asList(false, false, true, false),true,false));
        assertEquals(Arrays.asList(), Task.withinRange(Comparator.naturalOrder(), Arrays.asList(true),true,true));
        assertEquals(Arrays.asList(), Task.withinRange(Comparator.naturalOrder(), Arrays.asList(null, false),true,false));

    }


}
