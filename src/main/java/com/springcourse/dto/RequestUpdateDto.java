package com.springcourse.dto;

import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.RequestStage;
import com.springcourse.domain.vo.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestUpdateDto {
    @NotBlank(message = "Subject required")
    private String subject;
    private String description;
    @NotNull
    private RequestState state;
    @NotNull(message = "Owner required")
    private User owner;
    private List<RequestStage> stage = new ArrayList<>();

    public Request transformToRequest(){
        Request request = new Request(null, this.subject, this.description, null, this.state, this.owner, this.stage, null);
        return request;
    }
}