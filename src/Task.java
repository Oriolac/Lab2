import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

public class Task {

    public static List<Integer> withinRange(List<Integer> l, int max, int min){
        ArrayList listInRange = new ArrayList();
        try{
            Iterator<Integer> it = l.iterator();
            while(it.hasNext()){
                int value = it.next();
                if(isInRange(value,max,min)){
                    listInRange.add(value);
                }
            }
            return listInRange;
        } catch(NullPointerException ex){
            return listInRange;
        }
    }

    private static boolean isInRange(int value, int max, int min) {
        return value < max && value >= min;
    }

    public static <E extends Comparable<? super E>> List<E> withinRange(List<E> l, E max, E min){
        ArrayList<E> listInRange = new ArrayList<>();
        try{
            Iterator<E> it = l.iterator();
            while(it.hasNext()){
                E e = it.next();
                if(max.compareTo(e) > 0 && min.compareTo(e) <= 0){
                    listInRange.add(e);
                }
            }
            return listInRange;
        } catch(NullPointerException ex){
            return listInRange;
        }
    }

    public static <E> List<E> withinRange(Comparator<E> comp, List<E> l, E max, E min){
        ArrayList<E> listInRange = new ArrayList<>();
        try{
            Iterator<E> it = l.iterator();
            while(it.hasNext()){
                E e = it.next();
                if(comp.compare(e, max) < 0 && comp.compare(e,min ) >= 0){
                    listInRange.add(e);
                }
            }
            return listInRange;
        } catch (NullPointerException ex){
            return listInRange;
        }
    }

    public static <E extends Comparable<E>> void copyWithRange(List<E> trg, List<E> src, E max, E min){

    }
}
