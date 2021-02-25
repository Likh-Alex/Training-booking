package com.dev.fitbooking.service.impl;

import com.dev.fitbooking.dao.ShoppingCartDao;
import com.dev.fitbooking.dao.TicketDao;
import com.dev.fitbooking.model.ShoppingCart;
import com.dev.fitbooking.model.Ticket;
import com.dev.fitbooking.model.User;
import com.dev.fitbooking.model.WorkoutSession;
import com.dev.fitbooking.service.ShoppingCartService;
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
    public void addSession(WorkoutSession workoutSession, User user) {
        ShoppingCart shoppingCart = getByUser(user);
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setWorkoutSession(workoutSession);
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
