package com.fatesg.web_api.apis;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosDepartamentoInterface;
import com.fatesg.web_api.configs.RmiConfig;

public class ServidorDeDadosDepartamentoApi implements ServidorDeDadosDepartamentoInterface {
    private ArrayList<ServidorDeDadosDepartamentoInterface> servidores;

    public void Conectar() {
        if (this.servidores != null && !this.servidores.isEmpty()) {
            this.servidores.clear();
        } else {
            this.servidores = new ArrayList<>();
        }

        AddServico(RmiConfig.RMI_SERVICE_NAME, RmiConfig.RMI_HOST, RmiConfig.RMI_PORT);

        AddServico(RmiConfig.RMI_SERVICE_NAME, RmiConfig.RMI_HOST_SECOND, RmiConfig.RMI_PORT_SECOND);

        if (this.servidores.isEmpty()) {
            System.err.println("Nenhum servidor de departamentos disponível. Verifique as conexões RMI.");
        }
    }

    @Override
    public List<DepartamentoDto> listarDepartamentos(int limite, int offset) throws RemoteException {
        return this.getDepartamentos(limite, offset, true);
    }

    @Override
    public DepartamentoDto obterDepartamentoPorId(String id) throws RemoteException {
        return this.obterDepartamento(id, true);
    }

    @Override
    public int obterQtdeDepartamentos() throws RemoteException {
        return this.obterQtdeDepartamentos(true);
    }

    private void AddServico(String serviceName, String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            var servico = (ServidorDeDadosDepartamentoInterface) registry.lookup(serviceName);
            this.servidores.add(servico);
        } catch (RemoteException e) {
            System.err.println("Erro na comunicação com o servidor no construtor de DepartamentosService:");
            // e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("Serviço não encontrado no registry no construtor de DepartamentosService:");
            // e.printStackTrace();
        }
    }

    private List<DepartamentoDto> getDepartamentos(int limite, int offset, boolean firstTime) {
        for (var s : this.servidores) {
            try {
                var departamentos = s.listarDepartamentos(limite, offset);
                return departamentos;
            } catch (RemoteException e) {
                System.err.println("[" + s.toString() + "] Erro na comunicação com o servidor no getDepartamentos:");
            }
        }

        if (firstTime) {
            System.out.println("Tentando reconectar aos servidores...");
            this.Conectar();
            return this.getDepartamentos(limite, offset, false);
        }

        throw new RuntimeException("Erro na comunicação com o servidor ao obter departamentos");
    }

    private DepartamentoDto obterDepartamento(String id, boolean firstTime) {
        for (var s : this.servidores) {
            try {
                var departamentos = s.obterDepartamentoPorId(id);
                return departamentos;
            } catch (RemoteException e) {
                System.err.println(
                        "[" + s.toString() + "] Erro na comunicação com o servidor no obterDepartamentoPorId:");
            }
        }

        if (firstTime) {
            System.out.println("Tentando reconectar aos servidores...");
            this.Conectar();
            return this.obterDepartamento(id, false);
        }

        throw new RuntimeException("Erro na comunicação com o servidor ao obter departamentos");
    }

    private int obterQtdeDepartamentos(boolean firstTime) {
        for (var s : this.servidores) {
            try {
                var departamentos = s.obterQtdeDepartamentos();
                return departamentos;
            } catch (RemoteException e) {
                System.err.println(
                        "[" + s.toString() + "] Erro na comunicação com o servidor no obterQtdeDepartamentos:");
            }
        }

        if (firstTime) {
            System.out.println("Tentando reconectar aos servidores...");
            this.Conectar();
            return this.obterQtdeDepartamentos(false);
        }

        throw new RuntimeException("Erro na comunicação com o servidor ao obter quantidade de departamentos");
    }
}
