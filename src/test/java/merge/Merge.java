package merge;

public class Merge {

    /**
     *  归并排序原理：先拆分，再合并，归并排序首先将序列进行拆分直到每个序列不能拆分为止，
     *  然后对序列进行两两合并(合并的过程需要排序),知道最终只存在一个序列为止
     *
     *  归并排序效率分析：归并排序运行的时间复杂度为O(nlogn) 空间代价为O(n)
     *
     *  最好情况和最差情况下时间复杂度均为O(nlogn),归并排序是一种稳定的外部排序算法
     *
     *  归并排序C 代码递归方式实现(当然可以对其采用迭代方式实现)
     *
     *  void mergeSort(int[] array,int n)
     *  {
     *        if(n>1) //当序列长度大于1时进行拆分
     *        {
     *        		int *list1 = array;
     *        		int list1_size = 2/n;
     *        		int *list2 = array+2/n;
     *        		int list2_size = n-list1_size;
     *
     *        		mergeSort(list1,list1_size); //对序列进行拆分
     *        		mergeSort(list2,list2_size);
     *
     *        		merging(list1,list1_size,list2,list2_size);//对两个序列进行合并
     *        }
     *  }
     *
     *  void merging(int *list1,int list1_size,int *list2,int list2_size)
     *  {
     *  	 int i,j,k,m;
     *  	 int temp[maxsize];
     *  	 i=j=k=0;
     *  	while(i<list1_size&&j<list2_size) /将两个序列元素进行合并
     *    {
     *      	if(list1[i]<list2[j])
     *        {
     *      		temp[k++] = list1[i++];
     *        }else{
     *      		temp[k++] = list2[j++];
     *        }
     *      }
     *
     *      while(i<list1_size)  //将序列中剩余元素添加到temp中，两个序列中最多只会有一个序列包含剩余元素
     *      {
     *      	temp[k++] = list1[i++];
     *      }
     *
     * 		while(i<list1_size)
     *      {
     *      	temp[k++] = list1[i++];
     *      }
     *
     *      for(m=0,m<(list1_size+list2_size);m++)
     *      {
     *      	list1[m] = temp[m];
     *      }
     *  }
     *
     */

    /**
     * 归并排序
     *
     * @param a
     * @param p
     * @param q
     * @param r
     */
    public static void merge(int a[], int p, int q, int r) {
        int i, j, k, n1, n2;
        n1 = q - p + 1;
        n2 = r - q;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (i = 0, k = p; i < n1; i++, k++) {
            L[i] = a[k];
        }
        for (i = 0, k = q + 1; i < n2; i++, k++) {
            R[i] = a[k];
        }
        for (k = p, i = 0, j = 0; i < n1 && j < n2; k++) {
            if (L[i] > R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
        }
        if (i < n1) {
            for (j = i; j < n1; j++, k++) {
                a[k] = L[j];
            }
        }
        if (j < n2) {
            for (i = j; i < n2; i++, k++) {
                a[k] = R[i];
            }
        }
    }

    public static void mergeSort(int a[], int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }

}
