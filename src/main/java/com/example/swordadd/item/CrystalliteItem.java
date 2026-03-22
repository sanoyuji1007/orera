package com.example.swordadd.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CrystalliteItem extends Item {
    public CrystalliteItem(Settings settings) {
        super(settings);
    }

    // 最新バージョンでは ActionResult を返すのが標準です
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        // 体力が満タンでないかチェック
        if (user.getHealth() < user.getMaxHealth()) {

            // 1. 回復処理
            user.heal(4.0F);

            // 2. 音の再生（isClient() メソッドを使用）
            if (!world.isClient()) {
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 1.0F, 1.0F);
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.5F, 1.2F);
            }

            // 3. アイテムを減らす（サバイバルのみ）
            if (!user.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            // 4. クールタイム設定（第1引数は this ）
            user.getItemCooldownManager().set(itemStack, 20);

            // 成功したことを伝える
            return ActionResult.SUCCESS;
        }

        // 失敗（体力が満タン）なら PASS
        return ActionResult.PASS;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}