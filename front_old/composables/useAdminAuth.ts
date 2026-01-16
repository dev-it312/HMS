// Composable to fetch admin profile and handle logout
// Provides: admin (Ref), pending (Ref), error (Ref), isLoggingOut (Ref), logout(), refreshProfile()

import { ref } from 'vue'
import { useRuntimeConfig, useFetch, useRouter } from '#imports'

export function useAdminAuth() {
  const config = useRuntimeConfig()
  const apiBase = (config.public && config.public.apiBase) ? config.public.apiBase : ''

  // Fetch admin profile; uses cookie-based session (credentials: 'include')
  const { data: admin, pending, error, refresh } = useFetch(`${apiBase}/api/admin/profile`, {
    credentials: 'include'
  })

  const isLoggingOut = ref(false)
  const router = useRouter()

  async function logout() {
    isLoggingOut.value = true
    try {
      // Adjust path if your backend uses a different logout endpoint
      await $fetch(`${apiBase}/adminlogout`, { method: 'POST', credentials: 'include' })
      // Redirect to admin login
      await router.push('/admin/login')
    } catch (e) {
      // rethrow so callers can show a UI message if desired
      console.error('Logout error', e)
      throw e
    } finally {
      isLoggingOut.value = false
    }
  }

  return {
    admin,
    pending,
    error,
    isLoggingOut,
    logout,
    refreshProfile: refresh,
    apiBase
  }
}