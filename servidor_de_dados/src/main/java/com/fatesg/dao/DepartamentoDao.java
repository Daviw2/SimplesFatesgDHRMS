package com.fatesg.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.fatesg.config.DbConfig;
import com.fatesg.model.Departamento;

public class DepartamentoDao implements DaoInterface<Departamento> {
    private Connection conn;

    public DepartamentoDao(Connection conn) {
        this.conn = conn;
    }

    public Departamento Buscar(String id) throws Exception {
        String sql = """
                SELECT dept_no, dept_name
                FROM departments
                WHERE dept_no = ?
                LIMIT 1
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();

        Departamento departamento = null;
        if (rs.next()) {
            String deptNo = rs.getString("dept_no");
            String deptName = rs.getString("dept_name");
            departamento = new Departamento(deptNo, deptName);
        }

        rs.close();
        pstmt.close();

        return departamento;
    }

    public ArrayList<Departamento> Listar() throws Exception {
        return Listar(DbConfig.DEFAULT_LIMIT, 0);
    }

    public ArrayList<Departamento> Listar(int limit, int offset) throws Exception {
        String sql = """
                SELECT dept_no, dept_name
                FROM departments
                ORDER BY dept_name
                LIMIT ?
                OFFSET ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Departamento> departamentos = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("dept_no");
            String nome = rs.getString("dept_name");
            departamentos.add(new Departamento(id, nome));
        }

        rs.close();
        pstmt.close();

        return departamentos;
    }

    public int Contar() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM departments";
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
