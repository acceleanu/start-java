package com.deltapunkt.start;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LiveOrderBoard {
    private final List<Order> orders;

    public LiveOrderBoard() {
        orders = new ArrayList<>();
    }

    public List<Order> registerOrder(Order order) {
        orders.add(order);
        return orders;
    }

    public List<Order> cancelOrder(UUID id) {
        Optional<Order> orderMaybe = orders.stream().filter(o -> id.equals(o.getId())).findFirst();
        orderMaybe.ifPresent((o) -> orders.remove(o));
        return orders;
    }

    public enum OrderType {
        BUY,
        SELL
    }
}
