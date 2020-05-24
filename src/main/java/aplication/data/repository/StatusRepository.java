package aplication.data.repository;

import aplication.data.model.Order;
import aplication.data.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusRepository extends JpaRepository<Status, Integer> {

    @Query("SELECT o FROM dbo_order o WHERE (:statusId IS NULL OR (o.statusId = :statusId))")
    Page<Order> getListOrderByStatusId(Pageable pageable, @Param("statusId") Integer statusId);

    @Query("SELECT s FROM dbo_status s WHERE (:statusId IS NULL OR (s.id = :statusId))")
    Status findStatusByStatusId(@Param("statusId") Integer statusId);
}
