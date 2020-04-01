package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/register")
public class RegisterController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String register(){
		return "register/register";
	}

	@PostMapping(path = "")
	public String registerPost(User user, Model model){
		this.data.addUser(user);
		model.addAttribute("users", this.data.getUsers());
		return"redirect:/login";
	}
}
