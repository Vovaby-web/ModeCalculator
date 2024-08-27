package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipeline.model.ModesProcessor;
import pipeline.services.ModesService;
import pipeline.services.LoggedService;
@Controller
public class MainController {
  private final LoggedService loggedService;
  private final ModesProcessor modesProcessor;
  private String username;
  public MainController(LoggedService loggedService, ModesProcessor modesProcessor) {
    this.loggedService = loggedService;
    this.modesProcessor = modesProcessor;
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
  @PostMapping("/main")
  public String homePost(
      @RequestParam("lineLength") int lineLength,
      @RequestParam(required = false) String pointStart,
      @RequestParam(required = false) String pointEnd,
      @RequestParam(required = false) String diameter,
      @RequestParam(required = false) String density,
      @RequestParam(required = false) String pumpBrand,
      Model model) {
    model.addAttribute("username", username);
    modesProcessor.set(lineLength,pointStart,pointEnd,diameter,density,pumpBrand);
    if ("Yes".equals(modesProcessor.out())) {
      ModesService m=modesProcessor.model();
      model.addAttribute("lineLength", 300);
      model.addAttribute("line",1);
      // Отображаем элементы, которые требуется нарисовать
      model.addAttribute("showPressure", true);
    } else {
      ModesService m=modesProcessor.model();
      model.addAttribute("message", modesProcessor.out());
      model.addAttribute("lineLength",300);
    }
    return "main";
  }
}
