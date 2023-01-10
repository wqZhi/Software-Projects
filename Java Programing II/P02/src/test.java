import java.util.ArrayList;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        ArrayList<Character> alist = new ArrayList<Character>();
        alist.add('c');
//        Integer[] integers = new Integer[] {10, new Integer(20), null,
//            30, 40, new Integer(50)};
//        String[] t1 = {"a", "b", "c"};
//        ArrayList<Integer> x = new ArrayList<Integer>(10);
//
//        System.out.println(x.size());
//        int[] t1 = {1,2,3,4,5,6,7,8,9};
//        doesItWork(t1);
//        System.out.println(Arrays.toString(t1));

//        System.out.println(countEntries(t1));

    }

//    public static int countEntries (String[] ticketList) {
//        int count = 0;
//        int i = 0;
//        while (ticketList[i].length() > 0 && i < ticketList.length) {
//            count++;
//            i++;
//        }
//        return count;
//    }

    public static void doesItWork(int[] incoming) {
        int[] workingCopy = incoming;
        int len = incoming.length;
        int tmp;
        for(int i=0; i<len/2; i++) {
            tmp = workingCopy[i];
            workingCopy[i] = workingCopy[len - i - 1];
            workingCopy[len - i - 1] = tmp;
        }

        System.out.println(Arrays.toString(workingCopy));

    }
}
