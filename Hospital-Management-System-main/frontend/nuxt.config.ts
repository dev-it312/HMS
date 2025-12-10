// https://nuxt.com/docs/api/configuration/nuxt-config
// a rajouter au debut de tsconfig.json https://nuxt.com/docs/guide/concepts/typescript
export default defineNuxtConfig({
  srcDir: '.',
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  css: [
    '~/assets/styles.css'
  ],
  // Disable Nuxt auto components to avoid path transform error on Windows (vite virtual files)
  components: false,
  // Provide a default runtime config for backend API base if we want to use it later
  runtimeConfig: {
    public: {
      apiBase: '' // e.g. 'http://localhost:8080' — leave empty to use relative paths
    }
  }
})