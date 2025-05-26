import React, { useState } from 'react';

const JerseyNumberEditDialog = ({ player, onClose, onSave, onConflict }) => {
  const [jerseyNumber, setJerseyNumber] = useState(player.jerseyNumber);
  const [conflictData, setConflictData] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await onSave({ ...player, jerseyNumber: parseInt(jerseyNumber) });
    } catch (error) {
      if (error.status === 500) {
        const currentPlayer = await onConflict(player.id);
        setConflictData({
          original: player.jerseyNumber,
          current: currentPlayer.jerseyNumber,
          yourVersion: parseInt(jerseyNumber),
          currentVersion: currentPlayer.version
        });
      }
    }
  };

  const handleVersionSelect = async (selectedNumber) => {
    try {
      await onSave({
        ...player,
        jerseyNumber: selectedNumber,
        version: conflictData.currentVersion
      });
      onClose();
    } catch (error) {
      console.error('Error updating jersey number:', error);
    }
  };

  if (conflictData) {
    return (
      <div className="dialog-overlay">
        <div className="dialog-content">
          <h3>Version Conflict Detected</h3>
          <p>Another user has modified this player's jersey number. Please choose which version to keep:</p>
          <div className="version-options">
            <div className="version-option">
              <h4>Original Version</h4>
              <p>Jersey Number: {conflictData.original}</p>
              <button onClick={() => handleVersionSelect(conflictData.original)}>
                Use Original
              </button>
            </div>
            <div className="version-option">
              <h4>Current Version</h4>
              <p>Jersey Number: {conflictData.current}</p>
              <button onClick={() => handleVersionSelect(conflictData.current)}>
                Use Current
              </button>
            </div>
            <div className="version-option">
              <h4>Your Version</h4>
              <p>Jersey Number: {conflictData.yourVersion}</p>
              <button onClick={() => handleVersionSelect(conflictData.yourVersion)}>
                Use Your Version
              </button>
            </div>
          </div>
          <div className="dialog-buttons">
            <button type="button" onClick={onClose}>Cancel</button>
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="dialog-overlay">
      <div className="dialog-content">
        <h3>Edit Jersey Number for {player.firstName} {player.lastName}</h3>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <input
              type="number"
              value={jerseyNumber}
              onChange={(e) => setJerseyNumber(e.target.value)}
              placeholder="Jersey Number"
              required
            />
          </div>
          <div className="dialog-buttons">
            <button type="submit">Save</button>
            <button type="button" onClick={onClose}>Cancel</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default JerseyNumberEditDialog; 