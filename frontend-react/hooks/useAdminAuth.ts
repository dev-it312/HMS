import { useState, useEffect } from 'react'
import Router from 'next/router'

export type Admin = { name?: string; username?: string; email?: string } | null

export function useAdminAuth() {
  const [admin, setAdmin] = useState<Admin>(null)
  const [pending, setPending] = useState<boolean>(true)
  const [error, setError] = useState<any>(null)
  const [isLoggingOut, setIsLoggingOut] = useState<boolean>(false)

  async function fetchProfile() {
    setPending(true)
    setError(null)
    try {
      const res = await fetch('/api/admin/profile', { credentials: 'include' })
      if (!res.ok) throw new Error(`Profile fetch failed: ${res.status}`)
      const data = await res.json()
      setAdmin(data)
    } catch (e) {
      setError(e)
      setAdmin(null)
    } finally {
      setPending(false)
    }
  }

  useEffect(() => {
    fetchProfile()
  }, [])

  async function logout() {
    setIsLoggingOut(true)
    try {
      const res = await fetch('/adminlogout', { method: 'POST', credentials: 'include' })
      if (!res.ok) throw new Error(`Logout failed: ${res.status}`)
      // redirect to admin login
      await Router.push('/admin/login')
    } catch (e) {
      throw e
    } finally {
      setIsLoggingOut(false)
    }
  }

  return { admin, pending, error, isLoggingOut, logout, refreshProfile: fetchProfile }
}
