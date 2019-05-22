package com.deltapunkt.start;

import java.util.ArrayList;
import java.util.List;

public class LiveOrderBoard {
    private final List<Order> orders;

    public LiveOrderBoard() {
        orders = new ArrayList<>();
    }

    public List<Order> registerOrder(Order order) {
        orders.add(order);
        return orders;
    }

    public enum OrderType {
        BUY,
        SELL
    }
}
