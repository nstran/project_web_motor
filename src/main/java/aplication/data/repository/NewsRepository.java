package aplication.data.repository;

import aplication.data.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Integer> {

    @Query("select count(n.id) from dbo_news n")
    long getTotalNewses();

    @Query("SELECT n FROM dbo_news n " +
            "WHERE(:newsName IS NULL OR UPPER(n.name) LIKE CONCAT('%',UPPER(:newsName),'%'))")
    Page<News> getListNewsByNewsNameContaining(Pageable pageable, @Param("newsName") String newsName);
}

