package lt.vu.psk.service;

import lt.vu.psk.interceptor.LoggedAction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@LoggedAction
@ConditionalOnProperty(name = "features.player.calculation", havingValue = "random")
public class RandomCalculationService implements CalculationService {
    private final Map<Long, String> calculationResults = new ConcurrentHashMap<>();

    @Async
    @Override
    public void calculateWhetherUserWillMakeTheNBA(Long id) {
        try {
            Thread.sleep(5000);
            calculationResults.put(id, (id%2==0) ? "Yes" : "No");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCalculationResult(Long id) {
        return calculationResults.getOrDefault(id, "Calculation not finished");
    }
}
