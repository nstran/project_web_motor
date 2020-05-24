package aplication.data.service;

import aplication.data.model.News;
import aplication.data.repository.NewsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    private static final Logger logger = LogManager.getLogger(NewsService.class);

    @Autowired
    private NewsRepository newsRepository;

    public void addNewNews(News news) {
        newsRepository.save(news);
    }

    @Transactional
    public void addNewListNews(List<News> listNews) {
        newsRepository.save(listNews);
    }

    public News findOne(int newsId) {
        return newsRepository.findOne(newsId);
    }


    public boolean updateNews(News news) {
        try {
            newsRepository.save(news);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteNews(int newsId) {
        try {
            newsRepository.delete(newsId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }


    public List<News> getListAllNews() {
        try {
            return newsRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Page<News> getListNewsByNewsNameContaining(Pageable pageable, String newsName) {
        return newsRepository.getListNewsByNewsNameContaining(pageable, newsName);
    }

}
