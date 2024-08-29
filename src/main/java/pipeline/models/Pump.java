package pipeline.models;
public class Pump {
  private String value;
  private String name;
  // Конструктор
  public Pump(String value, String name) {
    this.value = value;
    this.name = name;
  }

  // Геттеры
  public String getValue() {
    return value;
  }

  public String getName() {
    return name;
  }
}
