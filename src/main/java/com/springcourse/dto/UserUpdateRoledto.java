package com.springcourse.dto;

import com.springcourse.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class UserUpdateRoledto {

    @NotNull (message = "Role required")
    private Role role;
}
