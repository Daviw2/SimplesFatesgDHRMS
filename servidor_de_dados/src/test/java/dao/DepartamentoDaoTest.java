package dao;

import org.junit.jupiter.api.Test;

import com.fatesg.config.DbConfig;
import com.fatesg.dao.DepartamentoDao;
import com.fatesg.database.MysqlConn;

public class DepartamentoDaoTest implements AutoCloseable{
    private DepartamentoDao dao;

    public DepartamentoDaoTest() {
        super();
        var conn = MysqlConn.getConn();
        this.dao = new DepartamentoDao(conn);
    }

    @Test
    public void DepartamentoDao_Listar_Deve_Retorna_Lista_De_Departamentos() {
        try {
            var departamentos = dao.Listar();

            assert departamentos.size() > 0;
            assert departamentos.size() <= DbConfig.DEFAULT_LIMIT;
            assert departamentos.get(0).getId() != null;
            assert departamentos.get(0).getNome() != null;

            // for(var departamento : departamentos) {
            // System.out.println(departamento.getId() + " - " + departamento.getNome());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoDao_Listar_Com_Limit_E_Offset_Deve_Retorna_Lista_De_Departamentos() {
        try {
            var departamentos = dao.Listar(5, 0);

            assert departamentos.size() > 0;
            assert departamentos.size() == 5;
            assert departamentos.get(0).getId() != null;
            assert departamentos.get(0).getNome() != null;

            // for(var departamento : departamentos) {
            // System.out.println(departamento.getId() + " - " + departamento.getNome());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoDao_Buscar_Com_Id_Valido_Deve_Retornar_Departamento() {
        try {
            var departamento = dao.Buscar("d001");

            assert departamento != null;
            assert departamento.getId() != null;
            assert departamento.getNome() != null;

            // for(var departamento : departamentos) {
            // System.out.println(departamento.getId() + " - " + departamento.getNome());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoDao_Buscar_Com_Id_Invalido_Deve_Retornar_Null() {
        try {
            var departamento = dao.Buscar("error");

            assert departamento == null;

            // for(var departamento : departamentos) {
            // System.out.println(departamento.getId() + " - " + departamento.getNome());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void DepartamentoDao_Contar_Deve_Retornar_Maior_Que_Zero() {
        try {
            var total = dao.Contar();

            assert total > 0;

            // System.out.println("Total de departamentos: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        MysqlConn.close();
    }
}
