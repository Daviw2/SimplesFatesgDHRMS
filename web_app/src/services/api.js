const API_BASE_URL = "http://localhost:8080/api";

export const funcionariosAPI = {
    list: async (page = 0, rows = 15) => {
        try {
            const offset = page * rows;
            const response = await fetch(
                `${API_BASE_URL}/funcionarios?limite=${rows}&offset=${offset}`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                },
            );
            if (!response.ok) throw new Error("Erro ao buscar funcionários");
            return await response.json();
        } catch (error) {
            console.error("Erro em funcionariosAPI.list:", error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await fetch(`${API_BASE_URL}/funcionarios/${id}`, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            });
            if (!response.ok) throw new Error("Erro ao buscar funcionário");
            return await response.json();
        } catch (error) {
            console.error("Erro em funcionariosAPI.getById:", error);
            throw error;
        }
    },
};

export const departamentosAPI = {
    list: async (page = 0, rows = 15) => {
        try {
            const offset = page * rows;
            const response = await fetch(
                `${API_BASE_URL}/departamentos?limite=${rows}&offset=${offset}`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                },
            );
            if (!response.ok) throw new Error("Erro ao buscar departamentos");
            return await response.json();
        } catch (error) {
            console.error("Erro em departamentosAPI.list:", error);
            throw error;
        }
    },

    getById: async (id) => {
        try {
            const response = await fetch(
                `${API_BASE_URL}/departamentos/${id}`,
                {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                },
            );
            if (!response.ok) throw new Error("Erro ao buscar departamento");
            return await response.json();
        } catch (error) {
            console.error("Erro em departamentosAPI.getById:", error);
            throw error;
        }
    },
};
