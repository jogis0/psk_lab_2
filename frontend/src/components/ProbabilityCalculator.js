import React, { useState } from 'react';

const ProbabilityCalculator = () => {
  const [userId, setUserId] = useState('');
  const [result, setResult] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleStartCalculation = async () => {
    if (!userId) return;
    
    try {
      setIsLoading(true);
      const response = await fetch(`/players/calculate/${userId}`, {
        method: 'POST',
      });
      const data = await response.text();
      setResult(data);
    } catch (error) {
      setResult('Error starting calculation: ' + error.message);
    } finally {
      setIsLoading(false);
    }
  };

  const handleGetResult = async () => {
    if (!userId) return;
    
    try {
      setIsLoading(true);
      const response = await fetch(`/players/result/${userId}`);
      const data = await response.text();
      setResult(data);
    } catch (error) {
      setResult('Error getting result: ' + error.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="probability-calculator">
      <h2>Will the user make it to the NBA league?</h2>
      <div className="result-display">
        {result}
      </div>
      <div className="calculator-inputs">
        <input
          type="text"
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          placeholder="Enter User ID"
          className="user-id-input"
        />
        <div className="calculator-buttons">
          <button 
            onClick={handleStartCalculation}
            disabled={isLoading || !userId}
            className="start-button"
          >
            Start Calculation
          </button>
          <button 
            onClick={handleGetResult}
            disabled={isLoading || !userId}
            className="result-button"
          >
            Get Result
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProbabilityCalculator; 