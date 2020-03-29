package com.temple.polymorphic.toolbox.security;

import com.temple.polymorphic.toolbox.dto.UserDto;
import com.temple.polymorphic.toolbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SecurityConfig implements AuthenticationProvider {

        private static List<User> users = new ArrayList();

        public SecurityConfig() {
            users.add(new User("admin", "admin", "ROLE_ADMIN"));
            users.add(new User("user", "user", "ROLE_USER"));
            //users = addUsers(us.getUsers());
        }

        /*private List<User>addUsers(List<UserDto> list){
            List<User> users = new ArrayList<>();

            for (UserDto userDto: list) {
                users.add(new User(userDto.getEmail(), userDto.getPassword(), getRole(userDto.getRole())));
            }

            return users;
        }
        private String getRole(String role){
            if(role.contains("User") || role.contains("user"))
                return "ROLE_USER";

            return "ROLE_ADMIN";
        }*/

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String name = authentication.getName();
            Object credentials = authentication.getCredentials();
            if (!(credentials instanceof String)) {
                return null;
            }
            String password = credentials.toString();

            Optional<User> userOptional = users.stream()
                    .filter(u -> u.match(name, password))
                    .findFirst();

            if (!userOptional.isPresent()) {
                throw new BadCredentialsException("Authentication failed for " + name);
            }

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(userOptional.get().role));
            Authentication auth = new
                    UsernamePasswordAuthenticationToken(name, password, grantedAuthorities);
            return auth;
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }

        private static class User {
            private String name;
            private String password;
            private String role;

            public User(String name, String password, String role) {
                this.name = name;
                this.password = password;
                this.role = role;
            }

            public boolean match(String name, String password) {
                return this.name.equals(name) && this.password.equals(password);
            }
        }
    }