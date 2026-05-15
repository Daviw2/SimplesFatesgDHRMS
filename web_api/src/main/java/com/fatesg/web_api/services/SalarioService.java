package com.fatesg.web_api.services;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.web_api.apis.ServidorDeDadosSalarioApi;

@Service
public class SalarioService {
    ServidorDeDadosSalarioApi stub;

    public SalarioService() {
        this.stub = new ServidorDeDadosSalarioApi();
        this.stub.Conectar();
    }

    public SalarioDto obterSalarioPorIdFuncionario(int idFuncionario)
    {
        try {
            return stub.obterSalarioPorId(idFuncionario);
        } catch (RemoteException e) {
            System.err.println("[ERROR] Erro no obterSalarioPorIdFuncionario: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public List<SalarioDto> obterSalarios(int limit, int offset){
        try {
            return stub.listarSalarios(limit, offset);
        } catch (RemoteException e) {
            System.err.println("[ERROR] Erro no obterSalarios: "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
