import java.util.Hashtable;


public class MyLinkedList {
    Node head = null;//链表头的引用

    /**
     * 向链表中插入数据
     * @param d 插入的数据
     */
    public Node addNode(int d){
        Node newNode = new Node(d);
        if(head == null){
            head = newNode;
            return head;
        }
        Node tmp = head;
        while (tmp.next!=null){
            tmp = tmp.next;
        }
        //add node to end;
        tmp.next = newNode;
        return head;
    }

    /**
     * 删除节点
     * @param index 删除第index个节点
     * @return 成功返回true，失败返回false
     */
    public Boolean deleteNode(int index){
        //删除元素的位置不合理
        if(index < 1 || index > length()){
            return false;
        }
        //删除链表第一个元素
        if(index == 1){
            head = head.next;
            return true;
        }
        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode.next != null){
            if(i == index){
                preNode.next = curNode.next;
                return true;
            }
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }

        return true;
    }

    /**
     * 返回链表的长度
     * @return
     */
    public int length(){
        int length = 0;
        Node tmp = head;
        while (tmp!=null){
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * 对链表进行排序
     * @return 返回排序后的头节点
     */
    public Node orderList(){
        Node nextNode = null;
        int temp = 0;
        Node curNode = head;
        while (curNode.next!=null){
            nextNode = curNode.next;
            while (nextNode!=null){
                if(curNode.data>nextNode.data){
                    temp = curNode.data;
                    curNode.data = nextNode.data;
                    nextNode.data = temp;
                }
                nextNode = nextNode.next;
            }
            curNode = curNode.next;
        }

        return head;
    }

    /**
     * 利用额外的空间删除重复数据
     * @param head
     */
    public void deleteDuplecateByStore(Node head){
        Hashtable<Integer,Integer> table = new Hashtable<Integer, Integer>();
        Node tmp = head;
        Node pre = null;
        while (tmp != null){
            if(table.containsKey(tmp.data)){
                pre.next = tmp.next;
            }else {
                table.put(tmp.data,1);
                pre = tmp;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 进行双重循环遍历删除
     * @param node
     */
    public void deleteDuplecateByIterate(Node node){
        Node p = head;
        while (p!=null){
            Node q = p;
            while (q.next!=null){
                if(p.data == q.next.data){
                    q.next = q.next.next;
                }else
                    q = q.next;
            }
            p = p.next;
        }
    }

    /**
     * 查找倒数第K个元素
     * @param head
     * @param k
     * @return
     */
    public Node findElem(Node head,int k){
        if(k<1 || k>this.length()){
            return null;
        }
        Node p1 = head;
        Node p2 = head;
        for (int i = 0; i < k - 1; i++) {
            p1 = p1.next;
        }
        while (p1!=null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 实现链表反转
     * @param head
     */
    public void reverseIteratively(Node head){
        Node pReversedHead = head;
        Node pNode = head;
        Node pPrev = null;
        while (pNode !=null){
            Node pNext = pNode.next;
            if(pNext==null){
                pReversedHead = pNode;
            }
            pNode.next = pPrev;
            pPrev = pNode;
            pNode = pNext;
        }
        this.head = pReversedHead;
    }

    /**
     * 查找链表的中间节点
     * @param head
     * @return
     */
    public Node searchMid(Node head){
        Node p = head;
        Node q = head;
        while (p!=null && p.next!=null && p.next.next!=null) {
            p = p.next.next;
            q = q.next;
        }
        return q;
    }

    /**
     * 倒序打印链表
     * @param pListHead
     */
    public void printListReversely(Node pListHead){
        if(pListHead != null){
            printListReversely(pListHead.next);
            System.out.print(pListHead.data+"\t");
        }
    }

    /**
     * 打印链表
     */
    public void printList(){
        Node tmp = head;
        while (tmp != null){
            System.out.print(tmp.data+"\t");
            tmp = tmp.next;
        }
        System.out.println();
    }


    public static void main(String[] args){
        MyLinkedList list = new MyLinkedList();
        list.addNode(5);
        list.addNode(3);
        list.addNode(1);
        list.addNode(3);
        list.addNode(5);
        list.addNode(4);
        list.addNode(6);
        list.addNode(7);
        System.out.println("listLen="+list.length());
        System.out.println("before order");
        list.printList();
        list.orderList();
        System.out.println("after order");
        list.printList();
        list.deleteDuplecateByIterate(list.head);
        System.out.println("after delDup");
        list.printList();
//        list.reverseIteratively(list.head);
//        System.out.println("after reverse");
//        list.printList();
//        System.out.println("after printListReversely");
//        list.printListReversely(list.head);
//        System.out.println();
        System.out.println("search mid");
        System.out.println(list.searchMid(list.head).data);

        System.out.println("find element");
        System.out.println(list.findElem(list.head,3).data);
    }
}



class Node{
    Node next = null;
    int data;
    public Node(int data){
        this.data = data;
    }
}