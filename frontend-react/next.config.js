/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  // simple rewrite so that API calls to /api/* go to backend during dev
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'http://localhost:8080/api/:path*' // backend dev
      },
      {
        source: '/adminlogout',
        destination: 'http://localhost:8080/adminlogout'
      }
    ]
  }
}

module.exports = nextConfig
