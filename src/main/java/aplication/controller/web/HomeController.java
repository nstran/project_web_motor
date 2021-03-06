package aplication.controller.web;

import aplication.constant.FormatPrice;
import aplication.data.model.Category;
import aplication.data.model.Product;
import aplication.data.service.CategoryService;
import aplication.data.service.ProductService;
import aplication.model.viewmodel.common.CategoryVM;
import aplication.model.viewmodel.common.ProductVM;
import aplication.model.viewmodel.home.BannerVM;
import aplication.model.viewmodel.home.HomeLandingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController extends BaseController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       @RequestParam(name = "categoryId", required = false) Integer categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "24") Integer size,
                       @RequestParam(name = "sortByPrice", required = false) String sort,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {


        HomeLandingVM vm = new HomeLandingVM();

        /**
         * set list bannerVM
         */
        ArrayList<BannerVM> listBanners = new ArrayList<>();
        listBanners.add(new BannerVM("", "https://d1hznszuapp9mk.cloudfront.net/motorbikes/November2019/Ortg0zpI5eUnDLFb5ES5.jpg"));
        listBanners.add(new BannerVM("", "https://d1hznszuapp9mk.cloudfront.net/motorbikes/November2019/8O4MPipndBN7tpYADJrj.jpg"));
        listBanners.add(new BannerVM("", "https://d1hznszuapp9mk.cloudfront.net/motorbikes/November2019/7AEidqxKGaGpuQZP2buG.jpg"));
        listBanners.add(new BannerVM("", "https://d1hznszuapp9mk.cloudfront.net/motorbikes/November2019/tKbK6Br6gm5Qt7LMGjb0.jpg"));
        listBanners.add(new BannerVM("", "https://d1hznszuapp9mk.cloudfront.net/motorbikes/November2019/1CH7oQscZl7UIl8saOWn.jpg"));
        listBanners.add(new BannerVM("", "https://d1hznszuapp9mk.cloudfront.net/motorbikes/November2019/peEKkzZWWo2PbF2lbIw4.jpg"));

        /**
         * set list categoryVM
         */
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

        /**
         * set list productVM
         */

        Sort sortable = new Sort(Sort.Direction.ASC, "id");
        if (sort != null) {
            if (sort.equals("ASC")) {
                sortable = new Sort(Sort.Direction.ASC, "price");
            } else {
                sortable = new Sort(Sort.Direction.DESC, "price");
            }
        }

        Pageable pageable = new PageRequest(page, size, sortable);

        Page<Product> productPage = null;

        if (categoryId != null) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, categoryId, null);
            Category category = categoryService.findOne(categoryId);
            vm.setKeyWord(category.getName());
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable, null, productName.getName().trim());
            vm.setKeyWord("Từ khóa: " + productName.getName());
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
            productVM.setPrice(FormatPrice.formatPrice(product.getPrice()));
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCreatedDate(product.getCreatedDate());

            productVMList.add(productVM);
        }

        vm.setListBanners(listBanners);
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());

        vm.setCategoryVMList(categoryVMList);
        vm.setProductVMList(productVMList);
        if (productVMList.size() == 0) {
            vm.setKeyWord("Không tìm thấy sản phẩm");
        }

        model.addAttribute("vm", vm);
        model.addAttribute("page", productPage);
        return "home";
    }


}
