import React, { createContext, useContext, useState, useEffect } from 'react';
import type { ReactNode } from 'react';
import type { ThemeType } from '../config/themes';
import { DEFAULT_THEME, availableThemes } from '../config/themes';

interface ThemeContextType {
  theme: ThemeType;
  setTheme: (theme: ThemeType) => void;
  toggleTheme: () => void;
}

const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

export const ThemeProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [theme, setThemeState] = useState<ThemeType>(() => {
    // Récupérer le thème sauvegardé ou utiliser le thème par défaut
    const savedTheme = localStorage.getItem('theme') as ThemeType;
    // Vérifier que le thème sauvegardé existe dans la config
    const isValidTheme = savedTheme && availableThemes.some(t => t.id === savedTheme);
    return isValidTheme ? savedTheme : DEFAULT_THEME;
  });

  useEffect(() => {
    // Appliquer le thème au document
    document.documentElement.setAttribute('data-theme', theme);
    localStorage.setItem('theme', theme);
  }, [theme]);

  const setTheme = (newTheme: ThemeType) => {
    setThemeState(newTheme);
  };

  const toggleTheme = () => {
    // Cycle à travers les thèmes sélectionnables par l'utilisateur
    const currentIndex = availableThemes.findIndex(t => t.id === theme);
    const nextIndex = (currentIndex + 1) % availableThemes.length;
    setThemeState(availableThemes[nextIndex].id);
  };

  return (
    <ThemeContext.Provider value={{ theme, setTheme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

export const useTheme = () => {
  const context = useContext(ThemeContext);
  if (context === undefined) {
    throw new Error('useTheme must be used within a ThemeProvider');
  }
  return context;
};
