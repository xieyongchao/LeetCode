package com.xie;

import org.junit.Test;

public class SearchTreeTest {
    @Test
    public void TestCreate() {
        SearchTree searchTree = new SearchTree();
        searchTree.init();
        searchTree.preOrder(searchTree.root);
        System.out.println();
        searchTree.inOrder(searchTree.root);
        System.out.println();
        searchTree.postOrder(searchTree.root);
        System.out.println();
        int[] a = {1, 2, 4, 3, 6, 9, 8, 7, 5};
        boolean res = searchTree.IsBSTSeq(a, 0, a.length - 1);
        System.out.println(res);
        int[] b = {1,2,3,4,5,6,7,8,9};
        NodeInt root = null;
        root = searchTree.transToBlanceTree(root,b,0,b.length-1);
        searchTree.preOrder(root);
        System.out.println();
        searchTree.inOrder(root);
        System.out.println();
        searchTree.postOrder(root);
        System.out.println();
        int[] s = {0};
        root = null;
        root = searchTree.transToBlanceTree2(b,s,0,b.length-1);
        searchTree.preOrder(root);
        System.out.println();
        searchTree.inOrder(root);
        System.out.println();
        searchTree.postOrder(root);
        System.out.println();
        boolean result = searchTree.isBST(searchTree.root);
        System.out.println(result);
        result = searchTree.isBST(root);
        System.out.println(result);
    }
}
