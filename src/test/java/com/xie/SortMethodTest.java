package com.xie;

import org.junit.Test;

public class SortMethodTest {
    @Test
    public void TestSort() {
        int[] a = {2, 5, 4, 3, 7, 1, 6, 8};
        SortMethod sortMethod = new SortMethod();
        sortMethod.BubbleSort(a);
        print(a);
        a = new int[]{2, 5, 4, 3, 7, 1, 6, 8};
        sortMethod.SelectSort(a);
        print(a);
        a = new int[]{2, 5, 4, 3, 7, 1, 6, 8};
        sortMethod.InsertSort(a);
        print(a);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8};
        int res = sortMethod.partition2(a, 0, 7);
        print(a);
        System.out.println(res);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8};
        sortMethod.QuickSort(a, 0, 7);
        print(a);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8};
        sortMethod.HeapSort(a);
        print(a);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8, 45, 23, 54, 13, 18, 16, 29, 22, 31};
        sortMethod.ShellSort(a);
        print(a);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8};
        sortMethod.ShellSort2(a);
        print(a);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8};
        sortMethod.MergeSort(a, 0, 7);
        print(a);
        a = new int[]{4, 5, 2, 3, 9, 0, 1, 6, 8, -1, -3, -5, -2, 0};
        sortMethod.CountSort(a);
        print(a);
        a = new int[]{14, 95, 52, 43, 77, 61, 26, 38};
        sortMethod.BucketSort(a);
        print(a);
        a = new int[]{14, 95, 52, 43, 77, 61, 26, 38};
        sortMethod.RadixSort(a);
        print(a);
        a = new int[]{4, 5, 2, 3, 7, 1, 6, 8};
        sortMethod.BinaryInsertSort(a);
        print(a);
        System.out.println("binarySearch");
        a = new int[]{1, 3, 5, 5, 5, 8, 9};
        int target = 6;
        res = sortMethod.binarySearch(a, 0, a.length - 1, target);
        System.out.println(res);
        res = sortMethod.binarySearch2(a, 0, a.length - 1, target);
        System.out.println(res);
        res = sortMethod.binarySearch3(a, 0, a.length - 1, target);
        System.out.println(res);
        res = sortMethod.binarySearch4(a, 0, a.length - 1, target);
        System.out.println(res);
        res = sortMethod.binarySearch5(a, 0, a.length - 1, target);
        System.out.println(res);
        res = sortMethod.binarySearch6(a, 0, a.length - 1, target);
        System.out.println(res);
        res = sortMethod.binarySearch7(a, 0, a.length - 1, target);
        System.out.println(res);
    }

    private void print(int[] a) {
        for (int i :
                a) {
            System.out.print(i);
        }
        System.out.println();
    }
}
