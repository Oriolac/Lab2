import java.util.*;

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

    public static <E extends Comparable<? super E>> List<? super E> withinRange(List<? extends E> l, E max, E min){
        ArrayList<E> listInRange = new ArrayList<>();
        try{
            Iterator<? extends E> it = l.iterator();
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

    public static <E> List<E> withinRange(Comparator<? super E> comp, List<? extends E> l, E max, E min){
        ArrayList<E> listInRange = new ArrayList<>();
        try{
            Iterator<? extends E> it = l.iterator();
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

    public static <E extends Comparable<? super E>> void copyWithRange(List<? super E> trg, List<? extends E> src, E max, E min){
        try{
            ListIterator<? super E> itTrg = trg.listIterator();
            Iterator<? extends E> itSrc = src.iterator();
            while(itSrc.hasNext()){
                E eToCopy = itSrc.next();
                if(max.compareTo(eToCopy) > 0 && min.compareTo(eToCopy) <= 0){
                    itTrg.add(eToCopy);
                }
            }

        } catch(NullPointerException ex){

        }
    }

    public static <E> void copyWithRange(Comparator<? super E> comp,List<? super E> trg, List<? extends E> src, E max, E min){
        try{
            ListIterator<? super E> itTrg = trg.listIterator();
            Iterator<? extends E> itSrc = src.iterator();
            while(itSrc.hasNext()){
                E eToCopy = itSrc.next();
                if(comp.compare(eToCopy, max) < 0 && comp.compare(eToCopy,min ) >= 0){
                    itTrg.add(eToCopy);
                }
            }
        } catch(NullPointerException ex){

        }
    }

}
