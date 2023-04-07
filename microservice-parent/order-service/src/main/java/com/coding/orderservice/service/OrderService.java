package com.coding.orderservice.service;

import com.coding.orderservice.dto.OrderLineItemsDto;
import com.coding.orderservice.dto.OrderRequest;
import com.coding.orderservice.model.Order;
import com.coding.orderservice.model.OrderLineItems;
import com.coding.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest) {

        /*Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRequest.getOrderLineItemsDtoList().stream().map(orderLineItemsDto -> mapTODto(orderLineItemsDto));*/


        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = orderRequest
                .getOrderLineItemsDtoList()
                .stream()
                .map(this::mapTODto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);


    }

    private OrderLineItems mapTODto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setId(orderLineItemsDto.getId());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());


        return orderLineItems;

    }
}
