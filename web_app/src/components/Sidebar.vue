<script setup>
import { useLayoutStore } from '@/stores/layout';
import { useRouter, useRoute } from 'vue-router';
import { computed } from 'vue';

const layoutStore = useLayoutStore();
const router = useRouter();
const route = useRoute();

const menuItems = [
  {
    label: 'Dashboard',
    icon: 'pi pi-home',
    to: '/',
  },
  {
    label: 'Funcionários',
    icon: 'pi pi-users',
    to: '/funcionarios',
  },
  {
    label: 'Departamentos',
    icon: 'pi pi-building',
    to: '/departamentos',
  },
];

const isActive = (menuItem) => {
  return route.path === menuItem.to;
};

const navigate = (to) => {
  router.push(to);
};

const sidebarClass = computed(() => {
  return {
    'sidebar': true,
    'collapsed': !layoutStore.sidebarVisible,
  };
});
</script>

<template>
  <aside :class="sidebarClass">
    <nav class="sidebar-nav">
      <div v-for="item in menuItems" :key="item.to" class="menu-item-wrapper">
        <button
          :class="['menu-item', { active: isActive(item) }]"
          @click="navigate(item.to)"
        >
          <i :class="item.icon" class="menu-icon"></i>
          <span class="menu-label">{{ item.label }}</span>
        </button>
      </div>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 250px;
  background: #2c3e50;
  height: 100vh;
  position: fixed;
  left: 0;
  top: 60px;
  overflow-y: auto;
  transition: transform 0.3s ease, width 0.3s ease;
  z-index: 99;
  border-right: 1px solid #34495e;
}

.sidebar.collapsed {
  transform: translateX(-100%);
  width: 0;
}

.sidebar-nav {
  padding: 1rem 0;
}

.menu-item-wrapper {
  margin: 0.5rem 0;
}

.menu-item {
  display: flex;
  align-items: center;
  width: 100%;
  padding: 1rem 1.5rem;
  background: none;
  border: none;
  color: #ecf0f1;
  cursor: pointer;
  font-size: 1rem;
  transition: all 0.2s ease;
  text-align: left;
  gap: 1rem;
}

.menu-item:hover {
  background-color: rgba(255, 255, 255, 0.1);
  padding-left: 2rem;
}

.menu-item.active {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-left: 4px solid #fff;
  padding-left: 1.5rem;
  font-weight: 600;
}

.menu-icon {
  font-size: 1.25rem;
  min-width: 1.25rem;
}

.menu-label {
  flex: 1;
}

/* Scrollbar styling */
.sidebar::-webkit-scrollbar {
  width: 6px;
}

.sidebar::-webkit-scrollbar-track {
  background: rgba(0, 0, 0, 0.1);
}

.sidebar::-webkit-scrollbar-thumb {
  background: rgba(255, 255, 255, 0.2);
  border-radius: 3px;
}

.sidebar::-webkit-scrollbar-thumb:hover {
  background: rgba(255, 255, 255, 0.3);
}

@media (max-width: 768px) {
  .sidebar {
    width: 200px;
  }

  .menu-item {
    padding: 0.75rem 1rem;
  }

  .menu-item:hover {
    padding-left: 1.5rem;
  }

  .menu-label {
    font-size: 0.9rem;
  }
}
</style>
