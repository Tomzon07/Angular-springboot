	
package com.innovaturelabs.training.examportal.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innovaturelabs.training.examportal.entity.User;
import com.innovaturelabs.training.examportal.form.EmailRecieveForm;
import com.innovaturelabs.training.examportal.form.ResetPasswordForm;
import com.innovaturelabs.training.examportal.form.UserForm;
import com.innovaturelabs.training.examportal.service.UserService;
import com.innovaturelabs.training.examportal.view.UserView;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;
    
    @PostMapping
    public UserView add(@Valid @RequestBody UserForm form) {
        return userService.add(form);
    }
    
    @GetMapping
    public Collection<User> list() {
        return userService.list();
    }
    @PutMapping
    public UserView update(
    		@Valid @RequestBody UserForm form
    		) {
    	 return userService.updateUserById(form);
    }
   
    @PutMapping ("/{userId}")
   public UserView update(
		    @PathVariable("userId") Integer userId,
    		@Valid @RequestBody UserForm form
    		) {
    	 return userService.updateUserById(userId,form);
      }
   
     @DeleteMapping("/{userId}")
     public void delete(
    		 @PathVariable("userId")Integer userId) {
    	 userService.delete(userId);
     }
    	

     @PostMapping("/generateToken")
     public void generateToken(@Valid @RequestBody EmailRecieveForm form ) {
         userService.generateToken(form.getEmail());
     }

     
     @PostMapping("/resetPassword/{token}")
     public UserView resetPassword(@PathVariable("token")String token,@RequestBody ResetPasswordForm form) {
         System.out.println("======"+token);		
         return userService.resetPassword(token,form.getPassword());
     }
   
}
