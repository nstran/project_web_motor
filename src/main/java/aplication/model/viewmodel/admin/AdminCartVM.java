package aplication.model.viewmodel.admin;

import aplication.model.viewmodel.cart.CartProductVM;
import aplication.model.viewmodel.common.LayoutHeaderAdminVM;

import java.util.List;

public class AdminCartVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<CartProductVM> cartProductVMList;
    private String totalPrice;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public List<CartProductVM> getCartProductVMList() {
        return cartProductVMList;
    }

    public void setCartProductVMList(List<CartProductVM> cartProductVMList) {
        this.cartProductVMList = cartProductVMList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
