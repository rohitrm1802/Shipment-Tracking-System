package com.project.shipment_tracking_system.Service;

import com.project.shipment_tracking_system.Entity.User;

import java.util.List;

public interface UserService {

    public User RegisterUser(User user);

    public String Login(User user);

    public User getUser(Long id);

    public List<User> getAll();

    public String deleteUser(Long id);

    public User updateUser(Long id,User user);
}
