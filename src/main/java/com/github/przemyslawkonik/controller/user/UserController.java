package com.github.przemyslawkonik.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.przemyslawkonik.entity.User;
import com.github.przemyslawkonik.exception.UserEmailException;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public String user() {
		return "user/user_profile";
	}

	@GetMapping("/edit/{id}")
	public String editUser(Model model, @PathVariable long id) {
		model.addAttribute("user", new User());
		return "user/user_edit";
	}

	@PostMapping("/edit/{id}")
	public String editUser(Model model, @PathVariable long id, @Valid @ModelAttribute User user, BindingResult br) {
		if (br.hasErrors()) {
			return "user/user_edit";
		}
		try {
			user.setId(id);
			userService.editUser(user);
			return "redirect:/users/" + id;
		} catch (UserEmailException e) {
			model.addAttribute("errorEmail", "That email is already taken");
			return "user/user_edit";
		}
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
		return "redirect:/";
	}

}
