package com.example.springapp.service;

import com.example.springapp.entity.Role;
import com.example.springapp.entity.User;
import com.example.springapp.exception.UserNotFoundException;
import com.example.springapp.repository.RoleRepository;
import com.example.springapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public User saveUser(User user) {
    log.info("Saving new user to the database");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public Role saveRole(Role role) {
    log.info("Saving new role to the database");
    return roleRepository.save(role);
  }

  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role to user");
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found!");
    }
    Role role = roleRepository.findByName(roleName);
    user.get().getRoles().add(role);
  }

  public User getUser(String username) {
    log.info("Fetching user");
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found!");
    }
    return user.get();
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
    Optional<User> user = userRepository.findUserByUsername(username);
    if (user.isEmpty()) {
      log.info("User not found!");
      throw new UserNotFoundException("User not found!");
    } else {
      log.info("User was loaded!");
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.get().getRoles().forEach(role ->
    {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
  }
}
