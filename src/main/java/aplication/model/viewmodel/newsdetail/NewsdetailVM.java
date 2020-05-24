package aplication.model.viewmodel.newsdetail;

import aplication.model.viewmodel.common.LayoutHeaderVM;
import aplication.model.viewmodel.common.NewsVM;

public class NewsdetailVM {
    private LayoutHeaderVM layoutHeaderVM;
    private NewsVM newsVM;

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public NewsVM getNewsVM() {
        return newsVM;
    }

    public void setNewsVM(NewsVM newsVM) {
        this.newsVM = newsVM;
    }
}
