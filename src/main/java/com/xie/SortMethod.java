package com.xie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class SortMethod {
    //冒泡排序
    public void BubbleSort(int[] a) {
        int len = a.length;
        for (int j = len - 1; j > 0; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (a[i] > a[i + 1]) {
                    swap(a, i, i + 1);
                }
            }
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    //选择排序
    public void SelectSort(int[] a) {
        int len = a.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (a[minIndex] < a[i]) {
                swap(a, minIndex, i);
            }
        }
    }

    //插入排序
    public void InsertSort(int[] a) {
        int len = a.length;
        for (int i = 1; i < len; i++) {
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    //快速排序:快速排序的思想：冒泡(partition的本质)+二分+递归分治
    public void QuickSort(int[] a, int left, int right) {
        if (left >= right) return;
        int p = partition(a, left, right);
        QuickSort(a, left, p - 1);
        QuickSort(a, p + 1, right);
    }

    /**
     * @return 返回pivot所在位置下标
     * @a 待排序的数组
     * @left 需要partition的起始下标
     * @right 需要partition的末尾下标
     */
    public int partition(int[] a, int left, int right) {
        int pivotIndex = left;
        int pivot = a[left];
        while (left < right) {
            while (a[right] > pivot && left < right) {
                right--;
            }
            //=保证左边第一个不被移走，若是=在右边，则首先应该将候选值放在最后，再反过来
            while (a[left] <= pivot && left < right) {
                left++;
            }
            if (left < right) {
                swap(a, left, right);
            }

        }
        //没被移走，所以才能换到中间(最后left=right)
        swap(a, pivotIndex, right);
        return right;
    }

    public int partition2(int[] a, int left, int right) {
        int low = left + 1;
        int high = right;//可省略新定义一个变量
        while (low < high) {
            while (a[high] >= a[left] && low < high) high--;//注意high先开始
            while (a[low] < a[left] && low < high) low++;
            if (low < high) {
                swap(a, low, high);
            }
        }
        swap(a, left, high);
        return high;
    }

    public void HeapSort(int[] a) {
        int len = a.length;
        //从后往前建立最大堆(从len/2-1开始(有子节点才有调整的必要）:参考Adjust函数，大于这个值的都没有进入循环)
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapAdjust2(a, i, len - 1);
        }
        //将最大值与最后节点值交换，然后将剩下的建堆
        for (int i = len - 1; i > 0; i--) {
            swap(a, 0, i);
            heapAdjust2(a, 0, i - 1);
        }
    }

    public void heapAdjust(int[] a, int start, int end) {
        //2 * child + 1为左节点，2 * child + 2为右节点
        for (int child = start * 2 + 1; child <= end; child = 2 * child + 1) {
            if (child < end && a[child] < a[child + 1]) {
                child++;
            }
            //若start比子节点大，说明不需要调整（因为child以下的默认已经为大顶堆）
            if (a[start] > a[child]) {
                break;
            }
            swap(a, start, child);
            start = child;
        }
    }

    //减少了交换次数，类似于插入排序和冒泡排序的区别
    public void heapAdjust2(int[] a, int start, int end) {
        int temp = a[start];
        for (int child = start * 2 + 1; child <= end; child = child * 2 + 1) {
            if (child < end && a[child] < a[child + 1]) {
                child++;
            }
            if (temp > a[child]) {
                break;
            }
            a[start] = a[child];
            start = child;
        }
        a[start] = temp;
    }

    public void ShellSort(int[] a) {
        int len = a.length;
        for (int k = len; k > 0; k = k / 2) {
            for (int m = 0; m < k; m++) {
                for (int i = m; i < len; i = i + k) {
                    int temp = a[i];
                    int j;
                    for (j = i; j - k >= 0 && a[j - k] > temp; j = j - k) {
                        a[j] = a[j - k];
                    }
                    a[j] = temp;
                }
            }
        }
    }

    public void ShellSort2(int[] a) {
        int k, i, j;
        for (k = a.length; k > 0; k = k / 2) {
            for (i = k; i < a.length; i++) {
                int temp = a[i];
                for (j = i - k; j >= 0 && a[j] > temp; j = j - k) {
                    a[j + k] = a[j];
                }
                a[j + k] = temp;
            }
        }
    }

    public void MergeSort(int[] a, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        MergeSort(a, left, mid);
        MergeSort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    //用辅助空间太能保证合并的复杂度为On，不用辅助空间，就是类似于插入排序方法合并
    public void merge(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (a[i] < a[j]) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = a[i];
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = a[j];
            j++;
            k++;
        }
        for (i = 0, j = left; i < temp.length; i++, j++) {
            a[j] = temp[i];
        }
    }

    //todo 二分插入排序
    public void BinaryInsertSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int temp = a[i];
            int j = i - 1;
            int insertIndex = binarySearch7(a, 0, j, temp);
            while (j >= insertIndex) {
                a[j + 1] = a[j];
                j--;
            }
            a[insertIndex] = temp;
        }
    }

    //递归只能用来找等于的情况，找插入无论返回left,right都不对
    public int binarySearch(int[] a, int left, int right, int target) {
        if (left >= right) return left;
        int mid = (left + right) / 2;
        if (a[mid] == target) return mid;
        if (a[mid] > target) return binarySearch(a, left, mid - 1, target);
        else return binarySearch(a, mid + 1, right, target);
    }

    public int binarySearch2(int[] a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == target) return mid;
            else if (a[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    public int binarySearch3(int[] a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] == target) return mid;
            else if (a[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    public int binarySearch4(int[] a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    public int binarySearch5(int[] a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] >= target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    public int binarySearch6(int[] a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }

    public int binarySearch7(int[] a, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    //计数排序
    public void CountSort(int[] a) {
        int i, max = a[0], min = a[0];
        for (i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        int[] temp = new int[max - min + 1];
        for (i = 0; i < a.length; i++) {
            temp[a[i] - min]++;
        }
        int j;
        for (i = 0, j = 0; i < temp.length; i++) {
            while (temp[i] > 0) {
                a[j] = i + min;
                j++;
                temp[i]--;
            }
        }
    }

    //桶排序:根据某个原则（十位或者百位的值等等）分到多个桶里，桶里各自进行排序
    public void BucketSort(int[] a) {
        int bucketSize = 10;//十位的值为例
        ArrayList<LinkedList<Integer>> bucketSet = new ArrayList<>();
        int i;
        for (i = 0; i < bucketSize; i++) {
            bucketSet.add(new LinkedList<>());
        }
        for (i = 0; i < a.length; i++) {
            bucketSet.get(a[i] / 10).add(a[i]);
        }
        int j = 0;
        for (i = 0; i < bucketSize; i++) {
            int s = bucketSet.get(i).size();
            if (s > 0) {
                Collections.sort(bucketSet.get(i));
                for (int k = 0; k < s; k++, j++) {
                    a[j] = bucketSet.get(i).removeFirst();
                }
            }
        }
    }

    //基数排序
    public void RadixSort(int[] a) {
        int maxRi = getMaxRi(a);
        for (int j = 0; j < maxRi; j++) {
            collect(a, distrubute(a, j));
        }
    }

    //分解到桶
    public ArrayList<ArrayList<Integer>> distrubute(int[] a, int index) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        int i;
        for (i = 0; i < 10; i++) {
            res.add(new ArrayList<>());
        }
        for (i = 0; i < a.length; i++) {
            res.get(getRiNum(a[i], index)).add(a[i]);
        }
        return res;
    }

    //从桶收集到数组
    public void collect(int[] a, ArrayList<ArrayList<Integer>> b) {
        int i = 0;
        for (ArrayList<Integer> bb : b
                ) {
            for (int bbb : bb
                    ) {
                if (i > a.length) break;
                a[i] = bbb;
                i++;
            }
        }
    }

    public int getRiNum(int a, int index) {
        String aStr = a + "";
        if (index > aStr.length() - 1) {
            return 0;
        }
        return aStr.charAt(aStr.length() - index - 1) - '0';
    }

    public int getMaxRi(int[] a) {
        int max = 0, res;
        for (int i = 0; i < a.length; i++) {
            res = 0;
            int temp = a[i];
            while (temp != 0) {
                temp = temp / 10;
                res++;
            }
            if (res > max) {
                max = res;
            }
        }
        return max;
    }

}
