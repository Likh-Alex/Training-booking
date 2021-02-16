package com.dev.spring.service.mapper;

import com.dev.spring.dto.response.ShoppingCartResponseDto;
import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.Ticket;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setId(shoppingCart.getId());
        dto.setTicketIds(shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
