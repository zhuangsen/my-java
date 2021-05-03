package com.zs.letcode.stack_queue;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author madison
 * @description
 * @date 2021/5/1 23:12
 */
public class Chapter1 {

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3);
        System.out.println(circularQueue.enQueue(1));
        System.out.println(circularQueue.enQueue(2));
        System.out.println(circularQueue.enQueue(3));
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.Rear());
        System.out.println(circularQueue.isFull());
        System.out.println(circularQueue.deQueue());
        System.out.println(circularQueue.enQueue(4));
        System.out.println(circularQueue.Rear());
    }

    private static class MyCircularQueue {
        private int[] queue;
        private int headIndex;
        private int count;
        private int capacity;


        public MyCircularQueue(int k) {
            this.capacity = k;
            this.queue = new int[k];
            this.headIndex = 0;
            this.count = 0;
        }

        public boolean enQueue(int value) {
            if (this.count == this.capacity) {
                return false;
            }
            this.queue[(this.headIndex + this.count) % this.capacity] = value;
            this.count += 1;
            return true;
        }

        public boolean deQueue() {
            if (this.count == 0) {
                return false;
            }
            this.headIndex = (this.headIndex + 1) % this.capacity;
            this.count -= 1;
            return true;
        }

        public int Front() {
            if (this.count == 0) {
                return -1;
            }
            return this.queue[this.headIndex];
        }

        public int Rear() {
            if (this.count == 0) {
                return -1;
            }
            int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
            return this.queue[tailIndex];
        }

        public boolean isEmpty() {
            return (this.count == 0);
        }

        public boolean isFull() {
            return (this.count == this.capacity);
        }
    }

    private static class Node {
        public int value;
        private Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private static class MyCircularQueue1 {
        private Node head, tail;
        private int count;
        private int capacity;
        // 线程安全
        private ReentrantLock queueLock = new ReentrantLock();

        public MyCircularQueue1(int k) {
            this.capacity = k;
        }

        public boolean enQueue(int value) {
            queueLock.lock();
            try {
                if (this.count == this.capacity) {
                    return false;
                }

                Node newNode = new Node(value);
                if (this.count == 0) {
                    this.head = this.tail = newNode;
                } else {
                    tail.next = newNode;
                    tail = newNode;
                }
                this.count++;
            } finally {
                queueLock.unlock();
            }
            return true;
        }

        public boolean deQueue() {
            if (this.count == 0) {
                return false;
            }
            this.head = this.head.next;
            this.count--;
            return true;
        }

        public int Front() {
            if (this.count == 0) {
                return -1;
            }
            return this.head.value;
        }

        public int Rear() {
            if (this.count == 0) {
                return -1;
            }
            return this.tail.value;
        }

        public boolean isEmpty() {
            return (this.count == 0);
        }

        public boolean isFull() {
            return (this.count == this.capacity);
        }
    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
}
