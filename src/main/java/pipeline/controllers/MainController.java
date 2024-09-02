package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pipeline.services.DataBaseService;
import pipeline.services.LoginService;
@Controller
public class MainController {
  LoginService loginService;
  //Для создания таблиц
  private final DataBaseService databaseService;
  public MainController(LoginService loginService,
                        DataBaseService databaseService) {
    this.loginService = loginService;
    this.databaseService = databaseService;
  }
  @GetMapping("/")
  public String loginGet() {
    // Создаем один раз таблицы и хватит
    // databaseService.createTable();
    return "main.html";
  }
  @PostMapping("/")
  public String loginPost(@RequestParam String username,
                          @RequestParam String password,Model model) {
    loginService.setLoggedComponent(username,password);
    boolean loggedIn = loginService.login();
    if (loggedIn) {
      return "redirect:/home";
    }
    model.addAttribute("message", "Неверный пароль!");
    return "main.html";
  }
}

