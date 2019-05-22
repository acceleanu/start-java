package com.deltapunkt.start;

import java.util.*;

import static com.deltapunkt.start.LiveOrderBoard.OrderType.BUY;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class LiveOrderBoard {
    private final List<Order> orders;
    private String quantityUnit = "kg";
    private String priceCurrency = "£";

    public LiveOrderBoard() {
        orders = new ArrayList<>();
    }

    public List<Order> registerOrder(Order order) {
        orders.add(order);
        return orders;
    }

    public List<Order> cancelOrder(UUID id) {
        Optional<Order> orderMaybe = orders.stream().filter(o -> id.equals(o.getId())).findFirst();
        orderMaybe.ifPresent(orders::remove);
        return orders;
    }

    public List<String> getSummary(OrderType orderType) {
        Comparator<Order> orderComparator = Comparator.comparing(Order::getPrice);
        if(orderType == BUY) {
            orderComparator = orderComparator.reversed();
        }

        List<String> result = orders.stream()
            .filter(ot -> ot.getOrderType() == orderType)
            .sorted(orderComparator)
            .map(this::mapOrderToSummaryString)
            .collect(toList());
        return result;
    }

    private String mapOrderToSummaryString(Order o) {
        return new StringBuilder()
            .append(o.getQuantity())
            .append(" ")
            .append(quantityUnit)
            .append(" for ")
            .append(priceCurrency)
            .append(o.getPrice()/100)
            .append(
                o.getPrice() % 100 > 0 ?
                    format(".%02d", o.getPrice() % 100)
                    :
                    ""
            )
            .toString();
    }

    public enum OrderType {
        BUY,
        SELL
    }
}
