package aplication.controller.api;

import aplication.data.model.News;
import aplication.data.service.NewsService;
import aplication.model.api.BaseApiResult;
import aplication.model.api.DataApiResult;
import aplication.model.dto.NewsDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "/api/news")
public class NewsApiController {
    private static final Logger logger = LogManager.getLogger(NewsApiController.class);

    @Autowired
    private NewsService newsService;


    @PostMapping(value = "/create")
    public BaseApiResult createNews(@RequestBody NewsDTO dto) {
        DataApiResult result = new DataApiResult();

        try {
            News news = new News();
            news.setName(dto.getName());
            news.setContent(dto.getContent());
            news.setMainImage(dto.getMainImage());
            news.setAuthor(dto.getAuthor());
            news.setCreatedDate(new Date());
            newsService.addNewNews(news);
            result.setData(news.getId());
            result.setMessage("Save news successfully: " + news.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{newsId}")
    public BaseApiResult updateRoom(@PathVariable int newsId,
                                    @RequestBody NewsDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            News news = newsService.findOne(newsId);
            news.setName(dto.getName());
            news.setContent(dto.getContent());
            news.setMainImage(dto.getMainImage());
            news.setAuthor(dto.getAuthor());
            news.setCreatedDate(new Date());
            newsService.addNewNews(news);
            result.setSuccess(true);
            result.setMessage("Update news successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


    @GetMapping("/detail/{newsId}")
    public BaseApiResult detailMaterial(@PathVariable int newsId) {
        DataApiResult result = new DataApiResult();

        try {
            News newsEntity = newsService.findOne(newsId);
            if (newsEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this news");
            } else {
                NewsDTO dto = new NewsDTO();
                dto.setId(newsEntity.getId());
                dto.setMainImage(newsEntity.getMainImage());
                dto.setName(newsEntity.getName());
                dto.setContent(newsEntity.getContent());
                dto.setAuthor(newsEntity.getAuthor());
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
}
