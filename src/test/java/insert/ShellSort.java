package insert;

public class ShellSort {

    public static void sSort(int a[]) {
        int length = a.length;
        int i, j;
        int h;
        int temp;
        for (h = length / 2; h > 0; h = h / 2) {
            for (i = h; i < length; i++) {
                temp = a[i];
                for (j = i - h; j >= 0; j -= h) {
                    if (temp < a[i]) {
                        a[j + h] = a[j];
                    } else {
                        break;
                    }
                }
                a[j + h] = temp;
            }
        }
    }


    /**
     * 希尔排序原理：设置间隔变量，将数组进行分组，组内排序，然后缩小间隔变量，直至间隔变量为1(结束条件)
     * 希尔排序的空间占用率为O(1),
     * <p>
     * 希尔排序在平均情况下时间复杂度为O(nlogn),最好情况下为O(n的平方)
     * <p>
     * 希尔排序是一种不稳定的内部排序算法
     *
     * @param array
     */
    public static int[] shellSort(int[] array) {
        int len = array.length;//获取数组长度
        int i, j, gap = len;//gap为初始间隔变量
        int temp;
        do {
            gap = gap / 3 + 1;//设置每次比较的循环变量
            for (i = gap; i < len; i++) {
                temp = array[i];
                for (j = i - gap; j > 0 && array[j] > temp; j -= gap)//此处执行的是直接插入排序
                {
                    array[j + gap] = array[j];
                }
                array[j + gap] = temp;
            }
        } while (gap > 1);

        return array;
    }

    /***
     * 打印排序结果
     */
    public static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 5, 9, 3, 4, 18, 7, 6};
        ShellSort.print(ShellSort.shellSort(array));
    }
}
