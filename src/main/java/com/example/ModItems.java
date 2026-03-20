package com.example;

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

    // アイテムを追加
    public static final Item BLACK_SWORD = register("black_sword", Item::new, new Item.Settings());

    // Item オブジェクトを返す register メソッド
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {

        // アイテムキーを作成
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TemplateMod.MOD_ID, name));

        // アイテムインスタンスを作成
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // アイテムを登録
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }

    public static void initialize() {

        //　ログに出力して、Modが正しく初期化されたことを確認する
        TemplateMod.LOGGER.info("MODアイテムの登録 " + TemplateMod.MOD_ID);

        // クリエイティブタブに追加（COMBATが「戦闘」のタブ)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(entries -> {
                    entries.add(BLACK_SWORD); // BLACK_SWORDを追加します
                });
    }
}
