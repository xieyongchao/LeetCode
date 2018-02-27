package com.xie;

import java.util.LinkedList;
import java.util.Stack;

public class SearchTree {
    NodeInt root;

    public NodeInt InsertNode(NodeInt root, int data) {
        if (root == null) {
            root = new NodeInt(data);
            return root;
        }
        if (data < root.data) {
            root.lchild = InsertNode(root.lchild, data);
        } else {
            root.rchild = InsertNode(root.rchild, data);
        }
        return root;
    }

    public void init() {
        int[] a = {5, 7, 3, 8, 6, 4, 2, 9, 1};
        for (int aa :
                a) {
            root = InsertNode(root, aa);
        }
    }

    public void preOrder(NodeInt root) {
        if (root == null) return;
        NodeInt pre = root;
        Stack<NodeInt> s = new Stack<>();
        while (pre != null || !s.isEmpty()) {
            if (pre != null) {
                System.out.print(pre.data);
                s.push(pre);
                pre = pre.lchild;
            } else {
                pre = s.pop();
                pre = pre.rchild;
            }
        }
    }

    public void inOrder(NodeInt root) {
        if (root == null) return;
        NodeInt pre = root;
        Stack<NodeInt> s = new Stack<>();
        while (pre != null || !s.empty()) {
            if (pre != null) {
                s.push(pre);
                pre = pre.lchild;
            } else {
                pre = s.pop();
                System.out.print(pre.data);
                pre = pre.rchild;
            }
        }
    }

    public void postOrder(NodeInt root) {
        if (root == null) return;
        NodeInt pre = root, cur = root;
        Stack<NodeInt> s = new Stack<>();
        s.push(root);
        while (!s.empty()) {
            cur = s.peek();
            if (cur.lchild == null && cur.rchild == null || (cur.lchild == pre || cur.rchild == pre)) {
                System.out.print(cur.data);
                pre = cur;
                s.pop();
            } else {
                if (cur.rchild != null) {
                    s.push(cur.rchild);
                }
                if (cur.lchild != null) {
                    s.push(cur.lchild);
                }
            }
        }
    }

    public boolean IsBSTSeq(int[] a, int left, int right) {
        if (left >= right) return true;
        int i;
        for (i = right - 1; i >= left && a[i] >= a[right]; i--) {
        }
        int j;
        for (j = i; j >= left && a[j] < a[right]; j--) {
        }
        if (j != left - 1) {
            return false;
        }
        return IsBSTSeq(a, 0, i) && IsBSTSeq(a, i + 1, right - 1);
    }

    public NodeInt transToBlanceTree(NodeInt root, int[] a, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        root = new NodeInt(a[mid]);
        root.lchild = transToBlanceTree(root.lchild, a, left, mid - 1);
        root.rchild = transToBlanceTree(root.rchild, a, mid + 1, right);
        return root;
    }

    //两种都可以用链表，第二种复杂度低On,第一种Onlogn
    public NodeInt transToBlanceTree2(int[] a, int[] s, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        NodeInt l = transToBlanceTree2(a, s, left, mid - 1);
        NodeInt root = new NodeInt();
        root.lchild = l;
        root.data = a[s[0]];
        s[0]++;
        root.rchild = transToBlanceTree2(a, s, mid + 1, right);
        return root;
    }

    public boolean isBST(NodeInt root) {
        if (root == null) return true;
        if (root.lchild != null && root.lchild.data >= root.data) return false;
        if (root.rchild != null && root.data > root.rchild.data) return false;
        return isBST(root.lchild) && isBST(root.rchild);
    }

}
