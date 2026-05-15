<script setup>
import { ref, onMounted } from 'vue';
import { departamentosAPI } from '@/services/api';

const departamentos = ref([]);
const loading = ref(false);
const totalRecords = ref(0);
const rows = ref(15);
const first = ref(0);

const onPage = async (event) => {
  loading.value = true;
  try {
    const page = Math.floor(event.first / event.rows);
    const data = await departamentosAPI.list(page, event.rows);
    departamentos.value = data;
    totalRecords.value = data.length > 0 ? data.length + event.first + 100 : event.first + event.rows;
    loading.value = false;
  } catch (error) {
    console.error('Erro ao carregar departamentos:', error);
    loading.value = false;
  }
};

onMounted(async () => {
  loading.value = true;
  try {
    const data = await departamentosAPI.list(0, rows.value);
    departamentos.value = data;
    totalRecords.value = data.length > 0 ? 1000 : 0;
    loading.value = false;
  } catch (error) {
    console.error('Erro ao carregar departamentos:', error);
    loading.value = false;
  }
});
</script>

<template>
  <div class="departamentos-container">
    <h1>Departamentos</h1>

    <DataTable
      :value="departamentos"
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
      <Column field="descricao" header="Descrição"></Column>
      <Column field="gerente" header="Gerente"></Column>
    </DataTable>
  </div>
</template>

<style scoped>
.departamentos-container {
  padding: 2rem;
}

h1 {
  margin-bottom: 2rem;
  color: #333;
}
</style>
