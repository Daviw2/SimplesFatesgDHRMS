package dao;

import org.junit.jupiter.api.Test;

import com.fatesg.config.DbConfig;
import com.fatesg.dao.CargoDao;
import com.fatesg.database.MysqlConn;

public class CargoDaoTest implements AutoCloseable {
    private CargoDao dao;

    public CargoDaoTest() {
        super();
        var conn = MysqlConn.getConn();
        this.dao = new CargoDao(conn);
    }

    @Test
    public void CargoDao_Listar_Deve_Retorna_Lista_De_Cargos() {
        try {
            var cargos = dao.Listar();

            assert cargos.size() > 0;
            assert cargos.size() <= DbConfig.DEFAULT_LIMIT;

            assert cargos.get(0).getFuncionarioId() > 0;
            assert cargos.get(0).getDescricao() != null;
            assert cargos.get(0).getDesde() != null;
            assert cargos.get(0).getAte() != null;

            // for (var cargo : cargos) {
            //     System.out.println(cargo.getFuncionarioId() + " - " + cargo.getDescricao() + " - " + cargo.getDesde() + " - " + cargo.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CargoDao_Listar_Com_Limit_E_Offset_Deve_Retorna_Lista_De_Cargos() {
        try {
            var cargos = dao.Listar(5, 0);

            assert cargos.size() > 0;
            assert cargos.size() == 5;

            assert cargos.get(0).getFuncionarioId() > 0;
            assert cargos.get(0).getDescricao() != null;
            assert cargos.get(0).getDesde() != null;
            assert cargos.get(0).getAte() != null;

            // for (var cargo : cargos) {
            //     System.out.println(cargo.getFuncionarioId() + " - " + cargo.getDescricao() + " - " + cargo.getDesde() + " - " + cargo.getAte());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CargoDao_Buscar_Com_Id_Valido_Deve_Retornar_Cargo() {
        try {
            var cargo = dao.Buscar("10001");

            assert cargo != null;
            assert cargo.getFuncionarioId() > 0;
            assert cargo.getDescricao() != null;
            assert cargo.getDesde() != null;
            assert cargo.getAte() != null;

            // System.out.println(cargo.getFuncionarioId() + " - " + cargo.getDescricao() + " - " + cargo.getDesde() + " - " + cargo.getAte());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CargoDao_Buscar_Com_Id_Invalido_Deve_Retornar_Null() {
        try {
            var cargo = dao.Buscar("0");

            assert cargo == null;

            // System.out.println(cargo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CargoDao_Contar_Deve_Retornar_Maior_Que_Zero() {
        try {
            var total = dao.Contar();

            assert total > 0;

            // System.out.println("Total de cargos: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        MysqlConn.close();
    }
}
