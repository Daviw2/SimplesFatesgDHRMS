package com.fatesg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.fatesg.config.DbConfig;
import com.fatesg.model.DepartamentoGerente;

public class DepartamentoGerenteDao implements DaoInterface<DepartamentoGerente> {
    private Connection conn;

    public DepartamentoGerenteDao(Connection conn) {
        this.conn = conn;
    }

    public DepartamentoGerente Buscar(String id) throws Exception {
        var regexNumeros = "\\d+";
        if (id == null || id.isEmpty() || !id.matches(regexNumeros)) {
            throw new IllegalArgumentException("ID não pode ser nulo ou vazio");
        }

        String sql = """
                SELECT dept_no, emp_no, from_date, to_date
                FROM dept_manager
                WHERE to_date >= CURRENT_DATE
                AND emp_no = ?
                LIMIT 1
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        DepartamentoGerente departamentoGerente = null;
        if (rs.next()) {
            String deptNo = rs.getString("dept_no");
            int empNo = rs.getInt("emp_no");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            departamentoGerente = new DepartamentoGerente(deptNo, empNo, desde, ate);
        }

        rs.close();
        pstmt.close();

        return departamentoGerente;
    }

    public ArrayList<DepartamentoGerente> Listar() throws Exception {
        return Listar(DbConfig.DEFAULT_LIMIT, 0);
    }

    public ArrayList<DepartamentoGerente> Listar(int limit, int offset) throws Exception {
        String sql = """
                SELECT dept_no, emp_no, from_date, to_date
                FROM dept_manager
                WHERE to_date >= CURRENT_DATE
                ORDER BY dept_no, emp_no
                LIMIT ?
                OFFSET ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<DepartamentoGerente> departamentosGerentes = new ArrayList<>();
        while (rs.next()) {
            String deptNo = rs.getString("dept_no");
            int empNo = rs.getInt("emp_no");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            departamentosGerentes.add(new DepartamentoGerente(deptNo, empNo, desde, ate));
        }

        rs.close();
        pstmt.close();

        return departamentosGerentes;
    }

    public int Contar() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM dept_manager WHERE to_date >= CURRENT_DATE";
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
