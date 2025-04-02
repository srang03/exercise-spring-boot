package org.example.exercisespringallabout.adapter.in.web;

import org.example.exercisespringallabout.dto.UserInfoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/{userName}")
    public UserInfoResponse getUserByUserName(@PathVariable String userName) {
        return new UserInfoResponse(userName, "010-1234-5678", "gildong@example.com");
    }

    @GetMapping()
    public List<UserInfoResponse> getUsers(){
        List<UserInfoResponse> userInfoResponses = new ArrayList<>();
        userInfoResponses.add(new UserInfoResponse("user1", "010-1234-5678", "user1@example.com"));
        userInfoResponses.add(new UserInfoResponse("user2", "010-5678-1234", "user2@example.com"));
        return userInfoResponses;
    }
}
