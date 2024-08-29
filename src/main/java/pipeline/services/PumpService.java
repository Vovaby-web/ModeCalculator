package pipeline.services;
import org.springframework.stereotype.Service;
import pipeline.models.Pump;

import java.util.List;
@Service
public class PumpService {
  public List<Pump> getAllPumps() {
    // Здесь вы можете получить данные из базы данных, например
    return List.of(new Pump("brand1", "НМ1250-260"),
        new Pump("brand2", "НМ710-280"));
  }
}
