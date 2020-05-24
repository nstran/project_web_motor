package aplication.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "dbo_product_image")
public class ProductImage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_image_id")
    @Id
    private int id;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;

    @Column(name = "link_design")
    private String linkDesign;

    @Column(name = "product_infor")
    private String productInfor;

    @Column(name = "link_library")
    private String linkLibrary;

    @Column(name = "link_product_option")
    private String linkProductOption;

    @Column(name = "content_design")
    private String contentDesign;

    @Column(name = "color_name")
    private String colorName;

    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getLinkDesign() {
        return linkDesign;
    }

    public void setLinkDesign(String linkDesign) {
        this.linkDesign = linkDesign;
    }

    public String getProductInfor() {
        return productInfor;
    }

    public void setProductInfor(String productInfor) {
        this.productInfor = productInfor;
    }

    public String getLinkLibrary() {
        return linkLibrary;
    }

    public void setLinkLibrary(String linkLibrary) {
        this.linkLibrary = linkLibrary;
    }

    public String getLinkProductOption() {
        return linkProductOption;
    }

    public void setLinkProductOption(String linkProductOption) {
        this.linkProductOption = linkProductOption;
    }

    public String getContentDesign() {
        return contentDesign;
    }

    public void setContentDesign(String contentDesign) {
        this.contentDesign = contentDesign;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
