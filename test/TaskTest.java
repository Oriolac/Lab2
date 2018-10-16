import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TaskTest {

    static List expectedList = new ArrayList<>();
    static List l = new ArrayList<>();
    static List targetList = new ArrayList<>();
    static String name = new String("Patata");
    static Employee[] emps = new Employee[10];
    static Engineer[] engs = new Engineer[10];
    static ProjectManager[] prom = new ProjectManager[10];
    EmployeeComparator empComp = new EmployeeComparator();

    @BeforeClass
    public static void insertObjects(){
        for(int i = 0; i < emps.length; i++){
            emps[i] = new Employee(name,Integer.toString(i).concat("0000000A"));
        }
        for(int i = 0; i < engs.length; i++){
            engs[i] = new Engineer(name,Integer.toString(i).concat("0000001A"));
        }
        for(int i = 0; i < prom.length; i++){
            prom[i] = new ProjectManager(name, Integer.toString(i).concat("0000002A"));
        }
    }

    @Test
    public void withinRangeIntegers() {
        expectedList = Arrays.asList();
        l = Task.withinRange(null, 2, 0);
        assertEquals(expectedList,l);
        assertEquals(expectedList,Task.withinRange(Arrays.asList(), 4, -2));
        l = Arrays.asList(-4, -1, 0, 2, 3, 34, 125);
        expectedList = Arrays.asList(-1,0);
        assertEquals(expectedList,Task.withinRange(l,2,-1));
        expectedList = Arrays.asList(125);
        assertEquals(expectedList,Task.withinRange(l,143,123));

        expectedList = Arrays.asList(-1,0,2,3);
        assertEquals(expectedList,Task.withinRange(l, 4, -2));

        expectedList = Arrays.asList(-4);
        l = Arrays.asList(-4, null, 0);
        assertEquals(expectedList,Task.withinRange(l,3,-5));

        expectedList = new ArrayList();
        l = new ArrayList();
        for(int i = -5; i < 6; i++){
            l.add(i);
            if(i >= -2 && i < 4){
                expectedList.add(i);
            }
        }
        assertEquals(expectedList,Task.withinRange(l, 4, -2));
    }

    @Test
    public void comparableWithPrimitives() {
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
    public void comparableWithReferences() {
        //Proves amb una llista de només 1 objecte.
        assertEquals(Arrays.asList(),Task.withinRange(Arrays.asList(),emps[2], engs[1]));
        l = Arrays.asList(emps[0],emps[1], emps[2], emps[3], emps[4], emps[5], emps[6],null, emps[7], emps[8], emps[9]);
        assertEquals(Arrays.asList(emps[1],emps[2],emps[3]),Task.withinRange(l,emps[4],emps[1]));
        assertEquals(Arrays.asList(emps[6]), Task.withinRange(l, emps[8],emps[6]));
        l = Arrays.asList(emps[4], emps[2], emps[3],emps[7], emps[1], emps[9], emps[2], null, emps[3]);
        assertEquals(Arrays.asList(emps[4], emps[2], emps[3], emps[2]), Task.withinRange(l, emps[6], emps[2]));

        //Proves amb una llista de Employees i Engineers
        l = Arrays.asList(emps[0], engs[0], engs[1]);
        assertEquals(Arrays.asList(emps[0], engs[0]),Task.withinRange(l,emps[1],emps[0]));
        assertEquals(Arrays.asList(emps[0], engs[0]),Task.withinRange(l,engs[1],emps[0]));
        l = Arrays.asList(engs[4], emps[2], engs[2], emps[3], engs[4], emps[5], null, engs[3]);
        assertEquals(Arrays.asList(engs[4], engs[2], emps[3], engs[4], emps[5]), Task.withinRange(l,engs[5], engs[2]));

        //Proves amb una llista de Employees, Engineeers i ProjectManagers
        l = Arrays.asList(emps[1], engs[2], prom[2], emps[3], null, engs[1]);
        assertEquals(Arrays.asList(emps[1],engs[2], prom[2]), Task.withinRange(l,emps[3],emps[1]));
        assertEquals(Arrays.asList(engs[2], prom[2], emps[3]), Task.withinRange(l,engs[4],prom[1]));
        l = Arrays.asList(emps[3], engs[3], emps[5], prom[9], prom[5]);
        assertEquals(Arrays.asList(emps[5], prom[5]), Task.withinRange(l, engs[9], emps[4]));
    }

    @Test
    public void comparatorWithPrimitives() {
        l = Arrays.asList(1L, -3L);
        assertEquals(Arrays.asList(1L),Task.withinRange(Comparator.naturalOrder(), (List<Long>) l,3L,-2L));
        assertEquals(Arrays.asList(false, false, false), Task.withinRange(Comparator.naturalOrder(), Arrays.asList(false, false, true, false),true,false));
        assertEquals(Arrays.asList(), Task.withinRange(Comparator.naturalOrder(), Arrays.asList(true),true,true));
        assertEquals(Arrays.asList(), Task.withinRange(Comparator.naturalOrder(), Arrays.asList(null, false),true,false));
    }

    @Test
    public void comparatorWithReferences() {
        //Proves amb una llista de només 1 objecte.
        assertEquals(Arrays.asList(),Task.withinRange(empComp, Arrays.asList(),emps[2], engs[1]));
        l = Arrays.asList(emps[0],emps[1], emps[2], emps[3], emps[4], emps[5], emps[6],null, emps[7], emps[8], emps[9]);
        assertEquals(Arrays.asList(emps[1],emps[2],emps[3]),Task.withinRange(empComp, l,emps[4],emps[1]));
        assertEquals(Arrays.asList(emps[6]), Task.withinRange(empComp, l, emps[8],emps[6]));
        l = Arrays.asList(emps[4], emps[2], emps[3],emps[7], emps[1], emps[9], emps[2], null, emps[3]);
        assertEquals(Arrays.asList(emps[4], emps[2], emps[3], emps[2]), Task.withinRange(empComp, l, emps[6], emps[2]));

        //Proves amb una llista de Employees i Engineers
        l = Arrays.asList(emps[0], engs[0], engs[1]);
        assertEquals(Arrays.asList(emps[0], engs[0]),Task.withinRange(empComp, l,emps[1],emps[0]));
        assertEquals(Arrays.asList(emps[0], engs[0]),Task.withinRange(empComp, l,engs[1],emps[0]));
        l = Arrays.asList(engs[4], emps[2], engs[2], emps[3], engs[4], emps[5], null, engs[3]);
        assertEquals(Arrays.asList(engs[4], engs[2], emps[3], engs[4], emps[5]), Task.withinRange(empComp, l,engs[5], engs[2]));

        //Proves amb una llista de Employees, Engineeers i ProjectManagers
        l = Arrays.asList(emps[1], engs[2], prom[2], emps[3], null, engs[1]);
        assertEquals(Arrays.asList(emps[1],engs[2], prom[2]), Task.withinRange(empComp, l,emps[3],emps[1]));
        assertEquals(Arrays.asList(engs[2], prom[2], emps[3]), Task.withinRange(empComp, l,engs[4],prom[1]));
        l = Arrays.asList(emps[3], engs[3], emps[5], prom[9], prom[5]);
        assertEquals(Arrays.asList(emps[5], prom[5]), Task.withinRange(empComp, l, engs[9], emps[4]));
    }

    @Test
    public void copyWithRangeWithComparableWithPrimitives() {
        l = Arrays.asList(false);
        targetList = new ArrayList(Arrays.asList());
        Task.copyWithRange(targetList, l, true, false);
        expectedList = Arrays.asList(false);
        assertEquals(expectedList, targetList);

        l = Arrays.asList(72L,43L,12L,48L,-24L,-23L,42L);
        targetList = new ArrayList(Arrays.asList(21L));
        expectedList = new ArrayList(Arrays.asList(21L, 12L, -23L, 42L));
        Task.copyWithRange(targetList, l, 43L, -23L);
        assertEquals(expectedList,targetList);
    }

    @Test
    public void copyWithRangeWithComparableWithReferences() {
        //Proves amb una llista de només 1 objecte.
        targetList = Arrays.asList();
        Task.copyWithRange(targetList, Arrays.asList(), emps[2], engs[1]);
        assertEquals(Arrays.asList(),targetList);

        l = Arrays.asList(emps[0],emps[1], emps[2], emps[3], emps[4], emps[5], emps[6],null, emps[7], emps[8], emps[9]);
        targetList = new ArrayList(Arrays.asList(emps[7]));
        Task.copyWithRange(targetList, l, emps[4], emps[1]);
        expectedList = new ArrayList(Arrays.asList(emps[7], emps[1], emps[2], emps[3]));
        assertEquals(expectedList,targetList);

        targetList = new ArrayList(Arrays.asList(emps[6]));
        Task.copyWithRange(targetList, l, emps[8], emps[6]);
        expectedList = new ArrayList(Arrays.asList(emps[6], emps[6]));
        assertEquals(expectedList, targetList);

        l = Arrays.asList(emps[4], emps[2], emps[3],emps[7], emps[1], emps[9], emps[2], null, emps[3]);
        targetList = new ArrayList(Arrays.asList(emps[1]));
        Task.copyWithRange(targetList, l, emps[6], emps[2]);
        assertEquals(Arrays.asList(emps[1],emps[4], emps[2], emps[3], emps[2]), targetList);

        //Proves amb una llista de Employees i Engineers
        l = Arrays.asList(engs[4], emps[2], engs[2], emps[3], engs[4], emps[5], null, engs[3]);
        targetList = new ArrayList(Arrays.asList(emps[3]));
        Task.copyWithRange(targetList, l, engs[5], engs[2]);
        expectedList = new ArrayList(Arrays.asList(emps[3],engs[4], engs[2], emps[3], engs[4], emps[5]));
        assertEquals(expectedList,targetList);

        //Proves amb una llista de Employees, Engineeers i ProjectManagers
        l = Arrays.asList(emps[1], engs[2], prom[2], emps[3], null, engs[1]);
        targetList = new ArrayList(Arrays.asList(prom[2]));
        Task.copyWithRange(targetList, l, emps[3], emps[1]);
        expectedList = new ArrayList(Arrays.asList(prom[2],emps[1],engs[2], prom[2]));
        assertEquals(expectedList, targetList);

        l = Arrays.asList(emps[3], engs[3], emps[5], prom[9], prom[5]);
        targetList = new ArrayList(Arrays.asList(engs[1]));
        Task.copyWithRange(targetList, l, engs[9], emps[4]);
        expectedList = new ArrayList(Arrays.asList(engs[1], emps[5], prom[5]));
        assertEquals(expectedList, targetList);
    }

    @Test
    public void copyWithRangeWithComparatorWithPrimitives() {
        l = Arrays.asList(false);
        targetList = new ArrayList(Arrays.asList());
        Task.copyWithRange(Comparator.naturalOrder(),targetList, l, true, false);
        expectedList = Arrays.asList(false);
        assertEquals(expectedList, targetList);



        l = Arrays.asList(72L,43L,12L,48L,-24L,-23L,42L);
        targetList = new ArrayList(Arrays.asList(21L));
        Task.copyWithRange(Comparator.naturalOrder(), targetList, l, 43L, -23L);
        expectedList = Arrays.asList(21L, 12L, -23L, 42L);
        assertEquals(expectedList,targetList);

        l = Arrays.asList(72L,43L,12L,48L,-24L,-23L,null,42L);
        targetList = new ArrayList(Arrays.asList(21L));
        Task.copyWithRange(Comparator.naturalOrder(), targetList, l, 43L, -23L);
        expectedList = Arrays.asList(21L, 12L, -23L);
        assertEquals(expectedList,targetList);
    }

    @Test
    public void copyWithRangeWithComparatorWithReferences() {
        //Proves amb una llista de només 1 objecte.
        targetList = Arrays.asList();
        Task.copyWithRange(empComp, targetList, Arrays.asList(), emps[2], engs[1]);
        expectedList = Arrays.asList();
        assertEquals(expectedList,targetList);


        l = Arrays.asList(emps[0],emps[1], emps[2], emps[3], emps[4], emps[5], emps[6],null, emps[7], emps[8], emps[9]);
        targetList = new ArrayList(Arrays.asList(emps[7]));
        Task.copyWithRange(empComp, targetList, l, emps[4], emps[1]);
        expectedList = Arrays.asList(emps[7], emps[1], emps[2], emps[3]);
        assertEquals(expectedList,targetList);

        targetList = new ArrayList(Arrays.asList(emps[6]));
        Task.copyWithRange(empComp, targetList, l, emps[8], emps[6]);
        expectedList = Arrays.asList(emps[6], emps[6]);
        assertEquals(expectedList, targetList);

        l = Arrays.asList(emps[4], emps[2], emps[3],emps[7], emps[1], emps[9], emps[2], null, emps[3]);
        targetList = new ArrayList(Arrays.asList(emps[1]));
        Task.copyWithRange(empComp,targetList, l, emps[6], emps[2]);
        assertEquals(Arrays.asList(emps[1],emps[4], emps[2], emps[3], emps[2]), targetList);

        //Proves amb una llista de Employees i Engineers
        l = Arrays.asList(engs[4], emps[2], engs[2], emps[3], engs[4], emps[5], null, engs[3]);
        targetList = new ArrayList(Arrays.asList(emps[3]));
        Task.copyWithRange(empComp, targetList, l, engs[5], engs[2]);
        expectedList = Arrays.asList(emps[3],engs[4], engs[2], emps[3], engs[4], emps[5]);
        assertEquals(expectedList,targetList);

        //Proves amb una llista de Employees, Engineeers i ProjectManagers
        l = Arrays.asList(emps[1], engs[2], prom[2], emps[3], null, engs[1]);
        targetList = new ArrayList(Arrays.asList(prom[2]));
        Task.copyWithRange(empComp, targetList, l, emps[3], emps[1]);
        expectedList = Arrays.asList(prom[2],emps[1],engs[2], prom[2]);
        assertEquals(expectedList, targetList);

        l = Arrays.asList(emps[3], engs[3], emps[5], prom[9], prom[5]);
        targetList = new ArrayList(Arrays.asList(engs[1]));
        Task.copyWithRange(empComp, targetList, l, engs[9], emps[4]);
        expectedList = Arrays.asList(engs[1], emps[5], prom[5]);
        assertEquals(expectedList, targetList);
    }
}
