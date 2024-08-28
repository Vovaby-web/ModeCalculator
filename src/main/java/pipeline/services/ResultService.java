package pipeline.services;
import org.springframework.stereotype.Component;
@Component
public class ResultService {
 private Double pres_in_head_1;
 private Double pres_out_head_1;
  private Double pres_in_head_2;
  private Double pres_out_head_2;
 private Double pres_in_final_1;
  public Double getPres_in_head_1() {
    return pres_in_head_1;
  }
  public void setPres_in_head_1(Double pres_in_head_1) {
    this.pres_in_head_1 = pres_in_head_1;
  }
  public Double getPres_out_head_1() {
    return pres_out_head_1;
  }
  public void setPres_out_head_1(Double pres_out_head_1) {
    this.pres_out_head_1 = pres_out_head_1;
  }
  public Double getPres_in_head_2() {
    return pres_in_head_2;
  }
  public void setPres_in_head_2(Double pres_in_head_2) {
    this.pres_in_head_2 = pres_in_head_2;
  }
  public Double getPres_out_head_2() {
    return pres_out_head_2;
  }
  public void setPres_out_head_2(Double pres_out_head_2) {
    this.pres_out_head_2 = pres_out_head_2;
  }
  public Double getPres_in_final_1() {
    return pres_in_final_1;
  }
  public void setPres_in_final_1(Double pres_in_final_1) {
    this.pres_in_final_1 = pres_in_final_1;
  }
}
