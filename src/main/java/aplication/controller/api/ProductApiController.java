package aplication.controller.api;

import aplication.data.model.Product;
import aplication.data.service.CategoryService;
import aplication.data.service.ProductService;
import aplication.model.api.BaseApiResult;
import aplication.model.api.DataApiResult;
import aplication.model.dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/product")
public class ProductApiController {

    private static final Logger logger = LogManager.getLogger(ProductApiController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public BaseApiResult createProduct(@RequestBody ProductDTO dto) {
        DataApiResult result = new DataApiResult();

        try {
            Product product = new Product();
            product.setName(dto.getName());
            product.setInfor(dto.getInfor());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            product.setBackgroundImage(dto.getBackgroundImage());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));
            product.setAmount(dto.getAmount());
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);
            result.setData(product.getId());
            result.setMessage("Save product successfully: " + product.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,
                                       @RequestBody ProductDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product = productService.findOne(productId);
            product.setName(dto.getName());
            product.setInfor(dto.getInfor());
            product.setPrice(dto.getPrice());
            product.setMainImage(dto.getMainImage());
            product.setBackgroundImage(dto.getBackgroundImage());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));
            product.setAmount(dto.getAmount());
            product.setCreatedDate(new Date());
            productService.addNewProduct(product);
            result.setSuccess(true);
            result.setMessage("Update product successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{productId}")
    public BaseApiResult detailMaterial(@PathVariable int productId) {
        DataApiResult result = new DataApiResult();

        try {
            Product productEntity = productService.findOne(productId);
            if (productEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                ProductDTO dto = new ProductDTO();
                dto.setId(productEntity.getId());
                if (productEntity.getCategory() != null) {
                    dto.setCategoryId(productEntity.getCategory().getId());
                }
                dto.setMainImage(productEntity.getMainImage());
                dto.setBackgroundImage(productEntity.getBackgroundImage());
                dto.setName(productEntity.getName());
                dto.setPrice(productEntity.getPrice());
                dto.setInfor(productEntity.getInfor());
                dto.setAmount(productEntity.getAmount());
                dto.setCreatedDate(new Date());
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
