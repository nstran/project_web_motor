package aplication.model.viewmodel.admin;

import aplication.model.viewmodel.common.LayoutHeaderAdminVM;
import aplication.model.viewmodel.common.ProductVM;

public class AdminProductDetailVM {
    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private ProductVM productVM;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public ProductVM getProductVM() {
        return productVM;
    }

    public void setProductVM(ProductVM productVM) {
        this.productVM = productVM;
    }
}
