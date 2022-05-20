package com.example.springapp.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.springapp.entity.Role;
import com.example.springapp.entity.User;
import com.example.springapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController
{
  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers()
  {
    return ResponseEntity.ok().body(userService.getUsers());
  }

  @GetMapping("/user/get")
  public ResponseEntity<User> getUser(@RequestParam String username) throws Exception
  {
    return ResponseEntity.ok().body(userService.getUser(username));
  }

  @PostMapping("/user/save")
  public ResponseEntity<User> saveUser(@RequestBody User user)
  {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveUser(user));
  }

  @PostMapping("/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role)
  {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }

  @PostMapping("/role/addtouser")
  public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) throws Exception
  {
    userService.addRoleToUser(form.getUsername(), form.getRoleName());
    return ResponseEntity.ok().build();
  }

  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException
  {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer "))
    {
      try
      {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refreshToken);
        String username = decodedJWT.getSubject();
        User user = userService.getUser(username);

        String access_token = JWT.create()
          .withSubject(user.getUsername())
          .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
          .withIssuer(request.getRequestURL().toString())
          .withClaim("roles", user.getRoles().stream().map(Role::getName)
            .collect(Collectors.toList())).sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
      } catch (Exception exception)
      {
        log.error("Error logging in: {}", exception.getMessage());
        response.setHeader("error", exception.getMessage());
        response.setStatus(FORBIDDEN.value());
        Map<String, String> error = new HashMap<>();
        error.put("error_message", exception.getMessage());
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
      }
    } else
    {
      throw new RuntimeException("Refresh token is missing");
    }
  }
}

@Data
class RoleToUserForm
{
  private String username;
  private String roleName;
}