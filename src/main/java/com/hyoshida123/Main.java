package com.hyoshida123;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("ユーザーIDを入力してください: ");
            String userId = scanner.nextLine();
            UsernameManager uManager = new UsernameManager(userId);
            AccountManager aManager = new AccountManager(userId);

            try {
                uManager.fetchUsername();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                aManager.fetchBankInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String username = uManager.getUsername();
            Map<String, Integer> bankInfo = aManager.getBankInfo();

            System.out.println("ユーザー名: " + username);
            if (bankInfo == null) {
                System.out.println("そのユーザーはまだ金融機関を登録していません。");
                System.out.println("");
                continue;
            }
            System.out.println("----金融機関一覧----");
            int totalBalance = 0;
            for (Map.Entry<String, Integer> entry: bankInfo.entrySet()) {
                System.out.println("金融機関名: " + entry.getKey());
                System.out.println(entry.getKey() + "の残高: " + entry.getValue());
                totalBalance += entry.getValue();
            }
            System.out.println("総残高: " + totalBalance);
            System.out.println("");
        }
    }
}
