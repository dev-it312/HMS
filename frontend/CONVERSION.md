# Frontend React - Hospital Management System

Ce dossier contient la version React du frontend du système de gestion hospitalière, converti depuis les templates HTML/CSS originaux.

## 🏗️ Structure du projet

```
frontend/
├── src/
│   ├── components/         # Composants réutilisables
│   │   ├── Header.tsx      # En-tête avec navigation
│   │   ├── Footer.tsx      # Pied de page
│   │   ├── Layout.tsx      # Layout avec header/footer
│   │   └── DashboardLayout.tsx  # Layout pour les dashboards
│   ├── pages/              # Pages de l'application
│   │   ├── Home.tsx        # Page d'accueil
│   │   ├── *Login.tsx      # Pages de connexion (Admin, Doctor, Patient, Receptionist)
│   │   ├── *Dashboard.tsx  # Dashboards pour chaque rôle
│   │   ├── *Management.tsx # Pages de gestion
│   │   ├── AddNewPatient.tsx
│   │   └── AddNewDoctor.tsx
│   ├── services/           # Services API
│   │   └── api.ts          # Service centralisé pour les appels API
│   ├── styles/             # Fichiers CSS
│   │   ├── Header.css
│   │   ├── Footer.css
│   │   ├── Dashboard.css
│   │   ├── Form.css
│   │   └── Home.css
│   ├── App.tsx             # Composant principal avec routing
│   ├── main.tsx            # Point d'entrée
│   └── index.css           # Styles globaux
├── public/                 # Assets statiques
├── package.json
└── vite.config.ts
```

## 🚀 Démarrage

### Installation des dépendances

```bash
npm install
```

### Développement

```bash
npm run dev
```

Le serveur de développement démarre sur `http://localhost:5173`

### Build de production

```bash
npm run build
```

## 📋 Pages converties

### Pages d'authentification

- ✅ Admin Login (`/login/admin`)
- ✅ Doctor Login (`/login/doctor`)
- ✅ Patient Login (`/login/patient`)
- ✅ Receptionist Login (`/login/receptionist`)

### Dashboards

- ✅ Admin Dashboard (`/admin/dashboard`)
- ✅ Doctor Dashboard (`/doctor/dashboard`)
- ✅ Patient Dashboard (`/patient/dashboard`)
- ✅ Receptionist Dashboard (`/receptionist/dashboard`)

### Pages de gestion

- ✅ Staff Management (`/admin/staff-management`)
- ✅ Doctor Management (`/admin/doctor-management`)
- ✅ Patient Management (`/receptionist/patient-management`)
- ✅ Appointment Management (`/receptionist/appointment-management`)
- ✅ Add New Patient (`/receptionist/patient/add`)
- ✅ Add New Doctor (`/admin/doctor/add`)

### Page d'accueil

- ✅ Home (`/`)

## 🔧 Technologies utilisées

- **React 19** - Framework UI
- **TypeScript** - Typage statique
- **React Router DOM** - Routing
- **Axios** - Requêtes HTTP
- **Vite** - Build tool & dev server

## 🔌 Intégration avec le backend

L'application React communique avec le backend Spring Boot via des appels API HTTP.

### Configuration du proxy (optionnel pour dev)

Pour le développement, vous pouvez configurer un proxy dans `vite.config.ts` :

```typescript
export default defineConfig({
  server: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
        changeOrigin: true,
      },
    },
  },
});
```

### Service API

Le fichier `src/services/api.ts` centralise tous les appels API vers le backend.

## 📝 Routes disponibles

| Route                     | Page                     | Rôle requis  |
| ------------------------- | ------------------------ | ------------ |
| `/`                       | Accueil                  | Public       |
| `/login/admin`            | Connexion Admin          | Public       |
| `/login/doctor`           | Connexion Médecin        | Public       |
| `/login/patient`          | Connexion Patient        | Public       |
| `/login/receptionist`     | Connexion Réceptionniste | Public       |
| `/admin/dashboard`        | Dashboard Admin          | Admin        |
| `/doctor/dashboard`       | Dashboard Médecin        | Doctor       |
| `/patient/dashboard`      | Dashboard Patient        | Patient      |
| `/receptionist/dashboard` | Dashboard Réceptionniste | Receptionist |

## 🎨 Styles

Les styles sont organisés par composant/page dans le dossier `src/styles/`.

---

## 📦 Pages à convertir (optionnel)

- Edit Doctor, Edit Receptionist
- Remove Doctor/Staff
- Search Patient, Update Patient
- Upload Patient Reports
- View Doctor List
- Book/Modify Appointment
- Doctor Schedules, Patient Details
- Generate Prescription
- Feedback & Reviews
