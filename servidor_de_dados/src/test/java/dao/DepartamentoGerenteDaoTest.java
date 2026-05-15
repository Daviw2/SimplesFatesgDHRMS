package dao;

import org.junit.jupiter.api.Test;

import com.fatesg.config.DbConfig;
import com.fatesg.dao.DepartamentoGerenteDao;
import com.fatesg.database.MysqlConn;

public class DepartamentoGerenteDaoTest implements AutoCloseable {
    private DepartamentoGerenteDao dao;

    public DepartamentoGerenteDaoTest() {
        super();
        var conn = MysqlConn.getConn();
        this.dao = new DepartamentoGerenteDao(conn);
    }

    @Test
    public void DepartamentoGerenteDao_Listar_Deve_Retorna_Lista_De_DepartamentosGerentes() {
        try {
            var departamentosGerentes = dao.Listar();

            assert departamentosGerentes.size() > 0;
            assert departamentosGerentes.size() <= DbConfig.DEFAULT_LIMIT;

            assert departamentosGerentes.get(0).getDepartamentoId() != null;
            assert departamentosGerentes.get(0).getFuncionarioId() > 0;
            assert departamentosGerentes.get(0).getDesde() != null;
            assert departamentosGerentes.get(0).getAte() != null;

            // for (var dg : departamentosGerentes) {
            //     System.out.println(dg.getDepartamentoId() + " - " + dg.getFuncionarioId() + " - " + dg.getDesde() + " - " + dg.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoGerenteDao_Listar_Com_Limit_E_Offset_Deve_Retorna_Lista_De_DepartamentosGerentes() {
        try {
            var departamentosGerentes = dao.Listar(5, 0);

            assert departamentosGerentes.size() > 0;
            assert departamentosGerentes.size() == 5;

            assert departamentosGerentes.get(0).getDepartamentoId() != null;
            assert departamentosGerentes.get(0).getFuncionarioId() > 0;
            assert departamentosGerentes.get(0).getDesde() != null;
            assert departamentosGerentes.get(0).getAte() != null;

            // for (var dg : departamentosGerentes) {
            //     System.out.println(dg.getDepartamentoId() + " - " + dg.getFuncionarioId() + " - " + dg.getDesde() + " - " + dg.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoGerenteDao_Buscar_Com_Id_Valido_Deve_Retornar_DepartamentoGerente() {
        try {
            var departamentoGerente = dao.Buscar("110854");

            assert departamentoGerente != null;
            assert departamentoGerente.getDepartamentoId() != null;
            assert departamentoGerente.getFuncionarioId() > 0;
            assert departamentoGerente.getDesde() != null;
            assert departamentoGerente.getAte() != null;

            // System.out.println(departamentoGerente.getDepartamentoId() + " - " + departamentoGerente.getFuncionarioId() + " - " + departamentoGerente.getDesde() + " - " + departamentoGerente.getAte());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoGerenteDao_Buscar_Com_Id_Invalido_Deve_Retornar_Null() {
        try {
            var departamentoGerente = dao.Buscar("0");

            assert departamentoGerente == null;

            // System.out.println(departamentoGerente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoGerenteDao_Contar_Deve_Retornar_Maior_Que_Zero() {
        try {
            var total = dao.Contar();

            assert total > 0;

            // System.out.println("Total de departamento-gerente: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        MysqlConn.close();
    }
}
