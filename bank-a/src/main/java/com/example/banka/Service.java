package com.example.banka;


import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author shen
 * @Date 2019/2/26 17:52
 **/
@org.springframework.stereotype.Service
public class Service {

    @Autowired(required = false)
    AccountDao accountDao;

    @Autowired(required = false)
    BankBClient bankBClient;

    @LcnTransaction
    public String start(int money) {
        String user = "shen";
        String state = bankBClient.addMoney(money,user);
        int res = 0;
        if ("success".equals(state)){
            Account account =  new Account();
            account.setMoney(money);
            account.setUser(user);
            res = accountDao.update(account);
            //如果2个应用操作数据库完了之后, 报错了,2个库同时回滚
            //制造异常,让程序回滚DB操作
            //throw new RuntimeException("error runtime");
            //如果2个应用操作数据库完了之后, 报错了,2个库同时回滚

        }
        return  res>0?"success":"error";
    }
}
