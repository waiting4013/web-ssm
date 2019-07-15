package stu.designpatterns.strategy.cash;

import stu.designpatterns.strategy.cash.CashSuper;

public class CashNormal extends CashSuper {

    @Override
    public double acceptCash(double money) {

        return money;
    }
}
