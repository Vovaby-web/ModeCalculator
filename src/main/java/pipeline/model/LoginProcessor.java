package pipeline.model;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.services.LoggedService;
@Component
@RequestScope
public class LoginProcessor {
  private final LoggedService loggedService;
  private String username;
  private String password;
  public LoginProcessor(LoggedService loggedService) {
    this.loggedService = loggedService;
  }
  public boolean login() {
    String username = this.getUsername();
    String password = this.getPassword();
    boolean loginResult = false;
    if ("1".equals(username) && "1".equals(password)) {
      loginResult = true;
      loggedService.setUsername(username);
    }
    return loginResult;
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
}
