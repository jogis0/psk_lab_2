package lt.vu.psk.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@ConditionalOnProperty(name = "features.player.calculation", havingValue = "fixed")
public class FixedCalculationService implements CalculationService {
    private final Map<Long, String> calculationResults = new ConcurrentHashMap<>();

    @Async
    @Override
    public void calculateWhetherUserWillMakeTheNBA(Long id) {
        try {
            Thread.sleep(3000);
            calculationResults.put(id, "Yes");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCalculationResult(Long id) {
        return calculationResults.getOrDefault(id, "Calculation not finished");
    }
}
