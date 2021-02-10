package com.springcourse.resource;

import com.springcourse.domain.vo.Request;
import com.springcourse.domain.vo.User;
import com.springcourse.dto.*;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.JwtManager;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users")
public class UserResource {
    @Autowired private UserService userService;
    @Autowired private RequestService requestService;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtManager jwtManager;

    @Secured({"ROLE_ADMINSTRATOR"})
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSavedto userDto){
        User userToSave = userDto.transformToUser();
        User createdUser = userService.save(userToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("@accessManager.isOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(name = "id") Long id ,@RequestBody @Valid UserUpdatedto userUpadatedto){
        User userToUpdate = userUpadatedto.transformToUser();
        userToUpdate.setId(id);
        User updatedUser = userService.update(userToUpdate);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id){
        User user = userService.getById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> listAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel<User> pm = userService.listAllOnLazyMode(pr);
        return ResponseEntity.ok(pm);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponsedto> login(@RequestBody @Valid UserLogindto user){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication auth = authManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        String email = userSpring.getUsername();
        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(jwtManager.createToken(email, roles));
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> listAllRequestsById(
            @PathVariable(name="id")Long id,
            @RequestParam(value="size", defaultValue = "10")int size,
            @RequestParam(value="page", defaultValue = "0")int page){
        PageRequestModel pr = new PageRequestModel(page, size);
        PageModel <Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);
        return ResponseEntity.ok(pm);
    }

    @Secured({"ROLE_ADMINSTRATOR"})
    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(name="id")Long id, @RequestBody @Valid UserUpdateRoledto userDto){
        User user = new User();
        user.setId(id);
        user.setRole(userDto.getRole());
        userService.updateRole(user);
        return ResponseEntity.ok().build();
    }
}
