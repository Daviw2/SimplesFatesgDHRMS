package com.fatesg.biblioteca.interfaces;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.fatesg.biblioteca.dtos.FuncionarioDto;

public interface ServidorDeDadosFuncionarioInterface extends Remote{
    FuncionarioDto obterFuncionarioPorId(int id) throws RemoteException;
    List<FuncionarioDto> listarFuncionarios(int limite, int offset) throws RemoteException;
    int obterQtdeFuncionarios() throws RemoteException;
}
