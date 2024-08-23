package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pipeline.services.LoggedService;
@Controller
public class MainController {
  private final LoggedService loggedService;
  public MainController(
      LoggedService loggedService) {
    this.loggedService = loggedService;
  }
  @GetMapping("/main")
  public String home(
      @RequestParam(required = false) String Logout,
      Model model) {
    if (Logout != null) {
      loggedService.setUsername(null);
    }
    String username = loggedService.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    model.addAttribute("username", username);
    return "main.html";
  }
}
