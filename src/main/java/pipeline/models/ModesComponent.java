package pipeline.models;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@Component
@SessionScope
public class ModesComponent {
  private String nameSave;
  private Integer lineLength;
  private Double pointStart;
  private Double pointEnd;
  private Double diameter;
  private Double density;
  private Double viscosity;
  private String pumpBrand;
  public void saveMode(ModesComponent modesComponent){
    setNameSave(modesComponent.getNameSave());
    setLineLength(modesComponent.getLineLength());
    setPointStart(modesComponent.getPointStart());
    setPointEnd(modesComponent.getPointEnd());
    setDiameter(modesComponent.getDiameter());
    setDensity(modesComponent.getDensity());
    setViscosity(modesComponent.getViscosity());
    setPumpBrand(modesComponent.getPumpBrand());
  }
  public void saveNull(){
    setNameSave("");
    setLineLength(0);
    setPointStart(0.0);
    setPointEnd(0.0);
    setDiameter(0.0);
    setDensity(0.0);
    setViscosity(0.0);
    setPumpBrand("");
  }
  public Integer getLineLength() {
    return lineLength;
  }
  public String getNameSave() {
    return nameSave;
  }
  public void setNameSave(String nameSave) {
    this.nameSave = nameSave;
  }
  public void setLineLength(Integer lineLength) {
    this.lineLength = lineLength;
  }
  public Double getPointStart() {
    return pointStart;
  }
  public void setPointStart(Double pointStart) {
    this.pointStart = pointStart;
  }
  public Double getPointEnd() {
    return pointEnd;
  }
  public void setPointEnd(Double pointEnd) {
    this.pointEnd = pointEnd;
  }
  public Double getDiameter() {
    return diameter;
  }
  public void setDiameter(Double diameter) {
    this.diameter = diameter;
  }
  public Double getDensity() {
    return density;
  }
  public void setDensity(Double density) {
    this.density = density;
  }
  public String getPumpBrand() {
    return pumpBrand;
  }
  public void setPumpBrand(String pumpBrand) {
    this.pumpBrand = pumpBrand;
  }
  public Double getViscosity() {
    return viscosity;
  }
  public void setViscosity(Double viscosity) {
    this.viscosity = viscosity;
  }
}


