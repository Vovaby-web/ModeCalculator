package pipeline.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipeline.services.ModesService;
import pipeline.models.*;
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
  @GetMapping("/home")
  public String homeGet(@RequestParam(required = false) String Logout,
                        Model model) {
    if (Logout != null) {
      loggedComponent.setUsername(null);
    }
    username = loggedComponent.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    if (baseComponent.getModes()==null) {
     baseComponent.setModes(modesService.loadName(username));
    }
    model.addAttribute("username", username);
    model.addAttribute("form", modesComponent);
    model.addAttribute("headform", baseComponent);
    return "home";
  }
  @PostMapping("/home")
  public String homePost(@ModelAttribute ModesComponent modesComponent,
                         @ModelAttribute BaseComponent baseComponent,
                         Model model) {
    model.addAttribute("username", username);
    model.addAttribute("form", modesComponent);
    model.addAttribute("headform", baseComponent);
    if ("Yes".equals(modesService.outError(modesComponent))) {
      ResultComponent res = modesService.outResult(modesComponent);
      modesComponent.saveMode(modesComponent);
      // Отображаем элементы, которые требуется нарисовать
      model.addAttribute("showPressure", true);
      model.addAttribute("line", 1);
      model.addAttribute("presout", res.getPres_out_head_1());
      model.addAttribute("presin", res.getPres_in_final_1());
    } else {
      model.addAttribute("message", this.modesService.outError(modesComponent));
    }
    return "home";
  }
  @PostMapping("/save")
  public String saveData() {
    modesService.saveParameters(modesComponent,username);
    return "redirect:/home";
  }
  @PostMapping("/load")
  public String loadData(@ModelAttribute BaseComponent baseComponent, Model model) {
    Integer selectedModeValue = baseComponent.getSelectMode();
    // Найдите соответствующий параметр
    String selectedModeName = baseComponent.getModes().stream()
        .filter(mode -> mode.getValue().equals(selectedModeValue))
        .map(SelectSaveParameter::getName)
        .findFirst()
        .orElse("No"); // На случай, если ничего не найдено
    if (!"No".equals(selectedModeName)) {
      modesComponent.saveMode(
          modesService.loadParameter(selectedModeName, username));
    }
    return "redirect:/home";
  }
  @PostMapping("/export")
  public String exportData() {
    modesComponent.setLineLength(200);
    return "redirect:/home";
  }
}
