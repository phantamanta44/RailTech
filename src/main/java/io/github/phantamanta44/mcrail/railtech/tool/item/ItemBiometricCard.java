package io.github.phantamanta44.mcrail.railtech.tool.item;

import io.github.phantamanta44.mcrail.item.IItemBehaviour;
import io.github.phantamanta44.mcrail.item.characteristic.CharLastLore;
import io.github.phantamanta44.mcrail.item.characteristic.CharLore;
import io.github.phantamanta44.mcrail.item.characteristic.CharName;
import io.github.phantamanta44.mcrail.item.characteristic.IItemCharacteristic;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

public class ItemBiometricCard implements IItemBehaviour {

    private final String IMPRINT_PATTERN = ChatColor.RED + "Blank|" + ChatColor.BLUE + "[\\w_]+";
    private final Collection<IItemCharacteristic> CHARS = Arrays.asList(
            new CharName(ChatColor.RESET + "Biometric Card"),
            new CharLore(0, ChatColor.RED + "Blank", IMPRINT_PATTERN),
            new CharLastLore(ChatColor.GRAY + "Sneak and right click to imprint.")
    );

    @Override
    public Material material() {
        return Material.PAPER;
    }

    @Override
    public Collection<IItemCharacteristic> characteristics() {
        return CHARS;
    }

    public UUID getImprinted(ItemStack stack) {
        ItemMeta meta = stack.getItemMeta();
        if (!meta.hasLore() || meta.getLore().get(0).startsWith(ChatColor.RED.toString()))
            return null;
        return UUID.fromString(meta.getLore().get(1).substring(ChatColor.DARK_GRAY.toString().length()));
    }

    @Override
    public boolean onUse(PlayerInteractEvent event, ItemStack stack) {
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
                && event.getPlayer().isSneaking()) {
            ItemMeta meta = stack.getItemMeta();
            meta.setLore(Arrays.asList(
                    ChatColor.BLUE + event.getPlayer().getName(),
                    ChatColor.DARK_GRAY + event.getPlayer().getUniqueId().toString(),
                    ChatColor.GRAY + "Sneak and right click to imprint."
            ));
            stack.setItemMeta(meta);
            return false;
        }
        return true;
    }

}
