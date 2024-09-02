package pipeline.models;
import org.springframework.stereotype.Component;
@Component
public class ResultComponent {
  private Double pres_out_head;   // Давление на головной станции
  private Double pres_in_final;   // Давление в конечном пункте
  private Integer pomp_a;         // a - коэффициент насоса
  private Double pomp_b;          // b - коэффициент насоса
  private Integer pomp_head;      // Напор на головной станции
  private Integer head_end;       // Напор в конечном пункте
  private Integer head_loss;      // Потери напора на всем участке
  private Double lambda;          // Коэффициент гидравлического сопротивления
  private Double reynolds_number; // Число Рейнольдса
  private Double square;          // Площадь сечения трубопровода
  private Double pumping;         // Производительность перекачки
  private final Integer[] pump_char_a={331,282,304};
  private final Double[] pump_char_b={0.451,0.792,0.579};
  public Integer[] getPump_char_a() {
    return pump_char_a;
  }
  public Double[] getPump_char_b() {
    return pump_char_b;
  }
  public Double getPres_out_head() {
    return pres_out_head;
  }
  public void setPres_out_head(Double pres_out_head) {
    this.pres_out_head = pres_out_head;
  }
  public Double getPres_in_final() {
    return pres_in_final;
  }
  public void setPres_in_final(Double pres_in_final) {
    this.pres_in_final = pres_in_final;
  }
  public Integer getPomp_a() {
    return pomp_a;
  }
  public void setPomp_a(Integer pomp_a) {
    this.pomp_a = pomp_a;
  }
  public Double getPomp_b() {
    return pomp_b;
  }
  public void setPomp_b(Double pomp_b) {
    this.pomp_b = pomp_b;
  }
  public Integer getPomp_head() {
    return pomp_head;
  }
  public void setPomp_head(Integer pomp_head) {
    this.pomp_head = pomp_head;
  }
  public Integer getHead_end() {
    return head_end;
  }
  public void setHead_end(Integer head_end) {
    this.head_end = head_end;
  }
  public Integer getHead_loss() {
    return head_loss;
  }
  public void setHead_loss(Integer head_loss) {
    this.head_loss = head_loss;
  }
  public Double getLambda() {
    return lambda;
  }
  public void setLambda(Double lambda) {
    this.lambda = lambda;
  }
  public Double getReynolds_number() {
    return reynolds_number;
  }
  public void setReynolds_number(Double reynolds_number) {
    this.reynolds_number = reynolds_number;
  }
  public Double getSquare() {
    return square;
  }
  public void setSquare(Double square) {
    this.square = square;
  }
  public Double getPumping() {
    return pumping;
  }
  public void setPumping(Double pumping) {
    this.pumping = pumping;
  }
}
