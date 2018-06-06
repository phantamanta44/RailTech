package io.github.phantamanta44.mcrail.railtech.common.component.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.phantamanta44.mcrail.Rail;
import io.github.phantamanta44.mcrail.item.IItemBehaviour;
import io.github.phantamanta44.mcrail.railtech.common.component.MachineComponent;
import io.github.phantamanta44.mcrail.railtech.tool.item.ItemBiometricCard;
import io.github.phantamanta44.mcrail.tile.RailTile;
import io.github.phantamanta44.mcrail.util.JsonUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MachineComponentSecurity extends MachineComponent {

    private final Set<UUID> permissible;
    private boolean enforcing;

    public MachineComponentSecurity(RailTile tile) {
        super(tile, "security");
        this.permissible = new HashSet<>();
        this.enforcing = false;
    }

    @Override
    public boolean checkInteract(PlayerInteractEvent event) {
        if (!enforcing || event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getPlayer().isSneaking()
                || permissible.contains(event.getPlayer().getUniqueId())) {
            return true;
        } else {
            event.getPlayer().sendMessage(ChatColor.RED + "You are not permitted to access this device!");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BREAK, 1F, 0.5F);
            return false;
        }
    }

    @Override
    public boolean acceptUpgrade(ItemStack stack) {
        IItemBehaviour item = Rail.itemRegistry().get(stack);
        if (item instanceof ItemBiometricCard) {
            UUID imprinted = ((ItemBiometricCard)item).getImprinted(stack);
            if (imprinted != null && !permissible.contains(imprinted)) {
                permissible.add(imprinted);
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        enforcing = false;
        permissible.clear();
    }

    public boolean isEnforcing() {
        return enforcing;
    }

    public void toggleEnforcing() {
        enforcing = !enforcing;
    }

    public Set<UUID> getPermissible() {
        return permissible;
    }

    @Override
    public void deserialize(JsonObject dto) {
        enforcing = dto.get("enforcing").getAsBoolean();
        permissible.clear();
        dto.get("allowed").getAsJsonArray()
                .forEach(e -> permissible.add(UUID.fromString(e.getAsString())));
    }

    @Override
    public JsonObject serialize() {
        JsonObject dto = new JsonObject();
        dto.addProperty("enforcing", enforcing);
        dto.add("allowed", permissible.stream()
                .map(id -> new JsonPrimitive(id.toString()))
                .collect(JsonUtils.arrayCollector()));
        return dto;
    }

}
