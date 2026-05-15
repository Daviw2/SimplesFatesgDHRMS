package dao;

import org.junit.jupiter.api.Test;

import com.fatesg.config.DbConfig;
import com.fatesg.dao.SalarioDao;
import com.fatesg.database.MysqlConn;

public class SalarioDaoTest implements AutoCloseable {
    private SalarioDao dao;

    public SalarioDaoTest() {
        super();
        var conn = MysqlConn.getConn();
        this.dao = new SalarioDao(conn);
    }

    @Test
    public void SalarioDao_Listar_Deve_Retorna_Lista_De_Salarios() {
        try {
            var salarios = dao.Listar();

            assert salarios.size() > 0;
            assert salarios.size() <= DbConfig.DEFAULT_LIMIT;

            assert salarios.get(0).getFuncionarioId() > 0;
            assert salarios.get(0).getValor() > 0;
            assert salarios.get(0).getDesde() != null;
            assert salarios.get(0).getAte() != null;

            // for (var salario : salarios) {
            //     System.out.println(salario.getFuncionarioId() + " - " + salario.getValor() + " - " + salario.getDesde() + " - " + salario.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SalarioDao_Listar_Com_Limit_E_Offset_Deve_Retorna_Lista_De_Salarios() {
        try {
            var salarios = dao.Listar(5, 0);

            assert salarios.size() > 0;
            assert salarios.size() == 5;

            assert salarios.get(0).getFuncionarioId() > 0;
            assert salarios.get(0).getValor() > 0;
            assert salarios.get(0).getDesde() != null;
            assert salarios.get(0).getAte() != null;

            // for (var salario : salarios) {
            //     System.out.println(salario.getFuncionarioId() + " - " + salario.getValor() + " - " + salario.getDesde() + " - " + salario.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SalarioDao_Buscar_Com_Id_Valido_Deve_Retornar_Salario() {
        try {
            var salario = dao.Buscar("10001");

            assert salario != null;
            assert salario.getFuncionarioId() > 0;
            assert salario.getValor() > 0;
            assert salario.getDesde() != null;
            assert salario.getAte() != null;

            // System.out.println(salario.getFuncionarioId() + " - " + salario.getValor() + " - " + salario.getDesde() + " - " + salario.getAte());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SalarioDao_Buscar_Com_Id_Invalido_Deve_Retornar_Null() {
        try {
            var salario = dao.Buscar("0");

            assert salario == null;

            // System.out.println(salario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void SalarioDao_Contar_Deve_Retornar_Maior_Que_Zero() {
        try {
            var total = dao.Contar();

            assert total > 0;

            // System.out.println("Total de salários: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        MysqlConn.close();
    }
}
