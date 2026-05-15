package com.fatesg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.fatesg.config.DbConfig;
import com.fatesg.model.DepartamentoFuncionario;

public class DepartamentoFuncionarioDao implements DaoInterface<DepartamentoFuncionario> {
    private Connection conn;

    public DepartamentoFuncionarioDao(Connection conn) {
        this.conn = conn;
    }

    public DepartamentoFuncionario Buscar(String id) throws Exception {
        var regexNumeros = "\\d+";
        if (id == null || id.isEmpty() || !id.matches(regexNumeros)) {
            throw new IllegalArgumentException("ID não pode ser nulo ou vazio");
        }

        String sql = """
                SELECT dept_no, emp_no, from_date, to_date
                FROM dept_emp
                WHERE to_date >= CURRENT_DATE
                AND emp_no = ?
                LIMIT 1
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        DepartamentoFuncionario departamentoFuncionario = null;
        if (rs.next()) {
            String deptNo = rs.getString("dept_no");
            int empNo = rs.getInt("emp_no");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            departamentoFuncionario = new DepartamentoFuncionario(deptNo, empNo, desde, ate);
        }

        rs.close();
        pstmt.close();

        return departamentoFuncionario;
    }

    public ArrayList<DepartamentoFuncionario> Listar() throws Exception {
        return Listar(DbConfig.DEFAULT_LIMIT, 0);
    }

    public ArrayList<DepartamentoFuncionario> Listar(int limit, int offset) throws Exception {
        String sql = """
                SELECT dept_no, emp_no, from_date, to_date
                FROM dept_emp
                WHERE to_date >= CURRENT_DATE
                ORDER BY dept_no, emp_no
                LIMIT ?
                OFFSET ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<DepartamentoFuncionario> departamentosFuncionarios = new ArrayList<>();
        while (rs.next()) {
            String deptNo = rs.getString("dept_no");
            int empNo = rs.getInt("emp_no");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            departamentosFuncionarios.add(new DepartamentoFuncionario(deptNo, empNo, desde, ate));
        }

        rs.close();
        pstmt.close();

        return departamentosFuncionarios;
    }

    public int Contar() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM dept_emp WHERE to_date >= CURRENT_DATE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        int total = 0;
        if (rs.next()) {
            total = rs.getInt("total");
        }

        rs.close();
        stmt.close();

        return total;
    }
}
