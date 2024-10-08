package pipeline.models;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Objects;
@Component
@SessionScope
public class LoggedComponent {
  private String username;
  private String password;
  public LoggedComponent() {
  }
  public LoggedComponent(String username, String password) {
    this.username = username;
    this.password = password;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LoggedComponent that = (LoggedComponent) o;
    return Objects.equals(username, that.username);
  }
  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }
}
