package pipeline.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service
public class DataBaseService {
  @Autowired
  private JdbcTemplate jdbcTemplate;
  public void createTable() {
    String sql = "CREATE TABLE IF NOT EXISTS loginUser (" +
        "id SERIAL PRIMARY KEY," +
        "login varchar(50) NOT NULL," +
        "password varchar(50) NOT NULL);";
    jdbcTemplate.execute(sql);
    String sql_1 = "CREATE TABLE IF NOT EXISTS parametr (" +
        "id SERIAL PRIMARY KEY," +
        "login varchar(50) NOT NULL," +
        "nameSave varchar(50) NOT NULL," +
        "lineLength integer NOT NULL," +
        "pointStart double precision NOT NULL," +
        "pointEnd double precision NOT NULL," +
        "diameter double precision NOT NULL," +
        "density double precision NOT NULL," +
        "pumpBrand varchar(50) NOT NULL);";
    jdbcTemplate.execute(sql_1);
  }
}
