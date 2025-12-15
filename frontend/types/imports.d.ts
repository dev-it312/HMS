// Minimal type declarations to satisfy TS/LSP for Nuxt '#imports' virtual module
// This file is intentionally permissive — adjust types later if you want stricter checking.

declare module '#imports' {
  import type { Ref } from 'vue'

  export function useRuntimeConfig(): any
  export function useFetch<T = any>(url: string, opts?: any): {
    data: Ref<T | undefined>
    pending: Ref<boolean>
    error: Ref<any>
    refresh: () => Promise<void>
  }
  export function useRouter(): any
}

// global $fetch provided by Nuxt
declare const $fetch: any