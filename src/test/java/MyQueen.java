import java.util.LinkedList;


class NodeQueen<E>{
    NodeQueen<E> next = null;
    E data;
    public NodeQueen(E data){
        this.data = data;
    }
}

public class MyQueen<E> {
    /**
     * 链表实现队列
     */
    private NodeQueen<E> head = null;
    private NodeQueen<E> tail = null;
    public boolean isEmpty(){
        return head == tail;
    }

    public void put(E data){
        NodeQueen<E> newNode = new NodeQueen<>(data);
        //队列为空
        if(head == null && tail == null){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }

    }

    public E pop(){
        if(isEmpty()){
            return null;
        }
        E data = head.data;
        head = head.next;
        return data;
    }

    public int size(){
        NodeQueen<E> tmp = head;
        int n = 0;
        while (tmp!=null){
            n++;
            tmp = tmp.next;
        }
        return n;
    }

    public static void main(String[] args) {
        MyQueen<Integer> q = new MyQueen<>();
        q.put(1);
        q.put(2);
        q.put(3);
        System.out.println("队列长度为:"+q.size());
        System.out.println("队列首元素:"+q.pop());
        System.out.println("队列首元素:"+q.pop());
    }
}

/**
 * 数组实现队列
 * @param <E>
 */
class MyQueenArr<E>{
    private LinkedList<E> list = new LinkedList<E>();
    private int size = 0;
    public synchronized void put(E e){
        list.addLast(e);
        size ++;
    }
    public synchronized E pop(){
        size--;
        return list.removeFirst();
    }

    public synchronized boolean empty(){
        return size == 0;
    }

    public synchronized int size (){
        return size;
    }
}