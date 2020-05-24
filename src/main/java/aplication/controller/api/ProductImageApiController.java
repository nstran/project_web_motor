package aplication.controller.api;

import aplication.data.model.ProductImage;
import aplication.data.service.ProductImageService;
import aplication.data.service.ProductService;
import aplication.model.api.BaseApiResult;
import aplication.model.api.DataApiResult;
import aplication.model.dto.ProductImageDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/product-image")
public class ProductImageApiController {

    private static final Logger logger = LogManager.getLogger(ProductImageApiController.class);


    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    @PostMapping("create")
    public BaseApiResult create(@RequestBody ProductImageDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            ProductImage productImage = new ProductImage();
            productImage.setLinkDesign(dto.getLinkDesign());
            productImage.setContentDesign(dto.getContentDesign());
            productImage.setLinkLibrary(dto.getLinkLibrary());
            productImage.setProductInfor(dto.getProductInfor());
            productImage.setColorName(dto.getColorName());
            productImage.setLinkProductOption(dto.getLinkProductOption());
            productImage.setProduct(productService.findOne(dto.getProductId()));
            productImage.setCreatedDate(new Date());
            productImageService.addNewProductImage(productImage);

            result.setMessage("Thêm thành công sản ảnh phẩm");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @PostMapping("/update/{productImageId}")
    public BaseApiResult updateImage(@PathVariable int productImageId, @RequestBody ProductImageDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            ProductImage productImage = productImageService.findOne(productImageId);
            productImage.setLinkDesign(dto.getLinkDesign());
            productImage.setContentDesign(dto.getContentDesign());
            productImage.setLinkLibrary(dto.getLinkLibrary());
            productImage.setColorName(dto.getColorName());
            productImage.setProductInfor(dto.getProductInfor());
            productImage.setLinkProductOption(dto.getLinkProductOption());
            productImage.setCreatedDate(new Date());
            productImageService.addNewProductImage(productImage);

            result.setSuccess(true);
            result.setMessage("Cập nhật ảnh" + productImage.getId() + " thành công:");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }

        return result;
    }

    @GetMapping("/detail/{productImageId}")
    public DataApiResult detail(@PathVariable int productImageId) {
        DataApiResult result = new DataApiResult();

        try {
            ProductImage productImage = productImageService.findOne(productImageId);
            if (productImage == null) {
                result.setSuccess(false);
                result.setMessage("Không tìm thấy ảnh!!!");
            } else {
                ProductImageDTO dto = new ProductImageDTO();
                dto.setId(productImage.getId());
                dto.setLinkDesign(productImage.getLinkDesign());
                dto.setContentDesign(productImage.getContentDesign());
                dto.setProductInfor(productImage.getProductInfor());
                dto.setLinkLibrary(productImage.getLinkLibrary());
                dto.setColorName(productImage.getColorName());
                dto.setLinkProductOption(productImage.getLinkProductOption());
                dto.setProductId(productImage.getProduct().getId());
                dto.setCreatedDate(productImage.getCreatedDate());

                result.setSuccess(true);
                result.setMessage("Lấy ảnh thành công!!");
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }

        return result;
    }


}

