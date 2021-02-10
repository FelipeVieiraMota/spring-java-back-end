package com.springcourse.repository;

import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.vo.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {

    List<Request> findAllByOwnerId (Long id);

    Page<Request> findAllByOwnerId (Long id, Pageable pageable);

    @Transactional(readOnly = false)
    @Modifying
    @Query("update request set state = ?2 where id = ?1")
    int updateStatus(Long id, RequestState state);
}
