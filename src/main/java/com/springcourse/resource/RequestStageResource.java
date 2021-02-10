package com.springcourse.resource;

import com.springcourse.domain.vo.RequestStage;
import com.springcourse.domain.vo.User;
import com.springcourse.dto.RequestStageSavedto;
import com.springcourse.service.RequestStageService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="request-stages")
public class RequestStageResource {

    @Autowired private RequestStageService stageService;

    @PostMapping
    public ResponseEntity<RequestStage> save (@RequestBody @Valid RequestStageSavedto requestStagedto){
        RequestStage requestStage = requestStagedto.transformToRequestStage();
        RequestStage createdRequestStage = stageService.save(requestStage);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestStage> getById(@PathVariable(name="id") Long id){
        RequestStage stage = stageService.getById(id);
        return ResponseEntity.ok(stage);
    }
}
