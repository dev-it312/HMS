// Configuration centralisée des thèmes
// Pour ajouter un nouveau thème : 
// 1. Ajouter le type dans ThemeType
// 2. Ajouter la configuration dans availableThemes
// 3. Ajouter les variables CSS dans index.css
// Le nouveau thème apparaîtra automatiquement dans le menu

export type ThemeType = 'light' | 'dark' | 'high-contrast';
// Pour ajouter un thème : export type ThemeType = 'light' | 'dark' | 'high-contrast' | 'ocean';

export interface ThemeConfig {
  id: ThemeType;
  name: string;
  icon: string;
  description: string;
}

// Tous les thèmes disponibles - tous sont affichés dans le menu utilisateur
export const availableThemes: ThemeConfig[] = [
  {
    id: 'light',
    name: 'Light Mode',
    icon: '☀️',
    description: 'Thème clair classique',
  },
  {
    id: 'dark',
    name: 'Dark Mode',
    icon: '🌙',
    description: 'Thème sombre pour réduire la fatigue oculaire',
  },
  {
    id: 'high-contrast',
    name: 'Couleurs Vives',
    icon: '🎨',
    description: 'Thème à fort contraste avec couleurs vives',
  },
  // Exemple : Ajouter un nouveau thème
  // {
  //   id: 'ocean',
  //   name: 'Océan',
  //   icon: '🌊',
  //   description: 'Thème inspiré de l\'océan',
  // },
];

// Fonction helper pour obtenir la config d'un thème
export const getThemeConfig = (themeId: ThemeType): ThemeConfig | undefined => {
  return availableThemes.find(theme => theme.id === themeId);
};

// Thème par défaut
export const DEFAULT_THEME: ThemeType = 'light';
