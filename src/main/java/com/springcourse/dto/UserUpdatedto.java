package com.springcourse.dto;

import com.springcourse.domain.enums.Role;
import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.RequestStage;
import com.springcourse.domain.vo.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class UserUpdatedto {
    @NotBlank (message = "Name required")
    private String name;

    @Email (message = "Invalid e-mail address")
    private String email;

    @Size(min=7, max=99, message = "Password must be between 7 and 99")
    private String password;

    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> stages = new ArrayList<>();

    public User transformToUser (){
        User user = new User(null, this.name, this.email, this.password, null,  this.requests, this.stages);
        return user;
    }
}
