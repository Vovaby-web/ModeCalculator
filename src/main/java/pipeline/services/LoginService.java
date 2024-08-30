package pipeline.services;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pipeline.models.LoggedComponent;
import pipeline.repository.DataBaseRepository;

import java.util.List;
@Service
@RequestScope
public class LoginService {
  private final LoggedComponent loggedComponent;
  private final DataBaseRepository dataBaseRepository;
  public LoginService(LoggedComponent loggedComponent, DataBaseRepository dataBaseRepository) {
    this.loggedComponent = loggedComponent;
    this.dataBaseRepository = dataBaseRepository;
  }
  public boolean login() {
    String username = loggedComponent.getUsername();
    String password = loggedComponent.getPassword();
    List<LoggedComponent> user=dataBaseRepository.findLogin();
    for (LoggedComponent comp:user){
      if (comp.getUsername().equals(username)&&comp.getPassword().equals(password)){
        loggedComponent.setUsername(username);
        return true;
      }else if (comp.getUsername().equals(username)&&!comp.getPassword().equals(password)){
        loggedComponent.setUsername(username);
        return false;
      }
    }
    dataBaseRepository.saveBaseUser(getLoggedComponent());
    return true;
  }
  public void setLoggedComponent(String username,String password) {
    loggedComponent.setUsername(username);
    loggedComponent.setPassword(password);
  }
  public LoggedComponent getLoggedComponent() {
    return loggedComponent;
  }
}
