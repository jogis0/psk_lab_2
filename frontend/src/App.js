import React, { useState, useEffect } from 'react';
import './App.css';
import './components/PlayerStyles.css';
import PlayerForm from './components/PlayerForm';
import PlayerList from './components/PlayerList';
import ProbabilityCalculator from './components/ProbabilityCalculator';

function App() {
  const [players, setPlayers] = useState([]);

  const fetchPlayers = async () => {
    try {
      const response = await fetch('http://localhost:8080/players');
      const data = await response.json();
      setPlayers(data);
    } catch (error) {
      console.error('Error fetching players:', error);
    }
  };

  useEffect(() => {
    fetchPlayers();
  }, []);

  const handleCreatePlayer = async (playerData) => {
    try {
      const response = await fetch('http://localhost:8080/players', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(playerData),
      });
      if (response.ok) {
        await fetchPlayers();
      }
    } catch (error) {
      console.error('Error creating player:', error);
    }
  };

  const handleUpdatePlayer = async (playerData) => {
    try {
      const response = await fetch('/players', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(playerData),
      });
      
      if (!response.ok) {
        const error = new Error('Update failed');
        error.status = response.status;
        throw error;
      }
      
      await fetchPlayers();
    } catch (error) {
      throw error;
    }
  };

  const handleConflict = async (playerId) => {
    try {
      const response = await fetch(`/players/${playerId}`);
      if (!response.ok) {
        throw new Error('Failed to fetch current player data');
      }
      return await response.json();
    } catch (error) {
      console.error('Error fetching current player data:', error);
      throw error;
    }
  };

  return (
    <div className="App">
      <header className="App-header">
        <h1>Player Management System</h1>
      </header>
      <main>
        <ProbabilityCalculator />
        <PlayerForm 
          onSubmit={handleCreatePlayer}
          initialData={null}
        />
        <PlayerList 
          players={players}
          onEdit={handleUpdatePlayer}
          onConflict={handleConflict}
        />
      </main>
    </div>
  );
}

export default App;
