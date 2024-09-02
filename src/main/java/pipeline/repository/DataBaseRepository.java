package pipeline.repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pipeline.models.LoggedComponent;
import pipeline.models.ModesComponent;
import pipeline.models.SelectSaveParameter;

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
  public List<LoggedComponent> findLogin() {
    String sql = "SELECT * FROM loginUser";
    RowMapper<LoggedComponent> rowMapper = (r, i) -> {
      LoggedComponent rowObject = new LoggedComponent();
      rowObject.setUsername(r.getString("login"));
      rowObject.setPassword(r.getString("password"));
      return rowObject;
    };
    return jdbc.query(sql, rowMapper);
  }
  public void saveBaseParameter(ModesComponent modesComponent, String login) {
    String sql = "INSERT INTO parameters (" +
       "login, nameSave, lineLength, pointStart, pointEnd, diameter, density, viscosity, pumpBrand) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbc.update(sql, login,
        modesComponent.getNameSave(),
        modesComponent.getLineLength(),
        modesComponent.getPointStart(),
        modesComponent.getPointEnd(),
        modesComponent.getDiameter(),
        modesComponent.getDensity(),
        modesComponent.getViscosity(),
        modesComponent.getPumpBrand());
  }
  public List<SelectSaveParameter> findUsername(String l) {
    String sql = "SELECT id,nameSave FROM parameters WHERE login = '" + l + "'";
    RowMapper<SelectSaveParameter> rowMapper = (r, i) -> {
      SelectSaveParameter rowObject = new SelectSaveParameter();
      rowObject.setValue(r.getInt("id"));
      rowObject.setName(r.getString("nameSave"));
      return rowObject;
    };
    return jdbc.query(sql, rowMapper);
  }
  public ModesComponent loadParameters(Integer id, String l) {
    String sql = "SELECT * FROM parameters" +
        " WHERE id = " + id +
        " AND login = '" + l + "';";
    RowMapper<ModesComponent> rowMapper = (r, i) -> {
      ModesComponent rowObject = new ModesComponent();
      rowObject.setNameSave(r.getString("nameSave"));
      rowObject.setLineLength(r.getInt("lineLength"));
      rowObject.setPointStart(r.getDouble("pointStart"));
      rowObject.setPointEnd(r.getDouble("pointEnd"));
      rowObject.setDiameter(r.getDouble("diameter"));
      rowObject.setDensity(r.getDouble("density"));
      rowObject.setViscosity(r.getDouble("viscosity"));
      rowObject.setPumpBrand(r.getString("pumpBrand"));
      return rowObject;
    };
    try {
      return jdbc.queryForObject(sql, rowMapper);
    } catch (EmptyResultDataAccessException e) {
      // Возвращаем null или бросаем кастомное исключение
      return null; // или можно бросать собственное исключение
    }
  }
}
