package com.iridium.iridiumskyblock.commands;

import com.iridium.iridiumskyblock.IridiumSkyblock;
import com.iridium.iridiumskyblock.Island;
import com.iridium.iridiumskyblock.User;
import com.iridium.iridiumskyblock.Utils;
import com.iridium.iridiumskyblock.managers.UserManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class HomeCommand extends Command {

    public HomeCommand() {
        super(Collections.singletonList("home"), "Teleport to your island home", "", true);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Player p = (Player) sender;
        User user = UserManager.getUser(p.getUniqueId());
        if (user.getIsland() != null) {
            user.getIsland().teleportHome(p);
        } else {
            if (IridiumSkyblock.getConfiguration().createIslandonHome) {
                IridiumSkyblock.getIslandManager().createIsland(p);
            } else {
                sender.sendMessage(Utils.color(IridiumSkyblock.getMessages().noIsland.replace("%prefix%", IridiumSkyblock.getConfiguration().prefix)));
            }
        }
    }

    @Override
    public void admin(CommandSender sender, String[] args, Island island) {
        Player p = (Player) sender;
        if (island != null) {
            island.teleportHome(p);
        }
    }

    @Override
    public List<String> TabComplete(CommandSender cs, org.bukkit.command.Command cmd, String s, String[] args) {
        return null;
    }
}
