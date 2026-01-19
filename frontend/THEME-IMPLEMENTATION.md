# Système de Thèmes - Documentation

## ✨ Vue d'ensemble

Le système de thèmes permet aux développeurs d'ajouter facilement de nouveaux thèmes. Les utilisateurs peuvent ensuite choisir parmi les thèmes disponibles via le menu "Paramètres > Apparence".

## 🎨 Thèmes disponibles

1. **☀️ Light Mode** - Thème clair par défaut
2. **🌙 Dark Mode** - Thème sombre pour réduire la fatigue oculaire
3. **🎨 Couleurs Vives** - Thème à fort contraste avec couleurs éclatantes (magenta, cyan, vert, jaune)

## 📁 Architecture

### Fichiers principaux

**Code source :**
- `src/config/themes.ts` - Configuration centralisée de tous les thèmes
- `src/contexts/ThemeContext.tsx` - Contexte React pour gérer le thème actif
- `src/index.css` - Variables CSS pour chaque thème

**Composants :**
- `src/components/Header.tsx` - Menu généré dynamiquement depuis la config

**Documentation :**
- `README-THEMES.md` - Guide rapide pour ajouter un thème
- `GUIDE-AJOUT-THEMES.md` - Documentation détaillée

## 🚀 Ajouter un nouveau thème

**3 étapes simples :**

1. **Modifier `config/themes.ts`** - Ajouter le type et la configuration
2. **Ajouter CSS dans `index.css`** - Définir les 21 variables CSS
3. **Terminé!** - Le thème apparaît automatiquement dans le menu

Voir [README-THEMES.md](README-THEMES.md) pour un guide détaillé avec exemples.

## 🎯 Fonctionnalités

✅ **Configuration centralisée** - Tous les thèmes définis dans `themes.ts`  
✅ **Génération dynamique du menu** - Les nouveaux thèmes apparaissent automatiquement  
✅ **Type-safe** - TypeScript valide les IDs de thèmes  
✅ **Persistance** - Le thème choisi est sauvegardé dans localStorage  
✅ **Transitions fluides** - Changements de couleurs animés (0.3s)  
✅ **Variables CSS** - Système unifié pour tous les composants

## 💡 Points importants

- **Les thèmes sont ajoutés dans le code** par les développeurs
- **Les utilisateurs choisissent** parmi les thèmes disponibles
- **Pas de création de thème par l'utilisateur** - évite la complexité inutile
- **Tous les thèmes dans `availableThemes` sont visibles** dans le menu

## 📊 Variables CSS

Chaque thème doit définir 21 variables CSS réparties en 5 catégories :

1. **Backgrounds & Textes** (5 variables)
2. **Header** (4 variables)
3. **Dropdowns** (4 variables)
4. **Components** (5 variables)
5. **Links** (2 variables)

Voir [GUIDE-AJOUT-THEMES.md](GUIDE-AJOUT-THEMES.md) pour la liste complète.

## 🔧 Utilisation programmatique

```typescript
import { useTheme } from '../contexts/ThemeContext';
import { availableThemes, getThemeConfig } from '../config/themes';

function MyComponent() {
  const { theme, setTheme, toggleTheme } = useTheme();
  
  // Changer de thème
  setTheme('dark');
  
  // Récupérer la config
  const config = getThemeConfig(theme);
  
  // Lister tous les thèmes
  console.log(availableThemes);
  
  return <div>Thème : {config?.name}</div>;
}
```

## ✅ Avantages

| Avantage | Description |
|----------|-------------|
| **Simple** | 3 étapes pour ajouter un thème |
| **Maintenable** | Configuration centralisée |
| **Extensible** | Facile d'ajouter des thèmes |
| **Type-safe** | Validation TypeScript |
| **Performant** | Variables CSS natives |

---

Pour plus de détails, consultez :
- [README-THEMES.md](README-THEMES.md) - Guide rapide
- [GUIDE-AJOUT-THEMES.md](GUIDE-AJOUT-THEMES.md) - Documentation complète
