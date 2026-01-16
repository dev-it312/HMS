<template>
  <div class="dashboard-container">
    <h2>Staff Management</h2>
    <ul>
      <li><NuxtLink to="/openAddStaff">Add Staff</NuxtLink></li>
      <li><NuxtLink to="/openViewOrEditStaffDetails">View or Edit Staff Details</NuxtLink></li>
      <li><NuxtLink to="/openRemoveStaff">Remove Staff</NuxtLink></li>
    </ul>
    <NuxtLink to="/admin/dashboard">← Retour au dashboard</NuxtLink>
  </div>
</template>

<script setup lang="ts">
// Migrated from src/main/resources/templates/staffManagement.html
</script>

<style scoped>
.dashboard-container { padding: 24px; font-family: Arial, sans-serif }
.dashboard-container ul { margin-top: 12px }
.dashboard-container li { margin: 6px 0 }
</style>