package aplication.model.viewmodel.admin;

import aplication.model.viewmodel.common.LayoutHeaderAdminVM;
import aplication.model.viewmodel.common.NewsVM;

import java.util.List;

public class AdminNewsVM {

    private List<NewsVM> newsVMList;
    private String keyWord;
    private LayoutHeaderAdminVM layoutHeaderAdminVM;

    public List<NewsVM> getNewsVMList() {
        return newsVMList;
    }

    public void setNewsVMList(List<NewsVM> newsVMList) {
        this.newsVMList = newsVMList;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }
}
