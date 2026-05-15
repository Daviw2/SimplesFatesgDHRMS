package com.fatesg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.fatesg.config.DbConfig;
import com.fatesg.model.Salario;

public class SalarioDao implements DaoInterface<Salario> {
    private Connection conn;

    public SalarioDao(Connection conn) {
        this.conn = conn;
    }

    public Salario Buscar(String id) throws Exception {
        String sql = """
                SELECT emp_no, salary, from_date, to_date
                FROM salaries
                WHERE to_date >= CURRENT_DATE
                AND emp_no = ?
                LIMIT 1
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        Salario salario = null;
        if (rs.next()) {
            int empNo = rs.getInt("emp_no");
            int valor = rs.getInt("salary");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            salario = new Salario(empNo, valor, desde, ate);
        }

        rs.close();
        pstmt.close();

        return salario;
    }

    public ArrayList<Salario> Listar() throws Exception {
        return Listar(DbConfig.DEFAULT_LIMIT, 0);
    }

    public ArrayList<Salario> Listar(int limit, int offset) throws Exception {
        String sql = """
                SELECT emp_no, salary, from_date, to_date
                FROM salaries
                WHERE to_date >= CURRENT_DATE
                ORDER BY emp_no, from_date
                LIMIT ?
                OFFSET ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Salario> salarios = new ArrayList<>();
        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            int valor = rs.getInt("salary");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            salarios.add(new Salario(empNo, valor, desde, ate));
        }

        rs.close();
        pstmt.close();

        return salarios;
    }

    public int Contar() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM salaries WHERE to_date >= CURRENT_DATE";
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
