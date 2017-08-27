package xyz.kots.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.kots.entity.User;
import xyz.kots.service.IUserService;

import java.util.List;

@CrossOrigin
@RestController
@Api(description="Operations pertaining to users")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "Add user")
    @PostMapping("registration")
    public ResponseEntity<Void> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "View a list of users")
    @GetMapping("registration")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
}
