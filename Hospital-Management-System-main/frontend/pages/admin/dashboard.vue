<template>
  <div class="dashboard-container">
    <h2>Welcome to the Admin Dashboard</h2>

    <div v-if="pending" class="status">Loading profile...</div>
    <div v-else-if="error" class="status error">Erreur lors du chargement du profil: {{ error?.message || error }}</div>
    <div v-else class="profile" v-if="admin">
      <p>Signed in as: <strong>{{ admin.name || admin.username || admin.email }}</strong></p>
    </div>

    <ul>
      <li><NuxtLink to="/staff-management">Staff Management</NuxtLink></li>
      <li><NuxtLink to="/doctor-management">Doctor Management</NuxtLink></li>
      <li><NuxtLink to="/view-appointments">View Appointments</NuxtLink></li>
      <li><NuxtLink to="/reviews">Reviews</NuxtLink></li>
      <li>
        <button class="logout-btn" @click="handleLogout" :disabled="isLoggingOut">
          {{ isLoggingOut ? 'Logging out...' : 'Log out' }}
        </button>
      </li>
    </ul>

    <div v-if="logoutError" class="status error">Erreur lors de la déconnexion: {{ logoutError.message || logoutError }}</div>
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from 'vue'
import { useRouter } from 'vue-router'
import { useAdminAuth } from '../../composables/useAdminAuth'

const { admin, pending, error, isLoggingOut, logout } = useAdminAuth()
const logoutError = ref(null)

async function handleLogout() {
  logoutError.value = null
  try {
    await logout()
  } catch (e) {
    logoutError.value = e
  }
}

// If admin is null and not pending, redirect to login - simple client-side guard
watchEffect(() => {
  if (!pending.value && !admin.value) {
    const router = useRouter()
    router.push('/admin/login')
  }
})
</script>

<style scoped>
.dashboard-container {
  padding: 16px;
}
.status { margin: 8px 0; }
.status.error { color: #c00; }
.logout-btn {
  background: #e53e3e;
  color: white;
  border: none;
  padding: 6px 10px;
  border-radius: 4px;
  cursor: pointer;
}
.logout-btn[disabled] {
  opacity: 0.6;
  cursor: not-allowed;
}
</style>