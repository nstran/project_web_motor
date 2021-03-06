package aplication.data.repository;

import aplication.data.model.Category;
import aplication.model.viewmodel.admin.ChartDataVM;
import aplication.model.viewmodel.admin.ChartDataVM1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select count(c.id) from dbo_category c")
    long getTotalCategories();

    @Query("SELECT c FROM dbo_category c WHERE (:categoryName IS NULL OR UPPER(c.name) LIKE CONCAT('%', UPPER(:categoryName), '%'))")
    Page<Category> getListCategoryByCategoryNameContaining(Pageable pageable, @Param("categoryName") String categoryName);

    @Query("select new aplication.model.viewmodel.admin.ChartDataVM(c.name, count(p.id)) " +
            "FROM dbo_category c " +
            "INNER JOIN c.productList p " +
            "GROUP BY c.id")
    List<ChartDataVM> getAllCategoryProduct();

    @Query("select new aplication.model.viewmodel.admin.ChartDataVM(c.name, sum(op.amount)) " +
            "FROM dbo_category c " +
            "INNER JOIN c.productList p " +
            "INNER JOIN p.orderProductList op " +
            "GROUP BY c.id")
    List<ChartDataVM> getSumAmountCategoryProduct();

    @Query("select new aplication.model.viewmodel.admin.ChartDataVM1(c.name, sum(op.price)) " +
            "FROM dbo_category c " +
            "INNER JOIN c.productList p " +
            "INNER JOIN p.orderProductList op " +
            "GROUP BY c.id")
    List<ChartDataVM1> getSumPriceCategoryProduct();

}

