package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pipeline.model.LoginProcessor;
@Controller
public class LoginController {
  LoginProcessor loginProcessor;
  public LoginController(LoginProcessor loginProcessor) {
    this.loginProcessor = loginProcessor;
  }
  @GetMapping("/")
  public String loginGet() {
    return "home.html";
  }
  @PostMapping("/")
  public String loginPost(@RequestParam String username,@RequestParam String password,Model model) {
    loginProcessor.setUsername(username);
    loginProcessor.setPassword(password);
    boolean loggedIn = loginProcessor.login();
    if (loggedIn) {
      return "redirect:/main";
    }
    model.addAttribute("message", "Неверный пароль!");
    return "home.html";
  }
}

