# Comment ajouter un nouveau thème

Le système de thèmes permet aux **développeurs** d'ajouter facilement de nouveaux thèmes dans le code. Les utilisateurs peuvent ensuite choisir parmi les thèmes disponibles via le menu "Paramètres > Apparence".

## 🚀 Ajouter un thème en 3 étapes

### Étape 1 : Modifier `src/config/themes.ts`

```typescript
// 1a. Ajouter votre thème dans le type
export type ThemeType = 'light' | 'dark' | 'high-contrast' | 'ocean';

// 1b. Ajouter la configuration
export const availableThemes: ThemeConfig[] = [
  // ... thèmes existants
  {
    id: 'ocean',
    name: 'Océan',
    icon: '🌊',
    description: 'Thème inspiré de l\'océan',
  },
];
```

### Étape 2 : Ajouter les CSS dans `src/index.css`

```css
[data-theme="ocean"] {
  /* Backgrounds */
  --bg-primary: #001f3f;
  --bg-secondary: #003459;
  
  /* Textes */
  --text-primary: #e0f7fa;
  --text-secondary: #80deea;
  --border-color: #006064;
  
  /* Header */
  --header-bg: #00838f;
  --header-hover: #006064;
  --header-text: #ffffff;
  --header-logo-bg: #003459;
  
  /* Dropdown */
  --dropdown-bg: #003459;
  --dropdown-text: #80deea;
  --dropdown-hover: #004d61;
  --dropdown-active: #00838f;
  
  /* Cards & Components */
  --card-bg: #003459;
  --card-shadow: rgba(0, 131, 143, 0.3);
  --button-primary: #00acc1;
  --button-primary-hover: #0097a7;
  --button-text: #ffffff;
  
  /* Links */
  --link-color: #80deea;
  --link-hover: #b2ebf2;
}
```

### Étape 3 : C'est tout! ✨

Le thème apparaît automatiquement dans le menu utilisateur.

## 📋 Variables CSS obligatoires

Chaque thème doit définir ces 21 variables :

| Catégorie | Variables |
|-----------|-----------|
| **Backgrounds** | `--bg-primary`, `--bg-secondary` |
| **Textes** | `--text-primary`, `--text-secondary`, `--border-color` |
| **Header** | `--header-bg`, `--header-hover`, `--header-text`, `--header-logo-bg` |
| **Dropdowns** | `--dropdown-bg`, `--dropdown-text`, `--dropdown-hover`, `--dropdown-active` |
| **Components** | `--card-bg`, `--card-shadow`, `--button-primary`, `--button-primary-hover`, `--button-text` |
| **Links** | `--link-color`, `--link-hover` |

## 🎨 Exemples de thèmes

### Thème Forêt
```typescript
{ id: 'forest', name: 'Forêt', icon: '🌲', description: 'Thème vert naturel' }
```

```css
[data-theme="forest"] {
  --bg-primary: #1b2a1f;
  --text-primary: #e8f5e9;
  --header-bg: #2e7d32;
  --button-primary: #4caf50;
  /* ... */
}
```

### Thème Sunset
```typescript
{ id: 'sunset', name: 'Coucher de Soleil', icon: '🌅', description: 'Tons chauds orangés' }
```

```css
[data-theme="sunset"] {
  --bg-primary: #1a0a0a;
  --text-primary: #ffe4e1;
  --header-bg: #d84315;
  --button-primary: #ff6f00;
  /* ... */
}
```

## ✅ Thèmes actuels

1. **☀️ Light Mode** - Thème clair par défaut
2. **🌙 Dark Mode** - Thème sombre
3. **🎨 Couleurs Vives** - Fort contraste avec couleurs éclatantes

## 💡 Conseils

- **Contraste** : Assurez un ratio minimum de 4.5:1 entre texte et fond
- **Cohérence** : Utilisez une palette harmonieuse
- **Test** : Vérifiez sur tous les composants (Header, Forms, Dashboards)

## 🔧 Utilisation dans le code

```typescript
import { useTheme } from '../contexts/ThemeContext';
import { getThemeConfig } from '../config/themes';

function MyComponent() {
  const { theme, setTheme } = useTheme();
  
  // Changer de thème
  setTheme('ocean');
  
  // Récupérer la config
  const config = getThemeConfig(theme);
  console.log(config?.name); // "Océan"
  
  return <div>Thème actuel : {theme}</div>;
}
```

---

**Note** : Les utilisateurs ne peuvent pas créer leurs propres thèmes. Seuls les développeurs peuvent ajouter des thèmes en modifiant le code source.
