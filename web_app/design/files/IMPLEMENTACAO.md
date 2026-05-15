# 🚀 Guia de Implementação - HR Pulse Enterprise Vue 3

Documento completo sobre a migração do código HTML/Tailwind para Vue 3 com Composition API.

## 📋 O que foi feito

### ✅ Conversão Completa
- ✨ Código HTML genérico → **Vue 3 com Composition API**
- 🎯 Componentes monolíticos → **Arquitetura modular e reutilizável**
- 📦 Estilos inline → **CSS modular com Tailwind**
- 🔄 JavaScript vanilla → **TypeScript com type safety**
- 📱 Layout responsivo → **Mobile-first preservado**

## 📂 Estrutura Final do Projeto

```
hr-pulse-enterprise-vue3/
├── src/
│   ├── components/
│   │   ├── App.vue                      # Raiz com layout flex
│   │   ├── Header.vue                   # Cabeçalho com menu
│   │   ├── Sidebar.vue                  # Menu lateral responsivo
│   │   ├── Dashboard.vue                # Página principal
│   │   ├── Footer.vue                   # Rodapé
│   │   ├── BottomNav.vue                # Nav mobile (bottom)
│   │   ├── StatusBadge.vue              # Componente reutilizável
│   │   ├── cards/
│   │   │   ├── MetricCard.vue           # Card de métrica
│   │   │   ├── TrendChart.vue           # Gráfico de tendência
│   │   │   ├── FeaturedCard.vue         # Card destaque
│   │   │   └── DistributionCard.vue     # Gráfico de distribuição
│   │   └── tables/
│   │       └── DepartmentTable.vue      # Tabela de departamentos
│   ├── composables/
│   │   ├── useSidebar.ts                # Lógica do sidebar
│   │   └── useDashboard.ts              # Dados do dashboard
│   ├── style.css                        # Estilos globais
│   └── main.ts                          # Entry point
├── index.html                           # HTML raiz
├── tailwind.config.ts                   # Config Tailwind + Design System
├── vite.config.ts                       # Bundler Vite
├── tsconfig.json                        # TypeScript
├── package.json                         # Dependências
└── README.md                            # Documentação
```

## 🎯 Principais Melhorias vs Original

### 1. **Componentização**
**Antes (HTML Monolítico):**
```html
<!-- 321 linhas em um único arquivo -->
<div class="bg-surface-container-lowest...">...</div>
```

**Depois (Vue Components):**
```vue
<!-- App.vue - Composição clara -->
<Sidebar :is-open="sidebarOpen" @toggle="toggleSidebar" />
<Header @toggle-sidebar="toggleSidebar" />
<Dashboard />
<Footer />
<BottomNav />
```

### 2. **Type Safety**
**Antes:**
```javascript
// Sem tipos
const navItems = [{ id: 'dashboard', icon: 'dashboard', ... }]
```

**Depois:**
```typescript
interface NavItem {
  id: string
  icon: string
  label: string
  href: string
  active: boolean
}

const navItems = ref<NavItem[]>([...])
```

### 3. **Reusabilidade**
**Antes:** Código duplicado em CSS/HTML
**Depois:** 
```vue
<!-- Usável em qualquer lugar -->
<MetricCard v-for="metric in metrics" :metric="metric" />
```

### 4. **Responsividade Aprimorada**
Detecta breakpoints automaticamente:
```typescript
const isMobile = computed(() => window.innerWidth < 1024)
```

## 🔧 Como Começar

### Instalação Rápida

```bash
# 1. Copiar projeto
cp -r hr-pulse-enterprise-vue3 meu-projeto
cd meu-projeto

# 2. Instalar dependências
npm install

# 3. Iniciar desenvolvimento
npm run dev

# 4. Build para produção
npm run build
```

### Ou usar como Template

```bash
# Usar com degit ou git clone
git clone https://github.com/seu-repo/hr-pulse-vue3.git
npm install
npm run dev
```

## 📱 Mapeamento HTML → Vue 3

### Layout Principal

**HTML Original:**
```html
<div class="flex h-screen">
  <aside class="fixed left-0..."><!-- Sidebar --></aside>
  <main class="flex-1..."><!-- Content --></main>
</div>
```

**Vue 3 Equivalente:**
```vue
<template>
  <div class="flex h-screen">
    <Sidebar :is-open="sidebarOpen" @toggle="toggleSidebar" />
    <div class="flex-1 flex flex-col lg:ml-64">
      <Header @toggle-sidebar="toggleSidebar" />
      <Dashboard />
      <Footer />
    </div>
  </div>
</template>

<script setup lang="ts">
const sidebarOpen = ref(false)
const toggleSidebar = () => sidebarOpen.value = !sidebarOpen.value
</script>
```

### Cards e Métricas

**HTML:**
```html
<div class="bg-surface-container-lowest border...">
  <span class="material-symbols-outlined">domain</span>
  <h3>TOTAL DE DEPARTAMENTOS</h3>
  <h2>12</h2>
  <p>Meta Q2: 15</p>
</div>
```

**Vue 3:**
```vue
<template>
  <div class="bg-surface-container-lowest border...">
    <span class="material-symbols-outlined">{{ metric.icon }}</span>
    <h3 class="text-label-md">{{ metric.title }}</h3>
    <h2 class="text-headline-lg">{{ metric.value }}</h2>
    <p>{{ metric.subtitle }}</p>
  </div>
</template>

<script setup lang="ts">
interface Metric {
  icon: string
  title: string
  value: string
  subtitle: string
}

defineProps<{ metric: Metric }>()
</script>
```

### Tabelas Dinâmicas

**HTML:**
```html
<tr>
  <td>Tecnologia</td>
  <td>Amanda Lima</td>
  <td>342</td>
  <td>R$ 1.850.000</td>
  <td><span class="bg-tertiary-fixed-dim...">No Orçamento</span></td>
</tr>
```

**Vue 3:**
```vue
<tbody>
  <tr v-for="dept in departments" :key="dept.id">
    <td>{{ dept.name }}</td>
    <td>{{ dept.leader }}</td>
    <td>{{ dept.headcount }}</td>
    <td>{{ dept.budget }}</td>
    <td>
      <StatusBadge :status="dept.status" />
    </td>
  </tr>
</tbody>

<script setup lang="ts">
interface Department {
  id: string
  name: string
  leader: string
  headcount: number
  budget: string
  status: 'on-budget' | 'alert'
}

const departments = ref<Department[]>([...])
</script>
```

## 🎨 Design System - Implementação

### Cores (Tailwind Config)

```typescript
// tailwind.config.ts
colors: {
  'primary': '#000000',
  'surface': '#f7f9fb',
  'secondary': '#515f74',
  'tertiary': '#6ffbbe',
  // ... resto das cores
}
```

**Uso em componentes:**
```vue
<div class="bg-primary text-on-primary"><!-- Preto com texto branco --></div>
<div class="bg-surface text-on-surface"><!-- Fundo claro --></div>
```

### Tipografia (CSS Classes)

```typescript
// tailwind.config.ts
fontSize: {
  'headline-lg': ['32px', { 
    lineHeight: '40px', 
    fontWeight: '600' 
  }],
  'body-md': ['16px', { 
    lineHeight: '24px', 
    fontWeight: '400' 
  }],
}
```

**Uso:**
```vue
<h1 class="text-headline-lg">Título Principal</h1>
<p class="text-body-md">Texto corpo</p>
<button class="text-label-md">Botão</button>
```

### Espaçamento

```vue
<!-- Padding/Margin baseado em 4px -->
<div class="p-6">Padding de 24px (6 * 4px)</div>
<div class="gap-gutter">Gap de 24px</div>
<div class="margin-desktop">Margem desktop de 40px</div>
```

## 🔄 Integração com APIs

### Padrão Recomendado

```typescript
// composables/useFetchData.ts
export const useFetchData = () => {
  const data = ref(null)
  const loading = ref(false)
  const error = ref(null)

  const fetch = async (url: string) => {
    loading.value = true
    try {
      const response = await fetch(url)
      data.value = await response.json()
    } catch (err) {
      error.value = err
    } finally {
      loading.value = false
    }
  }

  return { data, loading, error, fetch }
}
```

**Uso em componentes:**
```typescript
// components/Dashboard.vue
import { useFetchData } from '@/composables/useFetchData'

const { data: metrics, loading } = useFetchData()

onMounted(() => {
  fetch('/api/metrics')
})
```

## 📊 Exemplos Práticos

### Exemplo 1: Adicionar Nova Métrica

```typescript
// Em composables/useDashboard.ts
const newMetric: Metric = {
  id: 'revenue',
  icon: 'payments',
  title: 'RECEITA MENSAL',
  value: 'R$ 2,5M',
  subtitle: 'vs. Mês anterior: +5%',
  trend: 'up',
  trendValue: '+5%'
}

metrics.value.push(newMetric)
```

### Exemplo 2: Filtrar Departamentos

```vue
<script setup lang="ts">
const selectedStatus = ref<'on-budget' | 'alert' | 'all'>('all')

const filteredDepts = computed(() => {
  if (selectedStatus.value === 'all') return departments.value
  return departments.value.filter(d => d.status === selectedStatus.value)
})
</script>

<template>
  <div class="flex gap-2 mb-4">
    <button @click="selectedStatus = 'all'">Todos</button>
    <button @click="selectedStatus = 'on-budget'">No Orçamento</button>
    <button @click="selectedStatus = 'alert'">Alertas</button>
  </div>
  
  <DepartmentTable :departments="filteredDepts" />
</template>
```

### Exemplo 3: Modal/Dialog

```vue
<script setup lang="ts">
const isOpen = ref(false)

const openModal = () => isOpen.value = true
const closeModal = () => isOpen.value = false
</script>

<template>
  <button @click="openModal">Abrir Modal</button>

  <!-- Backdrop -->
  <Transition>
    <div 
      v-if="isOpen"
      class="fixed inset-0 bg-black/50 z-40"
      @click="closeModal"
    />
  </Transition>

  <!-- Modal -->
  <Transition>
    <div 
      v-if="isOpen"
      class="fixed inset-0 flex items-center justify-center z-50"
    >
      <div class="bg-surface-container-lowest rounded-xl p-6 max-w-md">
        <!-- Conteúdo -->
        <button @click="closeModal">Fechar</button>
      </div>
    </div>
  </Transition>
</template>
```

## 🧪 Boas Práticas

### 1. Composables para Lógica Compartilhada
```typescript
// ✅ Bom
export const useFormSubmit = () => {
  const formData = ref({})
  const submit = async () => { /* ... */ }
  return { formData, submit }
}

// ❌ Evitar
// Lógica em componentes = difícil reutilizar
```

### 2. Props com TypeScript
```typescript
// ✅ Bom
interface Props {
  id: string
  title: string
  isLoading?: boolean
}
defineProps<Props>()

// ❌ Evitar
defineProps(['id', 'title', 'isLoading'])
```

### 3. Emits Tipados
```typescript
// ✅ Bom
const emit = defineEmits<{
  update: [value: string]
  close: []
}>()

// ❌ Evitar
const emit = defineEmits(['update', 'close'])
```

### 4. Classes Dinâmicas
```vue
<!-- ✅ Bom -->
<div :class="[
  'base-class',
  isActive && 'active-class',
  status === 'danger' ? 'text-error' : 'text-success'
]" />

<!-- ❌ Evitar -->
<div :class="{ 'active': isActive }" />
```

## 📈 Performance

### Code Splitting
Vite automaticamente faz split de código:
```typescript
// Componentes lazy-loaded
const Dashboard = defineAsyncComponent(() => 
  import('./components/Dashboard.vue')
)
```

### CSS Purging
Tailwind remove CSS não usado automaticamente:
```
Original:   ~20KB
Minified:   ~5KB
Gzipped:    ~1.2KB
```

### Tree Shaking
```typescript
// ✅ Exporta apenas o usado
export const useDashboard = () => { /* ... */ }

// ❌ Evitar exports genéricos
export default { useDashboard, useModal, ... }
```

## 🐛 Debugging

### Vue DevTools
```bash
# Instalar extensão no navegador
# Chrome: Vue.js devtools
# Firefox: Vue.js devtools
```

### Console Logs
```typescript
// ✅ Bom
const metrics = computed(() => {
  const result = data.value.filter(...)
  console.log('metrics computed:', result)
  return result
})

// Em DevTools: Abra "Composition API" panel
```

## 🔐 Segurança

### XSS Prevention (Vue Automático)
```vue
<!-- ✅ Seguro - Vue escapa HTML -->
<div>{{ userInput }}</div>

<!-- ⚠️ Apenas para HTML confiável -->
<div v-html="safeHtml"></div>
```

### CORS
```typescript
// API chamadas com headers apropriados
fetch('/api/data', {
  headers: {
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${token}`
  }
})
```

## 📚 Próximos Passos

### Funcionalidades Recomendadas
1. ✅ Adicionar paginação na tabela
2. ✅ Implementar busca/filtros
3. ✅ Adicionar dark mode
4. ✅ Gráficos interativos (Chart.js)
5. ✅ Exportar dados (CSV/PDF)
6. ✅ Notificações em tempo real

### Dependências Úteis
```bash
# Gráficos
npm install chart.js vue-chartjs

# Data picker
npm install vue-datepicker

# Validação
npm install vee-validate zod

# HTTP Client
npm install axios

# State Management
npm install pinia

# Roteamento
npm install vue-router

# i18n (Internacionalização)
npm install vue-i18n
```

## 💡 Tips & Tricks

### Reutilizar Composition
```typescript
// Em Dashboard.vue
const { metrics, departments } = useDashboard()
const { isOpen, toggle } = useSidebar()

// Combine múltiplos composables
```

### Watch Automático
```typescript
const watch = watchEffect(() => {
  // Executa automaticamente quando deps mudam
  console.log('Metrics atualizadas:', metrics.value)
})

// Cleanup
onUnmounted(() => {
  // watch automático é limpo
})
```

### Transições Suaves
```vue
<Transition
  enter-active-class="transition duration-300"
  leave-active-class="transition duration-300"
  enter-from-class="opacity-0"
  leave-to-class="opacity-0"
>
  <div v-if="isVisible">Conteúdo</div>
</Transition>
```

## 📞 Suporte

Para dúvidas ou issues:
1. Consulte a [documentação Vue 3](https://vuejs.org)
2. Verifique [Tailwind CSS docs](https://tailwindcss.com)
3. Abra issue no repositório

---

**Desenvolvido com ❤️ | Vue 3 + Composition API + Tailwind CSS**
