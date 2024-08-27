package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@Service
@SessionScope
public class ModesService {
  private int lineLength;
  private double pointStart;
  private double pointEnd;
  private double diameter;
  private double density;
  private double pumpBrand;
  public ModesService() {
    lineLength=0;
    pointStart=0;
    pointEnd=0;
    diameter=0;
    density=0;
    pumpBrand=0;
  }
  public int getLineLength() {
    return lineLength;
  }
  public void setLineLength(int lineLength) {
    this.lineLength = lineLength;
  }
  public double getPointStart() {
    return pointStart;
  }
  public void setPointStart(double pointStart) {
    this.pointStart = pointStart;
  }
  public double getPointEnd() {
    return pointEnd;
  }
  public void setPointEnd(double pointEnd) {
    this.pointEnd = pointEnd;
  }
  public double getDiameter() {
    return diameter;
  }
  public void setDiameter(double diameter) {
    this.diameter = diameter;
  }
  public double getDensity() {
    return density;
  }
  public void setDensity(double density) {
    this.density = density;
  }
  public double getPumpBrand() {
    return pumpBrand;
  }
  public void setPumpBrand(double pumpBrand) {
    this.pumpBrand = pumpBrand;
  }
}


