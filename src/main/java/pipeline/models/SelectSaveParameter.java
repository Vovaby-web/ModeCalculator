package pipeline.models;
import java.util.Objects;
public class SelectSaveParameter {
  private Integer value;
  private String name;
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SelectSaveParameter that = (SelectSaveParameter) o;
    return Objects.equals(value, that.value);
  }
  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
  // Конструктор
  // Геттеры
  public Integer getValue() {
    return value;
  }
  public void setValue(Integer value) {
    this.value = value;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
