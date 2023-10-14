package Jala.TodoList.controller;

import Jala.TodoList.model.User;
import Jala.TodoList.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        log.info("Criando um novo Usuário com as informações [{}]", user);
        return userService.createUser(user);
    }


    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> g (@PathVariable(value = "id") String id) {
        log.info("Buscando usuario pelo id [{}]", id);
        User user = userService.findUserbyId(id);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") String id, @RequestBody User user) {
        log.info("Atualizando o User [{}], as novas informações são: [{}]", id, user);
        User updatedUser = userService.updateUserbyId(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok().body(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") String id,
                                             @RequestParam(value = "email") String email,
                                             @RequestParam(value = "password") String senha) {
        log.info("Excluindo usuário com o nome [{}]", id);
        boolean deleted = userService.deleteUser(id, email, senha);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}