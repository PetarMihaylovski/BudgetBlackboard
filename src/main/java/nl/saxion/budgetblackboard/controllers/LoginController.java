package nl.saxion.budgetblackboard.controllers;

import nl.saxion.budgetblackboard.dataProvider.DataProvider;
import nl.saxion.budgetblackboard.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "")
public class LoginController {
	private DataProvider data = DataProvider.getInstance();

	@GetMapping(path = "")
	public String redirect() {
			return "redirect:/login";
	}

	@GetMapping(path = "/login")
	public String login(HttpSession session) {
		if (session.getAttribute("email") != null) {
			return "redirect:/courses";
		}
		else {
			return "login/loginIndex";
		}
	}

	@PostMapping(path = "/login")
	public String loginPost(HttpSession session, Model model, User user){
		for (User usr : this.data.getUsers()) {
			if (usr.getEmail().equals(user.getEmail())){
				if (usr.getPassword().equals(user.getPassword())){
					session.setAttribute("email", user.getEmail());
					return "redirect:/courses";
				}
			}
		}
		model.addAttribute("errorMessage", "Username is not valid. Try again or register");
		System.out.println(user.getEmail() + "   " + user.getPassword());
		return "login/loginIndex";
	}
}
