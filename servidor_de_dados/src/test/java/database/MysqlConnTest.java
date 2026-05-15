package database;

import org.junit.jupiter.api.Test;
import com.fatesg.database.MysqlConn;

public class MysqlConnTest {

    @Test
    public void MysqlConn_Deve_Estabelecer_Conexao_Com_Banco_De_Dados() {
        var conn = MysqlConn.getConn();
        assert conn != null : "A conexão com o banco de dados deve ser estabelecida";
    }

    @Test
    public void MysqlConn_Deve_Estabelecer_Conexao_Com_Banco_De_Dados_Singleton() {
        var conn = MysqlConn.getConn();
        assert conn != null : "A conexão com o banco de dados deve ser estabelecida";

        var conn2 = MysqlConn.getConn();
        
        assert conn == conn2 : "A conexão com o banco de dados deve ser um singleton";
    }

}
