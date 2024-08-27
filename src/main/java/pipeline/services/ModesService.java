package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@Service
@SessionScope
public class ModesService {
  private int lineLength;
  private String pointStart;
  private String pointEnd;
  private String diameter;
  private String density;
  private String pumpBrand;
  public ModesService() {
    lineLength=0;
    pointStart="";
    pointEnd="";
    diameter="";
    density="";
    pumpBrand="";
  }
  public int getLineLength() {
    return lineLength;
  }
  public void setLineLength(int lineLength) {
    this.lineLength = lineLength;
  }
  public String getPointStart() {
    return pointStart;
  }
  public void setPointStart(String pointStart) {
    this.pointStart = pointStart;
  }
  public String getPointEnd() {
    return pointEnd;
  }
  public void setPointEnd(String pointEnd) {
    this.pointEnd = pointEnd;
  }
  public String getDiameter() {
    return diameter;
  }
  public void setDiameter(String diameter) {
    this.diameter = diameter;
  }
  public String getDensity() {
    return density;
  }
  public void setDensity(String density) {
    this.density = density;
  }
  public String getPumpBrand() {
    return pumpBrand;
  }
  public void setPumpBrand(String pumpBrand) {
    this.pumpBrand = pumpBrand;
  }
}


