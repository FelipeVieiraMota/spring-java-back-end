package com.springcourse.service;

import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.RequestFile;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.RequestFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RequestFileService {

    @Autowired private RequestFileRepository requestFileRepository;

    public PageModel<RequestFile> listAllByRequestId(Long requestId, PageRequestModel prm){
        Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());
        Page<RequestFile> page = requestFileRepository.findAllByRequestId(requestId, pageable);
        PageModel<RequestFile> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
        return pm;
    }
}
