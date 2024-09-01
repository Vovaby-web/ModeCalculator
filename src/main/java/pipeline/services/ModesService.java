package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.models.ModesComponent;
import pipeline.models.SelectSaveParameter;
import pipeline.models.ResultComponent;
import pipeline.repository.DataBaseRepository;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
  public List<SelectSaveParameter> loadName(String login) {
    // Здесь вы можете получить данные из базы данных, например
    return dataBaseRepository.findUsername(login);
  }
  public void saveParameters(ModesComponent modesComponent, String login) {
    dataBaseRepository.saveBaseParameter(modesComponent,login);
  }
  public ModesComponent loadParameter(Integer valueParameter, String login) {
    return dataBaseRepository.loadParameters(valueParameter,login);
  }
  public void printDataToPrinter(PrinterJob printerJob) {
    printerJob.setPrintable(new Printable() {
      @Override
      public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
          return NO_SUCH_PAGE; // Возвращаем эту константу, если это несуществующая страница
        }
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.drawString("Hello, Printing!", 100, 100);
        return PAGE_EXISTS; // указать, что страница существует
      }
    });
    // Здесь можно программно выбрать принтер
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
    if (printServices.length > 0) {
      try {
        // Устанавливаем первый доступный принтер
        printerJob.setPrintService(printServices[0]);
      } catch (PrinterException e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to set print service: " + e.getMessage());
      }
    } else {
      throw new RuntimeException("No printers found");
    }
  }
}
