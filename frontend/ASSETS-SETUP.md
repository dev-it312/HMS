# Configuration des assets statiques

## Logo de l'application

Pour que le logo s'affiche correctement, vous devez copier les images depuis le dossier `src/main/resources/static/` vers `frontend/public/`.

### Commandes PowerShell

```powershell
# Copier le logo principal
Copy-Item "c:\REPO\HMS\Hospital-Management-System-main\src\main\resources\static\LogoBanner.jpg" -Destination "c:\REPO\HMS\Hospital-Management-System-main\frontend\public\"

# Copier tous les fichiers CSS (si nécessaire pour référence)
Copy-Item "c:\REPO\HMS\Hospital-Management-System-main\src\main\resources\static\*.css" -Destination "c:\REPO\HMS\Hospital-Management-System-main\frontend\public\"
```

### Alternative manuelle

Copiez simplement le fichier :

- **Source** : `src/main/resources/static/LogoBanner.jpg`
- **Destination** : `frontend/public/LogoBanner.jpg`

## Vérification

Une fois copié, le fichier sera accessible dans React via :

```tsx
<img src="/LogoBanner.jpg" alt="Logo" />
```

## Autres assets

Si vous avez d'autres images ou fichiers statiques dans `src/main/resources/static/`, copiez-les aussi dans `frontend/public/` pour les utiliser dans l'application React.
