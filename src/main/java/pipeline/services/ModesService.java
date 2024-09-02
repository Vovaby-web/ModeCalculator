package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.models.ModesComponent;
import pipeline.models.SelectSaveParameter;
import pipeline.models.ResultComponent;
import pipeline.repository.DataBaseRepository;

import java.util.List;
@Service
@RequestScope
public class ModesService {
  private final ResultComponent resultComponent;
  private final DataBaseRepository dataBaseRepository;
  public ModesService(ResultComponent resultComponent,
                      DataBaseRepository dataBaseRepository) {
    this.resultComponent = resultComponent;
    this.dataBaseRepository = dataBaseRepository;
  }
  public String outError(ModesComponent modesComponent) {
    String str = "Yes";
    if (modesComponent.getNameSave() == null || modesComponent.getNameSave().isEmpty()) {
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
    }else if (modesComponent.getViscosity() == null) {
      str = "Отсутствует данные по вязкости нефтепродукта";
    } else if (modesComponent.getPumpBrand() == null || modesComponent.getPumpBrand().isEmpty()) {
      str = "Не выбран насосный агрегат";
    }
    return str;
  }
  public ResultComponent outResult(ModesComponent modesComponent) {
    // Определяем индекс выбранного насоса
    int i = Integer.parseInt(modesComponent.getPumpBrand());
    resultComponent.setPomp_a(resultComponent.getPump_char_a()[i]);
    resultComponent.setPomp_b(resultComponent.getPump_char_b()[i]);
    Double viscosity=9*Math.pow(10,-6); // Вязкость равна 9 сСт

    // Площадь сечения трубопровода
    resultComponent.setSquare(Math.PI*Math.pow(modesComponent.getDiameter()/1000.0,2)/4.0);
    double speed=(double)resultComponent.getPump_char_a()[i]*3600/resultComponent.getSquare();
    resultComponent.setReynolds_number(speed*(modesComponent.getDiameter()/1000)/viscosity);
    resultComponent.setLambda(calculationLambda(resultComponent.getReynolds_number()));
    return resultComponent;
  }
  public Double calculationLambda(Double reynolds_number){
    double result;
    if (reynolds_number<2320.0){
      result=64.0/reynolds_number;
    }else if (reynolds_number>=2320.0&&reynolds_number<Math.pow(10,4)){
      double gamma=1-Math.exp(-0.2*(reynolds_number-2320.0));
      result=(64.0/reynolds_number)*(1.0-gamma)+(0.3164/Math.pow(reynolds_number,1.0/4.0))*gamma;
    }else if (reynolds_number>=Math.pow(10,4)&&reynolds_number<27.0/Math.exp(1.143)){
      result=0.3164/Math.pow(reynolds_number,1.0/4.0);
    }else if (reynolds_number>=27.0/Math.exp(1.143)&&reynolds_number<500.0/Math.exp(1.0)){
      result=0.11*(Math.exp(1.0)+68.0/reynolds_number);
    }else result=0.11*Math.exp(1.0/4.0);
    return result;
  }
  public List<SelectSaveParameter> loadName(String login) {
    // Здесь вы можете получить данные из базы данных, например
    return dataBaseRepository.findUsername(login);
  }
  public void saveParameters(ModesComponent modesComponent, String login) {
    dataBaseRepository.saveBaseParameter(modesComponent, login);
  }
  public ModesComponent loadParameter(Integer valueParameter, String login) {
    return dataBaseRepository.loadParameters(valueParameter, login);
  }
}
