package com.example.demo.service;

import com.example.demo.dto.ChangeDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.Response;

public interface DemoService {
    Response login(LoginDto login);
    Response register(RegisterDto register);
    Response changedpwd(ChangeDto change);
}
