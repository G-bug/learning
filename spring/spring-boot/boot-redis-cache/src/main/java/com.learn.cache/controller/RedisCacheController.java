/**
 * @author G-bug 2019/10/12
 */
package com.learn.cache.controller;

import com.learn.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.learn.cache.dao.entity.User;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @auth Administrator
 */

@RestController
@RequestMapping("users")
public class RedisCacheController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        // getAllUsersAndPage
        return new ResponseEntity<>(userService.getAllUsersAndPage(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity<Void> addUser(@RequestBody User user, UriComponentsBuilder builder) {
        long id = userService.createUser(user).getId();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(id).toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @DeleteMapping("del/{id}")
    public ResponseEntity<Void> delUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
