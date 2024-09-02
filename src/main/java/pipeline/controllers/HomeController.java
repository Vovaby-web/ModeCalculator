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
    if ("Yes".equals(modesService.outError(modesComponent))) {
      ResultComponent res = modesService.outResult(modesComponent);
      this.modesComponent.saveMode(modesComponent);
      this.baseComponent.setModes(modesService.loadName(username));
      // Отображаем элементы, которые требуется нарисовать
      model.addAttribute("showPressure", true);
      model.addAttribute("line", 1);
      model.addAttribute("presout", res.getPres_out_head());
      model.addAttribute("presin", res.getPres_in_final());
      model.addAttribute("pomp_a", res.getPomp_a());
      model.addAttribute("pomp_b", res.getPomp_b());
    } else {
      model.addAttribute("message", this.modesService.outError(modesComponent));
    }
    model.addAttribute("form", this.modesComponent);
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
    if (selectedModeValue!=null) {
      modesComponent.saveMode(
          modesService.loadParameter(selectedModeValue, username));
    }
    return "redirect:/home";
  }
  @PostMapping("/export")
  public ResponseEntity<byte[]> exportData() {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Example Sheet");
    Row row = sheet.createRow(0);
    Cell cell = row.createCell(0);
    cell.setCellValue("Hello, Excel!");
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      workbook.write(outputStream);
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    byte[] bytes = outputStream.toByteArray();
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=example.xlsx");
    return ResponseEntity.ok()
      .headers(headers)
      .contentType(MediaType.APPLICATION_OCTET_STREAM)
      .body(bytes);
  }
}
