/*
 * **********************************************
 * San Francisco State University
 * CSC 220 -  Data Structures
 * File Name: LinkedBag.java
 * Author: Frank M. Carrano
 * Author: Timothy M. Henry
 * Author: Duc Ta
 * Author: <Ronak> <Basnet>
 * **********************************************
 */

package assignment03PartA;

import java.util.*;

public final class LinkedBag<T> implements PrimaryDataStructureBagInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    public boolean remove(T anEntry) {
        boolean result = false;
        Node currentNode = firstNode;
        Node nodeBefore = null;

        // This is me finding the node to remove //
        while (currentNode != null && !anEntry.equals(currentNode.data)) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        // if found Remove the node //
        if (currentNode != null) {
            if (nodeBefore == null) {
                // Removing the first node packages helped me out on this one //
                firstNode = currentNode.next;
            } else {
                // Bypassing the current node my friend helped me with this //
                nodeBefore.next = currentNode.next;
            }
            numberOfEntries--;
            result = true;
        }

        return result;
    }

    @Override
    public boolean removeAllOccurrences(T[][] entries) {
        System.out.println("[+] Removing 2D test array items from the bag...");

        System.out.println(" [-] Converting 2D array to 1D...");
        ArrayList<T> list = new ArrayList<>();

        // Convert 2D array to 1D list //
        for (T[] array : entries) {
            Collections.addAll(list, array);
        }

        // Print the requirements //
        System.out.print(" [-] Removing duplicates in the 1D array...");
        ArrayList<T> listWithoutDuplicates = removeDuplicates(list);

        System.out.print("\n [>] The final 1D array now contains:  ");
        for (T item : listWithoutDuplicates) {
            System.out.print(item + " ");
        }
        System.out.println();

        System.out.println(" [-] Removing the final 1D array items from the bag...");

        // Use the while loop and the contains method to see if it contains item and remove items //
        boolean anyRemoved = false;
        for (T item : listWithoutDuplicates) {
            while (contains(item)) {
                remove(item);
                // set true if item was removed //
                anyRemoved = true;
            }
        }

        return anyRemoved;
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;

        while (currentNode != null) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }

        // Reverse the array to maintain insertion order or else the output will be all messed up //
        for (int i = 0; i < result.length / 2; i++) {
            T temp = result[i];
            result[i] = result[result.length - i - 1];
            result[result.length - i - 1] = temp;
        }

        return result;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    public ArrayList<T> removeDuplicates(ArrayList<T> list) {
        ArrayList<T> result = new ArrayList<>();
        for (T element : list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }
}



// Overall summary i didn't like this specific assignment it was time-consuming :( //