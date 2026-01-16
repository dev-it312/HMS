import React, { useEffect, useState } from 'react'
import Link from 'next/link'
import { useRouter } from 'next/router'
import { useAdminAuth } from '../../hooks/useAdminAuth'
import '../../styles/globals.css'

export default function AdminDashboard() {
  const { admin, pending, error, isLoggingOut, logout } = useAdminAuth()
  const [logoutError, setLogoutError] = useState<any>(null)
  const router = useRouter()

  useEffect(() => {
    if (!pending && !admin) {
      router.push('/admin/login')
    }
  }, [pending, admin, router])

  async function handleLogout() {
    setLogoutError(null)
    try {
      await logout()
    } catch (e) {
      setLogoutError(e)
    }
  }

  return (
    <div className="container">
      <h2>Welcome to the Admin Dashboard</h2>

      {pending && <div className="status">Loading profile...</div>}
      {!pending && error && <div className="status error">Erreur lors du chargement du profil: {String((error as any)?.message || error)}</div>}

      {!pending && admin && (
        <div className="profile">
          <p>Signed in as: <strong>{admin.name || admin.username || admin.email}</strong></p>
        </div>
      )}

      <ul>
        <li><Link href="/staff-management">Staff Management</Link></li>
        <li><Link href="/doctor-management">Doctor Management</Link></li>
        <li><Link href="/view-appointments">View Appointments</Link></li>
        <li><Link href="/reviews">Reviews</Link></li>
        <li>
          <button className="logout-btn" onClick={handleLogout} disabled={isLoggingOut}>{isLoggingOut ? 'Logging out...' : 'Log out'}</button>
        </li>
      </ul>

      {logoutError && <div className="status error">Erreur lors de la déconnexion: {String((logoutError as any)?.message || logoutError)}</div>}
    </div>
  )
}
