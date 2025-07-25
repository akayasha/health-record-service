package com.example.userservice.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.WalletUtils;
import org.web3j.crypto.Credentials;

import java.nio.file.Paths;

@Service
public class WalletService {

    public String generateWallet(String password) throws Exception {
        // Store wallets in a secure directory (for demo, use "wallets" folder)
        String walletDir = Paths.get("wallets").toAbsolutePath().toString();
        String walletFile = WalletUtils.generateLightNewWalletFile(password, new java.io.File(walletDir));
        Credentials credentials = WalletUtils.loadCredentials(password, walletDir + "/" + walletFile);
        return credentials.getAddress();
    }
}