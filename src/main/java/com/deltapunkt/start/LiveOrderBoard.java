package com.deltapunkt.start;

import java.math.BigDecimal;
import java.util.*;

import static com.deltapunkt.start.LiveOrderBoard.OrderType.BUY;
import static java.lang.String.format;
import static java.util.stream.Collectors.*;

public class LiveOrderBoard {
    private final List<Order> orders;
    private final String quantityUnit;
    private final String priceCurrency;

    public LiveOrderBoard(String quantityUnit, String priceCurrency) {
        this.quantityUnit = quantityUnit;
        this.priceCurrency = priceCurrency;
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
        Comparator<Map.Entry<Long, BigDecimal>> summaryItemComparator = Comparator.comparing(Map.Entry::getKey);
        if (orderType == BUY) {
            summaryItemComparator = summaryItemComparator.reversed();
        }

        Set<Map.Entry<Long, BigDecimal>> summaryItems = orders.stream()
            .filter(ot -> ot.getOrderType() == orderType)
            .collect(groupingBy(Order::getPrice, reducing(BigDecimal.ZERO, Order::getQuantity, BigDecimal::add)))
            .entrySet();

        List<String> result = summaryItems
            .stream()
            .sorted(summaryItemComparator)
            .map(this::mapSummaryItemToString)
            .collect(toList());
        return result;
    }

    private String mapSummaryItemToString(Map.Entry<Long, BigDecimal> summaryItem) {
        return new StringBuilder()
            .append(summaryItem.getValue())
            .append(" ")
            .append(quantityUnit)
            .append(" for ")
            .append(priceCurrency)
            .append(summaryItem.getKey()/100)
            .append(
                summaryItem.getKey() % 100 > 0 ?
                    format(".%02d", summaryItem.getKey() % 100)
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
