package aplication.model.viewmodel.productdetail;

import aplication.model.viewmodel.common.LayoutHeaderVM;
import aplication.model.viewmodel.common.ProductVM;

public class ProductDetailVM {
    private ProductVM productVM;
    private LayoutHeaderVM layoutHeaderVM;

    public ProductVM getProductVM() {
        return productVM;
    }

    public void setProductVM(ProductVM productVM) {
        this.productVM = productVM;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }
}
