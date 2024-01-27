package org.example.s26834bank;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransferServiceITTest {

    @MockBean
    private ClientStorage clientStorage;

    @Autowired
    private TransferService transferService;

    @Test
    void clientListIsNotNull(){
        List<Client> allClients = clientStorage.getClientList();
        assertThat(allClients).isNotNull();
    }

    @Test
    void clientCanSendMoney() {
        //given
        ClientStorage clientStorage = new ClientStorage();
        clientStorage.registerClient(new Client("8", "Żakosław", 5000));
        TransferService transferService = new TransferService(clientStorage);
        //when
        Transfer transfer =  transferService.sendMoney("8", 2000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotSendMoney() {
        //given
        ClientStorage clientStorage = new ClientStorage();
        clientStorage.registerClient(new Client("8", "Żakosław", 5000));
        TransferService transferService = new TransferService(clientStorage);
        //when
        Transfer transfer =  transferService.sendMoney("8", 200000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }

    @Test
    void clientCanAddMoney() {
        //given
        ClientStorage clientStorage = new ClientStorage();
        clientStorage.registerClient(new Client("8", "Żakosław", 5000));
        TransferService transferService = new TransferService(clientStorage);
        //when
        Transfer transfer =  transferService.addMoney("8", 2000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotAddMoney() {
        //given
        ClientStorage clientStorage = new ClientStorage();
        clientStorage.registerClient(new Client("8", "Żakosław", 5000));
        TransferService transferService = new TransferService(clientStorage);
        //when
        Transfer transfer =  transferService.addMoney("8", -2000);
        //then
        Assertions.assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }


}