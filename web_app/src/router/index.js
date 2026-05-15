import { createRouter, createWebHistory } from 'vue-router'
import LayoutWrapper from '@/components/LayoutWrapper.vue'
import DashboardView from '@/views/DashboardView.vue'
import FuncionariosView from '@/views/FuncionariosView.vue'
import DepartamentosView from '@/views/DepartamentosView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: LayoutWrapper,
      children: [
        {
          path: '',
          name: 'Dashboard',
          component: DashboardView,
        },
        {
          path: 'funcionarios',
          name: 'Funcionários',
          component: FuncionariosView,
        },
        {
          path: 'departamentos',
          name: 'Departamentos',
          component: DepartamentosView,
        },
      ],
    },
  ],
})

export default router
