package com.springcourse.repository;

import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.RequestStage;
import com.springcourse.domain.vo.User;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTests {
    @Autowired private RequestStageRepository requestStageRepository;

    @Test
    public void AsaveTest(){
        User owner = new User();
        owner.setId(1L);
        Request request = new Request();
        request.setId(1L);
        RequestStage stage = new RequestStage(null, "Foi compomprado um novo laptop da marca HP e com 16 GB de RAM", new Date(), RequestState.CLOSED, request, owner);
        RequestStage createdStage = requestStageRepository.save(stage);
        assertThat(createdStage.getId()).isEqualTo(4L);
    }

    @Test
    public void getByIdTest(){
        Optional<RequestStage> result = requestStageRepository.findById(4L);
        RequestStage stage = result.get();

        assertThat(stage.getDescription()).isEqualTo("Foi compomprado um novo laptop da marca HP e com 16 GB de RAM");
    }

    @Test
    public void listByRequestIdTest(){
        List<RequestStage> stages = requestStageRepository.findAllByRequestId(4L);
        assertThat(stages.size()).isEqualTo(1);
    }
}
