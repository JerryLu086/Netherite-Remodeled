package com.jerrylu086.netherite_remodeled.mixin.client;

import com.jerrylu086.netherite_remodeled.NetheriteRemodeled;
import com.jerrylu086.netherite_remodeled.client.model.armor.HornedHelmetModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.client.IItemRenderProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
@SuppressWarnings("unused")
public abstract class ItemMixin {
    @Shadow(remap = false)
    private Object renderProperties;
    @Shadow
    public abstract Item asItem();

    @Inject(method = "getRenderPropertiesInternal", at = @At("HEAD"), cancellable = true, remap = false)
    private void netheriteRemodeled$getRenderPropertiesInternal(CallbackInfoReturnable<Object> cir) {
        if (this.asItem().equals(Items.NETHERITE_HELMET)) {
            cir.setReturnValue(new IItemRenderProperties() {
                private static final HornedHelmetModel CACHED_MODEL = new HornedHelmetModel(Minecraft.getInstance().getEntityModels().bakeLayer(NetheriteRemodeled.HORNED_HELMET_LOCATION));

                @Override
                public HumanoidModel<?> getArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> _default) {
                    return CACHED_MODEL;
                }
            });
        }
    }
}
