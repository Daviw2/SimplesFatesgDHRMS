<script setup>
import { ref, onMounted } from 'vue';
import { funcionariosAPI } from '@/services/api';

const funcionarios = ref([]);
const loading = ref(false);
const totalRecords = ref(0);
const rows = ref(15);
const first = ref(0);
const lazyParams = ref({
  first: 0,
  rows: 15,
  sortOrder: null,
  sortField: null,
  filters: {},
});

const onPage = async (event) => {
  lazyParams.value = event;
  loading.value = true;
  try {
    const page = Math.floor(event.first / event.rows);
    const data = await funcionariosAPI.list(page, event.rows);
    funcionarios.value = data;
    totalRecords.value = data.length > 0 ? data.length + event.first + 100 : event.first + event.rows;
    loading.value = false;
  } catch (error) {
    console.error('Erro ao carregar funcionários:', error);
    loading.value = false;
  }
};

onMounted(async () => {
  loading.value = true;
  try {
    const data = await funcionariosAPI.list(0, rows.value);
    funcionarios.value = data;
    totalRecords.value = data.length > 0 ? 1000 : 0;
    loading.value = false;
  } catch (error) {
    console.error('Erro ao carregar funcionários:', error);
    loading.value = false;
  }
});
</script>

<template>
  <div class="funcionarios-container">
    <h1>Funcionários</h1>

    <DataTable
      :value="funcionarios"
      :lazy="true"
      :paginator="true"
      :rows="rows"
      :totalRecords="totalRecords"
      :loading="loading"
      :first="first"
      @page="onPage"
      responsiveLayout="scroll"
      striped-rows
      showGridlines
    >
      <Column field="id" header="ID" :style="{ width: '100px' }"></Column>
      <Column field="nome" header="Nome"></Column>
      <Column field="sobrenome" header="Sobrenome"></Column>
      <Column field="sexo" header="Sexo"></Column>
      <Column field="dataNascimento" header="Data de Nascimento"></Column>
    </DataTable>
  </div>
</template>

<style scoped>
.funcionarios-container {
  padding: 2rem;
}

h1 {
  margin-bottom: 2rem;
  color: #333;
}
</style>
