<script setup>
import { useLayoutStore } from '@/stores/layout';
import TopBar from '@/components/TopBar.vue';
import Sidebar from '@/components/Sidebar.vue';
import { computed } from 'vue';
import { RouterView } from 'vue-router';

const layoutStore = useLayoutStore();

const mainContentClass = computed(() => {
  return {
    'main-content': true,
    'sidebar-collapsed': !layoutStore.sidebarVisible,
  };
});
</script>

<template>
  <div class="layout-wrapper">
    <TopBar />
    <Sidebar />
    <main :class="mainContentClass">
      <RouterView />
    </main>
  </div>
</template>

<style scoped>
.layout-wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background: #f5f5f5;
}

.main-content {
  margin-left: 250px;
  margin-top: 60px;
  flex: 1;
  transition: margin-left 0.3s ease;
  overflow-y: auto;
  background: white;
}

.main-content.sidebar-collapsed {
  margin-left: 0;
}

@media (max-width: 768px) {
  .main-content {
    margin-left: 0;
    margin-top: 60px;
  }
}
</style>
