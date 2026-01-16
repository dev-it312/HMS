# 🚀 Démarrage rapide - Frontend React

## ✅ Statut de la conversion

La conversion des templates HTML en React est **terminée et fonctionnelle** !

## 🎯 Pour démarrer l'application

### 1. Vérifier que vous êtes dans le bon dossier

```powershell
cd c:\REPO\HMS\Hospital-Management-System-main\frontend
```

### 2. Démarrer le serveur de développement

```powershell
npm run dev
```

### 3. Ouvrir dans le navigateur

L'application sera accessible sur : **http://localhost:5173**

## 🧪 Tester les pages

Vous pouvez tester les pages suivantes :

### Page d'accueil

- **URL** : http://localhost:5173/
- **Description** : Page d'accueil avec liens vers toutes les sections

### Pages de login

- **Admin** : http://localhost:5173/login/admin
- **Médecin** : http://localhost:5173/login/doctor
- **Patient** : http://localhost:5173/login/patient
- **Réceptionniste** : http://localhost:5173/login/receptionist

### Dashboards (nécessitent login)

- **Admin** : http://localhost:5173/admin/dashboard
- **Médecin** : http://localhost:5173/doctor/dashboard
- **Patient** : http://localhost:5173/patient/dashboard
- **Réceptionniste** : http://localhost:5173/receptionist/dashboard

### Pages de gestion

- **Gestion du personnel** : http://localhost:5173/admin/staff-management
- **Gestion des médecins** : http://localhost:5173/admin/doctor-management
- **Gestion des patients** : http://localhost:5173/receptionist/patient-management
- **Gestion des RDV** : http://localhost:5173/receptionist/appointment-management

### Formulaires

- **Ajouter un patient** : http://localhost:5173/receptionist/patient/add
- **Ajouter un médecin** : http://localhost:5173/admin/doctor/add

## ⚙️ Configuration du backend

### Option 1 : Proxy Vite (recommandé pour dev)

Modifiez `vite.config.ts` :

```typescript
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173,
    proxy: {
      "/adminlogin": "http://localhost:8080",
      "/doctorlogin": "http://localhost:8080",
      "/patientlogin": "http://localhost:8080",
      "/receptionistlogin": "http://localhost:8080",
      "/addNewPatient": "http://localhost:8080",
      "/addNewDoctor": "http://localhost:8080",
      // Ajoutez d'autres routes au besoin
    },
  },
});
```

### Option 2 : CORS sur le backend Spring Boot

Ajoutez une configuration CORS dans votre application Spring Boot :

```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:5173")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);
            }
        };
    }
}
```

## 📦 Build pour production

```powershell
# Build
npm run build

# Preview du build
npm run preview
```

Le build sera créé dans le dossier `dist/`.

## 🔧 Dépannage

### Le logo ne s'affiche pas

Vérifiez que `LogoBanner.jpg` est bien dans `frontend/public/`

### Erreurs de compilation

Réinstallez les dépendances :

```powershell
Remove-Item -Recurse -Force node_modules
npm install
```

### Le port 5173 est déjà utilisé

Changez le port dans `vite.config.ts` ou stoppez l'autre application

## 📚 Documentation complète

- [CONVERSION-RECAP.md](./CONVERSION-RECAP.md) - Détails de la conversion
- [CONVERSION.md](./CONVERSION.md) - Guide technique
- [ASSETS-SETUP.md](./ASSETS-SETUP.md) - Configuration des assets

---

🎉 **Bon développement !**
