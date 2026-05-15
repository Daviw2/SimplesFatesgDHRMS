package com.fatesg.biblioteca.protocolos.jsonrpc;

import java.io.Serializable;
import java.util.HashMap;

public class JsonRpcRequest implements Serializable{
    public long id;
    public String jsonRpc = "2.0";
    public String method;
    public String[] params;
    public HashMap<String, Object> namedParams;
}
