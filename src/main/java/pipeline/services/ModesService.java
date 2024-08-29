package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.models.ModesComponent;
import pipeline.models.ResultComponent;

import java.util.List;
@Service
@RequestScope
public class ModesService {
  private final ResultComponent resultComponent;
  public ModesService(ResultComponent resultComponent) {
    this.resultComponent = resultComponent;
  }
  public String outError(ModesComponent modesComponent) {
    String str = "Yes";
    if (modesComponent.getNameSave() == null|| modesComponent.getNameSave().isEmpty()) {
      str = "Введено неверное название данных";
    } else if (modesComponent.getLineLength() == null) {
      str = "Отсутствует данные по длине участка";
    } else if (modesComponent.getPointStart() == null) {
      str = "Отсутствует данные по высотной отметке начала участка";
    } else if (modesComponent.getPointEnd() == null) {
      str = "Отсутствует данные по высотной отметке конца участка";
    } else if (modesComponent.getDiameter() == null) {
      str = "Отсутствует данные по диаметру трубопровода";
    } else if (modesComponent.getDensity() == null) {
      str = "Отсутствует данные по плотности нефтепродукта";
    }else if (modesComponent.getPumpBrand() == null|| modesComponent.getPumpBrand().isEmpty()) {
      str = "Не выбран насосный агрегат";
    }
    return str;
  }
  public ResultComponent outResult(ModesComponent modesComponent) {
    resultComponent.setPres_out_head_1(modesComponent.getPointStart());
    resultComponent.setPres_in_final_1(modesComponent.getPointEnd());
    return resultComponent;
  }
  public List<String> getAllPumps(){
    return List.of("brand1", "brand2");
  }
}
