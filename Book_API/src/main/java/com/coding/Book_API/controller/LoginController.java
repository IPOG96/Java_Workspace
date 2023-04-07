package com.coding.Book_API.controller;


import com.coding.Book_API.Service.LoginService;
import com.coding.Book_API.common.APIResponse;
import com.coding.Book_API.dto.LoginRequestDTO;
import com.coding.Book_API.dto.SignUpRequestDTO;
import com.coding.Book_API.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;


    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {

        //  APIResponse apiResponse = new APIResponse();

        APIResponse apiResponse = loginService.signUp(signUpRequestDTO);


        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }


    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        //  APIResponse apiResponse = new APIResponse();

        APIResponse apiResponse = loginService.login(loginRequestDTO);


        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

    }

    @GetMapping("/privateApi")
    public ResponseEntity<APIResponse> privateApi(@RequestHeader(value = "authorization") String auth) throws Exception {
        APIResponse apiResponse = new APIResponse();
       /* String authorization = "";
        jwtUtils.verify(authorization);*/
        jwtUtils.verify(auth);

        apiResponse.setData("This is private API");

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }


}
