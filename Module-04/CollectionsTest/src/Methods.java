import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;

/**
 * Methods class for CollectionsTest
 */
public class Methods {

    /**
     * Creates an ArrayList, populates it with 2 million integers between 1-1000, then deletes each one.
     */
    public static void testArrayList() {
        System.out.println("Initializing...");
        ArrayList<Integer> list = new ArrayList<Integer>();

        System.out.println("Adding random integers...");
        Random rand = new Random();
        for(int i = 0; i < 2000000; i++)
            list.add(rand.nextInt(1000 - 1 + 1) + 1);
        System.out.println("ArrayList size = " + list.size());

        System.out.println("Deleting each integer...");
        list.removeAll(list);
        System.out.println("ArrayList size = " + list.size());

    } // end testArrayList()

    /**
     * Creates a LinkedList, populates it with 2 million integers between 1-1000, then deletes each one.
     */
    public static void testLinkedList() {
        System.out.println("Initializing...");
        LinkedList<Integer> list = new LinkedList<Integer>();

        System.out.println("Adding random integers...");
        Random rand = new Random();
        for(int i = 0; i < 2000000; i++)
            list.add(rand.nextInt(1000 - 1 + 1) + 1);
        System.out.println("LinkedList size = " + list.size());

        System.out.println("Deleting each integer...");
        list.removeAll(list);
        System.out.println("LinkedList size = " + list.size());

    } // end testLinkedList()

    /**
     * Creates a Hash table, populates it with 2 million integers between 1-1000, then deletes each one.
     */
    public static void testHashTable() {
        System.out.println("Initializing...");
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();

        System.out.println("Adding random integers...");
        Random rand = new Random();
        for(int i = 0; i < 2000000; i++)
            table.put(i, rand.nextInt(1000 - 1 + 1) + 1);
        System.out.println("Hash Table size = " + table.size());

        System.out.println("Deleting each integer...");
        for(int i = 0; i < 2000000; i++)
            table.remove(i);

        System.out.println("Hash Table size = " + table.size());

    } // end testHashTable()

} // end Methods
