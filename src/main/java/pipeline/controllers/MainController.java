package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipeline.services.LineService;
import pipeline.services.LoggedService;
@Controller
public class MainController {
  private final LoggedService loggedService;
  private final LineService lineService;
  private String username;
  public MainController(LoggedService loggedService, LineService lineService) {
    this.loggedService = loggedService;
    this.lineService = lineService;
  }
  @GetMapping("/main")
  public String homeGet(
      @RequestParam(required = false) String Logout,
      Model model) {
    if (Logout != null) {
      loggedService.setUsername(null);
    }
    username = loggedService.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    model.addAttribute("username", username);
    return "main";
  }
  @PostMapping( "/main")
  public String homePost(
      @RequestParam(required = false) String lineLength,
      @RequestParam(required = false) String lineColor,
      Model model) {
    model.addAttribute("username", username);
    if (lineLength.isEmpty()||lineColor.isEmpty()){
      model.addAttribute("message", "Введите параметры");
      model.addAttribute("lineLength", 0);
      model.addAttribute("lineColor", 0);
    }else {
      lineService.setLineLength(lineLength);
      lineService.setLineColor(lineColor);
      System.out.println(lineService.getLineColor());
      model.addAttribute("lineLength", Integer.parseInt(lineService.getLineLength()));
      model.addAttribute("lineColor", lineColor);
    }
    return "main";
  }
}
