package org.example.exercisespringallabout.adapter.in.web;

import org.example.exercisespringallabout.dto.UserInfo;
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
    public UserInfo getUserByUserName(@PathVariable String userName) {
        return new UserInfo(userName, "010-1234-5678", "gildong@example.com");
    }

    @GetMapping()
    public List<UserInfo> getUsers(){
        List<UserInfo> userInfos = new ArrayList<>();
        userInfos.add(new UserInfo("user1", "010-1234-5678", "user1@example.com"));
        userInfos.add(new UserInfo("user2", "010-5678-1234", "user2@example.com"));
        return userInfos;
    }
}
