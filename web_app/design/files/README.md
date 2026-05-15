# HR Pulse Enterprise - Vue 3 Dashboard

Uma implementação moderna do dashboard HR Pulse Enterprise em **Vue 3 com Composition API**, mantendo total coerência visual com o design system Enterprise HR.

## 🎯 Características

- ✨ **Vue 3** - Framework reativo moderno com Composition API
- 🎨 **Design System Integrado** - Cores, tipografia e componentes seguem o DESIGN.md
- 📱 **Totalmente Responsivo** - Mobile-first com breakpoints para tablet e desktop
- ⚡ **Tailwind CSS** - Utility-first CSS framework com configuração custom
- 🔧 **TypeScript** - Type safety completo
- 📦 **Modular** - Arquitetura de componentes reutilizáveis
- 🎭 **Material Icons** - Ícones via Google Material Symbols
- 🌐 **Internacionalizado** - Conteúdo em Português (BR)

## 📂 Estrutura do Projeto

```
├── src/
│   ├── components/
│   │   ├── cards/
│   │   │   ├── MetricCard.vue          # Card de métricas principais
│   │   │   ├── TrendChart.vue          # Gráfico de tendências
│   │   │   ├── FeaturedCard.vue        # Card destaque (folha de pagamento)
│   │   │   └── DistributionCard.vue    # Gráfico de distribuição por gênero
│   │   ├── tables/
│   │   │   └── DepartmentTable.vue     # Tabela de departamentos
│   │   ├── App.vue                     # Componente raiz
│   │   ├── Header.vue                  # Cabeçalho com navegação
│   │   ├── Sidebar.vue                 # Barra lateral (responsiva)
│   │   ├── Dashboard.vue               # Página principal
│   │   ├── Footer.vue                  # Rodapé
│   │   ├── BottomNav.vue               # Navegação mobile
│   │   └── StatusBadge.vue             # Badge de status
│   ├── style.css                       # Estilos globais e utilities
│   ├── main.ts                         # Entry point da aplicação
│   └── vite-env.d.ts                   # Tipos Vite
├── index.html                          # HTML raiz
├── vite.config.ts                      # Configuração Vite
├── tailwind.config.ts                  # Configuração Tailwind
├── tsconfig.json                       # Configuração TypeScript
├── tsconfig.node.json                  # TypeScript para Node
├── package.json                        # Dependências
├── postcss.config.js                   # Configuração PostCSS (gerado automaticamente)
└── README.md                           # Este arquivo
```

## 🚀 Como Iniciar

### Pré-requisitos
- Node.js 16+ 
- npm ou yarn

### Instalação

```bash
# Clone ou copie o projeto
cd hr-pulse-enterprise-vue3

# Instale as dependências
npm install

# Inicie o servidor de desenvolvimento
npm run dev
```

A aplicação abrirá automaticamente em `http://localhost:5173`

### Build para Produção

```bash
# Build otimizado
npm run build

# Visualizar build localmente
npm run preview
```

## 🎨 Design System

O projeto implementa completamente o **Enterprise HR Design System** com:

### Cores
- **Primary (Preto)**: `#000000` - Navegação e elementos principais
- **Secondary (Slate Gray)**: `#515f74` - Elementos secundários
- **Tertiary (Verde Esmeralda)**: `#6ffbbe` - Actions e sucesso
- **Surfaces**: Escala completa de grays para separação visual
- **Error**: `#ba1a1a` - Estados de alerta

### Tipografia (Inter)
- **Display LG**: 48px, font-weight 700
- **Headline LG**: 32px, font-weight 600
- **Headline MD**: 24px, font-weight 600
- **Body MD**: 16px, font-weight 400
- **Label MD**: 14px, font-weight 500

### Espaçamento
- **Unit**: 4px (baseline grid)
- **Gutter**: 24px (espaço entre elementos)
- **Desktop Margin**: 40px
- **Mobile Margin**: 16px

### Componentes
Todos os componentes seguem o design system:
- **Cards**: Border 1px, shadow-sm, rounded-lg (4px)
- **Botões**: Primary (solid), Secondary (ghost), Estados hover
- **Inputs**: 40px height, border focus blue, 2px soft glow
- **Tabelas**: Headers em label-sm, zebra striping (>20 rows)
- **Badges**: Pill-shaped com cor semântica

## 📱 Responsividade

### Breakpoints
- **Mobile**: <640px
- **Tablet**: 640px - 1023px
- **Desktop**: 1024px+

### Comportamentos
- **Sidebar**: Collapsa em hambúrguer (mobile), overlay com backdrop
- **Tabelas**: Vira card list em mobile
- **Grid**: 4 colunas mobile, 8 tablet, 12 desktop
- **Tipografia**: Font-size reduzido em mobile

## 🔧 Composables (Hooks)

Estrutura de composables reutilizáveis para lógica compartilhada:

```typescript
// Exemplo de uso em qualquer componente
import { ref, computed } from 'vue'

export const useDashboard = () => {
  const metrics = ref([...])
  const filtered = computed(() => metrics.value.filter(...))
  
  return { metrics, filtered }
}
```

## 🧩 Componentes Principais

### App.vue
Layout raiz com:
- Gerenciamento de estado do sidebar (mobile)
- Grid principal (sidebar + main content)
- Integração de Header, Dashboard, Footer

### Header.vue
Cabeçalho com:
- Botão de menu mobile
- Breadcrumb navigation
- Search bar
- Notificações
- Profile menu

### Sidebar.vue
Barra lateral com:
- Logo da empresa
- Navegação principal (Dashboard, Departments, Employees)
- Seção de ações (Quick Action, Settings, Support)
- Toggle responsivo

### Dashboard.vue
Página principal com layout de grid:
- Métricas chave (2 colunas)
- Gráfico de tendências (full-width)
- Card destaque (folha de pagamento)
- Gráfico de distribuição (rosca)
- Tabela de departamentos

### DepartmentTable.vue
Tabela de dados com:
- Headers styling
- Rows com hover effect
- Status badges
- Avatar com iniciais
- Scroll horizontal em mobile

## 🎭 Composição de Componentes

Exemplo de uso da Composition API:

```vue
<script setup lang="ts">
import { ref, computed } from 'vue'

interface Metric {
  id: string
  title: string
  value: number
}

const metrics = ref<Metric[]>([...])

const total = computed(() => 
  metrics.value.reduce((sum, m) => sum + m.value, 0)
)

const handleSort = (field: string) => {
  // Lógica de ordenação
}
</script>

<template>
  <div>
    <MetricCard 
      v-for="metric in metrics"
      :key="metric.id"
      :metric="metric"
    />
  </div>
</template>
```

## 🎯 Padrões de Desenvolvimento

### Naming Conventions
- Componentes: PascalCase (`MetricCard.vue`)
- Props/Data: camelCase (`isOpen`, `handleClick`)
- Classes CSS: kebab-case (`metric-card`, `btn-primary`)

### Type Safety
```typescript
interface Props {
  metric: Metric
  isLoading?: boolean
}

defineProps<Props>()
```

### Composables Pattern
```typescript
const { data, loading, error } = useData()
const { toggle, isOpen } = useSidebar()
```

## 📊 Gráficos

### TrendChart
- SVG-based line chart
- Grid lines com dashes
- Área preenchida sotto a curve
- Data points marcados
- Responsivo

### DistributionCard
- Gráfico de rosca SVG
- Percentuais calculados
- Legenda com cores
- Total no centro

## 🎨 Utilities CSS Personalizadas

Em `style.css`:
- Typography utilities (`.text-headline-lg`, etc.)
- Button variants (`.btn-primary`, `.btn-ghost`)
- Card styles (`.card`, `.card-elevated`)
- Input styles (`.input`)
- Focus rings

## 🔌 Integração com APIs

Estrutura pronta para integração:

```typescript
// services/api.ts
export const fetchMetrics = async () => {
  const response = await fetch('/api/metrics')
  return response.json()
}

// Em componentes
const { data: metrics } = await fetchMetrics()
```

## 🌙 Dark Mode (Preparado)

Estrutura preparada para dark mode:
```typescript
// Em tailwind.config.ts
darkMode: 'class'

// Em componentes
class="dark:bg-dark-surface dark:text-dark-on-surface"
```

## 📈 Performance

- Code splitting automático (Vite)
- Tree-shaking de CSS unused (Tailwind)
- Lazy loading de componentes (Vue 3)
- Image optimization ready
- Caching headers configurável

## 🧪 Testing (Setup)

Estrutura pronta para testes:

```bash
# Instalar dependências
npm install -D vitest @vue/test-utils @testing-library/vue

# Criar arquivo test
# componentes/__tests__/MetricCard.spec.ts
```

## 📚 Recursos

- [Vue 3 Documentation](https://vuejs.org)
- [Tailwind CSS](https://tailwindcss.com)
- [Material Icons](https://fonts.google.com/icons)
- [Design System](./DESIGN.md)

## 📝 Changelog

### v1.0.0 (Inicial)
- ✨ Implementação completa em Vue 3 Composition API
- 🎨 Design system totalmente integrado
- 📱 Responsividade mobile-first
- 🔧 TypeScript com type safety

## 📄 Licença

Este projeto segue a mesma licença do design system original.

## 👥 Contribuindo

Para contribuir:
1. Crie uma branch para sua feature
2. Mantenha a coerência visual do design system
3. Use TypeScript para novos componentes
4. Teste em mobile, tablet e desktop
5. Envie um pull request

---

**Desenvolvido com ❤️ usando Vue 3 e Tailwind CSS**
