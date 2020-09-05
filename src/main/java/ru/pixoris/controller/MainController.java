package ru.pixoris.controller;
import ru.pixoris.view.UserResponse;
import ru.pixoris.model.UserRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap;


@RestController
@RequestMapping("/main")
public class MainController {
    @GetMapping
    public HashMap<String, Object> get() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "Available");
        map.put("code", 200);
        return map;
    }

    @GetMapping("/user")
    public UserResponse user(
            @RequestParam(value = "age") Integer age,
            @RequestParam(value = "name", required = false, defaultValue = "Sasha") String name
    ){
        return new UserResponse(name, age);
    }

    @PostMapping("{id}/user")
    public String user(@PathVariable("id") String id){
        return "User id is " + id;
    }

    @PostMapping("/user")
    public ResponseEntity pay(@RequestBody UserRequest request) {
        if (request.getLogin().length() >= 4 && request.getPass().length() >= 4) {
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }
}