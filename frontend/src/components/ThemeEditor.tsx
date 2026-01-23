import React, { useState, useEffect } from 'react';
import '../styles/ThemeEditor.css';

interface CustomTheme {
  id: string;
  name: string;
  variables: {
    [key: string]: string;
  };
}

interface ThemeEditorProps {
  theme: CustomTheme | null;
  onSave: (theme: CustomTheme) => void;
  onCancel: () => void;
  onDelete?: () => void;
}

const DEFAULT_VARIABLES = {
  '--bg-primary': '#f6f8fa',
  '--bg-secondary': '#ffffff',
  '--text-primary': '#222222',
  '--text-secondary': '#555555',
  '--border-color': '#e1e4e8',
  '--header-bg': '#1976d2',
  '--header-hover': '#1565c0',
  '--header-text': '#ffffff',
  '--header-logo-bg': '#ffffff',
  '--dropdown-bg': '#ffffff',
  '--dropdown-text': '#1976d2',
  '--dropdown-hover': '#e3f2fd',
  '--dropdown-active': '#bbdefb',
  '--card-bg': '#ffffff',
  '--card-shadow': 'rgba(0, 0, 0, 0.1)',
  '--button-primary': '#1976d2',
  '--button-primary-hover': '#1565c0',
  '--button-text': '#ffffff',
  '--link-color': '#1976d2',
  '--link-hover': '#1565c0',
};

const VARIABLE_LABELS: { [key: string]: string } = {
  '--bg-primary': 'Arrière-plan principal',
  '--bg-secondary': 'Arrière-plan secondaire',
  '--text-primary': 'Texte principal',
  '--text-secondary': 'Texte secondaire',
  '--border-color': 'Couleur des bordures',
  '--header-bg': 'Fond de l\'en-tête',
  '--header-hover': 'Survol en-tête',
  '--header-text': 'Texte en-tête',
  '--header-logo-bg': 'Fond logo en-tête',
  '--dropdown-bg': 'Fond menu déroulant',
  '--dropdown-text': 'Texte menu déroulant',
  '--dropdown-hover': 'Survol menu déroulant',
  '--dropdown-active': 'Actif menu déroulant',
  '--card-bg': 'Fond des cartes',
  '--card-shadow': 'Ombre des cartes',
  '--button-primary': 'Bouton principal',
  '--button-primary-hover': 'Survol bouton principal',
  '--button-text': 'Texte bouton',
  '--link-color': 'Couleur des liens',
  '--link-hover': 'Survol des liens',
};

const ThemeEditor: React.FC<ThemeEditorProps> = ({ theme, onSave, onCancel, onDelete }) => {
  const [themeName, setThemeName] = useState(theme?.name || '');
  const [variables, setVariables] = useState(theme?.variables || DEFAULT_VARIABLES);

  useEffect(() => {
    if (theme) {
      setThemeName(theme.name);
      setVariables(theme.variables);
    }
  }, [theme]);

  const handleVariableChange = (varName: string, value: string) => {
    setVariables(prev => ({
      ...prev,
      [varName]: value
    }));
  };

  const handleSave = () => {
    if (!themeName.trim()) {
      alert('Veuillez entrer un nom pour le thème');
      return;
    }

    const savedTheme: CustomTheme = {
      id: theme?.id || `custom-${Date.now()}`,
      name: themeName,
      variables
    };

    onSave(savedTheme);
  };

  const getContrast = (color1: string, color2: string): string => {
    const getLuminance = (color: string): number => {
      // Conversion simplifiée
      const rgb = color.match(/^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i);
      if (!rgb) return 0;
      
      const r = parseInt(rgb[1], 16) / 255;
      const g = parseInt(rgb[2], 16) / 255;
      const b = parseInt(rgb[3], 16) / 255;
      
      const [rs, gs, bs] = [r, g, b].map(c => 
        c <= 0.03928 ? c / 12.92 : Math.pow((c + 0.055) / 1.055, 2.4)
      );
      
      return 0.2126 * rs + 0.7152 * gs + 0.0722 * bs;
    };

    const l1 = getLuminance(color1);
    const l2 = getLuminance(color2);
    const ratio = (Math.max(l1, l2) + 0.05) / (Math.min(l1, l2) + 0.05);
    
    if (ratio >= 7) return '✓ Excellent (AAA)';
    if (ratio >= 4.5) return '✓ Bon (AA)';
    if (ratio >= 3) return '⚠ Moyen';
    return '✗ Faible';
  };

  return (
    <div className="theme-editor">
      <div className="editor-header">
        <h2>{theme ? 'Modifier le Thème' : 'Créer un Nouveau Thème'}</h2>
        <input
          type="text"
          value={themeName}
          onChange={(e) => setThemeName(e.target.value)}
          placeholder="Nom du thème"
          className="theme-name-input"
        />
      </div>

      <div className="editor-content">
        <div className="color-variables-section">
          <h3>Variables de Couleur</h3>
          <div className="variables-grid">
            {Object.entries(DEFAULT_VARIABLES).map(([varName]) => (
              <div key={varName} className="variable-item">
                <label htmlFor={varName}>{VARIABLE_LABELS[varName] || varName}</label>
                <div className="color-input-group">
                  <input
                    type="color"
                    id={varName}
                    value={(variables as any)[varName] || (DEFAULT_VARIABLES as any)[varName]}
                    onChange={(e) => handleVariableChange(varName, e.target.value)}
                    className="color-picker"
                  />
                  <input
                    type="text"
                    value={(variables as any)[varName] || (DEFAULT_VARIABLES as any)[varName]}
                    onChange={(e) => handleVariableChange(varName, e.target.value)}
                    className="color-text-input"
                    placeholder="#000000"
                  />
                </div>
              </div>
            ))}
          </div>
        </div>

        <div className="preview-section">
          <h3>Aperçu du Thème</h3>
          <div className="theme-preview-large" style={{
            backgroundColor: variables['--bg-primary'],
            color: variables['--text-primary'],
            border: `2px solid ${variables['--border-color']}`
          }}>
            <div className="preview-header" style={{
              backgroundColor: variables['--header-bg'],
              color: variables['--header-text']
            }}>
              <h4>En-tête de Navigation</h4>
            </div>
            
            <div className="preview-content" style={{
              backgroundColor: variables['--bg-secondary']
            }}>
              <div className="preview-card" style={{
                backgroundColor: variables['--card-bg'],
                boxShadow: `0 2px 8px ${variables['--card-shadow']}`
              }}>
                <h5>Carte de Contenu</h5>
                <p style={{ color: variables['--text-primary'] }}>
                  Ceci est un exemple de texte principal.
                </p>
                <p style={{ color: variables['--text-secondary'] }}>
                  Ceci est un exemple de texte secondaire.
                </p>
                <a href="#" style={{ color: variables['--link-color'] }}>
                  Lien exemple
                </a>
              </div>
              
              <div className="preview-buttons">
                <button style={{
                  backgroundColor: variables['--button-primary'],
                  color: variables['--button-text']
                }}>
                  Bouton Principal
                </button>
                <button style={{
                  backgroundColor: variables['--button-primary-hover'],
                  color: variables['--button-text']
                }}>
                  Bouton Survol
                </button>
              </div>

              <div className="preview-dropdown" style={{
                backgroundColor: variables['--dropdown-bg'],
                color: variables['--dropdown-text'],
                border: `1px solid ${variables['--border-color']}`
              }}>
                <div>Menu Déroulant</div>
                <div style={{ backgroundColor: variables['--dropdown-hover'] }}>
                  Option en survol
                </div>
              </div>
            </div>
          </div>

          <div className="contrast-checker">
            <h4>Analyse du Contraste</h4>
            <div className="contrast-results">
              <div className="contrast-item">
                <span>Texte principal / Fond principal:</span>
                <span className="contrast-value">
                  {getContrast(variables['--text-primary'], variables['--bg-primary'])}
                </span>
              </div>
              <div className="contrast-item">
                <span>Texte en-tête / Fond en-tête:</span>
                <span className="contrast-value">
                  {getContrast(variables['--header-text'], variables['--header-bg'])}
                </span>
              </div>
              <div className="contrast-item">
                <span>Texte bouton / Fond bouton:</span>
                <span className="contrast-value">
                  {getContrast(variables['--button-text'], variables['--button-primary'])}
                </span>
              </div>
              <div className="contrast-item">
                <span>Texte secondaire / Fond principal:</span>
                <span className="contrast-value">
                  {getContrast(variables['--text-secondary'], variables['--bg-primary'])}
                </span>
              </div>
            </div>
            <p className="contrast-info">
              <small>
                ✓ AAA (ratio ≥7:1) = Excellent | ✓ AA (ratio ≥4.5:1) = Bon | 
                ⚠ Moyen (ratio ≥3:1) | ✗ Faible (ratio &lt;3:1)
              </small>
            </p>
          </div>
        </div>
      </div>

      <div className="editor-actions">
        <button className="btn-primary" onClick={handleSave}>
          ✓ OK
        </button>
        <button className="btn-secondary" onClick={onCancel}>
          ✗ Annuler
        </button>
        {onDelete && (
          <button className="btn-danger" onClick={onDelete}>
            🗑️ Supprimer
          </button>
        )}
      </div>
    </div>
  );
};

export default ThemeEditor;
