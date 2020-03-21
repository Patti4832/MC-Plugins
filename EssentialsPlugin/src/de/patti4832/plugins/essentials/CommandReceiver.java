package de.patti4832.plugins.essentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandReceiver implements CommandExecutor {
    Main main;

    public CommandReceiver(Main m){
        main = m;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("money")) {
            if(args.length == 0){
                return getMoney(sender);
            }else{
                if(args[0].equals("get")){
                    return getMoney(sender);
                }else if(args[0].equals("set")){
                    if(args.length==3)
                        return setMoney(sender, args[1], args[2]);
                }else if(args[0].equals("give")){
                    if(args.length==3)
                        return transferMoney(sender, args[1], args[2]);
                }
            }
        }
        return false;
    }

    private boolean getMoney(CommandSender sender){
        String player = sender.getName();
        int amount = MoneyManager.getMoney(player);
        if(amount!=-1) {
            sender.sendMessage("Current amount: " + amount);
            return true;
        }else{
            sender.sendMessage("Error");
            return false;
        }
    }

    private boolean setMoney(CommandSender sender, String dest, String amount){
        if(sender.isOp() || !(sender instanceof Player)){
            int am = -1;
            try {
                am = Integer.parseInt(amount);
            }catch (Exception e){
                return false;
            }
            if(am!=-1) {
                boolean tmp = MoneyManager.setMoney(dest, am);
                return tmp;
            }
        }
        return false;
    }

    private boolean transferMoney(CommandSender sender, String dest, String amount){
        String player = sender.getName();
        int am = -1;
        try {
            am = Integer.parseInt(amount);
        }catch (Exception e){
            return false;
        }
        int am1 = MoneyManager.getMoney(player);
        int am2 = MoneyManager.getMoney(dest);
        if(am1>=am){
            MoneyManager.setMoney(player, am1-am);
            MoneyManager.setMoney(dest, am2+am);
            sender.sendMessage(amount + " sent to " + dest);
            return true;
        }else{
            return false;
        }
    }
}

