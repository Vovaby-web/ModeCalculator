package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipeline.model.ModesProcessor;
import pipeline.services.ModesService;
import pipeline.services.LoggedService;
import pipeline.services.ResultService;
@Controller
public class MainController {
  private final LoggedService loggedService;
  private final ModesService modesService;
  private final ModesProcessor modesProcessor;
  private String username;
  public MainController(LoggedService loggedService, ModesService modesService,
                        ModesProcessor modesProcessor) {
    this.loggedService = loggedService;
    this.modesService = modesService;
    this.modesProcessor = modesProcessor;
  }
  @GetMapping("/main")
  public String homeGet(@RequestParam(required = false) String Logout, Model model) {
    if (Logout != null) {
      loggedService.setUsername(null);
    }
    username = loggedService.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    model.addAttribute("username", username);
    model.addAttribute("form", modesService);
    return "main";
  }
  @PostMapping("/main")
  public String homePost(@ModelAttribute ModesService modesService, Model model) {
    model.addAttribute("username", username);
    model.addAttribute("form", modesService);
    if ("Yes".equals(modesProcessor.outError(modesService))) {
      ResultService res= modesProcessor.outResult(modesService);
      // Отображаем элементы, которые требуется нарисовать
      model.addAttribute("showPressure", true);
      model.addAttribute("line", 1);
      model.addAttribute("presout", res.getPres_out_head_1());
      model.addAttribute("presin", res.getPres_in_final_1());
    }else {
      model.addAttribute("message", modesProcessor.outError(modesService));
    }
    return "main";
  }
}
