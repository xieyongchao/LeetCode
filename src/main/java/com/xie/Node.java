package com.xie;

public class Node {
    public String data;
    public Node lchild;
    public Node rchild;

    public Node() {
    }

    public Node(String data, Node lchild, Node rchild) {
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;
    }
}
