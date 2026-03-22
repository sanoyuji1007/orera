package com.example.swordadd.item;

import com.example.swordadd.TemplateMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {

    // 黒い剣の素材の性能
    public static final ToolMaterial BLACK_SWORD_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2500,
            9.0F,
            8.0F,
            30,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );

    // 青い剣の素材の性能
    public static final ToolMaterial BLUE_SWORD_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2500,
            9.0F,
            8.0F,
            30,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );

    // 黒い剣
    public static final Item BLACK_SWORD = register(
            "black_sword",
            Item::new,
            new Item.Settings().sword(BLACK_SWORD_MATERIAL, 6.0F, -2.4F)
    );

    // 青い剣
    public static final Item BLUE_SWORD = register(
            "blue_sword",
            Item::new,
            new Item.Settings().sword(BLUE_SWORD_MATERIAL, 6.0F, -2.4F)
    );

    // インゴット
    public static final Item CRYSTALLITE_INGOT = register(
            "crystallite_ingot",
            Item::new,
            new Item.Settings()
    );

    // registerメソッド
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TemplateMod.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {

        TemplateMod.LOGGER.info("MODアイテム登録: " + TemplateMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(entries -> {
                    entries.add(BLACK_SWORD);
                    entries.add(BLUE_SWORD);
                    entries.add(CRYSTALLITE_INGOT);
                });
    }
}