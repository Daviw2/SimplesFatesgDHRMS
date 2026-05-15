package com.fatesg.web_api.apis;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import com.fatesg.biblioteca.dtos.FuncionarioDto;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosFuncionarioInterface;
import com.fatesg.web_api.configs.RmiConfig;

@Service
public class ServidorDeDadosFuncionarioApi implements ServidorDeDadosFuncionarioInterface {

    private ArrayList<ServidorDeDadosFuncionarioInterface> servidores = new ArrayList<>();

    @PostConstruct
    public void Conectar() {
        this.servidores.clear();
        AddServico(RmiConfig.RMI_SERVICE_NAME, RmiConfig.RMI_HOST, RmiConfig.RMI_PORT);
        AddServico(RmiConfig.RMI_SERVICE_NAME, RmiConfig.RMI_HOST_SECOND, RmiConfig.RMI_PORT_SECOND);
    }

    @Override
    public List<FuncionarioDto> listarFuncionarios(int limite, int offset) throws RemoteException {
        return this.getFuncionarios(limite, offset, true);
    }

    @Override
    public FuncionarioDto obterFuncionarioPorId(int id) throws RemoteException {
        return this.getFuncionarioById(id, true);
    }

    @Override
    public int obterQtdeFuncionarios() throws RemoteException {
        return this.getQtdeFuncionarios(true);
    }

    private void AddServico(String serviceName, String host, int port) {
        try {
            Registry registry = LocateRegistry.getRegistry(host, port);
            ServidorDeDadosFuncionarioInterface servico = (ServidorDeDadosFuncionarioInterface) registry.lookup(serviceName);
            this.servidores.add(servico);
        } catch (Exception e) {
            System.err.println("Aviso: Nao foi possivel conectar em " + host + ":" + port);
        }
    }

    private List<FuncionarioDto> getFuncionarios(int limite, int offset, boolean firstTime) {
        for (ServidorDeDadosFuncionarioInterface s : this.servidores) {
            try {
                return s.listarFuncionarios(limite, offset);
            } catch (RemoteException e) {
            }
        }
        if (firstTime) {
            Conectar();
            return getFuncionarios(limite, offset, false);
        }
        return new ArrayList<>();
    }

    private FuncionarioDto getFuncionarioById(int id, boolean firstTime) {
        for (ServidorDeDadosFuncionarioInterface s : this.servidores) {
            try {
                return s.obterFuncionarioPorId(id);
            } catch (RemoteException e) {
            }
        }
        if (firstTime) {
            Conectar();
            return getFuncionarioById(id, false);
        }
        return null;
    }

    private int getQtdeFuncionarios(boolean firstTime) {
        for (ServidorDeDadosFuncionarioInterface s : this.servidores) {
            try {
                return s.obterQtdeFuncionarios();
            } catch (RemoteException e) {
            }
        }
        if (firstTime) {
            Conectar();
            return getQtdeFuncionarios(false);
        }
        return 0;
    }
}