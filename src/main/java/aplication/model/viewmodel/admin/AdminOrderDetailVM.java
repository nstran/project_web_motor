package aplication.model.viewmodel.admin;

import aplication.model.viewmodel.common.CategoryVM;
import aplication.model.viewmodel.common.LayoutHeaderAdminVM;
import aplication.model.viewmodel.order.OrderProductVM;

import java.util.List;

public class AdminOrderDetailVM {

    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<CategoryVM> categoryVMList;
    private List<OrderProductVM> orderProductVMList;
    private String totalPrice;
    private int totalProduct;
    private InfoCustomerVM infoCustomerVM;

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }

    public List<OrderProductVM> getOrderProductVMList() {
        return orderProductVMList;
    }

    public void setOrderProductVMList(List<OrderProductVM> orderProductVMList) {
        this.orderProductVMList = orderProductVMList;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public InfoCustomerVM getInfoCustomerVM() {
        return infoCustomerVM;
    }

    public void setInfoCustomerVM(InfoCustomerVM infoCustomerVM) {
        this.infoCustomerVM = infoCustomerVM;
    }
}
