package com.demo;

public class User{
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.save(100, money -> System.out.println("weixin"+money));
    }
}
