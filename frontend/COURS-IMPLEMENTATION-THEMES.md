--# 📚 Cours Complet : Implémentation d'un Système de Thèmes en React

## Pour Débutants en JavaScript et React

---

## 📋 Table des Matières

1. [Introduction et Concepts de Base](#1-introduction-et-concepts-de-base)
2. [Architecture du Système de Thèmes](#2-architecture-du-système-de-thèmes)
3. [Étape 1 : Configuration des Thèmes (themes.ts)](#3-étape-1--configuration-des-thèmes)
4. [Étape 2 : Context API et Gestion d'État](#4-étape-2--context-api-et-gestion-détat)
5. [Étape 3 : Variables CSS et Styling](#5-étape-3--variables-css-et-styling)
6. [Étape 4 : Menu de Sélection dans le Header](#6-étape-4--menu-de-sélection-dans-le-header)
7. [Étape 5 : Page de Gestion des Thèmes](#7-étape-5--page-de-gestion-des-thèmes)
8. [Étape 6 : Éditeur de Thèmes avec Prévisualisation](#8-étape-6--éditeur-de-thèmes-avec-prévisualisation)
9. [Exercices Pratiques](#9-exercices-pratiques)
10. [Résumé et Points Clés](#10-résumé-et-points-clés)

---

## 1. Introduction et Concepts de Base

### 🎯 Qu'est-ce qu'un Système de Thèmes ?

Un système de thèmes permet aux utilisateurs de **changer l'apparence visuelle** d'une application (couleurs, contrastes, etc.) selon leurs préférences. Pensez au mode sombre/clair de votre téléphone !

### 📚 Concepts JavaScript/React à Connaître

#### 1.1 Les Variables en JavaScript

```javascript
// const : pour les valeurs qui ne changent pas
const PI = 3.14159;

// let : pour les valeurs qui peuvent changer
let compteur = 0;
compteur = compteur + 1; // ✅ OK

// Variables d'objet
const utilisateur = {
  nom: "Alice",
  age: 25
};
```

#### 1.2 Les Fonctions Fléchées (Arrow Functions)

```javascript
// Ancienne syntaxe
function addition(a, b) {
  return a + b;
}

// Nouvelle syntaxe (arrow function)
const addition = (a, b) => {
  return a + b;
};

// Version courte (si une seule expression)
const addition = (a, b) => a + b;
```

#### 1.3 TypeScript : Types et Interfaces

```typescript
// Type simple
type CouleurTheme = 'light' | 'dark' | 'high-contrast';

// Interface (contrat pour un objet)
interface Utilisateur {
  id: number;
  nom: string;
  email: string;
}

// Utilisation
const user: Utilisateur = {
  id: 1,
  nom: "Alice",
  email: "alice@example.com"
};
```

#### 1.4 React Hooks de Base

```tsx
import { useState, useEffect } from 'react';

// useState : gérer l'état local d'un composant
const [compteur, setCompteur] = useState(0);
// compteur = valeur actuelle
// setCompteur = fonction pour changer la valeur

// useEffect : exécuter du code quand quelque chose change
useEffect(() => {
  console.log("Le compteur a changé :", compteur);
}, [compteur]); // [compteur] = dépendance
```

---

## 2. Architecture du Système de Thèmes

### 🏗️ Vue d'Ensemble

```
┌─────────────────────────────────────────────────────┐
│                    Application                       │
│                                                      │
│  ┌────────────────────────────────────────────┐    │
│  │         ThemeProvider (Context)             │    │
│  │  ┌──────────────────────────────────────┐  │    │
│  │  │   État Global : theme = "dark"       │  │    │
│  │  │   Fonction : setTheme(newTheme)      │  │    │
│  │  └──────────────────────────────────────┘  │    │
│  └────────────────────────────────────────────┘    │
│           ↓           ↓           ↓                 │
│     ┌─────────┐ ┌──────────┐ ┌──────────────┐     │
│     │ Header  │ │  Home    │ │ ThemeManager │     │
│     │         │ │          │ │              │     │
│     │ useTheme│ │ useTheme │ │  useTheme    │     │
│     └─────────┘ └──────────┘ └──────────────┘     │
└─────────────────────────────────────────────────────┘
```

### 📁 Structure des Fichiers

```
frontend/src/
├── config/
│   └── themes.ts              # Configuration des thèmes
├── contexts/
│   └── ThemeContext.tsx       # Context API pour partager le thème
├── components/
│   ├── Header.tsx             # Menu de sélection des thèmes
│   └── ThemeEditor.tsx        # Éditeur de thèmes personnalisés
├── pages/
│   └── ThemeManagement.tsx    # Page de gestion
└── styles/
    ├── ThemeManagement.css
    └── ThemeEditor.css
```

---

## 3. Étape 1 : Configuration des Thèmes

### 📝 Fichier : `config/themes.ts`

#### Pourquoi ce fichier ?
Centraliser toutes les informations sur les thèmes disponibles pour faciliter leur gestion.

```typescript
// 1️⃣ Définir les types de thèmes possibles
export type ThemeType = 'light' | 'dark' | 'high-contrast';

// EXPLICATION :
// ThemeType peut SEULEMENT être l'une de ces 3 valeurs
// Impossible d'écrire : const theme: ThemeType = 'blue'; ❌
```

#### Interface ThemeConfig

```typescript
// 2️⃣ Définir la structure d'un thème
export interface ThemeConfig {
  id: ThemeType;           // Identifiant unique
  name: string;            // Nom affiché à l'utilisateur
  icon: string;            // Emoji ou icône
  description: string;     // Description du thème
}

// EXPLICATION :
// Une interface = un contrat
// Tout objet ThemeConfig DOIT avoir ces 4 propriétés
```

#### Tableau des Thèmes Disponibles

```typescript
// 3️⃣ Créer la liste de tous les thèmes
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
];

// EXPLICATION :
// ThemeConfig[] = tableau d'objets ThemeConfig
// Chaque objet respecte la structure définie plus haut
```

#### Fonctions Utilitaires

```typescript
// 4️⃣ Fonction pour trouver un thème par son ID
export const getThemeConfig = (themeId: ThemeType): ThemeConfig | undefined => {
  return availableThemes.find(theme => theme.id === themeId);
};

// EXPLICATION LIGNE PAR LIGNE :
// - themeId: ThemeType = paramètre d'entrée (ex: 'dark')
// - : ThemeConfig | undefined = type de retour (soit un ThemeConfig, soit undefined)
// - .find() = méthode qui cherche dans le tableau
// - theme => theme.id === themeId = fonction fléchée de comparaison

// EXEMPLE D'UTILISATION :
const darkConfig = getThemeConfig('dark');
console.log(darkConfig?.name); // "Dark Mode"
```

#### Thème par Défaut

```typescript
// 5️⃣ Définir le thème utilisé au premier chargement
export const DEFAULT_THEME: ThemeType = 'light';

// EXPLICATION :
// Quand un utilisateur arrive pour la première fois,
// on utilise ce thème par défaut
```

### 🎓 Concepts Appris

- ✅ **Types TypeScript** : Restreindre les valeurs possibles
- ✅ **Interfaces** : Définir la structure d'un objet
- ✅ **Tableaux typés** : `ThemeConfig[]`
- ✅ **Fonctions fléchées** avec paramètres typés
- ✅ **Méthodes de tableau** : `.find()`

---

## 4. Étape 2 : Context API et Gestion d'État

### 📝 Fichier : `contexts/ThemeContext.tsx`

#### Qu'est-ce que le Context API ?

Imaginez une **boîte magique** accessible par tous les composants de votre application. Au lieu de passer des données de parent en enfant en enfant (prop drilling), le Context permet de **partager des données globalement**.

```
SANS Context (Prop Drilling) ❌
App → Header → Nav → ThemeButton
 ↓      ↓       ↓        ↓
theme  theme  theme   theme (fastidieux!)

AVEC Context ✅
App (ThemeProvider)
 ↓
ThemeButton (useTheme) ← accès direct!
```

#### Créer le Context

```typescript
import { createContext, useContext, useState, useEffect } from 'react';
import type { ReactNode } from 'react';
import type { ThemeType } from '../config/themes';
import { DEFAULT_THEME, availableThemes } from '../config/themes';

// 1️⃣ Définir le type des données dans le Context
interface ThemeContextType {
  theme: ThemeType;                    // Thème actuel
  setTheme: (theme: ThemeType) => void; // Fonction pour changer
  toggleTheme: () => void;             // Fonction pour alterner
}

// 2️⃣ Créer le Context
const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

// EXPLICATION :
// createContext() crée la "boîte magique"
// <ThemeContextType | undefined> = peut contenir ThemeContextType ou être vide
// undefined au début car pas encore de valeur
```

#### Le Provider (Fournisseur)

```typescript
// 3️⃣ Créer le composant Provider
export const ThemeProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  
  // EXPLICATION DES PARAMÈTRES :
  // React.FC = React Functional Component
  // { children: ReactNode } = accepte des composants enfants
  // ({ children }) = destructuration des props

  // 4️⃣ État local pour stocker le thème actuel
  const [theme, setThemeState] = useState<ThemeType>(() => {
    // Cette fonction s'exécute UNE SEULE FOIS au montage
    const savedTheme = localStorage.getItem('theme') as ThemeType;
    const isValidTheme = savedTheme && availableThemes.some(t => t.id === savedTheme);
    return isValidTheme ? savedTheme : DEFAULT_THEME;
  });

  // EXPLICATION DÉTAILLÉE :
  // - localStorage.getItem('theme') : récupère du navigateur
  // - as ThemeType : conversion de type (string → ThemeType)
  // - .some() : vérifie si au moins un élément correspond
  // - isValidTheme ? savedTheme : DEFAULT_THEME : opérateur ternaire
  //   Si valide → utiliser savedTheme, sinon → DEFAULT_THEME
```

#### useEffect pour la Persistence

```typescript
  // 5️⃣ Sauvegarder le thème à chaque changement
  useEffect(() => {
    // Appliquer le thème au document HTML
    document.documentElement.setAttribute('data-theme', theme);
    
    // Sauvegarder dans le navigateur
    localStorage.setItem('theme', theme);
  }, [theme]);

  // EXPLICATION :
  // useEffect se déclenche quand [theme] change
  // document.documentElement = balise <html>
  // setAttribute('data-theme', 'dark') → <html data-theme="dark">
  // localStorage.setItem = sauvegarde dans le navigateur
```

#### Fonctions de Manipulation

```typescript
  // 6️⃣ Fonction pour changer le thème
  const setTheme = (newTheme: ThemeType) => {
    setThemeState(newTheme);
  };

  // 7️⃣ Fonction pour alterner entre les thèmes
  const toggleTheme = () => {
    const currentIndex = availableThemes.findIndex(t => t.id === theme);
    const nextIndex = (currentIndex + 1) % availableThemes.length;
    setThemeState(availableThemes[nextIndex].id);
  };

  // EXPLICATION toggleTheme :
  // - findIndex() : trouve la position actuelle (0, 1, ou 2)
  // - (currentIndex + 1) : passe au suivant
  // - % availableThemes.length : opérateur modulo pour boucler
  //   Exemple : (2 + 1) % 3 = 0 (revient au début)
```

#### Retourner le Provider

```typescript
  // 8️⃣ Fournir les valeurs aux composants enfants
  return (
    <ThemeContext.Provider value={{ theme, setTheme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

// EXPLICATION :
// value={{ theme, setTheme, toggleTheme }} : données partagées
// {children} : affiche tous les composants enfants
```

#### Hook Personnalisé useTheme

```typescript
// 9️⃣ Créer un hook pour utiliser le Context facilement
export const useTheme = () => {
  const context = useContext(ThemeContext);
  
  if (context === undefined) {
    throw new Error('useTheme must be used within a ThemeProvider');
  }
  
  return context;
};

// EXPLICATION :
// useContext(ThemeContext) : récupère les données du Context
// if (context === undefined) : vérification de sécurité
// throw new Error() : lève une erreur si mal utilisé
// return context : retourne { theme, setTheme, toggleTheme }
```

### 🎓 Concepts Appris

- ✅ **Context API** : Partage de données global
- ✅ **useState avec fonction** : Initialisation paresseuse
- ✅ **useEffect** : Effets de bord (sauvegarder, appliquer)
- ✅ **localStorage** : Persistence des données
- ✅ **Hooks personnalisés** : Simplifier l'utilisation
- ✅ **Gestion d'erreurs** : `throw new Error()`

---

## 5. Étape 3 : Variables CSS et Styling

### 📝 Fichier : `index.css`

#### Qu'est-ce que les Variables CSS ?

Les **variables CSS** (ou custom properties) permettent de stocker des valeurs réutilisables.

```css
/* Définir une variable */
:root {
  --ma-couleur: #1976d2;
}

/* Utiliser la variable */
.bouton {
  background-color: var(--ma-couleur);
}
```

#### Avantages des Variables CSS
1. ✅ **Cohérence** : Une seule source de vérité
2. ✅ **Maintenabilité** : Changer une fois, appliqué partout
3. ✅ **Thèmes dynamiques** : Surcharger selon `[data-theme]`

#### Structure des Variables

```css
/* THÈME LIGHT (par défaut) */
:root {
  /* Couleurs de fond */
  --bg-primary: #f6f8fa;      /* Fond principal de la page */
  --bg-secondary: #ffffff;     /* Fond des cartes, formulaires */
  
  /* Couleurs de texte */
  --text-primary: #222222;     /* Texte principal */
  --text-secondary: #555555;   /* Texte moins important */
  
  /* Bordures */
  --border-color: #e1e4e8;     /* Couleur des bordures */
  
  /* En-tête (Header) */
  --header-bg: #1976d2;        /* Fond de la navigation */
  --header-hover: #1565c0;     /* Couleur au survol */
  --header-text: #ffffff;      /* Texte du header */
  --header-logo-bg: #ffffff;   /* Fond du logo */
  
  /* Menus déroulants (Dropdown) */
  --dropdown-bg: #ffffff;
  --dropdown-text: #1976d2;
  --dropdown-hover: #e3f2fd;
  --dropdown-active: #bbdefb;
  
  /* Cartes et Composants */
  --card-bg: #ffffff;
  --card-shadow: rgba(0, 0, 0, 0.1);
  --button-primary: #1976d2;
  --button-primary-hover: #1565c0;
  --button-text: #ffffff;
  
  /* Liens */
  --link-color: #1976d2;
  --link-hover: #1565c0;
}
```

#### Surcharge pour le Thème Dark

```css
/* THÈME DARK */
[data-theme="dark"] {
  /* ⚡ Ces variables REMPLACENT celles de :root */
  --bg-primary: #0d1117;
  --bg-secondary: #161b22;
  --text-primary: #e6edf3;
  --text-secondary: #8b949e;
  --border-color: #30363d;
  
  --header-bg: #1f2937;
  --header-hover: #374151;
  --header-text: #e6edf3;
  --header-logo-bg: #161b22;
  
  --dropdown-bg: #1f2937;
  --dropdown-text: #60a5fa;
  --dropdown-hover: #374151;
  --dropdown-active: #1e3a8a;
  
  --card-bg: #161b22;
  --card-shadow: rgba(0, 0, 0, 0.3);
  --button-primary: #2563eb;
  --button-primary-hover: #1d4ed8;
  --button-text: #ffffff;
  
  --link-color: #60a5fa;
  --link-hover: #93c5fd;
}
```

#### Comment ça fonctionne ?

```
1. Chargement de la page
   → :root définit les variables par défaut (light)

2. JavaScript applique data-theme="dark"
   → <html data-theme="dark">

3. CSS détecte [data-theme="dark"]
   → Les variables sont SURCHARGÉES

4. Tous les éléments utilisant var(--bg-primary)
   → Utilisent automatiquement la nouvelle valeur!
```

#### Utilisation dans les Composants

```css
/* Dans n'importe quel fichier CSS */
.my-component {
  background-color: var(--bg-primary);
  color: var(--text-primary);
  border: 1px solid var(--border-color);
}

/* ✨ MAGIE : Ces couleurs changent automatiquement
   selon le thème actif! */
```

### 🎓 Concepts Appris

- ✅ **Variables CSS** : `--nom-variable`
- ✅ **Fonction var()** : Utiliser une variable
- ✅ **Sélecteur d'attribut** : `[data-theme="dark"]`
- ✅ **Cascade CSS** : Surcharge des valeurs
- ✅ **rgba()** : Couleurs avec transparence

---

## 6. Étape 4 : Menu de Sélection dans le Header

### 📝 Fichier : `components/Header.tsx`

#### Objectif
Ajouter un menu déroulant "Paramètres → Affichage → Thèmes" avec la liste des thèmes disponibles.

#### Code Complet Expliqué

```tsx
import React from 'react';
import { Link } from 'react-router-dom';
import { useTheme } from '../contexts/ThemeContext';
import '../styles/Header.css';

const Header: React.FC = () => {
  // 1️⃣ Récupérer le thème actuel et la fonction pour le changer
  const { theme, setTheme } = useTheme();

  // EXPLICATION :
  // useTheme() retourne { theme: 'dark', setTheme: function, ... }
  // Destructuration : on extrait seulement theme et setTheme

  return (
    <>
      <div className="logo-container">
        <img src="/LogoBanner.jpg" alt="Hospital Management System Logo" className="logo-image" />
      </div>
      <header>
        <nav className="navbar">
          <ul>
            <li><Link to="/">Accueil</Link></li>
            <li><a href="#about">À propos</a></li>
            
            {/* ... autres liens ... */}
            
            {/* 2️⃣ Menu Paramètres */}
            <li className="dropdown">
              <a href="#">Paramètres ▼</a>
              <ul className="dropdown-menu">
                
                {/* 3️⃣ Sous-menu Affichage */}
                <li className="dropdown-submenu">
                  <a href="#">Affichage ▸</a>
                  <ul className="dropdown-submenu-content">
                    
                    {/* 4️⃣ Sous-sous-menu Thèmes */}
                    <li className="dropdown-submenu">
                      <a href="#">Thèmes ▸</a>
                      <ul className="dropdown-submenu-content">
                        
                        {/* 5️⃣ Option Light Mode */}
                        <li>
                          <a 
                            href="#" 
                            onClick={(e) => { 
                              e.preventDefault(); 
                              setTheme('light'); 
                            }}
                            className={theme === 'light' ? 'active' : ''}
                          >
                            ☀️ Light Mode
                          </a>
                        </li>

                        {/* EXPLICATION DÉTAILLÉE :
                        
                        href="#" : Lien factice (évite rechargement)
                        
                        onClick={(e) => { ... }} : Fonction au clic
                          - (e) : événement de clic
                          - e.preventDefault() : empêche le comportement par défaut
                          - setTheme('light') : change le thème
                        
                        className={...} : Classe conditionnelle
                          - theme === 'light' ? 'active' : ''
                          - Si thème actuel = light → ajoute classe 'active'
                          - Sinon → chaîne vide (pas de classe)
                        */
                        
                        {/* 6️⃣ Option Dark Mode */}
                        <li>
                          <a 
                            href="#" 
                            onClick={(e) => { e.preventDefault(); setTheme('dark'); }}
                            className={theme === 'dark' ? 'active' : ''}
                          >
                            🌙 Dark Mode
                          </a>
                        </li>
                        
                        {/* 7️⃣ Option High Contrast */}
                        <li>
                          <a 
                            href="#" 
                            onClick={(e) => { e.preventDefault(); setTheme('high-contrast'); }}
                            className={theme === 'high-contrast' ? 'active' : ''}
                          >
                            🎨 High Contrast
                          </a>
                        </li>
                        
                        {/* 8️⃣ Séparateur visuel */}
                        <li className="separator"></li>
                        
                        {/* 9️⃣ Lien vers la gestion des thèmes */}
                        <li>
                          <Link to="/theme-management">⚙️ Theme Management</Link>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
          </ul>
        </nav>
      </header>
    </>
  );
};

export default Header;
```

#### CSS pour les Sous-menus

```css
/* Menu principal */
.dropdown-menu {
  display: none;
  position: absolute;
  top: 100%;
  left: 0;
  background: var(--dropdown-bg);
  min-width: 180px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.08);
  border-radius: 4px;
  z-index: 10;
}

/* Afficher au survol */
.dropdown:hover .dropdown-menu {
  display: block;
}

/* Sous-menu (imbriqué) */
.dropdown-submenu-content {
  display: none;
  position: absolute;
  top: 0;           /* Aligné en haut */
  left: 100%;       /* À droite du parent */
  background: var(--dropdown-bg);
  min-width: 200px;
  box-shadow: 0 4px 16px rgba(0,0,0,0.08);
  border-radius: 4px;
  z-index: 11;      /* Au-dessus du parent */
}

/* Afficher le sous-menu au survol */
.dropdown-submenu:hover > .dropdown-submenu-content {
  display: block;
}

/* Indicateur visuel (flèche) */
.dropdown-submenu > a::after {
  content: ' ▸';
  float: right;
  margin-left: 0.5rem;
}

/* Style de l'option active */
.dropdown-submenu-content a.active {
  background: var(--dropdown-active);
  font-weight: 600;
}
```

### 🎓 Concepts Appris

- ✅ **Événements React** : `onClick={(e) => {...}}`
- ✅ **e.preventDefault()** : Empêcher le comportement par défaut
- ✅ **Classes conditionnelles** : `className={condition ? 'yes' : 'no'}`
- ✅ **Menus imbriqués** : Structure `<ul>` dans `<li>`
- ✅ **CSS position absolute** : Positionnement des sous-menus
- ✅ **Pseudo-éléments** : `::after` pour les flèches

---

## 7. Étape 5 : Page de Gestion des Thèmes

### 📝 Fichier : `pages/ThemeManagement.tsx`

#### Objectif
Créer une page pour **afficher, créer, modifier et supprimer** des thèmes personnalisés.

#### Structure de Données

```typescript
interface CustomTheme {
  id: string;           // Identifiant unique (ex: "custom-1234567890")
  name: string;         // Nom donné par l'utilisateur
  variables: {          // Toutes les variables CSS
    [key: string]: string;  // Clé = nom variable, Valeur = couleur
  };
}

// EXEMPLE D'OBJET :
const monTheme: CustomTheme = {
  id: "custom-1674567890",
  name: "Mon Thème Bleu",
  variables: {
    "--bg-primary": "#001f3f",
    "--text-primary": "#ffffff",
    // ... 19 autres variables
  }
};
```

#### État Local

```typescript
const ThemeManagement: React.FC = () => {
  const navigate = useNavigate();

  // 1️⃣ État : Liste des thèmes personnalisés
  const [customThemes, setCustomThemes] = useState<CustomTheme[]>(() => {
    const saved = localStorage.getItem('customThemes');
    return saved ? JSON.parse(saved) : [];
  });

  // EXPLICATION :
  // useState avec fonction : initialisation paresseuse
  // localStorage.getItem('customThemes') : récupère la chaîne JSON
  // JSON.parse(saved) : convertit JSON → objet JavaScript
  // saved ? ... : [] : si rien trouvé, tableau vide

  // 2️⃣ État : Mode création
  const [isCreating, setIsCreating] = useState(false);

  // 3️⃣ État : Thème en cours d'édition
  const [editingTheme, setEditingTheme] = useState<CustomTheme | null>(null);

  // EXPLICATION :
  // null = aucun thème en édition
  // CustomTheme = un thème spécifique en édition
```

#### Fonctions de Gestion

```typescript
  // 4️⃣ Créer un nouveau thème
  const handleCreateNewTheme = () => {
    setIsCreating(true);
    setEditingTheme(null);
  };

  // EXPLICATION :
  // Passe en mode création
  // Réinitialise l'édition (pas d'édition en cours)

  // 5️⃣ Modifier un thème existant
  const handleEditTheme = (themeToEdit: CustomTheme) => {
    setEditingTheme(themeToEdit);
    setIsCreating(false);
  };

  // EXPLICATION :
  // Charge le thème à éditer
  // Sort du mode création

  // 6️⃣ Sauvegarder un thème (création ou édition)
  const handleSaveTheme = (savedTheme: CustomTheme) => {
    let updatedThemes: CustomTheme[];
    
    if (editingTheme) {
      // MODE ÉDITION : Remplacer le thème existant
      updatedThemes = customThemes.map(t => 
        t.id === editingTheme.id ? savedTheme : t
      );
    } else {
      // MODE CRÉATION : Ajouter à la fin
      updatedThemes = [...customThemes, savedTheme];
    }
    
    // Mettre à jour l'état et le localStorage
    setCustomThemes(updatedThemes);
    localStorage.setItem('customThemes', JSON.stringify(updatedThemes));
    
    // Réinitialiser les modes
    setIsCreating(false);
    setEditingTheme(null);
  };

  // EXPLICATION DÉTAILLÉE :

  // .map(t => ...) : Parcourt chaque thème
  //   - Si t.id === editingTheme.id : remplacer par savedTheme
  //   - Sinon : garder le thème original (t)

  // [...customThemes, savedTheme] : Spread operator
  //   - Copie tous les éléments de customThemes
  //   - Ajoute savedTheme à la fin

  // JSON.stringify(updatedThemes) : Convertit objet → JSON
  //   Car localStorage ne stocke que des chaînes

  // 7️⃣ Supprimer un thème
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

  // EXPLICATION :

  // window.confirm() : Boîte de dialogue de confirmation
  //   Retourne true si OK, false si Annuler

  // .filter(t => t.id !== themeId) : Garde tous les thèmes
  //   SAUF celui avec l'id à supprimer

  // editingTheme?.id : Optional chaining
  //   - Si editingTheme est null : retourne undefined (pas d'erreur)
  //   - Sinon : retourne editingTheme.id

  // 8️⃣ Annuler la création/édition
  const handleCancel = () => {
    setIsCreating(false);
    setEditingTheme(null);
  };
```

#### Rendu Conditionnel

```tsx
  return (
    <div className="theme-management-container">
      <div className="theme-header-with-back">
        <h1>Gestion des Thèmes</h1>
        <button className="btn-back" onClick={() => navigate(-1)}>
          ← Retour
        </button>
      </div>
      
      {/* 9️⃣ Affichage conditionnel */}
      {!isCreating && !editingTheme ? (
        // VUE LISTE : ni en création, ni en édition
        <div className="theme-list-view">
          <div className="theme-actions">
            <button className="btn-primary" onClick={handleCreateNewTheme}>
              ➕ Créer un Nouveau Thème
            </button>
          </div>

          <div className="themes-grid">
            <h2>Thèmes Personnalisés</h2>
            {customThemes.length === 0 ? (
              // Aucun thème
              <p className="no-themes">Aucun thème personnalisé. Créez-en un !</p>
            ) : (
              // Afficher les cartes de thèmes
              <div className="custom-themes-list">
                {customThemes.map(customTheme => (
                  <div key={customTheme.id} className="theme-card">
                    <h3>{customTheme.name}</h3>
                    
                    {/* Prévisualisation */}
                    <div className="theme-preview" style={{
                      background: customTheme.variables['--bg-primary'],
                      color: customTheme.variables['--text-primary'],
                      border: `2px solid ${customTheme.variables['--border-color']}`
                    }}>
                      {/* ... contenu de prévisualisation ... */}
                    </div>
                    
                    {/* Actions */}
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
        // VUE ÉDITEUR : en création ou édition
        <ThemeEditor
          theme={editingTheme}
          onSave={handleSaveTheme}
          onCancel={handleCancel}
          onDelete={editingTheme ? () => handleDeleteTheme(editingTheme.id) : undefined}
        />
      )}
    </div>
  );
```

### 🎓 Concepts Appris

- ✅ **localStorage** : Persistence des données
- ✅ **JSON.parse / stringify** : Conversion objet ↔ JSON
- ✅ **Méthodes de tableau** : `.map()`, `.filter()`, `.find()`
- ✅ **Spread operator** : `[...array]`
- ✅ **Rendu conditionnel** : `condition ? A : B`
- ✅ **Optional chaining** : `obj?.prop`
- ✅ **Styles inline dynamiques** : `style={{ prop: value }}`

---

## 8. Étape 6 : Éditeur de Thèmes avec Prévisualisation

### 📝 Fichier : `components/ThemeEditor.tsx`

#### Objectif
Créer un éditeur complet avec :
- 21 sélecteurs de couleurs
- Prévisualisation en temps réel
- Analyse de contraste (accessibilité)
- Boutons OK / Annuler / Supprimer

#### Variables par Défaut

```typescript
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

// EXPLICATION :
// Valeurs utilisées pour initialiser un nouveau thème
// Correspond au thème Light par défaut
```

#### Labels Lisibles

```typescript
const VARIABLE_LABELS: { [key: string]: string } = {
  '--bg-primary': 'Arrière-plan principal',
  '--bg-secondary': 'Arrière-plan secondaire',
  '--text-primary': 'Texte principal',
  // ... etc
};

// EXPLICATION :
// { [key: string]: string } : Dictionnaire/Map
// Clé = nom technique, Valeur = nom convivial
// Utilisé pour afficher des labels compréhensibles
```

#### État Local

```typescript
const ThemeEditor: React.FC<ThemeEditorProps> = ({ theme, onSave, onCancel, onDelete }) => {
  // 1️⃣ Nom du thème
  const [themeName, setThemeName] = useState(theme?.name || '');

  // 2️⃣ Variables CSS
  const [variables, setVariables] = useState(theme?.variables || DEFAULT_VARIABLES);

  // EXPLICATION :
  // theme?.name : si theme existe, utilise name, sinon undefined
  // || '' : si undefined, utilise chaîne vide
  // Permet de gérer création (theme = null) ET édition (theme = objet)

  // 3️⃣ Synchroniser avec les props
  useEffect(() => {
    if (theme) {
      setThemeName(theme.name);
      setVariables(theme.variables);
    }
  }, [theme]);

  // EXPLICATION :
  // Se déclenche quand theme change
  // Utile si on passe d'un thème à un autre en édition
```

#### Gestion des Couleurs

```typescript
  // 4️⃣ Changer une variable
  const handleVariableChange = (varName: string, value: string) => {
    setVariables(prev => ({
      ...prev,
      [varName]: value
    }));
  };

  // EXPLICATION DÉTAILLÉE :

  // prev => ({ ... }) : Fonction de mise à jour
  //   prev = état précédent (ancien objet variables)

  // ...prev : Spread operator
  //   Copie toutes les propriétés de prev

  // [varName]: value : Propriété calculée
  //   varName = '--bg-primary' → crée/met à jour cette clé
  //   value = '#ff0000' → nouvelle valeur

  // EXEMPLE CONCRET :
  // Avant : { '--bg-primary': '#ffffff', '--text-primary': '#000000' }
  // Appel : handleVariableChange('--bg-primary', '#ff0000')
  // Après : { '--bg-primary': '#ff0000', '--text-primary': '#000000' }
```

#### Sauvegarde

```typescript
  // 5️⃣ Sauvegarder le thème
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

  // EXPLICATION :

  // !themeName.trim() : Vérification
  //   .trim() retire les espaces avant/après
  //   ! inverse : si chaîne vide après trim → true

  // theme?.id || `custom-${Date.now()}` :
  //   Si édition : garde l'ID existant
  //   Si création : génère nouvel ID avec timestamp

  // Date.now() : Timestamp en millisecondes
  //   Ex: 1674567890123 (unique!)

  // onSave(savedTheme) : Appelle la fonction prop
  //   Remonte le thème au composant parent
```

#### Calcul de Contraste

```typescript
  // 6️⃣ Calculer le contraste entre deux couleurs
  const getContrast = (color1: string, color2: string): string => {
    // Fonction pour calculer la luminance
    const getLuminance = (color: string): number => {
      // Extraire RGB de la couleur hexadécimale
      const rgb = color.match(/^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i);
      if (!rgb) return 0;
      
      // Convertir hex → décimal → 0-1
      const r = parseInt(rgb[1], 16) / 255;
      const g = parseInt(rgb[2], 16) / 255;
      const b = parseInt(rgb[3], 16) / 255;
      
      // Formule de luminance relative (WCAG)
      const [rs, gs, bs] = [r, g, b].map(c => 
        c <= 0.03928 ? c / 12.92 : Math.pow((c + 0.055) / 1.055, 2.4)
      );
      
      return 0.2126 * rs + 0.7152 * gs + 0.0722 * bs;
    };

    // EXPLICATION REGEX :
    // /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i
    //   ^ : début de chaîne
    //   #? : # optionnel
    //   ([a-f\d]{2}) : 2 caractères hexa (capturé)
    //   $ : fin de chaîne
    //   i : insensible à la casse

    // parseInt(rgb[1], 16) :
    //   rgb[1] = "1a" → parseInt("1a", 16) = 26

    // Calculer les luminances
    const l1 = getLuminance(color1);
    const l2 = getLuminance(color2);
    
    // Ratio de contraste (formule WCAG)
    const ratio = (Math.max(l1, l2) + 0.05) / (Math.min(l1, l2) + 0.05);
    
    // Interprétation selon WCAG
    if (ratio >= 7) return '✓ Excellent (AAA)';
    if (ratio >= 4.5) return '✓ Bon (AA)';
    if (ratio >= 3) return '⚠ Moyen';
    return '✗ Faible';
  };

  // NORMES WCAG :
  // AAA (ratio ≥ 7) : Conformité maximale
  // AA (ratio ≥ 4.5) : Conformité standard
  // Moyen (ratio ≥ 3) : Acceptable pour du grand texte
  // Faible (ratio < 3) : Non conforme
```

#### Rendu : Sélecteurs de Couleurs

```tsx
  <div className="variables-grid">
    {Object.entries(DEFAULT_VARIABLES).map(([varName]) => (
      <div key={varName} className="variable-item">
        <label htmlFor={varName}>
          {VARIABLE_LABELS[varName] || varName}
        </label>
        
        <div className="color-input-group">
          {/* Input color (sélecteur visuel) */}
          <input
            type="color"
            id={varName}
            value={(variables as any)[varName] || (DEFAULT_VARIABLES as any)[varName]}
            onChange={(e) => handleVariableChange(varName, e.target.value)}
            className="color-picker"
          />
          
          {/* Input text (saisie manuelle) */}
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
```

#### EXPLICATION :

```typescript
// Object.entries(DEFAULT_VARIABLES)
// Transforme : { '--bg-primary': '#fff', '--text-primary': '#000' }
// En tableau : [ ['--bg-primary', '#fff'], ['--text-primary', '#000'] ]

// .map(([varName]) => ...)
// Destructure chaque paire : [varName, value]
// On utilise seulement varName (value non nécessaire ici)

// <input type="color"> : Sélecteur de couleur natif HTML5
// onChange={(e) => ...} : Déclenché à chaque changement
// e.target.value : Nouvelle couleur sélectionnée
```

#### Rendu : Prévisualisation

```tsx
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
      </div>
      
      <div className="preview-buttons">
        <button style={{
          backgroundColor: variables['--button-primary'],
          color: variables['--button-text']
        }}>
          Bouton Principal
        </button>
      </div>
    </div>
  </div>
```

#### EXPLICATION :

```tsx
// Styles inline dynamiques
style={{ backgroundColor: variables['--bg-primary'] }}

// À chaque changement de variables :
// 1. React re-rend le composant
// 2. Les styles sont recalculés
// 3. La prévisualisation s'actualise automatiquement

// Template strings pour combiner valeurs :
boxShadow: `0 2px 8px ${variables['--card-shadow']}`
// Résultat : "0 2px 8px rgba(0, 0, 0, 0.1)"
```

#### Rendu : Analyseur de Contraste

```tsx
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
      
      {/* ... autres vérifications ... */}
    </div>
    
    <p className="contrast-info">
      <small>
        ✓ AAA (ratio ≥7:1) = Excellent | ✓ AA (ratio ≥4.5:1) = Bon | 
        ⚠ Moyen (ratio ≥3:1) | ✗ Faible (ratio &lt;3:1)
      </small>
    </p>
  </div>
```

#### EXPLICATION :

```tsx
// {getContrast(...)} : Appel de fonction dans JSX
// Exécuté à chaque rendu
// Retourne une chaîne : "✓ Excellent (AAA)"

// &lt; : Entité HTML pour <
// Évite les problèmes avec les balises JSX
```

### 🎓 Concepts Appris

- ✅ **Fonctions de mise à jour** : `setState(prev => ...)`
- ✅ **Propriétés calculées** : `[varName]: value`
- ✅ **Input color** : Sélecteur natif HTML5
- ✅ **Regex** : Extraction de composantes RGB
- ✅ **Math avancé** : Calculs de luminance et contraste
- ✅ **Styles inline dynamiques** : Mise à jour en temps réel
- ✅ **Template strings** : Interpolation de variables
- ✅ **Accessibilité** : Normes WCAG

---

## 9. Exercices Pratiques

### 🏋️ Exercice 1 : Ajouter un Nouveau Thème Prédéfini

**Objectif** : Ajouter un thème "Ocean" aux thèmes prédéfinis.

**Fichiers à modifier** :
1. `config/themes.ts`
2. `index.css`

**Instructions** :

```typescript
// 1. Dans themes.ts
export type ThemeType = 'light' | 'dark' | 'high-contrast' | 'ocean';

export const availableThemes: ThemeConfig[] = [
  // ... thèmes existants ...
  {
    id: 'ocean',
    name: 'Océan',
    icon: '🌊',
    description: 'Thème inspiré de l\'océan',
  },
];
```

```css
/* 2. Dans index.css */
[data-theme="ocean"] {
  --bg-primary: #001f3f;
  --bg-secondary: #003d5c;
  --text-primary: #e0f7ff;
  --text-secondary: #7fcdff;
  --border-color: #0074d9;
  
  --header-bg: #0074d9;
  --header-hover: #0056b3;
  --header-text: #ffffff;
  
  /* ... complétez les autres variables ... */
}
```

### 🏋️ Exercice 2 : Ajouter un Compteur de Thèmes

**Objectif** : Afficher le nombre total de thèmes personnalisés créés.

**Fichier** : `pages/ThemeManagement.tsx`

```tsx
// Dans le rendu
<div className="themes-grid">
  <h2>Thèmes Personnalisés ({customThemes.length})</h2>
  {/* ... reste du code ... */}
</div>
```

### 🏋️ Exercice 3 : Dupliquer un Thème

**Objectif** : Ajouter un bouton "Dupliquer" pour copier un thème existant.

```typescript
const handleDuplicateTheme = (themeToDuplicate: CustomTheme) => {
  const duplicatedTheme: CustomTheme = {
    id: `custom-${Date.now()}`,
    name: `${themeToDuplicate.name} (Copie)`,
    variables: { ...themeToDuplicate.variables }
  };
  
  const updatedThemes = [...customThemes, duplicatedTheme];
  setCustomThemes(updatedThemes);
  localStorage.setItem('customThemes', JSON.stringify(updatedThemes));
};
```

```tsx
// Ajouter le bouton dans la carte
<button onClick={() => handleDuplicateTheme(customTheme)}>
  📋 Dupliquer
</button>
```

### 🏋️ Exercice 4 : Exporter/Importer un Thème

**Objectif** : Permettre de sauvegarder un thème en fichier JSON.

```typescript
const handleExportTheme = (theme: CustomTheme) => {
  const dataStr = JSON.stringify(theme, null, 2);
  const dataBlob = new Blob([dataStr], { type: 'application/json' });
  
  const url = URL.createObjectURL(dataBlob);
  const link = document.createElement('a');
  link.href = url;
  link.download = `${theme.name}.json`;
  link.click();
  
  URL.revokeObjectURL(url);
};
```

### 🏋️ Exercice 5 : Filtrer par Nom

**Objectif** : Ajouter une barre de recherche pour filtrer les thèmes.

```typescript
const [searchTerm, setSearchTerm] = useState('');

const filteredThemes = customThemes.filter(theme =>
  theme.name.toLowerCase().includes(searchTerm.toLowerCase())
);
```

```tsx
<input
  type="text"
  placeholder="Rechercher un thème..."
  value={searchTerm}
  onChange={(e) => setSearchTerm(e.target.value)}
/>

{filteredThemes.map(theme => (
  // ... carte de thème ...
))}
```

---

## 10. Résumé et Points Clés

### 🎯 Ce Que Vous Avez Appris

#### Concepts React

| Concept | Utilisation | Exemple |
|---------|-------------|---------|
| **useState** | Gérer l'état local | `const [theme, setTheme] = useState('light')` |
| **useEffect** | Effets de bord | Sauvegarder dans localStorage |
| **useContext** | Partage de données global | ThemeContext accessible partout |
| **Custom Hooks** | Réutiliser la logique | `useTheme()` |
| **Props** | Passer des données | `<ThemeEditor theme={...} />` |
| **Callbacks** | Remonter des événements | `onSave={handleSave}` |

#### Concepts JavaScript/TypeScript

| Concept | Utilisation | Exemple |
|---------|-------------|---------|
| **Arrow Functions** | Syntaxe moderne | `(e) => { ... }` |
| **Destructuring** | Extraire des propriétés | `const { theme, setTheme } = useTheme()` |
| **Spread Operator** | Copier/fusionner | `{ ...prev, [key]: value }` |
| **Template Strings** | Interpolation | `` `custom-${Date.now()}` `` |
| **Optional Chaining** | Accès sécurisé | `theme?.name` |
| **Array Methods** | Manipulation de données | `.map()`, `.filter()`, `.find()` |

#### Concepts CSS

| Concept | Utilisation | Exemple |
|---------|-------------|---------|
| **CSS Variables** | Thèmes dynamiques | `var(--bg-primary)` |
| **Attribute Selectors** | Cibler par attribut | `[data-theme="dark"]` |
| **Position Absolute** | Menus déroulants | Positionnement des sous-menus |
| **Pseudo-elements** | Décorations | `::after` pour les flèches |

### 🔑 Architecture en 3 Couches

```
1. CONFIGURATION (themes.ts)
   ↓ Définit les thèmes disponibles
   
2. GESTION D'ÉTAT (ThemeContext.tsx)
   ↓ Partage le thème actuel à toute l'app
   
3. INTERFACES UTILISATEUR
   ├── Header.tsx (sélection rapide)
   ├── ThemeManagement.tsx (gestion)
   └── ThemeEditor.tsx (création/édition)
```

### 💡 Bonnes Pratiques Appliquées

1. ✅ **Séparation des responsabilités** : Un fichier = une fonction
2. ✅ **Réutilisabilité** : Composants et hooks génériques
3. ✅ **Typage fort** : TypeScript pour éviter les erreurs
4. ✅ **Persistence** : localStorage pour sauvegarder les préférences
5. ✅ **Accessibilité** : Analyse de contraste WCAG
6. ✅ **UX** : Prévisualisation en temps réel

### 📚 Pour Aller Plus Loin

#### Améliorations Possibles

1. **Animations** : Transitions fluides entre thèmes
2. **Thèmes automatiques** : Changer selon l'heure de la journée
3. **Partage** : Exporter/importer des thèmes
4. **Templates** : Thèmes de base pour démarrer
5. **Préférences système** : Respecter le thème de l'OS
6. **Gradient Support** : Permettre des dégradés
7. **Font Customization** : Personnaliser les polices

#### Ressources

- [React Documentation](https://react.dev/)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)
- [MDN Web Docs - CSS Variables](https://developer.mozilla.org/en-US/docs/Web/CSS/Using_CSS_custom_properties)
- [WCAG Contrast Guidelines](https://www.w3.org/WAI/WCAG21/Understanding/contrast-minimum.html)

---

## 🎓 Conclusion

Vous avez maintenant une compréhension complète de :

- ✅ Comment structurer une application React
- ✅ Comment utiliser le Context API pour partager des données
- ✅ Comment gérer l'état avec useState et useEffect
- ✅ Comment créer des interfaces utilisateur dynamiques
- ✅ Comment implémenter un système de thèmes complet
- ✅ Comment respecter les normes d'accessibilité

**Continuez à pratiquer** en réalisant les exercices et en explorant les améliorations proposées !

---

*Créé avec ❤️ pour les débutants en React*
