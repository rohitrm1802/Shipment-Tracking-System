package com.project.shipment_tracking_system.ServiceImpl;

import com.project.shipment_tracking_system.Entity.User;
import com.project.shipment_tracking_system.Repository.UserRepository;
import com.project.shipment_tracking_system.Security.JwtUtil;
import com.project.shipment_tracking_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtil jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User RegisterUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String Login(User user)
    {
        Authentication authentication = auth.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );

        if(!authentication.isAuthenticated())
        {
            throw new RuntimeException("Invalid Username and Password");
        }

        return jwtService.generateToken(user.getUsername(),user.getRole());
    }

    @Override
    public User getUser(Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    @Override
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(Long id)
    {
        userRepository.deleteById(id);
        return "User Successfully Deleted";
    }

    @Override
    public User updateUser(Long id, User user)
    {
        User user1 = userRepository
                .findById(id).orElseThrow(()-> new RuntimeException("User Not Found"));

        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());

        return userRepository.save(user1);
    }
}
