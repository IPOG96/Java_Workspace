package com.coding.Book_API.Service;


import com.coding.Book_API.common.APIResponse;
import com.coding.Book_API.dto.LoginRequestDTO;
import com.coding.Book_API.dto.SignUpRequestDTO;
import com.coding.Book_API.entity.Users;
import com.coding.Book_API.repo.UserRepository;
import com.coding.Book_API.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    public APIResponse signUp(SignUpRequestDTO signUpRequestDTO) {

        APIResponse apiResponse = new APIResponse();

        //DTO to entity conversion

        Users userEntity = new Users();
        userEntity.setName(signUpRequestDTO.getName());
        userEntity.setEmailId(signUpRequestDTO.getEmailId());
        userEntity.setActive(Boolean.TRUE);
        userEntity.setGender(signUpRequestDTO.getGender());
        userEntity.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        userEntity.setPassword(signUpRequestDTO.getPassword());


        userEntity = userRepository.save(userEntity);
        String token = jwtUtils.generateJwt(userEntity);

        Map<String, Object> map = new HashMap<>();
        map.put("accessToken", token);
        apiResponse.setData(map);


        //apiResponse.setData(token);

        return apiResponse;


    }

    public APIResponse login(LoginRequestDTO loginRequestDTO) {

        APIResponse apiResponse = new APIResponse();

        Users users = userRepository.findByEmailIdAndPassword(loginRequestDTO.getEmailId(), loginRequestDTO.getPassword());

        System.out.println("id" + loginRequestDTO.getEmailId());
        System.out.println("id" + loginRequestDTO.getPassword());
        System.out.println("Users" + users);


        System.out.println("Users" + users);

        if (users == null) {
            apiResponse.setData("User login failed");
            return apiResponse;
        }

        //generate jwt

        String token = jwtUtils.generateJwt(users);

        Map<String, Object> map = new HashMap<>();
        map.put("accessToken", token);
        apiResponse.setData(map);


        //apiResponse.setData(token);

        return apiResponse;

    }
}
