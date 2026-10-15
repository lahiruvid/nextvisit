import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      "/api/visits": {
        target: "http://localhost:8080",
        rewrite: (path) => path.replace(/^\/api/, "")
      },
      "/api/reminders": {
        target: "http://localhost:8081",
        rewrite: (path) => path.replace(/^\/api/, "")
      }
    }
  }
});
