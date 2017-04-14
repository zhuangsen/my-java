import java.util.Arrays;

/**
 * Created by madison on 2017/3/9.
 */
public class MyStack<E> {
    //使用数组实现
    private Object[] stack;
    //数组中存储元素的个数
    private int size;
    public MyStack(){
        stack = new Object[10];
    }

    /**
     * 判断堆栈是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    public E peek(){
        if (isEmpty()){
            return null;
        }
        return (E)stack[size - 1];
    }

    public E pop(){
        E e = peek();
        stack[size - 1] = null;
        size--;
        return e;
    }

    public E push(E item){
        ensureCapacity(size+1);
        stack[size++] = item;
        return item;
    }

    public void ensureCapacity(int size){
        int len = stack.length;
        if(size>len){
            int newLen = 10;
            stack = Arrays.copyOf(stack,newLen);
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<Integer>();
        s.push(1);
        s.push(2);
        System.out.println("栈中的元素个数:"+s.size);
        System.out.println("栈顶元素为:"+s.pop());
        System.out.println("栈底元素为:"+s.pop());
    }
}

class NodeStack<E>{
    NodeStack<E> next = null;
    E data;
    public NodeStack(E data){
        this.data = data;
    }
}

class Stack<E>{
    NodeStack<E> top = null;
    public boolean isEmpty(){
        return top == null;
    }

    public void push(E data){
        NodeStack<E> newNode = new NodeStack<E>(data);
        newNode.next = top;
        top = newNode;
    }

    public E pop(){
        if(isEmpty()){
            return null;
        }
        E data = top.data;
        top = top.next;
        return data;
    }

    public E peek(){
        if(isEmpty()){
            return null;
        }
        return top.data;
    }
}