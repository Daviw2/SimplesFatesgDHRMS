import { defineStore } from "pinia";
import { ref } from "vue";

export const useLayoutStore = defineStore("layout", () => {
    const sidebarVisible = ref(true);

    const toggleSidebar = () => {
        sidebarVisible.value = !sidebarVisible.value;
    };

    return {
        sidebarVisible,
        toggleSidebar,
    };
});
