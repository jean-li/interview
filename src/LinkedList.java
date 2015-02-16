import sun.invoke.empty.Empty;

/**
 * Created by jingli on 10/02/15.
 */
public class LinkedList {

    private static class Node<T> {
        private T data;
        Node<T> next;

        Node(T data, Node<T> next) {
            this.setData(data);
            this.next = next;
        }

        Node(T data) {
            this(data,null);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    private Node<T> head, tail;

    public LinkedList(Node<T> head, Node<T> tail) {
        this.head = this.tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addHead(T item) {
        head = new Node<T>(item);
        if (tail == null)
            tail = head;
    }

    /**
     * add the tail pointer
     */

    public void addTail(T item) {
        if (!isEmpty()) {
            tail.next = new Node<T>(item);
            tail = tail.next;
        } else {
            head = tail = new Node<T>(item);
        }
    }

    /**
     * print the list
     */

    public void traverse() {
        if (isEmpty()) {
            System.out.println("null");
        } else {
            for (Node<T> p = head; p != null; p = p.next)
                System.out.println(p.data);
        }
    }

    /**
     * insert node from head
     */
    public void addFromHead(T item) {
        Node<T> newNode = new Node<T>(item);
        newNode.next = head;
        head = newNode;
    }

    /**
     * insert node from tail
     */
    public void addFromTail(T item) {
        Node<T> newNode = new Node<T>(item);
        Node<T> p = head;
        while (p.next != null)
            p = p.next;
        p.next = newNode;
        newNode.next =null;
    }
    /**
     * delete node from head
     */
    public void removeFromHead() {
        if (!isEmpty())
            head = head.next;
        else
            System.out.println("This list is empty.");
    }
    /**
     * delete frem tail, lower effect
     */
    public void removeFromTail() {
        Node<T> prev = null, curr = head;
        while (curr.next != null) {
            prev =curr;
            curr = curr.next;
            if (curr.next == null)
                prev.next =null;
        }
    }

    /**
     * insert a new node or remove a node
     * @param appointedItem
     * @param item
     * @return
     */
    public boolean insert(T appointedItem, T item) {
        Node<T> prev = head, curr = head.next, newNode = new Node<T>(item);
        if (!isEmpty()) {
            while ((curr != null) && (appointedItem != curr.data)) {
                prev = curr;
                curr = curr.next;
            }
            newNode.next = curr;
            prev.next = newNode;
            return true;
        }
        return false;
    }

    public void remove(T item) {
        Node<T> curr = head, prev = null;
        boolean found  = false;
        while(curr != null && !found) {
            if (item == curr.data) {
                if (prev == null)
                    removeFromHead();
                else
                    prev.next = curr.next;
                found = true;
            } else {
                prev = curr;
                curr =curr.next;
            }
        }
    }

    public int indexOf(T item) {
        int index = 0;
        Node<T> p;
        for(p = head; p != null; p = p.next) {
            if (item == p.data)
               return index;
            index ++;
        }
        return -1;
    }

    /**
     * judge the list contains one data
     */
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    /**
     * reverse a linkList
     * @param head
     * @return
     */
    public Node<T> reverse(Node<T> head) {
        Node<T> prev = null, curr = head, next = null;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }

    public Node<T> reverseRecursive(Node<T> head) {
        if (head.next == null)
            return head;
        Node<T> newHead = null;
        Node<T> prev = null, next = head.next;
        head.next = prev;
        newHead = reverseRecursive(next);
        next.next = head;
        return newHead;
    }


    /**
     * merge two ordered linked lists
     * @param head1
     * @param head2
     * @return
     */
    public Node<T> mergeWithOrder(Node<T> head1, Node<T> head2) {
        Node<T> newHead = null, p = null;
        if (head1 == null)
            return head2;
        else if (head2 == null)
            return head1;
        if (head1.data < head2.data) {
            p = newHead = head1;
            head1 = head1.next;
        }else{
            p = newHead = head2;
            head2 = head2.next;
        }
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                p.next = head1;
                head1 = head1.next;
            } else {
                p.next = head2;
                head2 = head2.next;
            }
        }
        if (head1 != null)
            p.next = head1;
        else
            p.next = head2;
        return newHead;
    }

    public Node<T> mergeRecursive(Node<T> head1, Node<T> head2) {
        if (head1 == null)
            return head2;
        else
            return head1;
        Node<T> newHead = null;
        if (head1.data < head2.data) {
            newHead = head1;
            newHead.next = mergeRecursive(head1.next, head2);
        } else {
            newHead = head2;
            newHead.next = mergeRecursive(head1, head2.next);
        }

        return newHead;
    }

    /**
     * return the circle start node of the list if exist, otherwise, return null
     * @param head
     * @return
     */
    public Node<T> circleStart(Node<T> head) {
        if (head == null | head.next == null) {
            return null;
        }
        Node<T> p1 = head, p2 = head;
        boolean hasCircle = false;
        while(p1.next != null && p2.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1.data == p2.data)
                hasCircle = true;
                break;
        }
        if (hasCircle == true) {
            Node<T> head1 = head;
            head = null;
            Node<T> head2 = head.next;
            Node<T> start2 = circleStart(head2);
            if (start2 == null) {
                return head1;
                head = head1;
            } else {
                return start2;
                head = head1;
            }

        }
        return null;
    }

}


