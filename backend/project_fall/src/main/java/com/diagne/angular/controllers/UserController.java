package com.diagne.angular.controllers;

import com.diagne.angular.entities.User;
import com.diagne.angular.repositories.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private IUser userRepository;

    @GetMapping("/")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity FindUserById(@PathVariable(name = "idUser") Long idUser) {
        if (idUser == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve user with null ID");
        }
        User user = userRepository.getOne(idUser);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user){
        if (user == null){
            return ResponseEntity.badRequest().body( "cannot create with empy fields");
        }
        User createdUser = userRepository.save(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam(name = "mail") String mail, @RequestParam(name="password") String password){
        if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)){
            return ResponseEntity.badRequest().body("cannot login with mail or password empty");
        }
        User authenticatedUser  = userRepository.findByMailAndPassword(mail, password);
        if (authenticatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authenticatedUser);
    }
}