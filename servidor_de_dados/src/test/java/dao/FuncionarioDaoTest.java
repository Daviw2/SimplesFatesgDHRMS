package dao;

import org.junit.jupiter.api.Test;

import com.fatesg.config.DbConfig;
import com.fatesg.dao.FuncionarioDao;
import com.fatesg.database.MysqlConn;

public class FuncionarioDaoTest implements AutoCloseable {
    private FuncionarioDao dao;

    public FuncionarioDaoTest() {
        super();
        var conn = MysqlConn.getConn();
        this.dao = new FuncionarioDao(conn);
    }

    @Test
    public void FuncionarioDao_Listar_Deve_Retorna_Lista_De_Funcionarios() {
        try {
            var funcionarios = dao.Listar();

            assert funcionarios.size() > 0;
            assert funcionarios.size() <= DbConfig.DEFAULT_LIMIT;

            assert funcionarios.get(0).getId() > 0;
            assert funcionarios.get(0).getNome() != null;
            assert funcionarios.get(0).getSobrenome() != null;
            assert funcionarios.get(0).getSexo() == 'M' || funcionarios.get(0).getSexo() == 'F';
            assert funcionarios.get(0).getDataNascimento() != null;
            assert funcionarios.get(0).getDataContratacao() != null
                    && funcionarios.get(0).getDataContratacao().after(funcionarios.get(0).getDataNascimento())
                    && funcionarios.get(0).getDataContratacao().before(new java.util.Date());

            // for (var funcionario : funcionarios) {
            //     System.out.println(funcionario.getId() + " - " + funcionario.getNomeCompleto() + " - " + funcionario.getSexo() + " - " + funcionario.getDataNascimento() + " - " + funcionario.getDataContratacao());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FuncionarioDao_Listar_Com_Limit_E_Offset_Deve_Retorna_Lista_De_Funcionarios() {
        try {
            var funcionarios = dao.Listar(5, 0);

            assert funcionarios.size() > 0;
            assert funcionarios.size() == 5;

            assert funcionarios.get(0).getId() > 0;
            assert funcionarios.get(0).getNome() != null;
            assert funcionarios.get(0).getSobrenome() != null;
            assert funcionarios.get(0).getSexo() == 'M' || funcionarios.get(0).getSexo() == 'F';
            assert funcionarios.get(0).getDataNascimento() != null;
            assert funcionarios.get(0).getDataContratacao() != null
                    && funcionarios.get(0).getDataContratacao().after(funcionarios.get(0).getDataNascimento())
                    && funcionarios.get(0).getDataContratacao().before(new java.util.Date());

            // for (var funcionario : funcionarios) {
            //     System.out.println(funcionario.getId() + " - " + funcionario.getNomeCompleto() + " - " + funcionario.getSexo() + " - " + funcionario.getDataNascimento() + " - " + funcionario.getDataContratacao());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FuncionarioDao_Buscar_Com_Id_Valido_Deve_Retornar_Funcionario() {
        try {
            var funcionario = dao.Buscar("10001");

            assert funcionario != null;
            assert funcionario.getId() > 0;
            assert funcionario.getNome() != null;
            assert funcionario.getSobrenome() != null;
            assert funcionario.getSexo() == 'M' || funcionario.getSexo() == 'F';
            assert funcionario.getDataNascimento() != null;
            assert funcionario.getDataContratacao() != null
                    && funcionario.getDataContratacao().after(funcionario.getDataNascimento())
                    && funcionario.getDataContratacao().before(new java.util.Date());

            // for(var funcionario : funcionarios) {
            // System.out.println(funcionario.getId() + " - " + funcionario.getNome());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FuncionarioDao_Buscar_Com_Id_Invalido_Deve_Retornar_Null() {
        try {
            var funcionario = dao.Buscar("0");

            assert funcionario == null;

            // for(var funcionario : funcionarios) {
            // System.out.println(funcionario.getId() + " - " + funcionario.getNome());
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void FuncionarioDao_Contar_Deve_Retornar_Maior_Que_Zero() {
        try {
            var total = dao.Contar();

            assert total > 0;

            // System.out.println("Total de funcionários: " + total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        MysqlConn.close();
    }
}
