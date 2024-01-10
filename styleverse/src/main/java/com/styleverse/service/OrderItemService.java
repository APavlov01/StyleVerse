package com.styleverse.service;

import com.styleverse.dto.OrderItemDTO;
import com.styleverse.entity.OrderItem;
import com.styleverse.mapper.OrderItemMapper;
import com.styleverse.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItemDTO> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream()
                .map(OrderItemMapper.INSTANCE::orderItemToOrderItemDTO)
                .collect(Collectors.toList());
    }

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = OrderItemMapper.INSTANCE.orderItemDTOToOrderItem(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return OrderItemMapper.INSTANCE.orderItemToOrderItemDTO(savedOrderItem);
    }

    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        if (orderItemRepository.existsById(id)) {
            OrderItem orderItem = OrderItemMapper.INSTANCE.orderItemDTOToOrderItem(orderItemDTO);
            orderItem.setId(id);
            OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
            return OrderItemMapper.INSTANCE.orderItemToOrderItemDTO(updatedOrderItem);
        } else {
            return null; //  id does not exist
        }
    }

    public boolean deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        } else {
            return false; // id does not exist
        }
    }
}
