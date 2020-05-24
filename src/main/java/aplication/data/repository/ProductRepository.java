package aplication.data.repository;

import aplication.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select count(p.id) from dbo_product p")
    long getTotalProducts();

    @Query("SELECT p FROM dbo_product p " +
            "WHERE (:categoryId IS NULL OR (p.categoryId = :categoryId))" +
            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")

    Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable, @Param("categoryId") Integer categoryId, @Param("productName") String productName);

    @Query(value = "SELECT p FROM dbo_product p " +
            "WHERE (:categoryId IS NULL OR (p.categoryId = :categoryId))" +
            "AND (:productId IS NULL OR UPPER(p.id) LIKE CONCAT('%',UPPER(:productId),'%'))")
    Product findFirstProductByCategoryIdAndProductId(@Param("categoryId") int categoryId, @Param("productId") int productId);

}


