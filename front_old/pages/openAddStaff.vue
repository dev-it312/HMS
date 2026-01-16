<template>
  <div class="page">
    <h1>Add Staff</h1>
    <p>Choisissez le type de staff à ajouter :</p>
    <ul>
      <li><NuxtLink to="/openAddNewDoctor">Doctor</NuxtLink></li>
      <li><NuxtLink to="/openAddNewReceptionist">Receptionist</NuxtLink></li>
    </ul>
    <NuxtLink to="/staff-management">← Retour</NuxtLink>
  </div>
</template>

<script setup lang="ts">
// Placeholder migrated from addStaff.html
</script>

<style scoped>
.page { padding: 24px; font-family: Arial, sans-serif }
</style>
