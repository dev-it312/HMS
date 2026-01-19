# Guide : Ajouter un nouveau thème

## Architecture du système de thèmes

Le système de thèmes est conçu pour être extensible et facile à maintenir. Les thèmes sont définis dans le code par les développeurs et apparaissent automatiquement dans le menu utilisateur.

## Étapes pour ajouter un nouveau thème

### 1. Définir le thème dans `config/themes.ts`

```typescript
// 1a. Ajouter le type dans ThemeType
export type ThemeType = 'light' | 'dark' | 'high-contrast' | 'VOTRE_THEME';

// 1b. Ajouter la configuration dans availableThemes
export const availableThemes: ThemeConfig[] = [
  // ... thèmes existants
  {
    id: 'VOTRE_THEME',
    name: 'Nom affiché',
    icon: '🎨', // Emoji ou icône
    description: 'Description du thème',
  },
];
```

**Paramètres :**
- `id` : Identifiant unique du thème (utilisé dans le code)
- `name` : Nom affiché dans le menu
- `icon` : Emoji ou symbole affiché à côté du nom
- `description` : Description (s'affiche au survol)

### 2. Ajouter les variables CSS dans `index.css`

```css
[data-theme="VOTRE_THEME"] {
  /* Backgrounds */
  --bg-primary: #couleur;
  --bg-secondary: #couleur;
  
  /* Textes */
  --text-primary: #couleur;
  --text-secondary: #couleur;
  --border-color: #couleur;
  
  /* Header */
  --header-bg: #couleur;
  --header-hover: #couleur;
  --header-text: #couleur;
  --header-logo-bg: #couleur;
  
  /* Dropdown */
  --dropdown-bg: #couleur;
  --dropdown-text: #couleur;
  --dropdown-hover: #couleur;
  --dropdown-active: #couleur;
  
  /* Cards & Components */
  --card-bg: #couleur;
  --card-shadow: rgba(...);
  --button-primary: #couleur;
  --button-primary-hover: #couleur;
  --button-text: #couleur;
  
  /* Links */
  --link-color: #couleur;
  --link-hover: #couleur;
}
```

### 3. C'est tout ! 🎉

Le thème apparaîtra automatiquement dans le menu "Paramètres > Apparence".

## Exemples de thèmes

### Thème saisonnier

```typescript
{
  id: 'halloween',
  name: 'Halloween',
  icon: '🎃',
  description: 'Thème spécial Halloween',
}
```

```css
[data-theme="halloween"] {
  --bg-primary: #1a0f0f;
  --bg-secondary: #2a1a1a;
  --text-primary: #ff6600;
  --text-secondary: #cc5500;
  --button-primary: #ff6600;
  --button-primary-hover: #ff8833;
  /* ... autres variables */
}
```

## Variables CSS disponibles

Voici toutes les variables que vous devez définir pour chaque thème :

| Variable | Usage |
|----------|-------|
| `--bg-primary` | Fond principal de la page |
| `--bg-secondary` | Fond secondaire (formulaires, cards) |
| `--text-primary` | Couleur principale du texte |
| `--text-secondary` | Couleur secondaire du texte |
| `--border-color` | Couleur des bordures |
| `--header-bg` | Fond de la barre de navigation |
| `--header-hover` | Fond au survol dans la navigation |
| `--header-text` | Texte de la navigation |
| `--header-logo-bg` | Fond du conteneur du logo |
| `--dropdown-bg` | Fond des menus déroulants |
| `--dropdown-text` | Texte des menus déroulants |
| `--dropdown-hover` | Fond au survol dans les menus |
| `--dropdown-active` | Fond de l'option active |
| `--card-bg` | Fond des cartes et conteneurs |
| `--card-shadow` | Ombre des cartes (rgba) |
| `--button-primary` | Couleur des boutons principaux |
| `--button-primary-hover` | Couleur au survol des boutons |
| `--button-text` | Texte des boutons |
| `--link-color` | Couleur des liens |
| `--link-hover` | Couleur au survol des liens |

## Bonnes pratiques

### ✅ À faire :
- Définir **toutes** les variables CSS pour chaque thème
- Tester le contraste pour l'accessibilité
- Utiliser des noms de thèmes descriptifs
- Documenter les couleurs spéciales

### ❌ À éviter :
- Laisser des variables CSS non définies
- Utiliser des couleurs avec trop peu de contraste
- Modifier directement les couleurs dans les composants (toujours utiliser les variables CSS)

## Utilisation programmatique

### Dans un composant React :

```typescript
import { useTheme } from '../contexts/ThemeContext';
import { getThemeConfig } from '../config/themes';

function MyComponent() {
  const { theme, setTheme } = useTheme();
  
  // Obtenir la config du thème actuel
  const currentThemeConfig = getThemeConfig(theme);
  console.log(currentThemeConfig?.name); // "Light Mode", "Dark Mode", etc.
  
  // Changer de thème
  setTheme('high-contrast');
  
  // Alterner entre les thèmes
  toggleTheme();
  
  return <div>Thème actuel : {theme}</div>;
}
```

### Récupérer tous les thèmes :

```typescript
import { availableThemes } from '../config/themes';

// Tous les thèmes disponibles
console.log(availableThemes);
```

## Thèmes existants

1. **Light Mode** (`light`) - Thème clair par défaut
2. **Dark Mode** (`dark`) - Thème sombre pour réduire la fatigue oculaire  
3. **Couleurs Vives** (`high-contrast`) - Thème à fort contraste avec couleurs vives

## Migration depuis l'ancienne version

Si vous aviez du code utilisant directement `'light'` ou `'dark'`, il continue de fonctionner sans modification grâce à la rétrocompatibilité du système.
