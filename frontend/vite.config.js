import { fileURLToPath, URL } from 'node:url';

import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  build: {
    outDir: '../src/main/resources/static',
  }, // 빌드 결과물이 생성되는 경로
  server: {
    hmr: {
      host: 'localhost',
      protocol: 'ws',
    },
    proxy: {
      '/api': 'http://localhost:80',
      '/ws': {
        target: 'ws://localhost:80',
        ws: true,
      },
    }, // proxy 설정
  },
  define: {
    //  global is not defined 에러 처리
    global: {},
  },
});
