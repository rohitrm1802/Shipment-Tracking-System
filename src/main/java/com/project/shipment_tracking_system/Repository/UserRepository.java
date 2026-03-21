package com.project.shipment_tracking_system.Repository;

import com.project.shipment_tracking_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    Optional<User> findByUsername(String username);
    //User findByUserId(Long userId);
}
