package pipeline.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipeline.services.ModesService;
import pipeline.models.*;
import pipeline.services.PumpService;

import java.util.List;
@Controller
public class HomeController {
  private final LoggedComponent loggedComponent;
  private final ModesComponent modesComponent;
  private final ModesService modesService;
  private final BaseComponent baseComponent;
  private String username;
  public HomeController(LoggedComponent loggedComponent, ModesComponent modesComponent,
                        ModesService modesService, BaseComponent baseComponent) {
    this.loggedComponent = loggedComponent;
    this.modesComponent = modesComponent;
    this.modesService = modesService;
    this.baseComponent = baseComponent;
  }
  @Autowired
  private PumpService pumpService; // Ваш сервис для получения насосов
  @GetMapping("/home")
  public String homeGet(@RequestParam(required = false) String Logout, Model model) {
    if (Logout != null) {
      loggedComponent.setUsername(null);
    }
    username = loggedComponent.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    List<Pump> pumps = pumpService.getAllPumps(); // Получаем список насосов
    model.addAttribute("username", username);
    model.addAttribute("headform", baseComponent);
    model.addAttribute("pumps", pumps);
    model.addAttribute("form", modesComponent);
    return "home";
  }
  @PostMapping("/home")
  public String homePost(@ModelAttribute ModesComponent modesComponent,
                         @ModelAttribute BaseComponent baseComponent,
                         Model model) {
    model.addAttribute("username", username);
    model.addAttribute("form", modesComponent);
    model.addAttribute("headform", baseComponent);
    if ("Yes".equals(this.modesService.outError(modesComponent))) {
      ResultComponent res= this.modesService.outResult(modesComponent);
      // Отображаем элементы, которые требуется нарисовать
      model.addAttribute("showPressure", true);
      model.addAttribute("line", 1);
      model.addAttribute("presout", res.getPres_out_head_1());
      model.addAttribute("presin", res.getPres_in_final_1());
    }else {
      model.addAttribute("message", this.modesService.outError(modesComponent));
    }
    return "home";
  }
  @PostMapping("/save")
  public String saveData() {
   modesComponent.setLineLength(100);
    return "redirect:/home";
  }
  @PostMapping("/export")
  public String exportData() {
    modesComponent.setLineLength(200);
    return "redirect:/home";
  }
}
