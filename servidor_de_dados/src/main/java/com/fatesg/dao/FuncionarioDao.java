package com.fatesg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.fatesg.config.DbConfig;
import com.fatesg.model.Funcionario;

public class FuncionarioDao implements DaoInterface<Funcionario> {
    private Connection conn;

    public FuncionarioDao(Connection conn) {
        this.conn = conn;
    }

    public Funcionario Buscar(String id) throws Exception {
        var regexNumeros = "\\d+";
        if (id == null || id.isEmpty() || !id.matches(regexNumeros)) {
            throw new IllegalArgumentException("ID não pode ser nulo ou vazio");
        }
        String sql = """
                SELECT emp_no, birth_date, first_name, last_name, gender, hire_date
                FROM employees
                WHERE emp_no = ?
                LIMIT 1
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, Integer.parseInt(id));
        ResultSet rs = pstmt.executeQuery();

        Funcionario funcionario = null;
        if (rs.next()) {
            int empNo = rs.getInt("emp_no");
            String nome = rs.getString("first_name");
            String sobrenome = rs.getString("last_name");
            char sexo = rs.getString("gender").charAt(0);
            Date dataNascimento = rs.getDate("birth_date");
            Date dataAdmissao = rs.getDate("hire_date");
            funcionario = new Funcionario(empNo, nome, sobrenome, sexo, dataNascimento, dataAdmissao);
        }

        rs.close();
        pstmt.close();

        return funcionario;
    }

    public ArrayList<Funcionario> Listar() throws Exception {
        return Listar(DbConfig.DEFAULT_LIMIT, 0);
    }

    public ArrayList<Funcionario> Listar(int limit, int offset) throws Exception {
        String sql = """
                SELECT emp_no, birth_date, first_name, last_name, gender, hire_date
                FROM employees
                ORDER BY first_name, last_name
                LIMIT ?
                OFFSET ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        pstmt.setInt(2, offset);
        ResultSet rs = pstmt.executeQuery();

        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("emp_no");
            String nome = rs.getString("first_name");
            String sobrenome = rs.getString("last_name");
            char sexo = rs.getString("gender").charAt(0);
            Date dataNascimento = rs.getDate("birth_date");
            Date dataAdmissao = rs.getDate("hire_date");
            funcionarios.add(new Funcionario(id, nome, sobrenome, sexo, dataNascimento, dataAdmissao));
        }

        rs.close();
        pstmt.close();

        return funcionarios;
    }

    public int Contar() throws Exception {
        String sql = "SELECT COUNT(*) AS total FROM employees";
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
