import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    react({
      babel: {
        plugins: [['babel-plugin-react-compiler']],
      },
    }),
  ],
  server: {
    port: 5173,
    // Décommentez et configurez le proxy si nécessaire pour le développement
    // proxy: {
    //   '/adminlogin': 'http://localhost:8080',
    //   '/doctorlogin': 'http://localhost:8080',
    //   '/patientlogin': 'http://localhost:8080',
    //   '/receptionistlogin': 'http://localhost:8080',
    //   '/addNewPatient': 'http://localhost:8080',
    //   '/addNewDoctor': 'http://localhost:8080',
    //   '/adminlogout': 'http://localhost:8080',
    //   '/doctorlogout': 'http://localhost:8080',
    //   '/patientlogout': 'http://localhost:8080',
    //   '/receptionistlogout': 'http://localhost:8080',
    // }
  }
})
