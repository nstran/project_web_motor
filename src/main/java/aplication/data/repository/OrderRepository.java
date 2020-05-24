package aplication.data.repository;

import aplication.data.model.Order;
import aplication.model.viewmodel.admin.ChartDataVM1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM dbo_order o " +
            "WHERE (:guid IS NULL OR (o.guid = :guid))" +
            "AND (:userName IS NULL OR (o.userName = :userName))")
    List<Order> findOrderByGuidOrUserName(@Param("guid") String guid, @Param("userName") String userName);

    @Query("SELECT o FROM dbo_order o " +
            "WHERE(:cmnd IS NULL OR UPPER(o.cmnd) LIKE CONCAT('%',UPPER(:cmnd),'%'))")
    Page<Order> getListOrderByCmndContaining(Pageable pageable, @Param("cmnd") String cmnd);

    @Query("SELECT new aplication.model.viewmodel.admin.ChartDataVM1(DATE_FORMAT(o.createdDate, '%Y-%m'),sum(o.price)) " +
            "FROM dbo_order o " +
            "WHERE o.createdDate BETWEEN '2019-01-01 0:0:0' AND '2019-12-30 16:26:39' " +
            "GROUP BY DATE_FORMAT(o.createdDate, '%Y-%m')")
    List<ChartDataVM1> getTotalPriceInMonth();

    @Query("SELECT new aplication.model.viewmodel.admin.ChartDataVM1(DATE_FORMAT(o.createdDate, '%Y-%m'),sum(o.price)) " +
            "FROM dbo_order o " +
            "WHERE o.createdDate BETWEEN '2020-01-01 0:0:0' AND '2020-12-30 16:26:39' " +
            "GROUP BY DATE_FORMAT(o.createdDate, '%Y-%m')")
    List<ChartDataVM1> getTotalPriceInMonth1();

    @Query("SELECT new aplication.model.viewmodel.admin.ChartDataVM1(DATE_FORMAT(o.createdDate, '%Y'),sum(o.price)) " +
            "FROM dbo_order o " +
            "WHERE o.createdDate BETWEEN '2018' AND '2020' " +
            "GROUP BY DATE_FORMAT(o.createdDate, '%Y')")
    List<ChartDataVM1> getTotalPriceInYear();

}
