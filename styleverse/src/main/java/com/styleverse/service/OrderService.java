package com.styleverse.service;

import com.styleverse.dto.OrderDTO;
import com.styleverse.entity.Order;
import com.styleverse.mapper.OrderMapper;
import com.styleverse.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderMapper.INSTANCE::orderToOrderDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper.INSTANCE::orderToOrderDTO)
                .orElse(null);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
    }

    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        if (orderRepository.existsById(id)) {
            Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);
            order.setId(id);
            Order updatedOrder = orderRepository.save(order);
            return OrderMapper.INSTANCE.orderToOrderDTO(updatedOrder);
        } else {
            return null; // order with the given id not exist
        }
    }

    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false; // order with the given id not exist
        }
    }
}
