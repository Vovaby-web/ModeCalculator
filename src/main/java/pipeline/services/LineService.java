package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
@Service
@SessionScope
public class LineService {
    private String lineLength;
    private String lineColor;
  public String getLineLength() {
    return lineLength;
  }
  public void setLineLength(String lineLength) {
    this.lineLength = lineLength;
  }
  public String getLineColor() {
    return lineColor;
  }
  public void setLineColor(String lineColor) {
    this.lineColor = lineColor;
  }
}


