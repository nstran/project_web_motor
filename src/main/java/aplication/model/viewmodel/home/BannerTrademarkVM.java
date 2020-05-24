package aplication.model.viewmodel.home;

public class BannerTrademarkVM {
    private String link;
    private String imageUrl;

    public BannerTrademarkVM(String link, String imageUrl) {
        this.link = link;
        this.imageUrl = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
