// https://nuxt.com/docs/api/configuration/nuxt-config
// a rajouter au debut de tsconfig.json https://nuxt.com/docs/guide/concepts/typescript
export default defineNuxtConfig({
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  css: [
    '~/assets/styles.css'
  ]
})