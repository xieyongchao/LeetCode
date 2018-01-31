package com.xie;


import java.util.ArrayList;

public class BinaryTree {
    public Node root;
    public ArrayList<String> list = new ArrayList<>();

    public class Node {
        private String data;
        private Node lchild;
        private Node rchild;

        public Node(String data, Node lchild, Node rchild) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    public void init() {
        Node j = new Node("j", null, null);
        Node k = new Node("k", null, null);
        Node x = new Node("X", null, null);
        Node y = new Node("Y", null, null);
        Node d = new Node("d", x, y);
        Node e = new Node("e", null, null);
        Node f = new Node("f", null, k);
        Node c = new Node("c", e, f);
        Node b = new Node("b", d, null);
        Node a = new Node("a", b, c);
        this.root = a;
    }

    public void preOrder(Node root) {
        if (root == null) {
            return;
        }
        list.add(root.data);
        preOrder(root.lchild);
        preOrder(root.rchild);
    }

    public void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.lchild);
        list.add(root.data);
        inOrder(root.rchild);
    }

    public void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.lchild);
        postOrder(root.rchild);
        list.add(root.data);
    }

    public int getDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getDepth(root.lchild), getDepth(root.rchild)) + 1;
    }

    public int getMinDepth(Node root) {
        int dep = 1;
        if (root == null) {
            return 0;
        }
        if (root.lchild != null && root.rchild == null) {
            dep = getMinDepth(root.lchild) + 1;
        }
        if (root.rchild != null && root.lchild == null) {
            dep = getMinDepth(root.rchild) + 1;
        }
        if (root.lchild != null && root.rchild != null) {
            int minL = getMinDepth(root.lchild);
            int minR = getMinDepth(root.rchild);
            dep = Math.min(minL, minR) + 1;
        }
        return dep;
    }


}
