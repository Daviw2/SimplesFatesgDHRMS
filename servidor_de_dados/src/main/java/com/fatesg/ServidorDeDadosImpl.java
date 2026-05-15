package com.fatesg;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.fatesg.biblioteca.dtos.DepartamentoDto;
import com.fatesg.biblioteca.dtos.FuncionarioDto;
import com.fatesg.biblioteca.dtos.SalarioDto;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosDepartamentoInterface;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosFuncionarioInterface;
import com.fatesg.biblioteca.interfaces.ServidorDeDadosSalarioInterface;
import com.fatesg.log.Log;
import com.fatesg.services.DepartamentoService;
import com.fatesg.services.FuncionarioService;
import com.fatesg.services.SalarioService;

public class ServidorDeDadosImpl extends UnicastRemoteObject
        implements ServidorDeDadosDepartamentoInterface,
        ServidorDeDadosFuncionarioInterface,
        ServidorDeDadosSalarioInterface {

    private int porta;

    protected ServidorDeDadosImpl(int porta) throws RemoteException {
        super();
        this.porta = porta;
    }

    @Override
    public List<FuncionarioDto> listarFuncionarios(int limite, int offset) throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para listar funcionários com limite " + limite + " e offset "
                + offset);
        return new FuncionarioService().listarFuncionarios(limite, offset);
    }

    @Override
    public FuncionarioDto obterFuncionarioPorId(int id) throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para obter funcionário por ID: " + id);
        return new FuncionarioService().obterFuncionarioPorId(id);
    }

    @Override
    public List<DepartamentoDto> listarDepartamentos(int limite, int offset) throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para listar departamentos com limite " + limite + " e offset "
                + offset);
        return new DepartamentoService().listarDepartamentos(limite, offset);
    }

    @Override
    public DepartamentoDto obterDepartamentoPorId(String id) throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para obter departamento por ID: " + id);
        return new DepartamentoService().obterDepartamentoPorId(id);
    }

    @Override
    public List<SalarioDto> listarSalarios(int limite, int offset) throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para listar salários com limite " + limite + " e offset "
                + offset);
        return new SalarioService().listarSalarios(limite, offset);
    }

    @Override
    public int obterQtdeSalarios() throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para obter quantidade de salários");
        return new SalarioService().obterQtdeSalarios();
    }

    @Override
    public int obterQtdeFuncionarios() throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para obter quantidade de funcionários");
        return new FuncionarioService().obterQtdeFuncionarios();
    }

    @Override
    public int obterQtdeDepartamentos() throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para obter quantidade de departamentos");
        return new DepartamentoService().obterQtdeDepartamentos();
    }

    @Override
    public SalarioDto obterSalarioPorId(int id) throws RemoteException {
        Log.info("[" + porta + "] Requisição recebida para obter salario por ID: " + id);
        return new SalarioService().obterSalarioPorId(id);
    }
}
