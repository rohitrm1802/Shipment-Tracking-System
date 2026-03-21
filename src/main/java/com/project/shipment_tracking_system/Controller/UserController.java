package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User RegisterUser(@RequestBody User user)
    {
        return userService.RegisterUser(user);
    }

    @PostMapping("/login")
    public String Login(@RequestBody User user)
    {
        return userService.Login(user);
    }

    @GetMapping("/getId/{id}")
    public User getUser(@PathVariable Long id)
    {
        return userService.getUser(id);
    }

    @GetMapping("/getAllUser")
    public List<User> getAll()
    {
        return userService.getAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        return userService.deleteUser(id);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user)
    {
        return userService.updateUser(id,user);
    }
}
