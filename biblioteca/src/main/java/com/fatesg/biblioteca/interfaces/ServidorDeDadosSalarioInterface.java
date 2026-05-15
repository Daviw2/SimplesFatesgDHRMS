package com.fatesg.biblioteca.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import com.fatesg.biblioteca.dtos.SalarioDto;

public interface ServidorDeDadosSalarioInterface extends Remote {
    List<SalarioDto> listarSalarios(int limite, int offset) throws RemoteException;
    SalarioDto obterSalarioPorId(int idFuncionario) throws RemoteException;
    int obterQtdeSalarios() throws RemoteException;
}
