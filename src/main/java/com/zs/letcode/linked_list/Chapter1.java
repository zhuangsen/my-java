package com.zs.letcode.linked_list;

/**
 * 设计链表
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *  
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *  
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 * 相关标签
 * 设计
 * 链表
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/jy291/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/17 09:45
 */
public class Chapter1 {
    public static void main(String[] args) {

    }

    class MyLinkedList {
        int size;
        private SinglyListNode head;

        /**
         * Initialize your data structure here.
         */
        public MyLinkedList() {
            size = 0;
            head = new SinglyListNode(0);
        }

        /**
         * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
         */
        public int get(int index) {
            if (index < 0 || index >= size) {
                return -1;
            }
            SinglyListNode temp = head;
            for (int i = 0; i < index + 1; i++) {
                temp = temp.next;
            }
            return temp.val;

//            int n = 0;
//
//            while (temp != null) {
//                if (n == index) {
//                    return temp.val;
//                }
//                n++;
//                temp = temp.next;
//            }
//            return -1;
        }

        /**
         * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
         */
        public void addAtHead(int val) {
            addAtIndex(0, val);
//            head.val = val;
        }

        /**
         * Append a node of value val to the last element of the linked list.
         */
        public void addAtTail(int val) {
            addAtIndex(size, val);
//            SinglyListNode tail = head;
//            while (tail.next != null) {
//                tail.next = tail;
//            }
//            SinglyListNode newTail = new SinglyListNode(val);
//            tail.next = newTail;
        }

        /**
         * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
         */
        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index < 0) {
                index = 0;
            }
            size++;
            SinglyListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            SinglyListNode newNode = new SinglyListNode(val);
            newNode.next = pred.next;
            pred.next = newNode;

//            SinglyListNode temp = head;
//            int n = 0;
//            while (temp != null) {
//                if (n == index) {
//                    break;
//                }
//                temp = temp.next;
//            }
//            SinglyListNode node = new SinglyListNode(val);
//            temp.next = node;
        }

        /**
         * Delete the index-th node in the linked list, if the index is valid.
         */
        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            size--;
            SinglyListNode pred = head;
            for (int i = 0; i < index; i++) {
                pred = pred.next;
            }
            pred.next = pred.next.next;

//            SinglyListNode temp = head;
//            int n = 0;
//            while (temp != null) {
//                if (n == index) {
//                    break;
//                }
//                temp = temp.next;
//            }
//            temp.next = temp.next.next;
        }
    }


/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
}
