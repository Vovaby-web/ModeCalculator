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
    } else if (modesService.getPointStart()==0) {
      str = "Отсутствует данные по высотной отметке начала участка";
    } else if (modesService.getPointEnd()==0) {
      str = "Отсутствует данные по высотной отметке конца участка";
    } else if (modesService.getDiameter()==0) {
      str = "Отсутствует данные по диаметру трубопровода";
    } else if (modesService.getDensity()==0) {
      str = "Отсутствует данные по плотности нефтепродукта";
    } else if (modesService.getPumpBrand()==0) {
      str = "Не выбрана марка насоса";
    }
    return str;
  }
  public void set(int lineLength, double pointStart, double pointEnd,
                  double diameter, double density, double pumpBrand) {
    if (lineLength!=0)
      modesService.setLineLength(lineLength);
    if (pointStart!=0) 
      modesService.setPointStart(pointStart);
    if (pointEnd!=0)
      modesService.setPointEnd(pointEnd);
    if (diameter!=0)
      modesService.setDiameter(diameter);
    if (density!=0)
      modesService.setDensity(density);
    if (pumpBrand!=0)
      modesService.setPumpBrand(pumpBrand);
  }
  public ModesService model() {
    return modesService;
  }
}
