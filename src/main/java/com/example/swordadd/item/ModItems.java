package com.example.swordadd.item;

import com.example.swordadd.TemplateMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.trim.ArmorTrimMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Function;

public class ModItems {

    //黒い剣の素材の性能
    public static final ToolMaterial BLACK_SWORD_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2500,
            9.0F,
            8.0F,
            30,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );

    //青い剣の素材の性能
    public static final ToolMaterial BLUE_SWORD_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2500,
            9.0F,
            8.0F,
            30,
            ItemTags.NETHERITE_TOOL_MATERIALS
    );



    // 黒い剣を追加
    public static final Item BLACK_SWORD = register(
            "black_sword",
            Item::new, // 第2引数：itemFactory (Itemクラスのコンストラクタを渡す)
            new Item.Settings()
                    .sword(BLACK_SWORD_MATERIAL, 6.0F, -2.4F)
    );

    // 青い剣を追加
    public static final Item BLUE_SWORD = register(
            "blue_sword",
            Item::new, // 第2引数：itemFactory (Itemクラスのコンストラクタを渡す)
            new Item.Settings()
                    .sword(BLUE_SWORD_MATERIAL, 6.0F, -2.4F)
    );

    // クリスタライト・インゴット追加
    public static final Item CRYSTALLITE_INGOT = register(
            "crystallite_ingot",
            Item::new, // 第2引数：itemFactory (Itemクラスのコンストラクタを渡す)
            new Item.Settings()
    );


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
                    entries.add(BLUE_SWORD); // BLUE_SWORDを追加します
                    entries.add(CRYSTALLITE_INGOT); // CRYSTALLITE_INGOTを追加します
                });
    }
}
