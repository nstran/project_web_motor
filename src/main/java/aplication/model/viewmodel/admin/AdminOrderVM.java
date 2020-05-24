package aplication.model.viewmodel.admin;

import aplication.model.viewmodel.common.LayoutHeaderAdminVM;
import aplication.model.viewmodel.order.OrderVM;
import aplication.model.viewmodel.order.StatusVM;

import java.util.List;

public class AdminOrderVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private String keyWord;
    private List<OrderVM> orderVMList;
    private List<StatusVM> statusVMList;


    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<OrderVM> getOrderVMList() {
        return orderVMList;
    }

    public void setOrderVMList(List<OrderVM> orderVMList) {
        this.orderVMList = orderVMList;
    }

    public List<StatusVM> getStatusVMList() {
        return statusVMList;
    }

    public void setStatusVMList(List<StatusVM> statusVMList) {
        this.statusVMList = statusVMList;
    }
}
