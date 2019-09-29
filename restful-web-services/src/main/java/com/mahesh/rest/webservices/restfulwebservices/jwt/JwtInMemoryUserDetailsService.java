package com.mahesh.rest.webservices.restfulwebservices.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "in28minutes",
        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2")); //dummy
//    inMemoryUserList.add(new JwtUserDetails(2L, "mahesh",
//            "$2a$10$aFxDl0iwBQYNq9BTP6Gqteuv/XqscQ9t9/u87j3KHNgqQfxaJREXO", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "mahesh",
            "$2a$10$qAU7pihzr9DQkpl4XaTv5eUxjlCK3rPSDh0r6/ZltCxLyH7ylz7w.", "ROLE_USER_2")); //password@123@#!
  }

//  {
//    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU2NDQ1NDI0MywiaWF0IjoxNTYzODQ5NDQzfQ.k8PrZ-4y2sabNgsMm_1N1qVDJev-zdGxltvu0Rl_8g_CZLlmbZbHNoxXOn61fuUBK8DDbOw9pb2nmBpwsu5C1A"
//  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJpbjI4bWludXRlcyIsImV4cCI6MTU2NzEzMjc5NiwiaWF0IjoxNTY0ODg2Mzk2fQ.aEW44Maw-VzNMpacYffEluS7mCaCPqZdRqMo4fCBaD7UBuQLcX9LW5xPRIxNsVPecppZUMyjpZ66ZlsC-wRi-g"
//  }
  // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWhlc2giLCJleHAiOjE2NDA5OTUyMDAsImlhdCI6MTU2Mzg0OTQ0M30.Ks2mWnBM6JXPCSz3hbIrUWfVv4OTWFNTcQJT2ZCtvTJMsPeO_0MwlMlIWGALmqUUnEV53ZK585R8boaumhTZIw

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}
