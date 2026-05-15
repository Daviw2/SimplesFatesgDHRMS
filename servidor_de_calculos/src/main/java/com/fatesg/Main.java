package com.fatesg;

import com.fatesg.config.SocketConfig;

public class Main {
    public static void main(String[] args) {
        int portaArg = 0;

        if (args.length > 0) {
            try {
                portaArg = Integer.parseInt(args[0]);
                if (!(portaArg > 0 && portaArg <= 65535)) {
                    System.out.println("Porta inválida. Usando a porta padrão " + SocketConfig.PORT);
                }
            } catch (NumberFormatException e) {
                System.out.println("Argumento de porta inválido. Usando a porta padrão " + SocketConfig.PORT);
            }
        }

        portaArg = (portaArg > 0 && portaArg <= 65535) ? portaArg : SocketConfig.PORT;

        Server server = new Server();
        try {
            System.out.println("Servidor JsonRPC iniciado na porta " + portaArg);
            System.out.println("Aguardando conexões...");
            server.Run(portaArg);
        } catch (Exception e) {
            System.err.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}