package aplication.controller.web;

import aplication.data.model.News;
import aplication.data.service.NewsService;
import aplication.model.viewmodel.common.NewsVM;
import aplication.model.viewmodel.common.ProductVM;
import aplication.model.viewmodel.newsdetail.NewsdetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(path = "/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    @GetMapping("")
    public String news(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {

        NewsVM vm = new NewsVM();

        List<News> newsList = newsService.getListAllNews();
        List<NewsVM> newsVMList = new ArrayList<>();
        for (News news : newsList) {
            NewsVM newsVM = new NewsVM();
            newsVM.setId(news.getId());
            newsVM.setName(news.getName());
            newsVM.setContent(news.getContent());
            newsVM.setAuthor(news.getAuthor());
            newsVM.setMainImage(news.getMainImage());
            newsVM.setCreatedDate(news.getCreatedDate());
            newsVM.setContent(news.getContent());
            newsVMList.add(newsVM);
        }

        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setNewsVMList(newsVMList);
        model.addAttribute("vm", vm);

        return "/news";
    }

    @GetMapping("/{newsId}")
    public String newsDetail(Model model, HttpServletRequest request,
                             @Valid @ModelAttribute("productname") ProductVM productName,
                             @PathVariable int newsId) {

        NewsdetailVM vm = new NewsdetailVM();

        News news = newsService.findOne(newsId);

        NewsVM newsVM = new NewsVM();
        newsVM.setId(news.getId());
        newsVM.setName(news.getName());
        newsVM.setContent(news.getContent());
        newsVM.setAuthor(news.getAuthor());
        newsVM.setMainImage(news.getMainImage());
        newsVM.setCreatedDate(news.getCreatedDate());

        vm.setNewsVM(newsVM);
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        model.addAttribute("vm", vm);

        return "/news-detail";
    }
}
