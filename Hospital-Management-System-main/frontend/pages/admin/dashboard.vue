<template>
  <div class="dashboard-container">
    <h2>Welcome to the Admin Dashboard</h2>
    <ul>
      <li><NuxtLink to="/staff-management">Staff Management</NuxtLink></li>
      <li><NuxtLink to="/doctor-management">Doctor Management</NuxtLink></li>
      <li><NuxtLink to="/view-appointments">View Appointments</NuxtLink></li>
      <li><NuxtLink to="/reviews">Reviews</NuxtLink></li>
      <li><NuxtLink to="/adminlogout">Logg out</NuxtLink></li>
    </ul>
  </div>
</template>

<script setup lang="ts">
// Script section if needed
</script>

<style scoped>
/* Vous pouvez ajouter des styles spécifiques ici ou utiliser le CSS global */
</style>