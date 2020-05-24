package aplication.controller.web;

import aplication.constant.FormatPrice;
import aplication.data.model.Order;
import aplication.data.model.OrderProduct;
import aplication.data.service.CartProductService;
import aplication.data.service.CartService;
import aplication.data.service.OrderService;
import aplication.data.service.UserService;
import aplication.model.viewmodel.common.ProductVM;
import aplication.model.viewmodel.order.OrderDetailVM;
import aplication.model.viewmodel.order.OrderHistoryVM;
import aplication.model.viewmodel.order.OrderProductVM;
import aplication.model.viewmodel.order.OrderVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends BaseController {

    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/history")
    public String orderHistory(Model model,
                               @Valid @ModelAttribute("productname") ProductVM productName,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        OrderHistoryVM vm = new OrderHistoryVM();

        DecimalFormat df = new DecimalFormat("####0.00");


        List<OrderVM> orderVMS = new ArrayList<>();

        Cookie[] cookie = request.getCookies();

        String guid = null;
        boolean flag = false;

        List<Order> orderEntityList = null;

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUserName(null, username);
        } else {
            if (cookie != null) {
                for (Cookie c : cookie) {
                    if (c.getName().equals("guid")) {
                        flag = true;
                        guid = c.getValue();
                    }
                }
                if (flag == true) {
                    orderEntityList = orderService.findOrderByGuidOrUserName(guid, null);
                }
            }
        }

        if (orderEntityList != null) {
            for (Order order : orderEntityList) {
                OrderVM orderVM = new OrderVM();
                orderVM.setId(order.getId());
                orderVM.setCustomerName(order.getCustomerName());
                orderVM.setEmail(order.getEmail());
                orderVM.setAddress(order.getAddress());
                orderVM.setPhoneNumber(order.getPhoneNumber());
                orderVM.setCmnd(order.getCmnd());
                orderVM.setPrice(FormatPrice.formatPrice(order.getPrice()));
                orderVM.setCreatedDate(order.getCreatedDate());

                orderVMS.add(orderVM);
            }
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setOrderVMS(orderVMS);

        model.addAttribute("vm", vm);

        return "/order-history";
    }


    @GetMapping("/history/{orderId}")
    public String getOrderDetail(Model model,
                                 @Valid @ModelAttribute("productname") ProductVM productName,
                                 @PathVariable("orderId") int orderId) {

        OrderDetailVM vm = new OrderDetailVM();

        DecimalFormat df = new DecimalFormat("####0.00");

        double totalPrice = 0;

        List<OrderProductVM> orderProductVMS = new ArrayList<>();

        Order orderEntity = orderService.findOne(orderId);

        if (orderEntity != null) {
            for (OrderProduct orderProduct : orderEntity.getListProductOrders()) {
                OrderProductVM orderProductVM = new OrderProductVM();

                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setProductName(orderProduct.getProduct().getName());

                orderProductVM.setPrice(FormatPrice.formatPrice(orderProduct.getPrice()));

                totalPrice += orderProduct.getPrice();

                orderProductVMS.add(orderProductVM);
            }
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setOrderProductVMS(orderProductVMS);
        vm.setTotalPrice(FormatPrice.formatPrice(totalPrice));
        vm.setTotalProduct(orderProductVMS.size());

        model.addAttribute("vm", vm);

        return "/order-detail";
    }

}
