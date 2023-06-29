package com.example.demo.service;

import com.example.demo.dto.ChangeDto;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.User;
import com.example.demo.repository.DemoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private DemoRepo repo;
    @Override
    public Response login(LoginDto login) {
        Optional<User> user = repo.getUserbyUserName(login.getUsername());
        Response response = new Response();
        if(user.isPresent()){
            if(user.get().getPassword().equals(login.getPassword())){
                response.setSuccess(true);
                response.setMessage("SUCCESS");
            }else{
                response.setMessage("Wrong password");
                response.setSuccess(false);
            }
        }else{
            response.setMessage("Not Found");
            response.setSuccess(false);
        }
        return response;
    }

    @Override
    public Response register(RegisterDto register) {
        Optional<User> user = repo.getUserbyUserName(register.getUsername());
        Optional<User> user2 = repo.getUserById(register.getId());
        Response response = new Response();
        if(user.isPresent() || user2.isPresent()){
            response.setMessage("User already exists. Try logging in");
            response.setSuccess(false);
        }
        else {
            User newUser = new User();
            newUser.setId(register.getId());
            newUser.setName(register.getName());
            newUser.setUsername(register.getUsername());
            newUser.setPassword(register.getPassword());
            try {
                repo.save(newUser);
                response.setMessage("Registration successful");
                response.setSuccess(true);
            } catch (Exception ex) {
                response.setMessage("Registration failed due to: "+ ex.getMessage());
                response.setSuccess(false);
            }
        }
        return response;
    }

    @Override
    public Response changedpwd(ChangeDto change) {
        Optional<User> userOpt = repo.getUserbyUserName(change.getUsername());
        Response response = new Response();

        if(userOpt.isPresent()){
            User user = userOpt.get();
            if(user.getPassword().equals(change.getPassword())){
                user.setPassword(change.getNewPassword());
                try {
                    repo.save(user);
                    response.setSuccess(true);
                    response.setMessage("Password changed successfully");
                } catch(Exception e){
                    response.setSuccess(false);
                    response.setMessage("Password change failed due to: " + e.getMessage());
                }
            } else {
                response.setMessage("Wrong password");
                response.setSuccess(false);
            }
        } else {
            response.setMessage("User not found");
            response.setSuccess(false);
        }

        return response;
    }
}
