package com.deltapunkt.start;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.deltapunkt.start.LiveOrderBoard.OrderType.SELL;
import static com.deltapunkt.start.Order.createOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class LiveOrderBoardTest {
    private LiveOrderBoard unit;

    @Before
    public void setUp() {
        // Assume that the values for quantity and price were already transformed in the given units
        // and the units are the same for the whole board
        unit = new LiveOrderBoard();
    }

    @Test
    public void registerOneOrder() {
        Order order = createOrder1();

        List<Order> orders = unit.registerOrder(order); // make this return existing orders

        assertThat(orders).containsExactly(order);
    }

    @Test
    public void cancelExistingOrder() {
        Order order = createOrder1();

        List<Order> orders1 = unit.registerOrder(order);
        assertThat(orders1).containsExactly(order);

        List<Order> orders2 = unit.cancelOrder(order.getId());
        assertThat(orders2).isEmpty();
    }

    @Test
    public void getSummaryForASellOrder() {
        Order order = createOrder1();
        List<Order> orders1 = unit.registerOrder(order);
        assertThat(orders1).containsExactly(order);

        List<String> summary = unit.getSummary(SELL);
        assertThat(summary).containsExactly("3.5 kg for £303.01");
    }

    private Order createOrder1() {
        return createOrder( // create order with unique id
            "userId",
            "3.5", // use BigDecimal internally to represent the quantity
            30301, // price modelled as cents in the given currency - so here we have £303
            SELL         // use an enum to model the order type
        );
    }
}
