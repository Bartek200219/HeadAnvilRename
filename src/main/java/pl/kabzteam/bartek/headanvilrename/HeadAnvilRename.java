package pl.kabzteam.bartek.headanvilrename;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class HeadAnvilRename extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("Enabling "+getName()+" "+ getDescription().getVersion()+"...");
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enabled "+getName()+" "+ getDescription().getVersion()+"!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "Disabling "+getName()+" "+ getDescription().getVersion()+"!");
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        ItemStack currentHead = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();
        if (inv != null && inv.getType() == InventoryType.ANVIL && event.getSlotType() == InventoryType.SlotType.RESULT){
            if (currentHead.getType()==Material.PLAYER_HEAD){
                String nick = currentHead.getItemMeta().getDisplayName();
                currentHead=getPlayerHead(nick);
                event.setCurrentItem(currentHead);
            }
        }
    }

    public ItemStack getPlayerHead(String player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(player);
        item.setItemMeta(meta);
        return item;
    }

}
