package aplication.model.viewmodel.common;

import java.util.Date;

public class ProductImageVM {
    private int id;
    private String linkDesign;
    private String productInfor;
    private String linkLibrary;
    private String linkProductOption;
    private String contentDesign;
    private String colorName;
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
