package pipeline.controllers;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pipeline.services.ModesService;
import pipeline.models.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook; // Импортировать XSSFWorkbook
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
  @GetMapping("/home")
  public String homeGet(@RequestParam(required = false) String Logout,
                        Model model) {
    if (Logout != null) {
      loggedComponent.setUsername(null);
      modesComponent.saveNull();
      baseComponent.saveNull();
    }
    username = loggedComponent.getUsername();
    if (username == null) {
      return "redirect:/";
    }
    if (baseComponent.getModes() == null) {
      baseComponent.setModes(modesService.loadName(username));
    }
    model.addAttribute("username", username);
    model.addAttribute("form", modesComponent);
    model.addAttribute("headform", baseComponent);
    model.addAttribute("result", modesService.clearResult());
    return "home";
  }
  @PostMapping("/home")
  public String homePost(@ModelAttribute ModesComponent modesComponent,
                         @ModelAttribute BaseComponent baseComponent,
                         Model model) {
    model.addAttribute("username", username);
    model.addAttribute("result", modesService.clearResult());
    if (modesService.outError(modesComponent)) {
      ResultComponent res = modesService.outResult(modesComponent);
      if (res.getHead_main() > 0 && res.getPerformance() > 0) {

        this.modesComponent.saveMode(modesComponent);
        this.baseComponent.setModes(modesService.loadName(username));

        // Отображаем элементы, которые требуется нарисовать
        model.addAttribute("showPressure", true);

        model.addAttribute("result", res);

        // Передаем данные для рисования графиков
        model.addAttribute("perfData", res.getChart_perf());
        model.addAttribute("head_1", res.getChart_pomp());
        model.addAttribute("head_2", res.getChart_net());
        model.addAttribute("perfCur", res.getPerformance());
        model.addAttribute("headCur", res.getHead_main());

        model.addAttribute("line_length", modesComponent.getLineLength());
        List<Integer> headData = Arrays.asList(res.getTotalHead(), res.getTotalEnd());
        model.addAttribute("headData", headData);
        model.addAttribute("iforq", res.getIforq());

        // Формат для двух знаков после запятой
        model.addAttribute("presout", res.getPres_out_headStr());
        model.addAttribute("presin", res.getPres_in_finalStr());
      } else {
        model.addAttribute("message", "Расчеты по данным параметрам, " +
            "выполнить невозможно");
      }
    } else {
      model.addAttribute("message", modesComponent.getMessage());
    }
    model.addAttribute("form", modesComponent);
    model.addAttribute("headform", this.baseComponent);
    return "home";
  }
  @PostMapping("/save")
  public String saveData() {
    if (baseComponent.findName(modesComponent.getNameSave())) {
      modesService.saveParameters(modesComponent, username);
      baseComponent.setModes(modesService.loadName(username));
    }
    return "redirect:/home";
  }
  @PostMapping("/load")
  public String loadData(@ModelAttribute BaseComponent baseComponent, Model model) {
    Integer selectedModeValue = baseComponent.getSelectMode();
    if (selectedModeValue != null) {
      modesComponent.saveMode(
          modesService.loadParameter(selectedModeValue, username));
    }
    return "redirect:/home";
  }
  @PostMapping("/export")
  public ResponseEntity<byte[]> exportData() {
    return modesService.exportXls(modesComponent);
  }
}
