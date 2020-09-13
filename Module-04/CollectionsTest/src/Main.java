/**
 * @author Andre Fernandez
 * @version 1.0 - September 13, 2020
 *
 * This program creates instances of an ArrayList, a LinkedList, and a HashTable.
 *
 * Each collection receives 2 million random integers between 1-1000, and then
 * proceeds to delete them.
 *
 * The goal is to monitor this program in either VisualVM or JProfiler, and see
 * system performance when creating and deleting these collections.
 *
 */
public class Main {

    /**
     * Main method of the program that calls all test methods.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Testing an ArrayList...");
        Methods.testArrayList();

        System.out.println("\nTesting a LinkedList...");
        Methods.testLinkedList();

        System.out.println("\nTesting a Hash Table...");
        Methods.testHashTable();

        System.out.println("\nAll tests complete!");

    } // end main()

} // end Main
