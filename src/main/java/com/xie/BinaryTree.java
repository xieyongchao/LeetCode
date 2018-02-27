package com.xie;


import java.util.*;

public class BinaryTree {
    public Node root = new Node();
    private int start = 0;
    public ArrayList<String> list = new ArrayList<>();

    public void init() {
        Node j = new Node("j", null, null);
        Node k = new Node("k", null, null);
        Node x = new Node("X", null, null);
        Node y = new Node("Y", null, null);
        Node d = new Node("d", x, y);
        Node e = new Node("e", null, null);
        Node f = new Node("f", k, null);
        Node c = new Node("c", e, f);
        Node b = new Node("b", d, null);
        Node a = new Node("a", b, c);
        this.root = a;
    }

    //abcd#efxy###k
    public Node createTree(String[] s, int start) {
        if (s.length - start < 3 || start < 0) return null;
        if (s[start].equals("#")) {
            return null;
        }
        Node n = new Node();
        n.data = s[start];
        n.lchild = createTree(s, (int) Math.pow(2, start + 1) - 1);
        n.rchild = createTree(s, (int) Math.pow(2, start + 1));
        return n;
    }

    //这种方式不行，因为null并没有分配地址
    public void createTree(Node root) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        if (data.equals("#")) {
            root = null;
        } else {
            root = new Node();
            root.data = data;
            createTree(root.lchild);
            createTree(root.rchild);
        }
    }

    //这种方式也不行，因为分配地址后再传进函数，改变的只是地址的值，不是地址所指向的位置的值
    public void createTree0(Node root) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        if (data.equals("#")) {
            root = null;
        } else {
            root.lchild = new Node();
            root.rchild = new Node();
            root.data = data;
            createTree0(root.lchild);
            createTree0(root.rchild);
        }
    }

    public Node createTree2(Node root) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        if (data.equals("#")) {
            root = null;
        } else {
            root = new Node();
            root.data = data;
            root.lchild = createTree2(root.lchild);
            root.rchild = createTree2(root.rchild);
        }
        return root;
    }

    public Node createTree3(Node root, String[] a) {
        if (start >= a.length) {
            return null;
        }
        String data = a[start];
        start++;
        if (data.equals("#")) {
            root = null;
        } else {
            root = new Node();
            root.data = data;
            root.lchild = createTree3(root.lchild, a);
            root.rchild = createTree3(root.rchild, a);
        }
        return root;
    }

    public Node createTree4(Node root, String[] a, int[] s) {
        if (s[0] >= a.length) {
            return null;
        }
        String data = a[s[0]];
        s[0]++;
        if (data.equals("#")) {
            root = null;
        } else {
            root = new Node();
            root.data = data;
            root.lchild = createTree4(root.lchild, a, s);
            root.rchild = createTree4(root.rchild, a, s);
        }
        return root;
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

    public void preOrderIterate(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> s = new Stack<>();
        Node pre = root;
        while (pre != null || !s.empty()) {
            if (pre != null) {
                s.push(pre);
                System.out.print(pre.data);
                pre = pre.lchild;
            } else {
                pre = s.pop();
                pre = pre.rchild;
            }
        }
    }

    public void inOrderIterate(Node root) {
        if (root == null) return;
        Node pre = root;
        Stack<Node> s = new Stack<>();
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

    public void postOrderIterate(Node root) {
        if (root == null) return;
        Node pre = root;
        Stack<Node> s = new Stack<>();
        s.push(root);
        while (!s.empty()) {
            Node t = s.peek();
            if (t.lchild == null && t.rchild == null || (pre == t.lchild || pre == t.rchild)) {
                System.out.print(t.data);
                s.pop();
                pre = t;
            } else {
                if (t.rchild != null) {
                    s.push(t.rchild);
                }
                if (t.lchild != null) {
                    s.push(t.lchild);
                }
            }
        }
    }

    public void postOrderIterate2(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        LinkedList<String> q = new LinkedList<String>();
        Node pre = root;
        while (pre != null || !s.empty()) {
            if (pre != null) {
                s.push(pre);
                q.addFirst(pre.data);
                pre = pre.rchild;
            } else {
                pre = s.pop();
                pre = pre.lchild;
            }
        }
        for (String i :
                q) {
            System.out.print(i);
        }
    }

    public void layerOrder(Node root) {
        if (root == null) return;
        LinkedList<Node> l = new LinkedList<>();
        l.add(root);
        Node pre;
        while (!l.isEmpty()) {
            pre = l.removeFirst();
            System.out.print(pre.data);
            if (pre.lchild != null) {
                l.add(pre.lchild);
            }
            if (pre.rchild != null) {
                l.add(pre.rchild);
            }
        }
    }

    public void layerOrder2(Node root) {
        if (root == null) return;
        LinkedList<Node> l = new LinkedList<>();
        l.add(root);
        Node pre;
        Node last = root;
        Node nlast = root;
        while (!l.isEmpty()) {
            pre = l.removeFirst();
            System.out.print(pre.data);
            if (pre.lchild != null) {
                l.add(pre.lchild);
                nlast = pre.lchild;
            }
            if (pre.rchild != null) {
                l.add(pre.rchild);
                nlast = pre.rchild;
            }
            if (pre == last) {
                last = nlast;
                System.out.println();
            }
        }
    }

    public void layerOrder3(Node root) {
        if (root == null) return;
        LinkedList<Node> l = new LinkedList<>();
        l.add(root);
        Node cur;
        while (!l.isEmpty()) {
            int size = l.size();
            for (int i = 0; i < size; i++) {
                cur = l.removeFirst();
                System.out.print(cur.data);
                if (cur.lchild != null) {
                    l.add(cur.lchild);
                }
                if (cur.rchild != null) {
                    l.add(cur.rchild);
                }
            }
            System.out.println();
        }
    }

    public void zigzagLevelOrder(Node root) {
        if (root == null) return;
        LinkedList<Node> l = new LinkedList<>();
        l.add(root);
        int dep = 0;
        Node cur;
        while (!l.isEmpty()) {
            int size = l.size();
            for (int i = 0; i < size; i++) {
                if (dep % 2 == 0) {
                    cur = l.removeFirst();
                    System.out.print(cur.data);
                    if (cur.lchild != null) {
                        l.add(cur.lchild);
                    }
                    if (cur.rchild != null) {
                        l.add(cur.rchild);
                    }
                } else {
                    cur = l.removeLast();
                    System.out.print(cur.data);
                    if (cur.rchild != null) {
                        l.addFirst(cur.rchild);
                    }
                    if (cur.lchild != null) {
                        l.addFirst(cur.lchild);
                    }
                }
            }
            dep++;
            System.out.println();
        }
    }

    public void zigzagLevelOrder1(Node root) {
        if (root == null) return;
        LinkedList<Node> l = new LinkedList<>();
        l.add(root);
        Node cur;
        Node last = root;
        Node nlast = root;
        LinkedList<String> res = new LinkedList<>();
        LinkedList<LinkedList<String>> r = new LinkedList<>();
        int dep = 0;
        boolean flag = false;
        while (!l.isEmpty()) {
            cur = l.removeFirst();
            if (flag) {
                res.addFirst(cur.data);
            } else {
                res.add(cur.data);
            }
            if (cur.lchild != null) {
                l.add(cur.lchild);
                nlast = cur.lchild;
            }
            if (cur.rchild != null) {
                l.add(cur.rchild);
                nlast = cur.rchild;
            }
            if (cur == last) {
                last = nlast;
                flag = !flag;
                r.add(res);
                res = new LinkedList<>();
            }
        }
        for (LinkedList<String> i :
                r) {
            for (String k :
                    i) {
                System.out.print(k);
            }
            System.out.println();
        }
    }

    public int countNode(Node root) {
        if (root == null) return 0;
        return countNode(root.lchild) + countNode(root.rchild) + 1;
    }

    public int countLeaf(Node root) {
        if (root == null) return 0;
        if (root.lchild == null && root.rchild == null) {
            return 1;
        }
        return countLeaf(root.lchild) + countLeaf(root.rchild);
    }

    public int countKthNode(Node root, int k) {
        if (root == null || k <= 0) return 0;
        if (k == 1) return 1;
        return countKthNode(root.lchild, k - 1) + countKthNode(root.rchild, k - 1);
    }

    public boolean compareTree(Node root, Node root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }
        return compareTree(root.lchild, root1.lchild) && compareTree(root.rchild, root1.rchild);
    }

    public void swapChild(Node node) {
        if (node == null) return;
        Node temp = node.lchild;
        node.lchild = node.rchild;
        node.rchild = temp;
        swapChild(node.lchild);
        swapChild(node.rchild);
    }

    public Node getNodeByName(Node root, String name) {
        if (root == null) return null;
        if (root.data.equals(name)) return root;
        Node find = getNodeByName(root.lchild, name);
        if (find == null) {
            find = getNodeByName(root.rchild, name);
        }
        return find;
    }

    //
    public boolean getRouteByNode(Node root, Node node, ArrayList<Node> res) {
        if (root == null) return false;
        res.add(root);
        if (root == node) {
            return true;
        }
        boolean l = getRouteByNode(root.lchild, node, res);
        if (!l) {
            boolean r = getRouteByNode(root.rchild, node, res);
            if (!r) {
                res.remove(res.size() - 1);
                return false;
            }
        }
        return true;
    }

    public boolean hasNode(Node root, Node node) {
        if (root == null) return false;
        if (root == node) return true;
        return hasNode(root.lchild, node) || hasNode(root.rchild, node);
    }

    public Node findParent(Node root, Node n1, Node n2) {
        if (root == null) return null;
        if (hasNode(root.lchild, n1) && hasNode(root.rchild, n2) || hasNode(root.lchild, n2) && hasNode(root.rchild, n1))
            return root;
        if (hasNode(root.lchild, n1) && hasNode(root.lchild, n2)) return findParent(root.lchild, n1, n2);
        if (hasNode(root.rchild, n1) && hasNode(root.rchild, n2)) return findParent(root.rchild, n1, n2);
        return null;
    }

    public Node findParent2(Node root, Node n1, Node n2) {
        if (root == null) return null;
        ArrayList<Node> res1 = new ArrayList<>();
        getRouteByNode(root, n1, res1);
        ArrayList<Node> res2 = new ArrayList<>();
        getRouteByNode(root, n2, res2);
        int i;
        for (i = 0; i < res1.size() && i < res2.size(); i++) {
            if (res1.get(i) != res2.get(i)) break;
        }
        if (i == 0) return null;
        return res1.get(i - 1);
    }

    public int MaxLen(Node root, int[] maxLen) {
        if (root == null) return 0;
        int lDep = MaxLen(root.lchild, maxLen);
        int rDep = MaxLen(root.rchild, maxLen);
        if (lDep + rDep - 1 > maxLen[0]) {
            maxLen[0] = lDep + rDep - 1;
        }
        return (lDep > rDep ? lDep : rDep) + 1;
    }

    public Node findLCA(Node root, Node n1, Node n2) {
        if (root == null) return null;
        if (root == n1 || root == n2) {
            return root;
        }
        Node l = findLCA(root.lchild, n1, n2);
        Node r = findLCA(root.rchild, n1, n2);
        if (l != null && r != null) {
            return root;
        }
        return l != null ? l : r;
    }

    public int FindLevel(Node root, Node target) {
        if (root == null) return -1;
        if (root == target) return 0;
        int l = FindLevel(root.lchild, target);
        if (l != -1) return l + 1;
        else {
            int r = FindLevel(root.rchild, target);
            if (r != -1) return r + 1;
        }
        return -1;
    }

    public int FindDistance(Node root, Node n1, Node n2) {
        Node cla = findLCA(root, n1, n2);
        int l1 = FindLevel(cla, n1);
        int l2 = FindLevel(cla, n2);
        return l1 + l2;
    }

    //前序遍历不用栈
    public void postOrderO1(Node root) {
        if (root == null) return;
        Node cur = root, pre;
        while (cur != null) {
            if (cur.lchild == null) {
                System.out.print(cur.data);
                cur = cur.rchild;
            } else {
                pre = cur.lchild;
                while (pre.rchild != null && pre.rchild != cur) {
                    pre = pre.rchild;
                }
                if (pre.rchild == null) {
                    pre.rchild = cur;
                    System.out.print(cur.data);
                    cur = cur.lchild;
                } else {
                    pre.rchild = null;
                    cur = cur.rchild;
                }
            }
        }
    }
    //todo 中序遍历不用栈
    //todo 后续遍历不用栈

    //判断是否为平衡二叉树
    public boolean isBalance(Node root) {
        if (root == null) return true;
        int l = getDepth(root.lchild);
        int r = getDepth(root.rchild);
        if (l - r < -1 || l - r > 1)
            return false;
        return isBalance(root.lchild) && isBalance(root.rchild);
    }

    //另一种判断是否平衡的方法，将计算树的高度结合起来
    public int getDep(Node root, boolean[] isBalance) {
        if (root == null) {
            return 0;
        }
        int l = getDep(root.lchild, isBalance);
        int r = getDep(root.rchild, isBalance);
        if (l == r || l == r + 1 || l + 1 == r) {

        } else {
            isBalance[0] = false;
        }
        return l > r ? l + 1 : r + 1;
    }

    //或者反过来(不方便，并且没有计算高度，多了两个变量）
    public boolean isBalance(Node root, int[] dep) {
        if (root == null) {
            dep[0] = 0;
            return true;
        }
        int[] l = {0};
        int[] r = {0};
        if (isBalance(root.lchild, l) && isBalance(root.rchild, r)) {
            if (Math.abs(l[0] - r[0]) <= 1) {
                dep[0] = l[0] > r[0] ? l[0] + 1 : r[0] + 1;
                return true;
            }
        }
        return false;
    }

    public boolean isPerfectTree(Node root) {
        if (root == null) return true;
        Node pre = root;
        LinkedList<Node> q = new LinkedList<>();
        q.add(pre);
        boolean flag = false;
        while (!q.isEmpty()) {
            Node first = q.removeFirst();
            if (first.lchild != null)
                q.add(first.lchild);
            else
                flag = true;
            if (first.rchild != null) {
                if (flag)
                    return false;
                else
                    q.add(first.rchild);
            } else
                flag = true;
        }
        return true;
    }

    public boolean isPerfectTree2(Node root) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(root);
        boolean flag = false;
        while (!q.isEmpty()) {
            Node first = q.removeFirst();
            if (first != null) {
                if (!flag) {
                    q.add(first.lchild);
                    q.add(first.rchild);
                } else {
                    return false;
                }
            } else {
                flag = true;
            }
        }
        return true;
    }

    public boolean isPerfectTree3(Node root) {
        LinkedList<Node> q = new LinkedList<>();
        Node pre = root;
        while (pre != null) {
            q.add(pre.lchild);
            q.add(pre.rchild);
            pre = q.removeFirst();
        }
        while (!q.isEmpty()) {
            pre = q.removeFirst();
            if (pre != null) return false;
        }
        return true;
    }

    //二叉树转双向链表（返回尾部节点）
    public void transToLink(Node root, Node[] last) {
        if (root == null) return;
        transToLink(root.lchild, last);
        last[0].rchild = root;
        root.lchild = last[0];
        last[0] = root;
        transToLink(root.rchild, last);
    }

    //对上面的进行改进
    public void transToLink2(Node root, Node[] first) {
        if (root == null) return;
        transToLink2(root.rchild, first);
        first[0].lchild = root;
        root.rchild = first[0];
        first[0] = root;
        transToLink2(root.lchild, first);
    }

    //不行
    public Node transToLink3(Node root) {
        if (root == null) return null;
        Node cur = root, pre = root;
        while (cur != null) {
            if (cur.lchild == null) {
                System.out.print(cur.data);
                cur = cur.rchild;
            } else {
                pre = cur.lchild;
                while (pre.rchild != null && pre.rchild != cur) {
                    pre = pre.rchild;
                }
                if (pre.rchild == null) {
                    pre.rchild = cur;
                    cur = cur.lchild;
                } else {
                    System.out.print(cur.data);
                    cur = cur.rchild;
                }
            }
        }
        cur = root;
        pre = root;
        Node k;
        while (cur != null) {
            if (cur.rchild == null) {
                System.out.print(cur.data);
                cur = cur.lchild;
            } else {
                pre = cur.rchild;
                while (pre.lchild != null && pre.lchild != cur) {
                    pre = pre.lchild;
                }
                if (pre.lchild == null) {
                    pre.lchild = cur;
                    //k = cur;
                    cur = cur.rchild;
                    //k.rchild = pre;
                } else {
                    System.out.print(cur.data);
                    cur = cur.lchild;
                }
            }
        }
        return pre;
    }

    //边构建边输出
    public Node PreAndInToPost(String[] pre, int p, String[] in, int i, int len) {
        if (len <= 0) return null;
        Node node = new Node();
        node.data = pre[p];
        int neatLen = 0;
        while (neatLen < len && !in[neatLen + i].equals(pre[p])) {
            neatLen++;
        }

        node.lchild = PreAndInToPost(pre, p + 1, in, i, neatLen);

        node.rchild = PreAndInToPost(pre, p + neatLen + 1, in, i + neatLen + 1, len - neatLen - 1);

        System.out.print(pre[p]);

        return node;
    }

    public Node PreAndInToTree(String[] pre, int[] s, String[] in, int left, int right) {
        if (s[0] >= pre.length || left >= right) return null;
        int i = left;
        Node node = new Node();
        node.data = pre[s[0]];
        while (i < right && !pre[s[0]].equals(in[i])) {
            i++;
        }
        s[0]++;
        if (i > left) {
            node.lchild = PreAndInToTree(pre, s, in, left, i);
        }
        if (i < right - 1) {
            node.rchild = PreAndInToTree(pre, s, in, i + 1, right);
        }
        return node;
    }

    //寻找中序遍历下的前节点
    public Node findPre(Node root, Node node) {
        if (root == null) return null;
        Stack<Node> s = new Stack<>();
        Node cur = root, pre = null;
        while (cur != null || !s.empty()) {
            if (cur != null) {
                s.push(cur);
                cur = cur.lchild;
            } else {
                cur = s.pop();
                if (cur == node) {
                    return pre;
                }
                pre = cur;
                System.out.print(cur.data);
                cur = cur.rchild;
            }
        }
        return null;
    }

    //寻找中序遍历下的后继节点
    public Node findNext(Node root, Node node) {
        if (root == null) return null;
        Stack<Node> s = new Stack<>();
        Node cur = root, pre = null;
        while (cur != null || !s.empty()) {
            if (cur != null) {
                s.push(cur);
                cur = cur.lchild;
            } else {
                cur = s.pop();
                if (pre == node) {
                    return cur;
                }
                pre = cur;
                System.out.print(cur.data);
                cur = cur.rchild;
            }
        }
        return null;
    }

    //寻找中序遍历下的前节点
    public void findNext2(Node root, Node node, Node pre, Node res) {
        if (root == null) return;
        if(root == node){
            res.data = pre.data;
        }
        findNext2(root.lchild,node,pre,res);
        pre.data = root.data;
        System.out.print(root.data);
        findNext2(root.rchild,node,pre,res);
    }

}