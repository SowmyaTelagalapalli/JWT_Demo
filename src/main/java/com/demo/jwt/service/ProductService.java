package com.demo.jwt.service;

import com.demo.jwt.entity.UserInfo;
import com.demo.jwt.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class ProductService {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    public List<Integer> getAllProductsFromDB(){
       return IntStream.range(0,100).boxed().toList();
    }

    public String addUserDetails(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "User Added Successfully";
    }
}
