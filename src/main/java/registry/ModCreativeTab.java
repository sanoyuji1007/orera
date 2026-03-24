package registry;


import com.example.swordadd.TemplateMod;
import com.example.swordadd.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.minecraft.text.Text;

public class ModCreativeTab {

    //ここでクリエイティブタブにアイテムを登録する。
    public static final ItemGroup swordadd = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(TemplateMod.MOD_ID, "swordadd_tab"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup.swordadd.swordadd_tab"))
                    .icon(() -> new ItemStack(ModItems.CRYSTALLITE_INGOT))
                    .entries((displayContext, entries) -> {
                        //出来れば建材系、素材系、武器や防具、特殊効果のあるアイテム、モブエッグみたいなアイテムの順で
                        entries.add(ModItems.CRYSTALLITE_INGOT);
                        entries.add(ModItems.HEAL_CRYSTAL); // 他のアイテムも同様の形式で追加

                    })
                    .build()
    );

    public static void initialize() {
    }
}