# Frontend React POC

This is a small Next.js TypeScript proof-of-concept to help migrate pages from the Nuxt frontend.

Dev:

1. From project root, start backend spring-boot (if not already running):

```bash
# Windows
mvnw.cmd spring-boot:run
```

2. Start this Next dev server:

```bash
cd frontend-react
npm install
npm run dev
```

By default the dev server runs on port 3001 and rewrites `/api/*` and `/adminlogout` to `http://localhost:8080` so API calls are proxied to the backend.

What's included:
- `hooks/useAdminAuth.ts` : React hook version of the existing Nuxt composable.
- `pages/admin/dashboard.tsx` : migrated dashboard page.

You can use this POC to learn React by reading the hook and page and asking questions about individual lines.
