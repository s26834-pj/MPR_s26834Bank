package org.example.s26834bank;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {
    private TransferService transferService;

    @BeforeEach
    void setup() {
        ClientStorage clientStorage = new ClientStorage();
        transferService = new TransferService(clientStorage);
    }

    @Test
    void clientCanSendMoney() {
        //when
        Transfer transfer =  transferService.sendMoney("1", 100);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotSendMoney() {
        //when
        Transfer transfer =  transferService.sendMoney("5", 5000);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }

    @Test
    void clientCanAddMoney() {
        //when
        Transfer transfer =  transferService.addMoney("2", 300);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.ACCEPTED);
    }

    @Test
    void clientCanNotAddMoney() {
        //when
        Transfer transfer =  transferService.addMoney("1", -1000);
        //then
        assertThat(transfer.getTransferType()).isEqualTo(TransferType.DECLINED);
    }
}
