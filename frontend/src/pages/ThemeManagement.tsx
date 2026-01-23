import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import ThemeEditor from '../components/ThemeEditor';
import '../styles/ThemeManagement.css';

interface CustomTheme {
  id: string;
  name: string;
  variables: {
    [key: string]: string;
  };
}

const ThemeManagement: React.FC = () => {
  const navigate = useNavigate();
  const [customThemes, setCustomThemes] = useState<CustomTheme[]>(() => {
    const saved = localStorage.getItem('customThemes');
    return saved ? JSON.parse(saved) : [];
  });
  const [isCreating, setIsCreating] = useState(false);
  const [editingTheme, setEditingTheme] = useState<CustomTheme | null>(null);

  const handleCreateNewTheme = () => {
    setIsCreating(true);
    setEditingTheme(null);
  };

  const handleEditTheme = (themeToEdit: CustomTheme) => {
    setEditingTheme(themeToEdit);
    setIsCreating(false);
  };

  const handleSaveTheme = (savedTheme: CustomTheme) => {
    let updatedThemes: CustomTheme[];
    
    if (editingTheme) {
      // Mode édition
      updatedThemes = customThemes.map(t => 
        t.id === editingTheme.id ? savedTheme : t
      );
    } else {
      // Mode création
      updatedThemes = [...customThemes, savedTheme];
    }
    
    setCustomThemes(updatedThemes);
    localStorage.setItem('customThemes', JSON.stringify(updatedThemes));
    setIsCreating(false);
    setEditingTheme(null);
  };

  const handleDeleteTheme = (themeId: string) => {
    if (window.confirm('Êtes-vous sûr de vouloir supprimer ce thème ?')) {
      const updatedThemes = customThemes.filter(t => t.id !== themeId);
      setCustomThemes(updatedThemes);
      localStorage.setItem('customThemes', JSON.stringify(updatedThemes));
      if (editingTheme?.id === themeId) {
        setEditingTheme(null);
        setIsCreating(false);
      }
    }
  };

  const handleCancel = () => {
    setIsCreating(false);
    setEditingTheme(null);
  };

  return (
    <div className="theme-management-container">
      <div className="theme-header-with-back">
        <h1>Gestion des Thèmes</h1>
        <button className="btn-back" onClick={() => navigate(-1)}>
          ← Retour
        </button>
      </div>
      
      {!isCreating && !editingTheme ? (
        <div className="theme-list-view">
          <div className="theme-actions">
            <button className="btn-primary" onClick={handleCreateNewTheme}>
              ➕ Créer un Nouveau Thème
            </button>
          </div>

          <div className="themes-grid">
            <h2>Thèmes Personnalisés</h2>
            {customThemes.length === 0 ? (
              <p className="no-themes">Aucun thème personnalisé. Créez-en un !</p>
            ) : (
              <div className="custom-themes-list">
                {customThemes.map(customTheme => (
                  <div key={customTheme.id} className="theme-card">
                    <h3>{customTheme.name}</h3>
                    <div className="theme-preview" style={{
                      background: customTheme.variables['--bg-primary'],
                      color: customTheme.variables['--text-primary'],
                      border: `2px solid ${customTheme.variables['--border-color']}`
                    }}>
                      <div className="preview-header" style={{
                        background: customTheme.variables['--header-bg'],
                        color: customTheme.variables['--header-text']
                      }}>
                        Header
                      </div>
                      <div className="preview-content">
                        <p>Texte principal</p>
                        <p style={{ color: customTheme.variables['--text-secondary'] }}>
                          Texte secondaire
                        </p>
                        <button style={{
                          background: customTheme.variables['--button-primary'],
                          color: customTheme.variables['--button-text']
                        }}>
                          Bouton
                        </button>
                      </div>
                    </div>
                    <div className="theme-card-actions">
                      <button onClick={() => handleEditTheme(customTheme)}>
                        ✏️ Modifier
                      </button>
                      <button onClick={() => handleDeleteTheme(customTheme.id)} className="btn-danger">
                        🗑️ Supprimer
                      </button>
                    </div>
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      ) : (
        <ThemeEditor
          theme={editingTheme}
          onSave={handleSaveTheme}
          onCancel={handleCancel}
          onDelete={editingTheme ? () => handleDeleteTheme(editingTheme.id) : undefined}
        />
      )}
    </div>
  );
};

export default ThemeManagement;
