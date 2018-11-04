package com.demo;

public class Bank {
    public void save(int money,NotifyHandler handler){
        System.out.println(money);
        handler.notifySender(money);

    }

}
