package com.springcourse.repository;

import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.RequestStage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Long> {

    List<RequestStage> findAllByRequestId(Long id);
    Page<RequestStage> findAllByRequestId(Long id, Pageable pageable);

    @Query("update request set state = ?2 where id = ?1")
    Request updateStatus(Long id, RequestState state);
}
