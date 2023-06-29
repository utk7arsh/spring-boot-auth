package com.example.demo.controller;

import com.example.demo.dto.ChangeDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.Response;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto login){
        Response response = new Response();
        try{
             response = demoService.login(login);
        }catch(Exception ex){
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody RegisterDto register) {
        Response response = new Response();

        try {
            response = demoService.register(register);
        } catch (Exception ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/changedpwd")
    public ResponseEntity<Response> changedpwd(@RequestBody ChangeDto change) {
        Response response = new Response();
        try {
            response = demoService.changedpwd(change);
        } catch (Exception ex) {
            response.setMessage(ex.getMessage());
            response.setSuccess(false);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
