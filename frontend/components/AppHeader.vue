<template>
  <header>
    <div class="logo-container">
      <img src="/LogoBanner.jpg" alt="Hospital Logo" class="logo-image" />
    </div>
    <nav class="navbar">
      <ul>
        <li><NuxtLink to="/">Home</NuxtLink></li>
        <li><NuxtLink to="/admin/dashboard">Admin</NuxtLink></li>
        <li><NuxtLink to="/doctor-dashboard">Doctor</NuxtLink></li>
        <li><NuxtLink to="/patient-dashboard">Patient</NuxtLink></li>
      </ul>
    </nav>
  </header>
</template>

<script setup lang="ts">
// Script section if needed
</script>

<style scoped>
/* Header and navbar styles can be refined here if needed */
</style>
