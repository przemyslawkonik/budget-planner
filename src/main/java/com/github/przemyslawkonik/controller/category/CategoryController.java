package com.github.przemyslawkonik.controller.category;

import java.util.ArrayList;
import java.util.List;

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

import com.github.przemyslawkonik.entity.Category;
import com.github.przemyslawkonik.repository.CategoryRepository;
import com.github.przemyslawkonik.service.user.UserService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository catRep;

	@Autowired
	private UserService userService;

	@GetMapping("")
	public String categories(Model model) {
		model.addAttribute("categories", catRep.findByUser(userService.getSessionUser()));
		return "/category/category_menu";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("category", new Category());
		return "/category/category_add";
	}

	@PostMapping("/add")
	public String add(@Valid @ModelAttribute Category category, BindingResult br) {
		if (br.hasErrors()) {
			return "/category/category_add";
		}
		catRep.save(category);
		return "redirect:/categories";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable long id) {
		model.addAttribute("category", catRep.findOne(id));
		return "/category/category_edit";
	}

	@PostMapping("/edit/{id}")
	public String edit(@Valid @ModelAttribute Category category, BindingResult br) {
		if (br.hasErrors()) {
			return "/category/category_edit";
		}
		catRep.save(category);
		return "redirect:/categories";
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable long id) {
		catRep.delete(id);
		return "redirect:/categories";
	}

	@ModelAttribute("types")
	public List<String> getTypes() {
		List<String> list = new ArrayList<>();
		list.add("income");
		list.add("expense");
		return list;
	}

}
