package com.xie;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BinaryTreeTest {
    @Test
    public void TestPreOrder() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        binaryTree.preOrder(binaryTree.root);
        printList(binaryTree.list);
        binaryTree.list.clear();
        binaryTree.inOrder(binaryTree.root);
        printList(binaryTree.list);
        binaryTree.list.clear();
        binaryTree.postOrder(binaryTree.root);
        printList(binaryTree.list);
    }

    @Test
    public void TestIterate() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        binaryTree.preOrderIterate(binaryTree.root);
        System.out.println();
        binaryTree.inOrderIterate(binaryTree.root);
        System.out.println();
        binaryTree.postOrderIterate(binaryTree.root);
    }

    @Test
    public void TestGetDepth() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        int dep = binaryTree.getDepth(binaryTree.root);
        System.out.print(dep);
        int minDep = binaryTree.getMinDepth(binaryTree.root);
        System.out.print(minDep);
    }

    public void printList(ArrayList<String> list) {
        for (String l :
                list) {
            System.out.print(l);
        }
        System.out.println();
    }
}
