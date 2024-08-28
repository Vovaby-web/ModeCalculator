package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@Service
@SessionScope
public class ModesService {
  private Integer lineLength;
  private Double pointStart;
  private Double pointEnd;
  private Double diameter;
  private Double density;
  private String pumpBrand;
  public Integer getLineLength() {
    return lineLength;
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
}


