package com.fatesg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.google.gson.Gson;

import com.fatesg.biblioteca.dtos.FolhaDto;
import com.fatesg.biblioteca.dtos.ReciboDto;
import com.fatesg.biblioteca.protocolos.jsonrpc.JsonRpcError;
import com.fatesg.biblioteca.protocolos.jsonrpc.JsonRpcRequest;
import com.fatesg.biblioteca.protocolos.jsonrpc.JsonRpcResponse;

import com.fatesg.servicos.CalculoDeFolhaService;
import com.fatesg.utils.DescontoUtil;

public class Server {
    public void Run(int port) throws IOException {
        ServerSocket ss = new ServerSocket(port);
        while (ss.isBound()) {
            Socket clientSocket = ss.accept();
            new Thread(() -> handleClient(clientSocket)).start();
        }
        ss.close();
    }

    private void handleClient(Socket socket) {
        try (
                Socket s = socket;
                BufferedReader entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter saida = new PrintWriter(s.getOutputStream(), true)) {
            String request = entrada.readLine();
            if (request != null) {
                String response = handleRequest(request);
                saida.println(response);
                s.close();
            }
        } catch (IOException e) {
            System.err.println("Erro ao comunicar com o cliente: " + e.getMessage());
        }
    }

    public String handleRequest(String request) {
        Gson gson = new Gson();

        try {
            JsonRpcRequest rpcRequest = gson.fromJson(request, JsonRpcRequest.class);

            if (!rpcRequest.jsonRpc.equals("2.0")) {
                throw new IllegalArgumentException("Unsupported JSON-RPC version");
            }

            String method = rpcRequest.method;
            String[] params = rpcRequest.params;

            JsonRpcResponse<Object> rpcResponse = new JsonRpcResponse<Object>();
            rpcResponse.id = rpcRequest.id;

            CalculoDeFolhaService service = new CalculoDeFolhaService();
            switch (method) {
                case "calcularFolhaDePagamento":
                    byte mes = Byte.parseByte(params[0]);
                    short ano = Short.parseShort(params[1]);

                    System.out.println("[INFO] " + java.time.LocalDateTime.now() + " - "
                            + "Execução metodo calcularFolhaDePagamento(" + mes + "," + ano + ")");

                    FolhaDto retornoTotal = service.calcularFolhaDePagamento(mes, ano,
                            DescontoUtil.obterDescontosPadrao());
                    rpcResponse.result = retornoTotal;
                    break;
                case "calcularFolhaDePagamentoDoDepartamento":
                    String idDepartamento = params[0];
                    byte mesRef = Byte.parseByte(params[1]);
                    short anoRef = Short.parseShort(params[2]);

                    System.out.println("[INFO] " + java.time.LocalDateTime.now() + " - "
                            + "Execução metodo calcularFolhaDePagamentoDoDepartamento(" + idDepartamento + "," + mesRef
                            + "," + anoRef + ")");

                    FolhaDto retornoDepto = service.calcularFolhaDePagamentoDoDepartamento(idDepartamento, mesRef,
                            anoRef, DescontoUtil.obterDescontosPadrao());
                    rpcResponse.result = retornoDepto;
                    break;
                case "calcularReciboDePagamento":
                    int idFuncionario = Integer.parseInt(params[0]);
                    byte mesRecibo = Byte.parseByte(params[1]);
                    short anoRecibo = Short.parseShort(params[2]);

                    System.out.println("[INFO] " + java.time.LocalDateTime.now() + " - "
                            + "Execução metodo calcularReciboDePagamento(" + idFuncionario + "," + mesRecibo + ", "
                            + anoRecibo + ")");

                    ReciboDto recibo = service.calcularReciboDePagamento(idFuncionario, mesRecibo, anoRecibo,
                            DescontoUtil.obterDescontosPadrao());
                    rpcResponse.result = recibo;
                    break;
                case "calcularSalarioLiquido":
                    double salarioBruto = Double.parseDouble(params[0]);

                    System.out.println("[INFO] " + java.time.LocalDateTime.now() + " - "
                            + "Execução metodo calcularSalarioLiquido(" + salarioBruto + ")");

                    double salarioLiquido = service.calcularSalarioLiquido(salarioBruto,
                            DescontoUtil.obterDescontosPadrao());
                    rpcResponse.result = salarioLiquido;
                    break;
                default:
                    rpcResponse.error = new JsonRpcError(-32601, "Method not found: " + method);
                    break;
            }

            return gson.toJson(rpcResponse);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            JsonRpcResponse<Object> rpcResponse = new JsonRpcResponse<Object>();
            rpcResponse.error = new JsonRpcError() {
                {
                    code = -32603;
                    message = "Internal error: " + e.getMessage();
                }
            };
            return gson.toJson(rpcResponse);
        }
    }
}
