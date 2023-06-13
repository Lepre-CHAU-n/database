package com.assignmenttwo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assignmenttwo.models.User;

import jakarta.servlet.http.HttpServletResponse;
import com.assignmenttwo.models.UserRepository;


@Controller
public class UsersController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users/view")
    public String getAllUsers(Model model){
        System.out.println("getting users");
        List<User> users = userRepo.findAll();
        model.addAttribute("us", users);
        return "users/showAll";
    }

    @GetMapping("/users/view/{uid}")
    public String getUser(Model model, @PathVariable String uid){
        
        System.out.println("GET User " + uid);
        // call db
        int id = Integer.parseInt(uid);
        User u = userRepo.findById(id).get();
        userRepo.delete(u);
        model.addAttribute("us");
        return "showUser";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
        System.out.println("ADD user");
        String newName = newuser.get("name");
        String newPwd = newuser.get("password");
        int newSize = Integer.parseInt(newuser.get("size"));
        int newHeight = Integer.parseInt(newuser.get("height"));
        String newHairColor = newuser.get("hairColor");
        double newGpa = Double.parseDouble(newuser.get("gpa"));
        
        userRepo.save(new User(newName, newPwd, newSize, newHeight, newHairColor, newGpa));
        response.setStatus(201);
        return "users/addedUser";
    }


    @DeleteMapping("/users/delete/{uid}")
    public String deleteUser(Model model, @PathVariable String uid) {
        System.out.println("Delete user");
        int id = Integer.parseInt(uid);
        userRepo.deleteById(id);
        return "users/showAll"; // return to show all page, unique user should be removed
    }





    @GetMapping("users/edit/{uid}")
    public String showEditForm(Model model, @PathVariable String uid) {
        System.out.println("EDIT User " + uid);
        // Retrieve the user from the database based on the given uid
        int id = Integer.parseInt(uid);
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            // Handle the case when the user with the specified ID does not exist
            return "error"; // You can create an error page or redirect to an appropriate view
        }
        model.addAttribute("user", user);
        return "users/editUser";
    }

    @PostMapping("/users/edit/{uid}")
    public String editUser(@PathVariable String uid, @RequestParam Map<String, String> updatedUser, HttpServletResponse response) {
        System.out.println("UPDATE User " + uid);
        // Retrieve the user from the database based on the given uid
        int id = Integer.parseInt(uid);
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            // Handle the case when the user with the specified ID does not exist
            return "error"; // You can create an error page or redirect to an appropriate view
        }
        // Update the user attributes with the new values
        String newName = updatedUser.get("name");
        String newPwd = updatedUser.get("password");
        int newSize = Integer.parseInt(updatedUser.get("size"));
        user.setName(newName);
        user.setPassword(newPwd);
        user.setSize(newSize);
        userRepo.save(user);
        response.setStatus(200);
        return "redirect:/users/view"; // Redirect to the view page after updating a user
    }


    

}
