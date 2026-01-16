@echo off
REM start-dev.bat désactivé par préférence utilisateur.
REM Démarrez le frontend manuellement depuis la racine :
REM cd frontend && npm install && npm run dev

echo Le script start-dev.bat est désactivé. Démarrez le frontend manuellement avec :
echo cd frontend && npm install && npm run dev
echo Puis lancez le backend avec :
echo mvnw.cmd spring-boot:run  (ou mvn spring-boot:run)
pause>nul