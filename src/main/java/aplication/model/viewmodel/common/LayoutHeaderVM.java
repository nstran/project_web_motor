package aplication.model.viewmodel.common;


import java.util.ArrayList;
import java.util.List;

public class LayoutHeaderVM {
    private String companyName;
    private List<CategoryVM> categoryVMList;
    private ArrayList<HeaderMenuVM>  headerMenuVMArrayList;
    private String userName;
    private String avatar;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<CategoryVM> getCategoryVMList() {
        return categoryVMList;
    }

    public void setCategoryVMList(List<CategoryVM> categoryVMList) {
        this.categoryVMList = categoryVMList;
    }

    public ArrayList<HeaderMenuVM> getHeaderMenuVMArrayList() {
        return headerMenuVMArrayList;
    }

    public void setHeaderMenuVMArrayList(ArrayList<HeaderMenuVM> headerMenuVMArrayList) {
        this.headerMenuVMArrayList = headerMenuVMArrayList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
