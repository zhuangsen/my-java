package select;

public class SelectSort {

    /**
     * 选择排序
     *
     * @param a
     */
    public static void selSort(int[] a) {
        int temp;
        int flag;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            temp = a[i];
            flag = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < temp) {
                    temp = a[j];
                    flag = j;
                }
            }
            if (flag != i) {
                a[flag] = a[i];
                a[i] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] array = {1, 5, 9, 3, 4, 18, 7, 6};
        SelectSort ss = new SelectSort();
        ss.print(ss.selectSort(array));
    }

    /**
     * 选择排序原理：从待排序序列中选出最小的元素，放入到已排好序列
     * 元素比较次数：需要比较的次数为n(n-1)/2，总的元素移动次数为3(n-1) ,空间占用情况为O(1)
     * 元素交换次数：
     * 最好情况：最好的情况，元素移动的次数为0
     * 最坏情况：和最好情况一致，元素需要移动3(n-1)次
     * 空间占用情况：
     *
     * @param array
     * @return
     */
    public int[] selectSort(int[] array) {
        int i, j, len = array.length;
        for (i = 0; i < len; i++) {
            int k = i;
            //对序列中第i+1以后的元素进行遍历，找到最小元素下标
            for (j = i + 1; j < len; j++) {
                if (array[k] > array[j]) {
                    k = j;
                }
            }
            if (k != i) {
                swap(array, i, k); //交换两个元素位置
            }
        }
        return array;
    }

    /**
     * 交换两个元素位置
     *
     * @param array
     * @param i
     * @param k
     */
    private void swap(int[] array, int i, int k) {
        int temp = array[k];
        array[k] = array[i];
        array[i] = temp;
    }

    /***
     * 打印排序结果
     */
    public void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }
}
