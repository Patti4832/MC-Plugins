package de.patti4832.plugins.essentials;

import java.util.HashMap;

public class MoneyManager {
    private static int startamount = 500;

    private static HashMap<String,Integer> moneylist = new HashMap<String,Integer>();

    private static Main main;

    public static void init(Main m){
        main = m;
    }

    private static void load(String player){
        int plam = -1;
        try {
            plam = main.getConfig().getInt("money-" + player);
        }catch (Exception e){

        }
        if(plam!=-1)
            moneylist.put(player, plam);
        else {
            moneylist.put(player, startamount);
            main.getConfig().set("money-"+player, startamount);
            main.saveConfig();
        }
    }

    public static int getMoney(String player){
        main.saveDefaultConfig();
        load(player);
        int amount = moneylist.getOrDefault(player, -1);
        if(amount==-1){
            setMoney(player, startamount);
            main.getConfig().set("money-"+player, startamount);
            main.saveConfig();
            return startamount;
        }else{
            main.getConfig().set("money-"+player, amount);
            main.saveConfig();
            return amount;
        }
    }

    public static boolean setMoney(String player, int amount){
        main.saveDefaultConfig();
        load(player);
        if(amount>0) {
            moneylist.put(player, amount);
            main.getConfig().set("money-"+player, amount);
            main.saveConfig();
            return true;
        }
        return false;
    }
}
