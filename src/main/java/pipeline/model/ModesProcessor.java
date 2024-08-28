package pipeline.model;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.services.ModesService;
import pipeline.services.ResultService;
@Component
@RequestScope
public class ModesProcessor {
  private final ResultService resultService;
  public ModesProcessor(ResultService resultService) {
    this.resultService = resultService;
  }
  public String outError(ModesService modesService) {
    String str = "Yes";
    if (modesService.getLineLength() == null) {
      str = "Отсутствует данные по длине участка";
    } else if (modesService.getPointStart() == null) {
      str = "Отсутствует данные по высотной отметке начала участка";
    } else if (modesService.getPointEnd() == null) {
      str = "Отсутствует данные по высотной отметке конца участка";
    } else if (modesService.getDiameter() == null) {
      str = "Отсутствует данные по диаметру трубопровода";
    } else if (modesService.getDensity() == null) {
      str = "Отсутствует данные по плотности нефтепродукта";
    }else if (modesService.getPumpBrand() == null||modesService.getPumpBrand().isEmpty()) {
      str = "Не выбран насосный агрегат";
    }
    return str;
  }
  public ResultService outResult(ModesService modesService) {
    resultService.setPres_out_head_1(modesService.getPointStart());
    resultService.setPres_in_final_1(modesService.getPointEnd());
    return resultService;
  }
}
