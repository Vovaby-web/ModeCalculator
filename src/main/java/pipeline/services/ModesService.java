package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.models.ModesComponent;
import pipeline.models.SelectSaveParameter;
import pipeline.models.ResultComponent;
import pipeline.repository.DataBaseRepository;

import java.util.ArrayList;
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
  public boolean outError(ModesComponent modesComponent) {
    modesComponent.setMessage("");
    if (modesComponent.getNameSave() == null || modesComponent.getNameSave().isEmpty()) {
      modesComponent.setMessage("Введено неверное название данных");
      return false;
    } else if (modesComponent.getLineLength() == null) {
      modesComponent.setMessage("Отсутствует данные по длине участка");
      return false;
    } else if (modesComponent.getPointStart() == null) {
      modesComponent.setMessage("Отсутствует данные по высотной отметке начала участка");
      return false;
    } else if (modesComponent.getPointEnd() == null) {
      modesComponent.setMessage("Отсутствует данные по высотной отметке конца участка");
      return false;
    } else if (modesComponent.getDiameter() == null) {
      modesComponent.setMessage("Отсутствует данные по диаметру трубопровода");
      return false;
    } else if (modesComponent.getDensity() == null) {
      modesComponent.setMessage("Отсутствует данные по плотности нефтепродукта");
      return false;
    } else if (modesComponent.getViscosity() == null) {
      modesComponent.setMessage("Отсутствует данные по вязкости нефтепродукта");
      return false;
    } else if (modesComponent.getPumpBrand() == null || modesComponent.getPumpBrand().isEmpty()) {
      modesComponent.setMessage("Не выбран насосный агрегат");
      return false;
    }
    return true;
  }
  public ResultComponent clearResult() {
    resultComponent.clear();
    return resultComponent;
  }
  public ResultComponent outResult(ModesComponent modesComponent) {
    // Определяем индекс выбранного насоса
    int i = Integer.parseInt(modesComponent.getPumpBrand());
    resultComponent.setPomp_a(resultComponent.getPump_a()[i]);
    resultComponent.setPomp_b(resultComponent.getPump_b()[i]);
    // Напор, создаваемый подпорным агрегатом
    resultComponent.setHead_booster(30);
    // Напор в конечной точке
    resultComponent.setHead_end(6);
    // Площадь сечения трубопровода
    resultComponent.setSquare(Math.PI * Math.pow(modesComponent.getDiameter() / 1000.0, 2) / 4.0);
    calc(modesComponent, resultComponent.limit_perf[i]);
    resultComponent.setPres_out_head(modesComponent.getDensity() / 100000.0 * 9.8 *
        (resultComponent.getHead_main() + resultComponent.getHead_booster() +
            modesComponent.getPointStart()));
    resultComponent.setPres_in_final(modesComponent.getDensity() / 100000.0 * 9.8 *
        (resultComponent.getHead_end() + modesComponent.getPointEnd()));
    resultComponent.setTotalHead(resultComponent.getHead_main() + resultComponent.getHead_booster() +
        (int) Math.round(modesComponent.getPointStart()));
    resultComponent.setTotalEnd(resultComponent.getHead_end() +
        (int) Math.round(modesComponent.getPointEnd()));
    return resultComponent;
  }
  private boolean stack=false;
  private void calc(ModesComponent modesComponent, int a) {
    List<Integer> chart_pomp = new ArrayList<>();
    List<Integer> chart_net = new ArrayList<>();
    List<Integer> chart_perf = new ArrayList<>();
    int pomp = resultComponent.getPomp_a();
    int net;
    int k = 1;
    int q = k;
    resultComponent.setHead_main(0);
    resultComponent.setPerformance(0);
    stack=false;
    while (pomp > 0) {
      pomp = (int) (resultComponent.getPomp_a() - resultComponent.getPomp_b() *
          Math.pow(10, -4) * Math.pow(q, 2));
      chart_pomp.add(pomp);
      net = (int) calcHeadmain(modesComponent, q);
      chart_net.add(net);
      chart_perf.add(q);
      if (pomp >= net - 1 && pomp <= net + 1) {
        resultComponent.setHead_main(pomp);
        resultComponent.setPerformance(q);
        stack=true;
        net = (int) calcHeadmain(modesComponent, q);
        stack=false;
      }
      if (net > pomp + 150)
        break;
      q += k;
    }
    resultComponent.setChart_pomp(chart_pomp);
    resultComponent.setChart_net(chart_net);
    resultComponent.setChart_perf(chart_perf);
  }
  private double calcHeadmain(ModesComponent modesComponent, int q) {
    // С учетом внутренней толщины трубы 8мм*2 = 16мм
    double d = (modesComponent.getDiameter() - 16.0) / 1000.0;
    // v = Q / S; м3/ч разделить м2; Перевод ч в секунды = 3600
    double speed = (double) q / (3600.0 * resultComponent.getSquare());
    // Re = v(скорость) * d(диаметр в мм, поэтому делим на 1000)) /
    // m(вязкость в сСт, поэтому умножаем на 10^-6)
    double reyn = speed * d / (modesComponent.getViscosity() * Math.pow(10.0, -6));
    double lambda = calcLambda(reyn, d);
    resultComponent.setIforq(lambda * Math.pow(speed, 2) / (d * 2.0 * 9.8));
    double head_net = 1.02 * resultComponent.getIforq() * (modesComponent.getLineLength() * 1000.0) +
        resultComponent.getHead_end() - resultComponent.getHead_booster() +
        (modesComponent.getPointEnd() - modesComponent.getPointStart());
    if (stack){
      resultComponent.setLambda(lambda);
      resultComponent.setReynolds_number(reyn);
    }
    return head_net;
  }
  public Double calcLambda(double reyn, double d) {
    double result = 0.0;
    // Относительная шероховатость трубы
    double del = 0.2;
    // Абсолютная шероховатость трубы
    double e = del / (d * 1000.0);
    if (reyn < 2320.0) {
      result = 64.0 / reyn;
    } else if (reyn >= 2320.0 && reyn < Math.pow(10.0, 4)) {
      double gamma = 1.0 - Math.exp(-0.2 * (reyn - 2320.0));
      result = (64.0 / reyn) * (1.0 - gamma) + (0.3164 / Math.pow(reyn, 1.0 / 4.0)) * gamma;
    } else if (reyn >= Math.pow(10.0, 4) && reyn < 27.0 / Math.pow(e, 1.143)) {
      result = 0.3164 / Math.pow(reyn, 1.0 / 4.0);
    } else if (reyn >= 27.0 / Math.pow(e, 1.143) && reyn < 500.0 / e) {
      result = 0.11 * Math.pow(e + 68.0 / reyn, 1.0 / 4.0);
    } else if (reyn >= 500.0 / e) {
      result = 0.11 * Math.pow(e, 1.0 / 4.0);
    }
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
