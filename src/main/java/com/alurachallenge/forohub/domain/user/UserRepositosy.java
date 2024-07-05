package com.alurachallenge.forohub.domain.user;

import com.alurachallenge.forohub.domain.userProfile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepositosy extends JpaRepository<User,Long> {
    public UserDetails findByEmail(String username);
}
