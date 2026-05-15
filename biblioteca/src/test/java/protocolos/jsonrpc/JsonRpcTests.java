package protocolos.jsonrpc;

import org.junit.jupiter.api.Test;

import com.fatesg.biblioteca.protocolos.jsonrpc.*;

public class JsonRpcTests {
    @Test
    public void JsonRpcRequest_Deve_Existir(){
        assert JsonRpcRequest.class != null; 
    }

    @Test
    public void JsonRpcResponse_Deve_Existir(){
        assert JsonRpcResponse.class != null; 
    }

    @Test
    public void JsonRpcError_Deve_Existir(){
        assert JsonRpcError.class != null; 
    }
}
