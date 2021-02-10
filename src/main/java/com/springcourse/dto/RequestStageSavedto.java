package com.springcourse.dto;

import com.springcourse.domain.enums.RequestState;
import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.RequestStage;
import com.springcourse.domain.vo.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class RequestStageSavedto {
    private String description;
    @NotNull(message = "State required")
    private RequestState state;
    @NotNull(message = "Request required")
    private Request request;
    @NotNull(message = "Owner required")
    private User owner;

    public RequestStage transformToRequestStage(){
        RequestStage stage = new RequestStage(null, this.description, null, this.state, this.request, this.owner);
        return stage;
    }
}
