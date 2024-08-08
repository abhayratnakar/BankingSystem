package com.pay.abhay.service;

import com.pay.abhay.userpack.User;

public interface UserService {
    public void login();

    public void createAccount();

    public void Logout(User user);

    public void checkAccount(User fromuser, User toUser);

    public void FundTransfer(int amount, int pin, User fromUser, User toUser);

    public void withdraw(User user);

    public void ChangePin(User user);

    public void createLog(User user, String msg);
}
