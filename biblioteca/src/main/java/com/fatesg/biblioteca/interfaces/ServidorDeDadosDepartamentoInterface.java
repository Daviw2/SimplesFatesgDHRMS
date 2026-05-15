package com.fatesg.biblioteca.interfaces;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.fatesg.biblioteca.dtos.DepartamentoDto;

public interface ServidorDeDadosDepartamentoInterface extends Remote{
    DepartamentoDto obterDepartamentoPorId(String id) throws RemoteException;
    List<DepartamentoDto> listarDepartamentos(int limite, int offset) throws RemoteException;
    int obterQtdeDepartamentos() throws RemoteException;
}
