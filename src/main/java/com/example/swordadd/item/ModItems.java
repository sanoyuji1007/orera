package com.example.swordadd.item;

import com.example.swordadd.TemplateMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    //　回復結晶
    public static final Item HEAL_CRYSTAL = register(
            "heal_crystal",
            CrystalliteItem::new,
            new Item.Settings().maxCount(16)
    );

    // 共通の登録メソッド
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TemplateMod.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {
        TemplateMod.LOGGER.info("回復結晶の登録を開始します: " + TemplateMod.MOD_ID);

        // クリエイティブタブ（戦闘）に追加
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(entries -> {
                    entries.add(HEAL_CRYSTAL);
                });
    }
}