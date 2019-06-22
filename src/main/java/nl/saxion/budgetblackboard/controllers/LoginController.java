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
	private boolean wrongCredentials;

	@GetMapping(path = "")
	public String redirect() {
			return "redirect:/login";
	}

	@GetMapping(path = "/login")
	public String login(HttpSession session, Model model) {
		if (session.getAttribute("email") != null) {
			return "redirect:/courses";
		}
		else {
			if (this.wrongCredentials){
				model.addAttribute("errorMessage", "Wrong credentials. Please try again");
			}
			return "login/loginIndex";
		}
	}

	@PostMapping(path = "/login")
	public String loginPost(HttpSession session, User user){
		for (User usr : this.data.getUsers()) {
			if (usr.getEmail().equals(user.getEmail())){
				if (usr.getPassword().equals(user.getPassword())){
					session.setAttribute("email", user.getEmail());
					return "redirect:/courses";
				}
			}
		}
		this.wrongCredentials = true;
		return "redirect:/login" ;
	}

	@GetMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
}
