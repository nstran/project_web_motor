package aplication.controller.api;


import aplication.data.model.Order;
import aplication.data.model.Product;
import aplication.data.service.OrderService;
import aplication.data.service.StatusService;
import aplication.model.api.BaseApiResult;
import aplication.model.api.DataApiResult;
import aplication.model.dto.OrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/order")
public class OrderApiController {

    private static final Logger logger = LogManager.getLogger(CategoryApiController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatusService statusService;

    @PostMapping("update/{orderId}")
    public BaseApiResult update(@PathVariable int orderId, @RequestBody OrderDTO dto) {

        BaseApiResult result = new BaseApiResult();

        try {
            Order order = orderService.findOne(orderId);
            order.setCustomerName(dto.getCustomerName());
            order.setPhoneNumber(dto.getPhoneNumber());
            order.setAddress(dto.getAddress());
            order.setEmail(dto.getEmail());
            order.setCmnd(dto.getCmnd());
            order.setStatus(statusService.findOne(dto.getStatusId()));
            orderService.addNewOrder(order);
            result.setSuccess(true);
            result.setMessage("Cập nhật thành công");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{orderId}")
    public BaseApiResult detail(@PathVariable int orderId) {
        DataApiResult result = new DataApiResult();

        try {
            Order order = orderService.findOne(orderId);
            if (order == null) {
                result.setSuccess(false);
                result.setMessage("Không thể tìm thấy hóa đơn!");
            } else {
                OrderDTO dto = new OrderDTO();
                dto.setId(order.getId());
                dto.setCustomerName(order.getCustomerName());
                dto.setPhoneNumber(order.getPhoneNumber());
                dto.setAddress(order.getAddress());
                dto.setEmail(order.getEmail());
                dto.setCmnd(order.getCmnd());
                if (order.getStatus() != null) {
                    dto.setStatusId(order.getStatus().getId());
                }
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
}
