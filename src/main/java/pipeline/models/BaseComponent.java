package pipeline.models;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.List;
@Component
@SessionScope
public class BaseComponent {
  private List<String> pumpBase;
  public List<String> getPumpBase() {
    return pumpBase;
  }
  public void setPumpBase(List<String> pumpBase) {
    this.pumpBase = pumpBase;
  }
}
