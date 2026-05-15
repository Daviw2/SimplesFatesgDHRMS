package com.fatesg.biblioteca.protocolos.jsonrpc;

import java.io.Serializable;

public class JsonRpcResponse<T> implements Serializable{
    public long id;
    public String jsonRpc;
    public T result;
    public JsonRpcError error;
}
