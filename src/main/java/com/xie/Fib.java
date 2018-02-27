package com.xie;

import java.util.HashMap;

public class Fib {
    class Fib3 {
        int a;
    }
    static HashMap<Integer, Long> map = new HashMap<>();

    public static void main(String[] args) {
        int N = 40;
        long t1 = System.currentTimeMillis();
        long c = F(N);
        long t2 = System.currentTimeMillis();
        System.out.println("F running time is:" + (t2 - t1) + "ms.");
        System.out.println(c);
        t1 = System.currentTimeMillis();
        c = F1(N);
        t2 = System.currentTimeMillis();
        System.out.println("F1 running time is:" + (t2 - t1) + "ms.");
        System.out.println(c);
        t1 = System.currentTimeMillis();
        c = F2(N);
        t2 = System.currentTimeMillis();
        System.out.println("F2 running time is:" + (t2 - t1) + "ms.");
        System.out.println(c);
    }

    public static long F(int n) {
        // TODO Auto-generated method stub
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return F(n - 1) + F(n - 2);
    }

    public static long F1(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int temp;
        for (int i = 1; i < n; i++) {
            temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

    public static long F2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long res = F2(n - 1) + F2(n - 2);
        map.put(n, res);
        return res;
    }
}

class Fib2 {
    int a;
}