package pipeline.models;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.List;
@Component
@SessionScope
public class BaseComponent {
  private List<SelectSaveParameter> modes;
  private Integer selectMode;
  public void saveNull() {
    setModes(null);
    setSelectMode(0);
  }
  public boolean findName(String name){
    for (SelectSaveParameter mode:getModes()){
      if (name.equals(mode.getName()))
        return false;
    }
    return true;
  }
  public List<SelectSaveParameter> getModes() {
    return modes;
  }
  public void setModes(List<SelectSaveParameter> modes) {
    this.modes = modes;
  }
  public Integer getSelectMode() {
    return selectMode;
  }
  public void setSelectMode(Integer selectMode) {
    this.selectMode = selectMode;
  }

}
