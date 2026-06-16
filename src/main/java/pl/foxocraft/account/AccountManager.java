package pl.foxocraft.account;

import pl.foxocraft.util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {

    private List<Account> accounts;
    private Account selectedAccount;
    private FileManager fileManager;

    public AccountManager() {
        this.accounts = new ArrayList<>();
        this.fileManager = new FileManager();
        loadAccounts();
    }

    public void addAccount(String username, String nickname) {
        Account account = new Account(username, nickname);
        accounts.add(account);
        saveAccount(account);
    }

    public void removeAccount(String username) {
        accounts.removeIf(acc -> acc.getUsername().equals(username));
        File accountFile = new File(fileManager.getAccountDirectory(), username + ".json");
        fileManager.deleteFile(accountFile);
    }

    public void selectAccount(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                this.selectedAccount = account;
                return;
            }
        }
    }

    public void changeNickname(String username, String newNickname) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                account.setNickname(newNickname);
                saveAccount(account);
                return;
            }
        }
    }

    public Account getSelectedAccount() {
        return selectedAccount;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(accounts);
    }

    private void saveAccount(Account account) {
        try {
            File accountFile = new File(fileManager.getAccountDirectory(), account.getUsername() + ".json");
            String json = account.toJSON();
            fileManager.saveFile(accountFile, json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccounts() {
        File accountDir = fileManager.getAccountDirectory();
        File[] files = accountDir.listFiles((dir, name) -> name.endsWith(".json"));

        if (files != null) {
            for (File file : files) {
                try {
                    String json = fileManager.loadFile(file);
                    Account account = Account.fromJSON(json);
                    if (account != null) {
                        accounts.add(account);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Account {
        private String username;
        private String nickname;
        private long createdAt;
        private String uuid;

        public Account(String username, String nickname) {
            this.username = username;
            this.nickname = nickname;
            this.createdAt = System.currentTimeMillis();
            this.uuid = generateUUID();
        }

        private String generateUUID() {
            return java.util.UUID.randomUUID().toString();
        }

        public String toJSON() {
            return "{" +
                    "\"username\":\"" + username + "\"," +
                    "\"nickname\":\"" + nickname + "\"," +
                    "\"uuid\":\"" + uuid + "\"," +
                    "\"createdAt\":" + createdAt +
                    "}";
        }

        public static Account fromJSON(String json) {
            try {
                String username = extractValue(json, "username");
                String nickname = extractValue(json, "nickname");
                Account account = new Account(username, nickname);
                account.uuid = extractValue(json, "uuid");
                account.createdAt = Long.parseLong(extractValue(json, "createdAt"));
                return account;
            } catch (Exception e) {
                return null;
            }
        }

        private static String extractValue(String json, String key) {
            String search = "\"" + key + "\":\"";
            int start = json.indexOf(search);
            if (start == -1) {
                search = "\"" + key + "\":";
                start = json.indexOf(search);
                if (start == -1) return "";
                start += search.length();
                int end = json.indexOf(",", start);
                if (end == -1) end = json.indexOf("}", start);
                return json.substring(start, end).trim();
            }
            start += search.length();
            int end = json.indexOf("\"", start);
            return json.substring(start, end);
        }

        public String getUsername() { return username; }
        public String getNickname() { return nickname; }
        public void setNickname(String nickname) { this.nickname = nickname; }
        public String getUUID() { return uuid; }
        public long getCreatedAt() { return createdAt; }
    }
}