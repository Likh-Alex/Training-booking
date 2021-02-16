package com.dev.spring.service.impl;

import com.dev.spring.dao.ShoppingCartDao;
import com.dev.spring.dao.TicketDao;
import com.dev.spring.model.MovieSession;
import com.dev.spring.model.ShoppingCart;
import com.dev.spring.model.Ticket;
import com.dev.spring.model.User;
import com.dev.spring.service.ShoppingCartService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final TicketDao ticketDao;
    private final ShoppingCartDao shoppingCartDao;

    @Autowired
    public ShoppingCartServiceImpl(TicketDao ticketDao,
                                   ShoppingCartDao shoppingCartDao) {
        this.ticketDao = ticketDao;
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        ShoppingCart shoppingCart = getByUser(user);
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        Ticket sessionTicket = ticketDao.add(ticket);
        shoppingCart.getTickets().add(sessionTicket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setTickets(new ArrayList<>(){});
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
