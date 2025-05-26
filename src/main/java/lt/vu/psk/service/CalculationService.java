package lt.vu.psk.service;

public interface CalculationService {
    void calculateWhetherUserWillMakeTheNBA(Long id);
    String getCalculationResult(Long id);
}
