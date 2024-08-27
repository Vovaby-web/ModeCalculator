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
      @RequestParam(required = false) String pumpBrand,
      Model model) {
    if (Logout != null) {
      loggedService.setUsername(null);
    }
    username = loggedService.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    model.addAttribute("username", username);
    model.addAttribute("pumpBrand", "");
    return "main";
  }
  @PostMapping("/main")
  public String homePost(
      @RequestParam("lineLength") int lineLength,
      @RequestParam(required = false) Double pointStart,
      @RequestParam(required = false) Double pointEnd,
      @RequestParam(required = false) Double diameter,
      @RequestParam(required = false) Double density,
      @RequestParam(required = false) String pumpBrand,
      Model model) {
    model.addAttribute("username", username);
    modesProcessor.set(lineLength, pointStart, pointEnd, diameter, density, pumpBrand);
    ModesService m = modesProcessor.model();
    model.addAttribute("lineLength", m.getLineLength());
    model.addAttribute("pointStart", m.getPointStart());
    model.addAttribute("pointEnd", m.getPointEnd());
    model.addAttribute("diameter", m.getDiameter());
    model.addAttribute("density", m.getDensity());
    model.addAttribute("pumpBrand", m.getPumpBrand());
    if ("Yes".equals(modesProcessor.out())) {
      // Отображаем элементы, которые требуется нарисовать
      model.addAttribute("showPressure", true);
      model.addAttribute("line", 1);
    } else {
      model.addAttribute("message", modesProcessor.out());
    }
    return "main";
  }
}
