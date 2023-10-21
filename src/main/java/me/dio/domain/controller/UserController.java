package me.dio.domain.controller;

import com.fasterxml.jackson.annotation.JsonView;
import me.dio.domain.dto.UserDto;
import me.dio.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody
                                          @Validated(UserDto.View.RegistrationPost.class)
                                          @JsonView(UserDto.View.RegistrationPost.class) UserDto userToCreate){
        var userModel = userService.create(userToCreate.toUserModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userModel.getId())
                .toUri();
        var userResponseDto = new UserDto(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(){
        var userList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userList.stream().map(UserDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable UUID id){
        var user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserDto(user));
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable(value = "id") UUID id,
                                          @RequestBody @Validated(UserDto.View.UserPut.class)
                                          @JsonView(UserDto.View.UserPut.class) UserDto userToUpdate){
        var userUpdated = userService.updateUser(id, userToUpdate);
        var userResponseDto = new UserDto(userUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted sucessfully!");
    }
}
