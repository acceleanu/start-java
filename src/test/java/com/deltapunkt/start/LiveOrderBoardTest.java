package com.deltapunkt.start;

import org.junit.Test;

import java.util.List;

import static com.deltapunkt.start.LiveOrderBoard.OrderType.SELL;
import static com.deltapunkt.start.Order.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class LiveOrderBoardTest {
    @Test
    public void registerOneOrder() {
        // Also assume that the values for quantity and price were already transformed in the given units
        // and the units are the same for the whole board
        LiveOrderBoard unit = new LiveOrderBoard();
        Order order = createOrder( // create order with unique id
            "userId",
            "3.5", // use BigDecimal internally to represent the quantity
            30301, // price modelled as cents in the given currency - so here we have £303
            SELL         // use an enum to model the order type
        );

        List<Order> orders = unit.registerOrder(order); // make this return existing orders

        assertThat(orders).containsExactly(order);
    }

    @Test
    public void cancelExistingOrder() {
        LiveOrderBoard unit = new LiveOrderBoard();
        Order order = createOrder(
            "userId",
            "3.5",
            30301,
            SELL
        );

        List<Order> orders1 = unit.registerOrder(order);
        assertThat(orders1).containsExactly(order);

        List<Order> orders2 = unit.cancelOrder(order.getId());
        assertThat(orders2).isEmpty();
    }
}
