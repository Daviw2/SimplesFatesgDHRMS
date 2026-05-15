package com.fatesg;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.fatesg.config.ServerConfig;

public class Main {
    public static void main(String[] args) {
        int portaArg = 0;
        
        if (args.length > 0) {
            try {
                portaArg = Integer.parseInt(args[0]);
                if (!(portaArg > 0 && portaArg <= 65535)) {
                    System.out.println("Porta inválida. Usando a porta padrão " + ServerConfig.RMI_PORT);
                }
            } catch (NumberFormatException e) {
                System.out.println("Argumento de porta inválido. Usando a porta padrão " + ServerConfig.RMI_PORT);
            }
        }
        
        try {
            portaArg = (portaArg > 0 && portaArg <= 65535) ? portaArg : ServerConfig.RMI_PORT;
            
            // Criar o objeto remoto
            ServidorDeDadosImpl servico = new ServidorDeDadosImpl(portaArg);

            // Criar registry na porta especificada
            Registry registry = LocateRegistry.createRegistry(portaArg);
            
            // Registrar o serviço
            registry.rebind("ServidorDeDados", servico);
            
            System.out.println("Servidor RMI iniciado na porta " + portaArg);
            System.out.println("Serviço 'ServidorDeDados' registrado com sucesso!");
            System.out.println("Aguardando conexões...");
            
        } catch (RemoteException e) {
            System.err.println("Erro ao iniciar o servidor RMI:");
            e.printStackTrace();
        }
    }
}