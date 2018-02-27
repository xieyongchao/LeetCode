package com.xie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createTree0(binaryTree.root);
        binaryTree.layerOrder2(binaryTree.root);
        System.out.println( "Hello World!" );
    }
    /*
    *input[]={2,3,1,1,1,4}
    * f[]={2,4,4,4,5,8}
    * m[]={1,2,2,2,3,4}
     */
    public int jumpGame(int[] input){
        int l = input.length;
        if (l == 0 || l==1 || input[0] == 0) return 0;
        int[] far = new int[l];
        //int[] num = new int[l];
        far[0] = input[0];
        int num = 1;
        int pre = far[0];
        int cur = pre;
        if (far[0] >= l-1)return 1;
        for (int i = 0; i < l; ){
            for(int j = i + 1;j <= pre && j<l;j++) {
                if (j + input[j] > cur) {
                    cur = j + input[j];
                }
            }
            num++;
            i = pre;
            if(cur>=l-1)break;
            if(cur == pre)return 0;
            pre = cur;
        }
        return num;
    }
    public int jumpGame1(int[] input){
        int l = input.length;
        int num = 0;
        int cur = 0;
        int i = 0 ;
        while (cur < l -1 ) {
            int pre = cur;
            for (; i <= pre; i++) {
                if (i + input[i] > cur) {
                    cur = i + input[i];
                }
            }
            num++;
            if (cur == pre) return 0;
        }
        return num;
    }
    public boolean jumpGame2(int[] input){
        int l = input.length;
        if (l == 0 ) return true;
        if (input[0] == 0) return false;
        int[] far = new int[l];
        far[0] = input[0];
        if (far[0] >= l-1)return true;
        for (int i = 1; i < l; i++){
            if(i<far[i-1]) {
                if (input[i] <= far[i - 1] - 1) {
                    far[i] = far[i - 1];
                }
                if (input[i] > far[i - 1] - 1) {
                    far[i] = far[i - 1] - input[i - 1] + input[i] + 1;
                }
            }
            else {
                if (input[i]==0) return false;
                far[i] = far[i-1]+input[i];
            }
            if (far[i] >= l-1)return true;
        }
        return false;
    }
    public List<List<Integer>> subsetsx(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackx(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrackx(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        //if (start == nums.length)
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrackx(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack1(list, new ArrayList<>(), nums, target, 0);
        return list;

    }

    private void backtrack1(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        if(remain == 0) list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack1(list, tempList, nums, remain - nums[i], i + 1);
            tempList.remove(tempList.size() - 1);
        }

    }
    public List<List<Integer>> combinations (int[] num, int n){
        List<List<Integer>> res = new ArrayList<>();//记录的是路径
        List<Integer> path = new ArrayList<>();
        backtrack3(res,path,num,0,n,0);
        return res;
    }
    private void backtrack3(List<List<Integer>> res,List<Integer> path,int[] num,int k,int n,int level){
        if(level>=n){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=k;i<num.length;i++){
            path.add(num[i]);
            backtrack3(res,path,num,i+1,n,level+1);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> combinations2 (int[] num, int n){//记录的是节点
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack4(res,path,num,0,n);
        return res;
    }
    private void backtrack4(List<List<Integer>> res,List<Integer> path,int[] num,int k,int n){
        if( k>num.length-1 ){
            if( path.size()==n )
            res.add(new ArrayList<>(path));
            return;
        }
        path.add(num[k]);
        backtrack4(res,path,num,k+1,n);
        path.remove(path.size()-1);
        backtrack4(res,path,num,k+1,n);

    }
    public List<List<Integer>> combinations3 (int[] num, int n){//有相同值
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] used= new boolean[num.length];
        backtrack5(res,path,num,0,n,used);
        return res;
    }
    private void backtrack5(List<List<Integer>> res,List<Integer> path,int[] num,int level,int n,boolean[] used){
        if( path.size()==n ){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i=level;i<num.length;i++) {
            if(used[i]||!used[i])continue;
            used[i]=true;
            path.add(num[i]);
            backtrack5(res, path, num, i + 1, n,used);
            used[i]=false;
            path.remove(path.size() - 1);
        }
    }
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack2(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length())
            list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < s.length(); i++){
                if(isPalindrome(s, start, i)){
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
    public void backtrack2(List<List<String>> list, List<String> tempList, String s, int start){

        list.add(new ArrayList<>(tempList));

        for(int i = start; i < s.length(); i++){

            tempList.add(s.substring(start, i + 1));
            backtrack2(list, tempList, s, i + 1);
            tempList.remove(tempList.size() - 1);

        }

    }

    public boolean isPalindrome(String s, int low, int high){
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
    public List<List<String>> partition1(String s) {
        List<List<String>> resultLst = new ArrayList<List<String>>();
        ArrayList<String> currLst = new ArrayList<String>();
        backTrack(resultLst,currLst,s,0);
        return resultLst;
    }
    public void backTrack(List<List<String>> resultLst, List<String> currLst, String s, int l){
        if(currLst.size()>0 //the initial str could be palindrome
                && l>=s.length()){
            List<String> r = new ArrayList<String>(currLst);
            resultLst.add(r);
        }
        for(int i=l;i<s.length();i++){
            if(isPalindrome(s,l,i)){
                if(l==i)
                    currLst.add(Character.toString(s.charAt(i)));
                else
                    currLst.add(s.substring(l,i+1));
                backTrack(resultLst,currLst,s,i+1);
                currLst.remove(currLst.size()-1);
            }
        }
    }
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        for (int num : nums) {  // ①从数组中取出每个元素
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>(res.get(i));  // ②逐一取出中间结果集
                temp.add(num);  // ③将 num 放入中间结果集
                res.add(temp);  // ④加入到结果集中
            }
        }
        return res;
    }
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        dfs(res, temp, nums, 0);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, int j) {
        res.add(new ArrayList<Integer>(temp));
        for(int i = j; i < nums.length; i++) {
            temp.add(nums[i]);  //① 加入 nums[i]
            dfs(res, temp, nums, i + 1);  //② 递归
            temp.remove(temp.size() - 1);  //③ 移除 nums[i]
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> ans = new ArrayList<Integer>();
        backtrack(res,ans,0,nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> ans, int i, int[] nums) {
        if(i==nums.length){
            res.add(new ArrayList<Integer>(ans));  // process solution. add into the result list
            return;
        }
        ans.add(nums[i]);
        backtrack(res,ans,i+1,nums);
        ans.remove(ans.size()-1);
        backtrack(res,ans,i+1,nums);
    }

    public int water(int[] nums) {
        int ans = 0, i = 0, j = nums.length-1, imax = 0, jmax = 0;
        while (i <= j){
            if (nums[i] < nums[j]){
                if(nums[i] > imax)
                    imax = nums[i];
                else
                    ans += (imax - nums[i]);
                i ++ ;
            }else{
                if(nums[j] > jmax)
                    jmax = nums[j];
                else
                    ans += (jmax - nums[j]);
                j -- ;
            }
        }
        return ans;
    }
    boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }
    boolean comparison1(String str, String pattern) {
        int s = str.length();
        int p = pattern.length();
        boolean[][] a = new boolean[s+1][p+1];
        a[0][0] = true;
        for (int i=0;i<s;i++){
            for (int j=0;j<p;j++){
                if (a[i][j] == true && pattern.charAt(j) != '*' && (pattern.charAt(j) == str.charAt(i) || pattern.charAt(j) == '?')) {
                    a[i+1][j+1] = true;
                }
                if (a[i][j] == true && pattern.charAt(j) == '*') {
                    for (int k = i; k < s+1; k++) a[k][j + 1] = true;
                }
            }
        }
        return a[s][p];
    }
    boolean comparison2(String str, String pattern) {
        int s = str.length();
        int p = pattern.length();
        boolean[][] a = new boolean[s+1][p+1];
        a[0][0] = true;
        for (int i=0;i<s;i++){
            for (int j=0;j<p;j++){
                if (pattern.charAt(j) != '*') {
                    a[i+1][j+1] = a[i][j] && (pattern.charAt(j) == str.charAt(i) || pattern.charAt(j) == '?');
                }
                if (pattern.charAt(j) == '*') {
                    a[i+1][j+1] = a[i][j+1] || a[i+1][j];
                }
            }
        }
        return a[s][p];
    }
    int binarySearch(int[] a,int x){
        int l=0,r=a.length-1;
        while(l<r){
            int m = (l+r)/2;
            if (x==a[m])return m;
            else if (x>a[m])l=m+1;
            else r=m;
        }
        return l;
    }
}
