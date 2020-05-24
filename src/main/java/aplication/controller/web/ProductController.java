package aplication.controller.web;

import aplication.constant.FormatPrice;
import aplication.data.model.Product;
import aplication.data.model.ProductImage;
import aplication.data.service.ProductService;
import aplication.model.viewmodel.common.ProductImageVM;
import aplication.model.viewmodel.common.ProductVM;
import aplication.model.viewmodel.productdetail.ProductDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public String productDetail(@PathVariable Integer productId, Model model,
                                @Valid @ModelAttribute("productname") ProductVM productName,
                                HttpServletResponse response,
                                HttpServletRequest request,
                                final Principal principal) {

        /**
         * check cookie to create cart guid
         */
        ProductDetailVM vm = new ProductDetailVM();

        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();

        if (productEntity != null) {
            productVM.setId(productEntity.getId());
            productVM.setName(productEntity.getName());
            productVM.setMainImage(productEntity.getMainImage());
            productVM.setBackgroundImage(productEntity.getBackgroundImage());
            productVM.setShortDesc(productEntity.getShortDesc());
            productVM.setPrice(FormatPrice.formatPrice(productEntity.getPrice()));
            productVM.setInfor(productEntity.getInfor());
            productVM.setCreatedDate(productEntity.getCreatedDate());
            /**
             * set list product image vm
             */
            List<ProductImageVM> productImageVMS = new ArrayList<>();
            for (ProductImage productImage : productEntity.getProductImageList()) {
                ProductImageVM productImageVM = new ProductImageVM();
                productImageVM.setLinkDesign(productImage.getLinkDesign());
                productImageVM.setProductInfor(productImage.getProductInfor());
                productImageVM.setLinkLibrary(productImage.getLinkLibrary());
                productImageVM.setLinkProductOption(productImage.getLinkProductOption());
                productImageVM.setContentDesign(productImage.getContentDesign());
                productImageVM.setColorName(productImage.getColorName());
                productImageVMS.add(productImageVM);
            }

            productVM.setProductImageVMS(productImageVMS);
        }


        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setProductVM(productVM);

        model.addAttribute("vm", vm);

        return "/product-detail";
    }

}
