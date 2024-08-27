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
    if (modesService.getLineLength()==0) {
      str = "Отсутствует данные по длине участка";
    } else if (modesService.getPointStart().isEmpty()) {
      str = "Отсутствует данные по высотной отметке начала участка";
    } else if (modesService.getPointEnd().isEmpty()) {
      str = "Отсутствует данные по высотной отметке конца участка";
    } else if (modesService.getDiameter().isEmpty()) {
      str = "Отсутствует данные по диаметру трубопровода";
    } else if (modesService.getDensity().isEmpty()) {
      str = "Отсутствует данные по плотности нефтепродукта";
    } else if (modesService.getPumpBrand().isEmpty()) {
      str = "Не выбрана марка насоса";
    }
    return str;
  }
  public void set(int lineLength, String pointStart, String pointEnd,
                  String diameter, String density, String pumpBrand) {
    if (lineLength!=0)
      modesService.setLineLength(lineLength);
    if (!pointStart.isEmpty())
      modesService.setPointStart(pointStart);
    if (!pointEnd.isEmpty())
      modesService.setPointEnd(pointEnd);
    if (!diameter.isEmpty())
      modesService.setDiameter(diameter);
    if (!density.isEmpty())
      modesService.setDensity(density);
    if (!pumpBrand.isEmpty())
      modesService.setPumpBrand(pumpBrand);
  }
  public ModesService model() {
    return modesService;
  }
}
