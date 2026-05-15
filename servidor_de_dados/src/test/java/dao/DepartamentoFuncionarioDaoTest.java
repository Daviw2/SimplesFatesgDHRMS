package dao;

import org.junit.jupiter.api.Test;

import com.fatesg.config.DbConfig;
import com.fatesg.dao.DepartamentoFuncionarioDao;
import com.fatesg.database.MysqlConn;

public class DepartamentoFuncionarioDaoTest implements AutoCloseable {
    private DepartamentoFuncionarioDao dao;

    public DepartamentoFuncionarioDaoTest() {
        super();
        var conn = MysqlConn.getConn();
        this.dao = new DepartamentoFuncionarioDao(conn);
    }

    @Test
    public void DepartamentoFuncionarioDao_Listar_Deve_Retorna_Lista_De_DepartamentosFuncionarios() {
        try {
            var departamentosFuncionarios = dao.Listar();

            assert departamentosFuncionarios.size() > 0;
            assert departamentosFuncionarios.size() <= DbConfig.DEFAULT_LIMIT;

            assert departamentosFuncionarios.get(0).getDepartamentoId() != null;
            assert departamentosFuncionarios.get(0).getFuncionarioId() > 0;
            assert departamentosFuncionarios.get(0).getDesde() != null;
            assert departamentosFuncionarios.get(0).getAte() != null;

            // for (var df : departamentosFuncionarios) {
            //     System.out.println(df.getDepartamentoId() + " - " + df.getFuncionarioId() + " - " + df.getDesde() + " - " + df.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoFuncionarioDao_Listar_Com_Limit_E_Offset_Deve_Retorna_Lista_De_DepartamentosFuncionarios() {
        try {
            var departamentosFuncionarios = dao.Listar(5, 0);

            assert departamentosFuncionarios.size() > 0;
            assert departamentosFuncionarios.size() == 5;

            assert departamentosFuncionarios.get(0).getDepartamentoId() != null;
            assert departamentosFuncionarios.get(0).getFuncionarioId() > 0;
            assert departamentosFuncionarios.get(0).getDesde() != null;
            assert departamentosFuncionarios.get(0).getAte() != null;

            // for (var df : departamentosFuncionarios) {
            //     System.out.println(df.getDepartamentoId() + " - " + df.getFuncionarioId() + " - " + df.getDesde() + " - " + df.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoFuncionarioDao_Buscar_Com_Id_Valido_Deve_Retornar_DepartamentoFuncionario() {
        try {
            var departamentoFuncionario = dao.Buscar("10001");

            assert departamentoFuncionario != null;
            assert departamentoFuncionario.getDepartamentoId() != null;
            assert departamentoFuncionario.getFuncionarioId() > 0;
            assert departamentoFuncionario.getDesde() != null;
            assert departamentoFuncionario.getAte() != null;

            // System.out.println(departamentoFuncionario.getDepartamentoId() + " - " + departamentoFuncionario.getFuncionarioId() + " - " + departamentoFuncionario.getDesde() + " - " + departamentoFuncionario.getAte());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoFuncionarioDao_Buscar_Com_Id_Invalido_Deve_Retornar_Null() {
        try {
            var departamentoFuncionario = dao.Buscar("0");

            assert departamentoFuncionario == null;

            // System.out.println(departamentoFuncionario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoFuncionarioDao_Contar_Deve_Retornar_Maior_Que_Zero() {
        try {
            var total = dao.Contar();

            assert total > 0;

            // System.out.println("Total de departamento-funcionário: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        MysqlConn.close();
    }
}
