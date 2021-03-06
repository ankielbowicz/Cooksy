package com.cooksy.backdoor;

import com.cooksy.dto.UserDto;
import com.cooksy.service.UserService;
import com.cooksy.util.type.UserSortedType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserBackDoor {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(required = false) String sortBy) {
        return sortBy != null ? userService.getSortedUsers(UserSortedType.valueOf(sortBy)) :
                userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(Long.valueOf(id));
    }

    @GetMapping("/type/{typeId}")
    public List<UserDto> getUsersByType(@PathVariable String typeId) {
        return userService.getUsersByTypeId(Long.valueOf(typeId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        userService.updateUser(Long.valueOf(id), userDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.removeUser(Long.valueOf(id));
    }
}
