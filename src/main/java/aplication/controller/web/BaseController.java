package aplication.controller.web;

import aplication.constant.CompanyConstant;
import aplication.data.model.Cart;
import aplication.data.model.Category;
import aplication.data.model.User;
import aplication.data.service.CartService;
import aplication.data.service.CategoryService;
import aplication.data.service.UserService;
import aplication.model.viewmodel.common.CategoryVM;
import aplication.model.viewmodel.common.HeaderMenuVM;
import aplication.model.viewmodel.common.LayoutHeaderAdminVM;
import aplication.model.viewmodel.common.LayoutHeaderVM;
import aplication.model.viewmodel.home.HomeLandingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    public LayoutHeaderVM getLayoutHeaderVM() {

        LayoutHeaderVM vm = new LayoutHeaderVM();
        ArrayList<HeaderMenuVM> headerMenuVMArrayList = new ArrayList<>();

        headerMenuVMArrayList.add(new HeaderMenuVM("Trang chủ", "/"));
        headerMenuVMArrayList.add(new HeaderMenuVM("Giới thiệu", "/"));
        headerMenuVMArrayList.add(new HeaderMenuVM("Liên hệ", "/"));
        headerMenuVMArrayList.add(new HeaderMenuVM("Hỗ trợ", "/"));
        headerMenuVMArrayList.add(new HeaderMenuVM("Tin tức", "/news"));

        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            category.setCreatedDate(category.getCreatedDate());
            categoryVMList.add(categoryVM);
        }


        vm.setCategoryVMList(categoryVMList);
        vm.setHeaderMenuVMArrayList(headerMenuVMArrayList);
        vm.setCompanyName(CompanyConstant.NAME);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userService.findUserByUsername(username);

        if (userEntity != null) {
            vm.setUserName(username);
            if (userEntity.getAvatar() != null) {
                vm.setAvatar(userEntity.getAvatar());
            } else
                vm.setAvatar("https://aets.org.es/wp-content/uploads/2014/12/omita-el-icono-del-perfil-avatar-placeholder-gris-de-la-foto-99724602.jpg");
        }

        return vm;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {

        LayoutHeaderAdminVM vm = new LayoutHeaderAdminVM();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userService.findUserByUsername(username);

        if (userEntity != null) {
            vm.setUserName(username);
            if (userEntity.getAvatar() != null) {
                vm.setAvatar(userEntity.getAvatar());
            } else
                vm.setAvatar("https://aets.org.es/wp-content/uploads/2014/12/omita-el-icono-del-perfil-avatar-placeholder-gris-de-la-foto-99724602.jpg");
        }

        return vm;
    }

    public void checkCookies(HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal) {

        Cookie cookie[] = request.getCookies();

        if(principal != null ) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Cart cart = cartService.findByUserName(username);
            if(cart != null ) {
                Cookie cookie1 = new Cookie("guid", cart.getGuid());
                cookie1.setPath("/");
                response.addCookie(cookie1);
            }else {
                UUID uuid = UUID.randomUUID();
                String guid = uuid.toString();
                Cart cart1 = new Cart();
                cart1.setGuid(guid);
                cart1.setUsername(username);
                cartService.addNewCart(cart1);

                Cookie cookie2 = new Cookie("guid", guid);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
        }else {
            boolean flag = true;

            String guid = null;
            if(cookie != null ){
                for(Cookie c : cookie) {
                    if (c.getName().equals("guid")) {
                        flag = false;
                        guid = c.getValue();
                    }
                }
            }

            if(flag == true) {
                UUID uuid = UUID.randomUUID();
                String guid2 = uuid.toString();
                Cart cart2 = new Cart();
                cart2.setGuid(guid2);
                cartService.addNewCart(cart2);

                Cookie cookie3 = new Cookie("guid",guid2);
                cookie3.setPath("/");
                cookie3.setMaxAge(60*60*24);
                response.addCookie(cookie3);

            } else {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if(cartEntity == null) {
                    Cart cart3 = new Cart();
                    cart3.setGuid(guid);
                    cartService.addNewCart(cart3);
                }
            }
        }
    }
}
