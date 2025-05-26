import React, { useState } from 'react';
import JerseyNumberEditDialog from './JerseyNumberEditDialog';

const PlayerList = ({ players, onEdit, onConflict }) => {
  const [selectedPlayer, setSelectedPlayer] = useState(null);

  const handleEditClick = (player) => {
    setSelectedPlayer(player);
  };

  const handleClose = () => {
    setSelectedPlayer(null);
  };

  const handleSave = async (updatedPlayer) => {
    try {
      await onEdit(updatedPlayer);
      setSelectedPlayer(null);
    } catch (error) {
      throw error;
    }
  };

  return (
    <div className="player-list">
      <h2>Players</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Height</th>
            <th>Weight</th>
            <th>Country</th>
            <th>Jersey Number</th>
            <th>Version</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {players.map(player => (
            <tr key={player.id}>
              <td>{player.id}</td>
              <td>{player.firstName} {player.lastName}</td>
              <td>{player.age}</td>
              <td>{player.height} cm</td>
              <td>{player.weight} kg</td>
              <td>{player.country}</td>
              <td>{player.jerseyNumber}</td>
              <td>{player.version}</td>
              <td>
                <button onClick={() => handleEditClick(player)}>Edit Jersey Number</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {selectedPlayer && (
        <JerseyNumberEditDialog
          player={selectedPlayer}
          onClose={handleClose}
          onSave={handleSave}
          onConflict={onConflict}
        />
      )}
    </div>
  );
};

export default PlayerList; 