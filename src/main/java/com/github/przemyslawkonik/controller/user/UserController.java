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
import com.github.przemyslawkonik.repository.PaymentMethodRepository;
import com.github.przemyslawkonik.repository.UserRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PaymentMethodRepository paymentRepo;

	@GetMapping("/{id}")
	public String user(Model model, @PathVariable long id) {
		model.addAttribute("userMethods", paymentRepo.findAllByUsersId(id));
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

	@GetMapping("/edit/payments/{id}")
	public String editUserPaymentMethods(Model model, @PathVariable long id) {
		// model.addAttribute("user", userRepo.findOne(id));
		model.addAttribute("methods", paymentRepo.findAll());
		return "/user/user_payment_methods_edit";
	}

	@PostMapping("/edit/payments/{id}")
	public String editUserPaymentMethods(@ModelAttribute User user) {
		userRepo.save(user);
		return "redirect:/users/" + user.getId();
	}

	@GetMapping("/edit/money/{id}")
	public String editUserMoney() {
		return "/user/user_money_edit";
	}

	@PostMapping("/edit/money/{id}")
	public String editUserMoney(@ModelAttribute User user) {
		userRepo.save(user);
		return "redirect:/users/" + user.getId();
	}

	@ModelAttribute("user")
	public User user() {
		return userRepo.findOne(userService.getSessionUser().getId());
	}

}
