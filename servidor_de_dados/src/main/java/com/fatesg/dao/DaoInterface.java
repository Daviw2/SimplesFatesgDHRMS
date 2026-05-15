package com.fatesg.dao;

import java.util.ArrayList;

public interface DaoInterface<T> {
    public ArrayList<T> Listar() throws Exception;
    public ArrayList<T> Listar(int limit, int offset) throws Exception;
    public T Buscar(String id) throws Exception;
    public int Contar() throws Exception;
}
