package aplication.data.service;

import aplication.data.model.Order;
import aplication.data.repository.OrderRepository;
import aplication.model.viewmodel.admin.ChartDataVM1;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;


    public boolean updateStatus(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public void addNewOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOne(int orderId) {
        return orderRepository.findOne(orderId);
    }


    public Page<Order> getListOrderByCmndContaining(Pageable pageable, String cmnd) {
        return orderRepository.getListOrderByCmndContaining(pageable, cmnd);
    }

    public List<Order> findOrderByGuidOrUserName(String guid, String userName) {
        return orderRepository.findOrderByGuidOrUserName(guid,userName);
    }

    public List<ChartDataVM1> getTotalPriceInMonth() {
        return orderRepository.getTotalPriceInMonth();
    }

    public List<ChartDataVM1> getTotalPriceInMonth1() {
        return orderRepository.getTotalPriceInMonth1();
    }

    public List<ChartDataVM1> getTotalPriceInYear() {
        return orderRepository.getTotalPriceInYear();
    }
}

