<script setup>
import { useLayoutStore } from '@/stores/layout';

const layoutStore = useLayoutStore();
</script>

<template>
  <div class="topbar">
    <div class="topbar-content">
      <button class="menu-toggle" @click="layoutStore.toggleSidebar">
        <i class="pi pi-bars"></i>
      </button>
      <h2 class="app-title">App</h2>
    </div>
  </div>
</template>

<style scoped>
.topbar {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
  height: 60px;
}

.topbar-content {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: 100%;
  padding: 0 1rem;
  max-width: 100%;
}

.menu-toggle {
  background: none;
  border: none;
  color: white;
  font-size: 1.5rem;
  cursor: pointer;
  padding: 0.5rem;
  border-radius: 4px;
  transition: background-color 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
}

.menu-toggle:hover {
  background-color: rgba(255, 255, 255, 0.1);
}

.app-title {
  color: white;
  margin: 0;
  margin-left: 1rem;
  font-size: 1.25rem;
  font-weight: 600;
}

@media (max-width: 768px) {
  .topbar-content {
    padding: 0 0.5rem;
  }

  .app-title {
    margin-left: 0.5rem;
    font-size: 1rem;
  }
}
</style>
