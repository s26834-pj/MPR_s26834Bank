package org.example.s26834bank;

import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final ClientStorage clientStorage;

    public TransferService(ClientStorage clientStorage) {
        this.clientStorage = clientStorage;
    }
    public Transfer sendMoney(String clientID, int amount){
        Transfer transfer = new Transfer();
        Client client = clientStorage.findClientByID(clientID);

        if(client == null){
            System.out.println("Klient o podanym numerze nie istnieje. Wprowadz prawidłowy numer");
            return null;
        }

        int newBalance = client.getBalance() - amount;

        if(newBalance < 0 ){
            System.out.println("Masz za mało środków na ruchunku. Nie możesz zreazlizować przelewu.");
            transfer.setNewBalance(client.getBalance());
            transfer.setTransferType(TransferType.DECLINED);
        }
        else{
            transfer.setNewBalance(newBalance);
            transfer.setTransferType(TransferType.ACCEPTED);
            client.setBalance(newBalance);
        }

        return transfer;
    }

    public Transfer addMoney(String clientID, int amount){
        Transfer transfer = new Transfer();
        Client client = clientStorage.findClientByID(clientID);

        if(client == null){
            System.out.println("Klient o podanym numerze nie istnieje. Wprowadz prawidłowy numer");
            return null;
        }
        int newBalance;
        newBalance = client.getBalance() + amount;

        if(amount < 0){
            System.out.println("Podałeś wartość ujemną. Podaj kwotę większa od 0");
            transfer.setNewBalance(client.getBalance());
            transfer.setTransferType(TransferType.DECLINED);
        }
        else{
            transfer.setNewBalance(newBalance);
            transfer.setTransferType(TransferType.ACCEPTED);
            client.setBalance(newBalance);
        }

        return transfer;
    }
}