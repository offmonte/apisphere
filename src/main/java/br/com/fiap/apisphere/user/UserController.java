package br.com.fiap.apisphere.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user, UriComponentsBuilder uriBuilder){
        var uri =uriBuilder
            .path("/user/{id}")
            .buildAndExpand(user.getId())
            .toUri();

        service.create(user);
        return ResponseEntity
            .created(uri)
            .body(user);

    }
}
