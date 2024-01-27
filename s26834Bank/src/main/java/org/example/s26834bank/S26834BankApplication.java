package org.example.s26834bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S26834BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(S26834BankApplication.class, args);
        execute();
    }

    private static void execute() {
        ClientStorage clientStorage = new ClientStorage();
        clientStorage.registerClient(new Client("6","Bogumił", 5000));
        TransferService transferService = new TransferService(clientStorage);
        System.out.println(clientStorage.getClientList().size());
        System.out.println(clientStorage.toString());
        transferService.sendMoney("1", 20);
        System.out.println(clientStorage.toString());
        transferService.addMoney("3", 1500);
        System.out.println(clientStorage.toString());
        transferService.sendMoney("5", 10000);
        transferService.sendMoney("7",10);
        transferService.addMoney("7",15);
        System.out.println(clientStorage.findClientByID("6"));




    }

}
