package org.example.s26834bank;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ClientStorage {
    private List<Client> clientList = new ArrayList<>();

    public ClientStorage(){
        clientList.add(new Client("1","Grzegorz",10000));
        clientList.add(new Client("2","Ala",2200));
        clientList.add(new Client("3","Teresa",100));
        clientList.add(new Client("4","Michal",1000));
        clientList.add(new Client("5","Marcin",2000));

    }

    public void registerClient(Client client){
        clientList.add(client);
    }

    public Client findClientByID(String clientID){
        for(Client client : clientList){
            if(client.getClientID().equals(clientID)){
                return client;
            }
        }
        return null;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    @Override
    public String toString() {
        return "ClientStorage{" +
                "clientList=" + clientList +
                '}';
    }
}