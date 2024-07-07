package com.starshootercity.shulkcols;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Shulker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

public class ShulkColours extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return;
        if (event.getRightClicked() instanceof Shulker shulker) {
            ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
            if (item == null) return;
            DyeColor color = getDyeColor(item);
            if (color == null) return;
            if (event.getPlayer().getGameMode() != GameMode.CREATIVE) item.setAmount(item.getAmount() - 1);
            event.getPlayer().getInventory().setItem(event.getHand(), item);
            if (event.getHand() == EquipmentSlot.HAND) event.getPlayer().swingMainHand();
            else event.getPlayer().swingOffHand();
            shulker.setColor(color);
        }
    }

    private @Nullable DyeColor getDyeColor(ItemStack item) {
        return switch (item.getType()) {
            case BLACK_DYE -> DyeColor.BLACK;
            case BLUE_DYE -> DyeColor.BLUE;
            case RED_DYE -> DyeColor.RED;
            case CYAN_DYE -> DyeColor.CYAN;
            case GRAY_DYE -> DyeColor.GRAY;
            case LIME_DYE -> DyeColor.LIME;
            case PINK_DYE -> DyeColor.PINK;
            case BROWN_DYE -> DyeColor.BROWN;
            case GREEN_DYE -> DyeColor.GREEN;
            case WHITE_DYE -> DyeColor.WHITE;
            case ORANGE_DYE -> DyeColor.ORANGE;
            case PURPLE_DYE -> DyeColor.PURPLE;
            case YELLOW_DYE -> DyeColor.YELLOW;
            case MAGENTA_DYE -> DyeColor.MAGENTA;
            case LIGHT_BLUE_DYE -> DyeColor.LIGHT_BLUE;
            case LIGHT_GRAY_DYE -> DyeColor.LIGHT_GRAY;
            default -> null;
        };
    }
}