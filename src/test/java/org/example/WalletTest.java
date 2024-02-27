package org.example;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class WalletTest extends TestCase {

    public void testOwnerConstructor() {
        Owner owner1 = new Owner("maritzaazz", 17);
        Owner owner2 = new Owner("touangelou", 27);

        Wallet wallet = new Wallet(owner1, null, 0.0);
        Wallet wallet2 = new Wallet(owner2, null, 0.0);

        assertNotSame(wallet, wallet2); // Memastikan bahwa objek Wallet tidak sama
    }

    @Test
    public void testCardConstructor() {
        Card card = new Card();
        card.setNamaPemilik("maritzaaazz");
        card.setNamaBank("BankA");
        card.setNoRek("123456789");
        card.setMasaAktif(LocalDate.now().plusYears(2));

        // Memeriksa properti dengan menggunakan assertNotEquals
        assertNotEquals("BankB", card.getNamaBank(), "Bank name should not be BankB");
    }

    @Test
    public void testCashConstructor() {
        Owner owner = new Owner("John", 30);
        Double initialCash = 100.0;

        Wallet wallet1 = new Wallet(owner, null, initialCash);
        Wallet wallet2 = new Wallet(owner, null, initialCash);

        // Memeriksa properti dengan menggunakan assertNotEquals
        assertNotEquals(200.0, wallet1.getCash(), "Initial cash should not be 200.0");
    }

    @Test
    public void testConstructor() {
        Owner owner = new Owner("maritzaaazz", 20);
        Wallet wallet = new Wallet(owner, null, 0.0);

        assertNull(wallet.getCards());
    }

    @Test
    public void testWithdrawTest() {
        Wallet wallet = new Wallet(
                new Owner("maritzaaazz", 17),
                null, 100.0
        );

        assertEquals(50.0, wallet.withdraw(50.0)); // Menarik uang sejumlah 50.0 dari saldo 100.0
    }

    @Test
    public void testDepositTest() {
        Wallet wallet1 = new Wallet(
                new Owner("maritzaazz", 17),
                null, 100.0
        );
        Wallet wallet2 = new Wallet(
                new Owner("maritzaazz", 17),
                null, 100.0
        );

        Double expectedTotalCash = 200.0;  // Saldo wallet1 + saldo wallet2

        assertEquals(expectedTotalCash, wallet1.getCash() + wallet2.getCash(), 0.01);
    }

    @Test
    public void testAddCards() {
        Wallet wallet = new Wallet(
                new Owner("maritzaaazz", 17),
                new ArrayList<>(), 0.0
        );

        wallet.addCards("BRI", 1234567890);
        wallet.addCards("BRI", 1234567810);
        wallet.addCards("BRI", 1234566810);

        assertEquals(1, wallet.getCards().size()); // Menambahkan satu kartu,
        // jumlah kartu harus sesuai dengan yang diharapkan
    }

    @Test
    public void testRemoveCard() {
        Wallet wallet = new Wallet(
                new Owner("maritzaazz", 17),
                new ArrayList<>(), 0.0
        );

        wallet.addCards("BRI", 1234567890);
        wallet.addCards("BCA", 987654321);

        wallet.removeCard(1234567890);

        assertEquals("BCA", wallet.getCards().get(0).getNamaBank()); // Kartu yang
        // tersisa harus sesuai dengan yang diharapkan
    }
}