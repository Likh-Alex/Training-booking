package com.dev.spring.service.mapper;

import com.dev.spring.dto.response.OrderResponseDto;
import com.dev.spring.model.Order;
import com.dev.spring.model.Ticket;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderDate(order.getOrderDate().toString());
        dto.setTicketIds(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
