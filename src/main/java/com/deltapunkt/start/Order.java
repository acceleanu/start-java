package com.deltapunkt.start;

import com.deltapunkt.start.LiveOrderBoard.OrderType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final String userId;
    private final BigDecimal quantity;
    private final long price;
    private final OrderType orderType;

    public static Order createOrder(String userId, String quantity, long price, OrderType orderType) {
        UUID id = UUID.randomUUID();
        return new Order(id, userId, new BigDecimal(quantity), price, orderType);
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    private Order(UUID id, String userId, BigDecimal quantity, long price, OrderType orderType) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", quantity=" + quantity +
            ", price=" + price +
            ", orderType=" + orderType +
            '}';
    }
}
