package com.xie;

import org.junit.Test;
import java.util.List;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testApp()
    {
        App app = new App();
        int[] a = {2,3,1,1,4};
        //a = new int[]{7,0,9,6,9,6,1,7,9,0,1,2,9,0,3};
        //a = new int[]{1,1,2,2,0,1,1};
        //a = new int[]{1,3,2};
        //a = new int[]{1,0,2};
        int resi = app.jumpGame1(a);
        System.out.println(resi);
        boolean res = app.jumpGame2(a);
        System.out.println(res);
    }
    @Test
    public void testBinarySearch()
    {
        App app = new App();
        int[] a = {1,3,6,8,12};
        int res = app.binarySearch(a,9);
        System.out.println(res);
        res = app.binarySearch(a,7);
        System.out.println(res);
        res = app.binarySearch(a,5);
        System.out.println(res);
        res = app.binarySearch(a,2);
        System.out.println(res);


    }
    @Test
    public void testCombinationSum2()
    {
        App app = new App();
        int[] a = {1,3,6,8,12};
        List<List<Integer>> res = app.combinationSum2(a,9);
        printList(res);
    }
    private void printList(List<List<Integer>> res){
        for (List<Integer> r:res) {
            for (int i:r){
                System.out.print(i+",");
            }
            System.out.println();
        }
    }
    @Test
    public void testCombinations()
    {
        App app = new App();
        int[] a = {2,1,2,4};
        List<List<Integer>> res = app.combinations3(a,2);
        printList(res);
    }
    @Test
    public void testSubSets()
    {
        App app = new App();
        int[] a = {1,2,2};
        List<List<Integer>> res = app.permute(a);
        printList(res);
    }
}
