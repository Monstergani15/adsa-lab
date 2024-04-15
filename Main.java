import java.util.Scanner;

class Node {
    protected int data;
    protected Node next, prev;

    public Node() { next = null; prev = null; data = 0; }
    public Node(int d, Node n, Node p) { data = d; next = n; prev = p; }

    public void setLinkNext(Node n) { next = n; }
    public void setLinkPrev(Node p) { prev = p; }
    public Node getLinkNext() { return next; }
    public Node getLinkPrev() { return prev; }
    public void setData(int d) { data = d; }
    public int getData() { return data; }
}

class CircularDoublyLinkedList {
    protected Node start, end;
    public int size;

    public CircularDoublyLinkedList() {
        start = null; end = null; size = 0;
    }

    public boolean isEmpty() { return start == null; }
    public int getSize() { return size; }

    public void insertAtStart(int val) { /* ... unchanged ... */ }
    public void insertAtEnd(int val) { /* ... unchanged ... */ }
    public void insertAtPos(int val, int pos) { /* ... unchanged ... */ }
    public void deleteAtPos(int pos) { /* ... unchanged ... */ }
    public void display() { /* ... unchanged ... */ }
}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        System.out.println("Circular Doubly Linked List Test\n");
        char ch;
        do {
            System.out.println("\nCircular Doubly Linked List Operations\n");
            System.out.println("1.insert at beginning\n2.insert at end\n3.insert at position\n4.delete at position\n5.Check empty\n6.Get size");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: System.out.println("Enter integer element to insert"); list.insertAtStart(scan.nextInt()); break;
                case 2: System.out.println("Enter integer element to insert"); list.insertAtEnd(scan.nextInt()); break;
                case 3: System.out.println("Enter integer element to insert\nEnter position"); int num = scan.nextInt(), pos = scan.nextInt(); if (pos < 1 || pos > list.getSize()) System.out.println("Invalid position\n"); else list.insertAtPos(num, pos); break;
                case 4: System.out.println("Enter position"); int p = scan.nextInt(); if (p < 1 || p > list.getSize()) System.out.println("Invalid position\n"); else list.deleteAtPos(p); break;
                case 5: System.out.println("Empty status=" + list.isEmpty()); break;
                case 6: System.out.println("Size=" + list.getSize() + "\n"); break;
                default: System.out.println("Wrong Entry\n"); break;
            }
            list.display();
            System.out.println("\nDo you want to continue(Type y or n)\n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}
