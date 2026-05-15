package com.fatesg.apis;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosSalarioInterface;
import com.fatesg.config.RmiConfig;

public class ServidorDeDadosSalarioApi implements ServidorDeDadosSalarioInterface {
    private ArrayList<ServidorDeDadosSalarioInterface> servidores;

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

    private void AddServico(String serviceName, String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            var servico = (ServidorDeDadosSalarioInterface) registry.lookup(serviceName);
            this.servidores.add(servico);
        } catch (RemoteException e) {
            System.err.println("Erro na comunicação com o servidor no construtor de DepartamentosService:");
            // e.printStackTrace();
        } catch (NotBoundException e) {
            System.err.println("Serviço não encontrado no registry no construtor de DepartamentosService:");
            // e.printStackTrace();
        }
    }

    @Override
    public List<SalarioDto> listarSalarios(int limite, int offset) throws RemoteException {
        for (var s : this.servidores) {
            try {
                var salarios = s.listarSalarios(limite, offset);
                return salarios;
            } catch (RemoteException e) {
                System.err.println("[" + s.toString() + "] Erro na comunicação com o servidor no getListarSalarios:");
            }
        }
        throw new RemoteException("Nenhum servidor disponível para listar salários.");
    }

    @Override
    public int obterQtdeSalarios() throws RemoteException {
        for (var s : this.servidores) {
            try {
                var qtde = s.obterQtdeSalarios();
                return qtde;
            } catch (RemoteException e) {
                System.err.println("[" + s.toString() + "] Erro na comunicação com o servidor no getQtdeSalarios:");
            }
        }
        throw new RemoteException("Nenhum servidor disponível para obter quantidade de salários.");
    }

    @Override
    public SalarioDto obterSalarioPorId(int id) throws RemoteException {
        for (var s : this.servidores) {
            try {
                var salario = s.obterSalarioPorId(id);
                return salario;
            } catch (RemoteException e) {
                System.err.println("[" + s.toString() + "] Erro na comunicação com o servidor no obterSalarioPorId: "+id);
            }
        }
        throw new RemoteException("Nenhum servidor disponível para obter salários.");

    }
}
