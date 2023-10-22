package me.dio.domain.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@CrossOrigin
@RestController
@RequestMapping("/users")
@Tag(name = "Users Controller", description = "RESTful API for managing users.")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    @Operation(summary = "Create a new user.", description = "Create a new user and return the created user's data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided", content = @Content)
    })
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
    @Operation(summary = "Get all users", description = "Retrieve a list of all registered users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class)))
    })
    public ResponseEntity<List<UserDto>> findAll(){
        var userList = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userList.stream().map(UserDto::new).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", description = "Retrieve a specific user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<UserDto> findById(@PathVariable UUID id){
        var user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new UserDto(user));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a user", description = "Update the data of an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "422", description = "Invalid user data provided", content = @Content)
    })
    public ResponseEntity<UserDto> update(@PathVariable(value = "id") UUID id,
                                          @RequestBody @Validated(UserDto.View.UserPut.class)
                                          @JsonView(UserDto.View.UserPut.class) UserDto userToUpdate){
        var userUpdated = userService.updateUser(id, userToUpdate);
        var userResponseDto = new UserDto(userUpdated);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Delete an existing user based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted sucessfully!");
    }
}
