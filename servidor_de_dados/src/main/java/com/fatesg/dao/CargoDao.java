package com.fatesg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.fatesg.config.DbConfig;
import com.fatesg.model.Cargo;

public class CargoDao implements DaoInterface<Cargo> {
    private Connection conn;

    public CargoDao(Connection conn) {
        this.conn = conn;
    }

    public Cargo Buscar(String id) throws Exception {
        var regexNumeros = "\\d+";
        if (id == null || id.isEmpty() || !id.matches(regexNumeros)) {
            throw new IllegalArgumentException("ID não pode ser nulo ou vazio");
        }

        String sql = """
                SELECT emp_no, title, from_date, to_date
                FROM titles
                WHERE to_date >= CURRENT_DATE
                AND emp_no = ?
                LIMIT 1
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        Cargo cargo = null;
        if (rs.next()) {
            int empNo = rs.getInt("emp_no");
            String descricao = rs.getString("title");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            cargo = new Cargo(empNo, descricao, desde, ate);
        }

        rs.close();
        pstmt.close();

        return cargo;
    }

    public ArrayList<Cargo> Listar() throws Exception {
        return Listar(DbConfig.DEFAULT_LIMIT, 0);
    }

    public ArrayList<Cargo> Listar(int limit, int offset) throws Exception {
        String sql = """
                SELECT emp_no, title, from_date, to_date 
                FROM titles
                WHERE to_date >= CURRENT_DATE
                ORDER BY emp_no, from_date 
                LIMIT ? 
                OFFSET ?
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Cargo> cargos = new ArrayList<>();
        while (rs.next()) {
            int empNo = rs.getInt("emp_no");
            String descricao = rs.getString("title");
            Date desde = rs.getDate("from_date");
            Date ate = rs.getDate("to_date");
            cargos.add(new Cargo(empNo, descricao, desde, ate));
        }

        rs.close();
        pstmt.close();

        return cargos;
    }

    public int Contar() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM titles WHERE to_date >= CURRENT_DATE";
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
