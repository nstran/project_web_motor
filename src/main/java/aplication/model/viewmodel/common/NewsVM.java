package aplication.model.viewmodel.common;

import aplication.extension.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;
import java.util.List;

public class NewsVM {
    private int id;
    private String name;
    private String mainImage;
    private String content;
    private String author;
    private Date createdDate;
    private LayoutHeaderVM layoutHeaderVM;
    private List<NewsVM> newsVMList;

    @JsonSerialize(using = CustomDateSerializer.class)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }

    public List<NewsVM> getNewsVMList() {
        return newsVMList;
    }

    public void setNewsVMList(List<NewsVM> newsVMList) {
        this.newsVMList = newsVMList;
    }
}
