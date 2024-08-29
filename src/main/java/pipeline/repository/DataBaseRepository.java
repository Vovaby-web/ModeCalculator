package pipeline.repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pipeline.models.LoggedComponent;

import java.util.List;
@Repository
public class DataBaseRepository {
  private final JdbcTemplate jdbc;
  public DataBaseRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }
  public void saveBaseUser(LoggedComponent loggedComponent) {
    String sql = "INSERT INTO loginUser (login,password) VALUES (?, ?)";
    jdbc.update(sql, loggedComponent.getUsername(), loggedComponent.getPassword());
  }
  public List<LoggedComponent> findAll() {
    String sql = "SELECT * FROM loginUser";
    RowMapper<LoggedComponent> rowMapper = (r, i) -> {
      LoggedComponent rowObject = new LoggedComponent();
      rowObject.setUsername(r.getString("login"));
      rowObject.setPassword(r.getString("password"));
      return rowObject;
    };
    return jdbc.query(sql, rowMapper);
  }
}

