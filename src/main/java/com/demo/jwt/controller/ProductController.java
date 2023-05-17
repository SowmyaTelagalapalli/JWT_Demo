package com.demo.jwt.controller;

import com.demo.jwt.DTO.AuthRequest;
import com.demo.jwt.entity.UserInfo;
import com.demo.jwt.service.JwtService;
import com.demo.jwt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to JWT Demo";
    }

    @GetMapping("/getAllProducts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Integer> getAllProducts(){
        return productService.getAllProductsFromDB();
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserInfo userInfo){
       return productService.addUserDetails(userInfo);
    }
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid User Name !");
        }
    }

}
