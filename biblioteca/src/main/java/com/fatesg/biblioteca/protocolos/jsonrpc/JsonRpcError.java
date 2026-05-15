package com.fatesg.biblioteca.protocolos.jsonrpc;

import java.io.Serializable;

public class JsonRpcError implements Serializable{
    public int code;
    public String message;

    public JsonRpcError() {
    }

    public JsonRpcError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}