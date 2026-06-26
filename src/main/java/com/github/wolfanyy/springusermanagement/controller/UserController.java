package com.github.wolfanyy.springusermanagement.controller;

import com.github.wolfanyy.springusermanagement.dto.UserRequest;
import com.github.wolfanyy.springusermanagement.entity.User;
import com.github.wolfanyy.springusermanagement.mapper.UserMapper;
import com.github.wolfanyy.springusermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute(
                "users",
                userService.findAll()
        );
        return "users/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {

        model.addAttribute(
                "user",
                new UserRequest()
        );

        return "users/create";
    }

    @PostMapping
    public String create(
            @Valid @ModelAttribute("user")
            UserRequest userRequest,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "users/create";
        }

        userService.create(
                userMapper.toEntity(userRequest)
        );

        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editForm(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute(
                "user",
                userMapper.toRequest(
                        userService.findById(id)
                )
        );

        return "users/edit";
    }

    @PutMapping("/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute("user")
            UserRequest userRequest,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        userRequest.setId(id);

        userService.update(
                userMapper.toEntity(userRequest)
        );

        return "redirect:/users";
    }
}
