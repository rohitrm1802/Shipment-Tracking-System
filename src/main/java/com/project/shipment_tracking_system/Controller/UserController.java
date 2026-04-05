package com.project.shipment_tracking_system.Controller;

import com.project.shipment_tracking_system.DTO.ApiResponse;
import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> RegisterUser(@RequestBody User user)
    {
        User user1 = userService.RegisterUser(user);

        return ResponseEntity.ok(
                new ApiResponse<>(true,"User Registered Successfully",user1)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> Login(@RequestBody User user)
    {
        String message = userService.Login(user);

        return ResponseEntity.ok(
                new ApiResponse<>(true,"User LoggedIn",message)
        );
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id)
    {
        User user = userService.getUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,"User Fetch Successfully",user)
        );
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<ApiResponse<List<User>>> getAll()
    {
        List<User> user = userService.getAll();

        return ResponseEntity.ok(
                new ApiResponse<>(true,"User Fetched Successfully",user)
        );
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id)
    {
        String message = userService.deleteUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,"User Successfully Deleted",message)
        );
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user)
    {
        User user1 = userService.updateUser(id,user);

        return ResponseEntity.ok(
                new ApiResponse<>(true,"User Updated Successfully",user1)
        );
    }
}
