package pipeline.model;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.services.ModesService;
@Component
@RequestScope
public class ModesProcessor {
  private final ModesService modesService;
  public ModesProcessor(ModesService modesService) {
    this.modesService = modesService;
  }
  public String out() {
    String str = "Yes";
    if (modesService.getLineLength() == 0) {
      str = "Отсутствует данные по длине участка";
    } else if (modesService.getPointStart() == null) {
      str = "Отсутствует данные по высотной отметке начала участка";
    } else if (modesService.getPointEnd() == null) {
      str = "Отсутствует данные по высотной отметке конца участка";
    } else if (modesService.getDiameter() == null) {
      str = "Отсутствует данные по диаметру трубопровода";
    } else if (modesService.getDensity() == null) {
      str = "Отсутствует данные по плотности нефтепродукта";
    } else if (modesService.getPumpBrand().isEmpty()) {
      str = "Не выбрана марка насоса";
    }
    return str;
  }
  public void set(int lineLength, Double pointStart, Double pointEnd,
                  Double diameter, Double density, String pumpBrand) {
    if (lineLength != 0)
      modesService.setLineLength(lineLength);
    if (pointStart != null)
      modesService.setPointStart(pointStart);
    if (pointEnd != null)
      modesService.setPointEnd(pointEnd);
    if (diameter != null)
      modesService.setDiameter(diameter);
    if (density != null)
      modesService.setDensity(density);
    if (pumpBrand!=null)
      modesService.setPumpBrand(pumpBrand);
    else modesService.setPumpBrand("");
  }
  public ModesService model() {
    return modesService;
  }
}
