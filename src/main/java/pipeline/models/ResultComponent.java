package pipeline.models;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ResultComponent {
  private Double pres_out_head;      // Давление на головной станции
  private Double pres_in_final;      // Давление в конечном пункте
  private Integer pomp_a;            // a - коэффициент насоса
  private Double pomp_b;             // b - коэффициент насоса
  private Integer head_main;         // Напор на головной станции
  private Integer head_end;          // Напор в конечном пункте
  private Integer head_booster;      // Напор подпорного агрегата
  private Double lambda;             // Коэффициент гидравлического сопротивления
  private Double reynolds_number;    // Число Рейнольдса
  private Double square;             // Площадь сечения трубопровода
  private Integer performance;       // Производительность
  private Integer totalHead;         // Общий напор станции
  private Integer totalEnd;          // Общий на конечном пункте
  private Double iforq;
  public final Integer[] limit_perf = {1250, 2500, 3600};
  public final Integer[] pump_a = {331, 282, 304};
  public final Double[] pump_b = {0.451, 0.792, 0.579};
  private List<Integer> chart_pomp;      // График напора насоса
  private List<Integer> chart_net;       // График напора сети
  private List<Integer> chart_perf;      // График производительности сети
  public void clear(){
    pres_out_head=0.0;
    pres_in_final=0.0;
    pomp_a=0;
    pomp_b=0.0;
    head_main=0;
    head_end=0;
    head_booster=0;
    lambda=0.0;
    reynolds_number=0.0;
    square=0.0;
    performance=0;
    iforq=0.0;
    totalHead=0;
    totalEnd=0;
  }
  public List<Integer> getChart_pomp() {
    return chart_pomp;
  }
  public void setChart_pomp(List<Integer> chart_pomp) {
    this.chart_pomp = chart_pomp;
  }
  public List<Integer> getChart_net() {
    return chart_net;
  }
  public void setChart_net(List<Integer> chart_net) {
    this.chart_net = chart_net;
  }
  public List<Integer> getChart_perf() {
    return chart_perf;
  }
  public void setChart_perf(List<Integer> chart_perf) {
    this.chart_perf = chart_perf;
  }
  public Integer getTotalHead() {
    return totalHead;
  }
  public void setTotalHead(Integer totalHead) {
    this.totalHead = totalHead;
  }
  public Integer getTotalEnd() {
    return totalEnd;
  }
  public void setTotalEnd(Integer totalEnd) {
    this.totalEnd = totalEnd;
  }
  public Double getIforq() {
    return iforq;
  }
  public void setIforq(Double iforq) {
    this.iforq = iforq;
  }
  public Integer getPerformance() {
    return performance;
  }
  public void setPerformance(Integer performance) {
    this.performance = performance;
  }
  public Integer[] getPump_a() {
    return pump_a;
  }
  public Double[] getPump_b() {
    return pump_b;
  }
  public Double getPres_out_head() {
    return pres_out_head;
  }
  public String getPres_out_headStr() {
    return String.format("%.2f",pres_out_head);
  }
  public void setPres_out_head(Double pres_out_head) {
    this.pres_out_head = pres_out_head;
  }
  public Double getPres_in_final() {
    return pres_in_final;
  }
  public String getPres_in_finalStr() {
    return String.format("%.2f",pres_in_final);
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
  public Integer getHead_main() {
    return head_main;
  }
  public void setHead_main(Integer head_main) {
    this.head_main = head_main;
  }
  public Integer getHead_end() {
    return head_end;
  }
  public void setHead_end(Integer head_end) {
    this.head_end = head_end;
  }
  public Integer getHead_booster() {
    return head_booster;
  }
  public void setHead_booster(Integer head_booster) {
    this.head_booster = head_booster;
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
  public String getSquareStr() {
    return String.format("%.2f",square);
  }

  public void setSquare(Double square) {
    this.square = square;
  }
}
