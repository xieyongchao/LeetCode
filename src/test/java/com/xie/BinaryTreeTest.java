package com.xie;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

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
        System.out.println();
        binaryTree.postOrderIterate2(binaryTree.root);
        System.out.println();
        binaryTree.layerOrder(binaryTree.root);
        System.out.println();
        binaryTree.layerOrder2(binaryTree.root);
        System.out.println();
        binaryTree.layerOrder3(binaryTree.root);
        System.out.println();
        binaryTree.zigzagLevelOrder(binaryTree.root);
        System.out.println();
        binaryTree.zigzagLevelOrder1(binaryTree.root);
    }

    @Test
    public void TestGetDepth() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        int dep = binaryTree.getDepth(binaryTree.root);
        System.out.println(dep);
        int minDep = binaryTree.getMinDepth(binaryTree.root);
        System.out.println(minDep);
        int NodeNum = binaryTree.countNode(binaryTree.root);
        System.out.println(NodeNum);
        int res = binaryTree.countLeaf(binaryTree.root);
        System.out.println(res);
        res = binaryTree.countKthNode(binaryTree.root, 4);
        System.out.println(res);
    }

    @Test
    public void TestCreateTree() {
        BinaryTree binaryTree = new BinaryTree();
        String[] s = {"a", "b", "d", "x", "#", "#", "y", "#", "#", "#", "c", "e", "#", "#", "f", "k", "#", "#", "#",};
        int[] start = {0};
        binaryTree.root = binaryTree.createTree4(binaryTree.root, s, start);
        binaryTree.layerOrder2(binaryTree.root);
        BinaryTree binaryTree1 = new BinaryTree();
        binaryTree1.init();
        boolean res = binaryTree.compareTree(binaryTree.root, binaryTree1.root);
        System.out.println(res);
        binaryTree1.swapChild(binaryTree1.root);
        binaryTree1.layerOrder2(binaryTree1.root);
    }

    @Test
    public void TestFindTree() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        Node e = binaryTree.getNodeByName(binaryTree.root, "e");
        System.out.println(e.data);
        Node f = binaryTree.getNodeByName(binaryTree.root, "X");
        System.out.println(binaryTree.hasNode(binaryTree.root, f));
        Node p = binaryTree.findParent(binaryTree.root, e, f);
        if (p == null) System.out.println("null");
        else System.out.println(p.data);
        p = binaryTree.findParent2(binaryTree.root, e, f);
        if (p == null) System.out.println("null");
        p = binaryTree.findLCA(binaryTree.root, e, f);
        if (p == null) System.out.println("null");
        else System.out.println(p.data);
        ArrayList<Node> res = new ArrayList<>();
        binaryTree.getRouteByNode(binaryTree.root, e, res);
        for (Node i :
                res) {
            System.out.print(i.data);
        }
        System.out.println(binaryTree.FindDistance(binaryTree.root, e, f));
        p = binaryTree.findPre(binaryTree.root,e);
        if (p == null) System.out.println("null");
        else System.out.println(p.data);
        p = binaryTree.findNext(binaryTree.root,e);
        if (p == null) System.out.println("null");
        else System.out.println(p.data);
        p = new Node();
        binaryTree.findNext2(binaryTree.root,e,new Node(),p);
        System.out.println(p.data);
    }

    @Test
    public void TestLength() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        int[] maxLen = {0};
        binaryTree.MaxLen(binaryTree.root, maxLen);
        System.out.println(maxLen[0]);
        Node e = binaryTree.getNodeByName(binaryTree.root, "e");
        int level = binaryTree.FindLevel(binaryTree.root, e);
        System.out.println(level);
        binaryTree.postOrderO1(binaryTree.root);
        System.out.println();
        System.out.println(binaryTree.isBalance(binaryTree.root));
        boolean[] res = {true};
        int dep = binaryTree.getDep(binaryTree.root, res);
        System.out.println(dep);
        System.out.println(res[0]);
        int[] d = {0};
        boolean r = binaryTree.isBalance(binaryTree.root, d);
        System.out.println(d[0]);
        System.out.println(r);
        r = binaryTree.isPerfectTree(binaryTree.root);
        System.out.print("perfect?:");
        System.out.println(r);
        LinkedList<Node> l = new LinkedList<>();
        l.add(null);
        System.out.println(l.size());
        r = binaryTree.isPerfectTree2(binaryTree.root);
        System.out.print("perfect2?:");
        System.out.println(r);
        r = binaryTree.isPerfectTree3(binaryTree.root);
        System.out.print("perfect3?:");
        System.out.println(r);
    }

    @Test
    public void TestTransfer() {
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.init();
        Node[] head = {new Node()};
        binaryTree.transToLink(binaryTree.root, head);
        Node pre = head[0];
        while (pre.lchild != null) {
            System.out.print(pre.data);
            pre = pre.lchild;
        }
        System.out.println();
        binaryTree = new BinaryTree();
        binaryTree.init();
        Node[] head2 = {new Node()};
        binaryTree.transToLink2(binaryTree.root, head2);
        pre = head2[0];
        while (pre.rchild != null) {
            System.out.print(pre.data);
            pre = pre.rchild;
        }
        System.out.println();
        binaryTree = new BinaryTree();
        binaryTree.init();
        Node h = binaryTree.transToLink3(binaryTree.root);
        pre = h;
        System.out.println();
        while (pre.lchild != null) {
            System.out.print(pre.data);
            pre = pre.lchild;
        }
        System.out.println();
    }

    @Test
    public void TestCreate(){
        BinaryTree binaryTree = new BinaryTree();
        String[] pre = {"a","b","d","X","Y","c","e","f","k"};
        String[] in = {"X","d","Y","b","a","e","c","k","f"};
        int[] s = {0};
        Node r = binaryTree.PreAndInToTree(pre,s,in,0,in.length);
        System.out.println();
        binaryTree.preOrderIterate(r);
        System.out.println();
        binaryTree.inOrderIterate(r);
        System.out.println();
        binaryTree.postOrderIterate(r);
        System.out.println();
        Node p = binaryTree.PreAndInToPost(pre,0,in,0,in.length);
        System.out.println();
        binaryTree.preOrderIterate(p);
        System.out.println();
        binaryTree.inOrderIterate(p);
        System.out.println();
        binaryTree.postOrderIterate(r);

    }
    public void printList(ArrayList<String> list) {
        for (String l :
                list) {
            System.out.print(l);
        }
        System.out.println();
    }
}
