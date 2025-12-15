<template>
  <div class="home">
    <h1>Hospital Management System - Frontend</h1>
    <p>Bienvenue — utilisez les liens ci-dessous pour accéder à l'interface.</p>
    <ul>
      <li><NuxtLink to="/admin/dashboard">Admin Dashboard</NuxtLink></li>
      <li><NuxtLink to="/admin/login">Admin Login</NuxtLink></li>
    </ul>
  </div>
</template>

<script setup lang="ts">
// simple static home page to avoid 404 at /
</script>

<style scoped>
.home { padding: 24px; font-family: Arial, sans-serif }
</style>
