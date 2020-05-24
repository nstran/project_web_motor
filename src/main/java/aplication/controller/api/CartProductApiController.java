package aplication.controller.api;


import aplication.data.model.Cart;
import aplication.data.model.CartProduct;
import aplication.data.model.Product;
import aplication.data.service.CartProductService;
import aplication.data.service.CartService;
import aplication.data.service.ProductService;
import aplication.model.api.BaseApiResult;
import aplication.model.dto.CartProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cart-product")
public class CartProductApiController {

    private static final Logger logger = LogManager.getLogger(CategoryApiController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductService productService;


    @PostMapping("/add")
    public BaseApiResult addToCart(@RequestBody CartProductDTO dto) {
        BaseApiResult result = new BaseApiResult();
        for (Product product : productService.getListAllProducts())
            try {
                if (dto.getGuid() != null && dto.getAmount() > 0 && dto.getProductId() > 0 && product.getAmount() >= dto.getAmount()) {
                    Cart cartEntity = cartService.findFirstCartByGuid(dto.getGuid());
                    Product productEntity = productService.findOne(dto.getProductId());
                    if (cartEntity != null && productEntity != null) {
                        CartProduct cartProductEntity = cartProductService.findFirstCartProductByCartIdAndProductId(cartEntity.getId(), productEntity.getId());
                        if (cartProductEntity != null) {
                            cartProductEntity.setAmount(cartProductEntity.getAmount() + dto.getAmount());
                            cartProductService.updateCartProduct(cartProductEntity);
                        } else {
                            CartProduct cartProduct = new CartProduct();
                            cartProduct.setAmount(dto.getAmount());
                            cartProduct.setCart(cartEntity);
                            cartProduct.setProduct(productEntity);
                            cartProductService.addNewCartProduct(cartProduct);
                        }
                        result.setMessage("Thêm thành công!");
                        result.setSuccess(true);
                        return result;
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        result.setMessage("Không được nhập số lượng âm hoặc lớn hơn ");
        result.setSuccess(false);
        return result;
    }

    @PostMapping("/update")
    public BaseApiResult updateCartProduct(@RequestBody CartProductDTO dto) {
        BaseApiResult result = new BaseApiResult();
        Product product = productService.findFirstProductByCategoryOrProductName(dto.getId(), dto.getProductId());
        try {
            if (dto.getId() > 0 && dto.getAmount() > 0) {
                CartProduct cartProductEntity = cartProductService.findOne(dto.getId());

                if (cartProductEntity != null) {
                    cartProductEntity.setAmount(dto.getAmount());
                    cartProductService.updateCartProduct(cartProductEntity);
                    result.setMessage("Cập nhật giỏ hàng thành công !");
                    result.setSuccess(true);
                    return result;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;
    }

    @GetMapping("/delete/{cartProductId}")
    public BaseApiResult deleteCartProduct(@PathVariable int cartProductId) {
        BaseApiResult result = new BaseApiResult();

        try {
            if (cartProductService.deleteCartProduct(cartProductId) == true) {
                result.setMessage("Xóa thành công");
                result.setSuccess(true);
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setSuccess(false);
        result.setMessage("Fail!");
        return result;
    }
}
