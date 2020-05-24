package aplication.data.service;


import aplication.data.model.Order;
import aplication.data.model.Status;
import aplication.data.repository.StatusRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);


    @Autowired
    private StatusRepository statusRepository;

    public Status findOne(int statusId) {
        return statusRepository.findOne(statusId);
    }

    public Page<Order> getListOrderByStatusId(Pageable pageable, Integer statusId) {
        return statusRepository.getListOrderByStatusId(pageable, statusId);
    }

    public List<Status> getListAllStatus() {
        try {
            return statusRepository.findAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Status findStatusByStatusId(Integer statusId) {
        return statusRepository.findStatusByStatusId(statusId);
    }

}
