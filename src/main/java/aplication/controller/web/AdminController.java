package aplication.controller.web;


import aplication.constant.FormatPrice;
import aplication.data.model.*;
import aplication.data.service.*;
import aplication.model.viewmodel.admin.*;
import aplication.model.viewmodel.cart.CartProductVM;
import aplication.model.viewmodel.common.*;
import aplication.model.viewmodel.order.OrderProductVM;
import aplication.model.viewmodel.order.OrderVM;
import aplication.model.viewmodel.order.StatusVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String admin(Model model) {

        HomeAdminVM vm = new HomeAdminVM();
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm", vm);
        return "/admin/home";
    }


    @GetMapping("/product")
    public String product(Model model,
                          @Valid @ModelAttribute("productname") ProductVM productName,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size
    ) {
        AdminProductVM vm = new AdminProductVM();


        /**
         * set list categoryVM
         */
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVMList.add(categoryVM);
        }


        Pageable pageable = new PageRequest(page, size);

        Page<Product> productPage = null;

        if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, productName.getName().trim());
            vm.setKeyWord("Find with key: " + productName.getName());
        } else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, null);
        }


        List<ProductVM> productVMList = new ArrayList<>();

        for (Product product : productPage.getContent()) {
            ProductVM productVM = new ProductVM();
            if (product.getCategory() == null) {
                productVM.setCategoryName("Unknown");
            } else {
                productVM.setCategoryName(product.getCategory().getName());
            }
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setBackgroundImage(product.getBackgroundImage());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setInfor(product.getInfor());
            productVM.setPrice(FormatPrice.formatPrice(product.getPrice()));
            productVM.setAmount(product.getAmount());
            productVM.setCreatedDate(product.getCreatedDate());
            productVMList.add(productVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        vm.setProductVMList(productVMList);
        if (productVMList.size() == 0) {
            vm.setKeyWord("Not found any product");
        }


        model.addAttribute("vm", vm);
        model.addAttribute("page", productPage);

        return "/admin/product";
    }

    @GetMapping("product-image/{productId}")
    public String productimage(@PathVariable int productId, Model model,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal
    ) {
        AdminProductVM vm = new AdminProductVM();
        List<ProductVM> productVMList = new ArrayList<>();
        List<Product> productList = productService.getListAllProducts();
        for (Product product : productList) {
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVMList.add(productVM);
        }

        List<ProductImageVM> productImageVMList = new ArrayList<>();

        Product product = productService.findOne(productId);
        for (ProductImage productImage : product.getProductImageList()) {
            ProductImageVM productImageVM = new ProductImageVM();
            productImageVM.setId(productImage.getId());
            productImageVM.setLinkDesign(productImage.getLinkDesign());
            productImageVM.setContentDesign(productImage.getContentDesign());
            productImageVM.setProductInfor(productImage.getProductInfor());
            productImageVM.setLinkLibrary(productImage.getLinkLibrary());
            productImageVM.setLinkProductOption(productImage.getLinkProductOption());
            productImageVM.setColorName(productImage.getColorName());
            productImageVM.setCreatedDate(productImage.getCreatedDate());

            productImageVMList.add(productImageVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setProductVMList(productVMList);
        vm.setProductImageVMList(productImageVMList);

        if (productImageVMList.size() == 0) {
            vm.setKeyWord("Không có ảnh nào");
        }

        model.addAttribute("productId", productId);
        model.addAttribute("vm", vm);

        return "/admin/product-image";
    }


    @GetMapping("category")
    public String category(Model model,
                           @Valid @ModelAttribute("categoryname") CategoryVM categoryName,
                           @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                           @RequestParam(name = "size", required = false, defaultValue = "5") Integer size) {

        AdminProductVM vm = new AdminProductVM();

        Pageable pageable = new PageRequest(page, size);
        Page<Category> categoryPage = null;

        if (categoryName.getName() != null && !categoryName.getName().isEmpty()) {
            categoryPage = categoryService.getListCategoryByCategoryNameContaining(pageable, categoryName.getName().trim());
            vm.setKeyWord("Kết Quả: " + categoryName.getName());
        } else {
            categoryPage = categoryService.getListCategoryByCategoryNameContaining(pageable, null);
        }

        List<CategoryVM> categoryVMList = new ArrayList<>();
        for (Category category : categoryPage.getContent()) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVM.setCreatedDate(category.getCreatedDate());

            categoryVMList.add(categoryVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);
        if (categoryVMList.size() == 0) {
            vm.setKeyWord("Không có danh mục nào !!!");
        }

        model.addAttribute("vm", vm);
        model.addAttribute("page", categoryPage);

        return "/admin/category";
    }


    @GetMapping("/news")
    public String news(Model model,
                       @Valid @ModelAttribute("newsname") NewsVM newsName,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "3") Integer size
    ) {
        AdminNewsVM vm = new AdminNewsVM();

        Pageable pageable = new PageRequest(page, size);

        Page<News> newsPage = null;

        if (newsName.getName() != null && !newsName.getName().isEmpty()) {
            newsPage = newsService.getListNewsByNewsNameContaining(pageable, newsName.getName().trim());
            vm.setKeyWord("Find with key: " + newsName.getName());
        } else {
            newsPage = newsService.getListNewsByNewsNameContaining(pageable, null);
        }

        List<NewsVM> newsVMList = new ArrayList<>();

        for (News news : newsPage.getContent()) {
            NewsVM newsVM = new NewsVM();
            newsVM.setId(news.getId());
            newsVM.setName(news.getName());
            newsVM.setMainImage(news.getMainImage());
            newsVM.setContent(news.getContent());
            newsVM.setContent(news.getContent());
            newsVM.setAuthor(news.getAuthor());
            newsVM.setCreatedDate(news.getCreatedDate());

            newsVMList.add(newsVM);
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setNewsVMList(newsVMList);

        model.addAttribute("vm", vm);
        model.addAttribute("page", newsPage);

        return "/admin/news";
    }

    @GetMapping("/order")
    public String order(Model model,
                        @Valid @ModelAttribute("cmnd") OrderVM cmnd,
                        @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                        @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
                        HttpServletResponse response,
                        HttpServletRequest request,
                        final Principal principal) {

        AdminOrderVM vm = new AdminOrderVM();

        Pageable pageable = new PageRequest(page, size);

        Page<Order> orderPage = null;

        if (cmnd.getCmnd() != null && !cmnd.getCmnd().isEmpty()) {
            orderPage = orderService.getListOrderByCmndContaining(pageable, cmnd.getCmnd().trim());
            vm.setKeyWord("Tìm CMND: " + cmnd.getCmnd());
        } else {
            orderPage = orderService.getListOrderByCmndContaining(pageable, null);
        }


        List<OrderVM> orderVMList = new ArrayList<>();

        for (Order order : orderPage.getContent()) {
            OrderVM orderVM = new OrderVM();
            orderVM.setId(order.getId());
            orderVM.setUserName(order.getUserName());
            orderVM.setCustomerName(order.getCustomerName());
            orderVM.setAddress(order.getAddress());
            orderVM.setCmnd(order.getCmnd());
            orderVM.setEmail(order.getEmail());
            orderVM.setPhoneNumber(order.getPhoneNumber());
            orderVM.setCreatedDate(order.getCreatedDate());
            orderVM.setPrice(FormatPrice.formatPrice(order.getPrice()));
            orderVM.setStatus(order.getStatus().getName());
            orderVMList.add(orderVM);
        }

        /**
         * set list StatusVM
         */

        List<StatusVM> statusVMList = new ArrayList<>();
        for (Status status : statusService.getListAllStatus()) {
            StatusVM statusVM = new StatusVM();

            statusVM.setId(status.getId());
            statusVM.setName(status.getName());

            statusVMList.add(statusVM);
        }

        vm.setStatusVMList(statusVMList);
        vm.setOrderVMList(orderVMList);
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm", vm);
        model.addAttribute("page", orderPage);

        return "/admin/order";
    }


    @GetMapping("product-detail/{productId}")
    public String detailProduct(@PathVariable Integer productId, Model model,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                final Principal principal) {
        this.checkCookies(response, request, principal);

        AdminProductDetailVM vm = new AdminProductDetailVM();
        Product product = productService.findOne(productId);
        ProductVM productVM = new ProductVM();

        if (product != null) {
            productVM.setId(product.getId());
            productVM.setCategoryName(product.getCategory().getName());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setPrice(FormatPrice.formatPrice(product.getPrice()));
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setProductVM(productVM);

        model.addAttribute("vm", vm);

        return "admin/product-detail";

    }

    @GetMapping("/cart")
    public String cart(Model model,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       final Principal principal) {

        this.checkCookies(response, request, principal);
        AdminCartVM vm = new AdminCartVM();
        double totalPrice = 0;
        Cookie cookie[] = request.getCookies();
        boolean flag = false;
        String guid = null;

        List<CartProductVM> cartProductVMList = new ArrayList<>();

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("guid")) {
                    flag = true;
                    guid = c.getValue();
                }
            }
        }
        if (flag == true) {
            Cart cart = cartService.findFirstCartByGuid(guid);
            if (cart != null) {
                for (CartProduct cartProduct : cart.getListCartProducts()) {
                    CartProductVM cartProductVM = new CartProductVM();
                    cartProductVM.setId(cartProduct.getId());
                    cartProductVM.setProductId(cartProduct.getProduct().getId());
                    cartProductVM.setProductName(cartProduct.getProduct().getName());
                    cartProductVM.setMainImage(cartProduct.getProduct().getMainImage());
                    cartProductVM.setAmount(cartProduct.getAmount());
                    double price = cartProduct.getAmount() * cartProduct.getProduct().getPrice();
                    cartProductVM.setPrice(FormatPrice.formatPrice(price));
                    totalPrice += price;

                    cartProductVMList.add(cartProductVM);
                }
            }
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCartProductVMList(cartProductVMList);
        vm.setTotalPrice(FormatPrice.formatPrice(totalPrice));

        model.addAttribute("vm", vm);

        return "admin/cart";

    }

    @GetMapping("/checkout")
    public String checkout(Model model, final Principal principal) {
        AdminCheckoutVM vm = new AdminCheckoutVM();
        OrderVM order = new OrderVM();
        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User userEntity = userService.findUserByUsername(username);
            if (userEntity != null) {
                order.setUserName(userEntity.getUserName());
                order.setAddress(userEntity.getAddress());
                order.setCustomerName(userEntity.getName());
                order.setPhoneNumber(userEntity.getPhoneNumber());
                order.setEmail(userEntity.getEmail());
                order.setCmnd(userEntity.getCmnd());
            }
        }

        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        model.addAttribute("order", order);
        model.addAttribute("vm", vm);
        return "admin/checkout";
    }

    @PostMapping("/admin-checkout")
    public String checkout(@Valid @ModelAttribute("order") OrderVM orderVM,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal) {

        Order order = new Order();
        boolean flag = false;

        Cookie cookie[] = request.getCookies();

        String guid = null;

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("guid")) {
                    flag = true;
                    guid = c.getValue();
                }
            }
        }

        if (flag == true) {
            double totalPrice = 0;

            if (principal != null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUserName(username);
            }
            order.setGuid(guid);
            order.setAddress(orderVM.getAddress());
            order.setCustomerName(orderVM.getCustomerName());
            order.setEmail(orderVM.getEmail());
            order.setStatus(statusService.findOne(1));
            order.setPhoneNumber(orderVM.getPhoneNumber());
            order.setCmnd(orderVM.getCmnd());
            order.setCreatedDate(new Date());

            Cart cart = cartService.findFirstCartByGuid(guid);
            if (cart != null) {
                List<OrderProduct> orderProductList = new ArrayList<>();
                for (CartProduct cartProduct : cart.getListCartProducts()) {

                    Product product = productService.findFirstProductByCategoryOrProductName(cartProduct.getAmount(), cartProduct.getProductId());
                    product.setAmount(product.getAmount() - cartProduct.getAmount());
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrder(order);
                    orderProduct.setProduct(cartProduct.getProduct());
                    orderProduct.setAmount(cartProduct.getAmount());

                    double price = cartProduct.getAmount() * cartProduct.getProduct().getPrice();
                    totalPrice += price;

                    orderProduct.setPrice(price);
                    orderProductList.add(orderProduct);
                }

                order.setListProductOrders(orderProductList);
                order.setPrice(totalPrice);

                orderService.addNewOrder(order);
                cartService.deleteCart(cart.getId());
            }
        }
        return "redirect:/admin/order";
    }

    @GetMapping("order/{orderId}")
    public String adminOrderDetail(Model model,
                                   @PathVariable("orderId") int orderId,
                                   HttpServletRequest request) {

        AdminOrderDetailVM vm = new AdminOrderDetailVM();

        double totalPrice = 0;

        List<OrderProductVM> orderProductVMList = new ArrayList<>();
        InfoCustomerVM infoCustomerVM = new InfoCustomerVM();

        Order order = orderService.findOne(orderId);
        infoCustomerVM.setId(order.getId());
        infoCustomerVM.setName(order.getCustomerName());
        infoCustomerVM.setAddress(order.getAddress());
        infoCustomerVM.setEmail(order.getEmail());
        infoCustomerVM.setPhoneNumber(order.getPhoneNumber());
        infoCustomerVM.setCmnd(order.getCmnd());

        if (order != null) {
            for (OrderProduct orderProduct : order.getListProductOrders()) {
                OrderProductVM orderProductVM = new OrderProductVM();
                orderProductVM.setProductId(orderProduct.getProduct().getId());
                orderProductVM.setAmount(orderProduct.getAmount());
                orderProductVM.setMainImage(orderProduct.getProduct().getMainImage());
                orderProductVM.setProductName(orderProduct.getProduct().getName());
                orderProductVM.setPrice(FormatPrice.formatPrice(orderProduct.getPrice()));

                totalPrice += orderProduct.getPrice();

                orderProductVMList.add(orderProductVM);
            }
        }


        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setOrderProductVMList(orderProductVMList);
        vm.setTotalPrice(FormatPrice.formatPrice(totalPrice));
        vm.setTotalProduct(orderProductVMList.size());
        vm.setInfoCustomerVM(infoCustomerVM);

        model.addAttribute("vm", vm);

        return "/admin/order-detail";
    }

    @GetMapping("/chart")
    public String chart(Model model) {

        ChartVM vm = new ChartVM();

        List<ChartDataVM> chartCategoryProduct = categoryService.getAllCategoryProduct();
        List<ChartDataVM> chartSumAmountCategoryProduct = categoryService.getSumAmountCategoryProduct();
        List<ChartDataVM1> chartSumPriceCategoryProduct = categoryService.getSumPriceCategoryProduct();
        List<ChartDataVM1> chartSumPriceInMonth = orderService.getTotalPriceInMonth();
        List<ChartDataVM1> chartSumPriceInMonth1 = orderService.getTotalPriceInMonth1();
        List<ChartDataVM1> chartSumPriceYear = orderService.getTotalPriceInYear();

        vm.setChartCategoryProductVMS(chartCategoryProduct);
        vm.setChartSumAmountPrductByCategory(chartSumAmountCategoryProduct);
        vm.setChartSumPricePrductByCategory(chartSumPriceCategoryProduct);
        vm.setChartSumPriceInMonth(chartSumPriceInMonth);
        vm.setChartSumPriceInMonth18(chartSumPriceInMonth1);
        vm.setChartSumPriceInYear(chartSumPriceYear);
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());

        model.addAttribute("vm", vm);

        return "/admin/chart";
    }
}
