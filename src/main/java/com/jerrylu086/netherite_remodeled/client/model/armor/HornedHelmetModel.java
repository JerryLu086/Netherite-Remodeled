package com.jerrylu086.netherite_remodeled.client.model.armor;

import com.google.common.collect.ImmutableList;
import com.jerrylu086.netherite_remodeled.NetheriteRemodeled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

/**
 * HornedHelmetModel - JerryLu086
 * Created using Tabula 8.0.0
 * Updated to 1.18
 */
public class HornedHelmetModel extends HumanoidModel<LivingEntity> {
    // private static final ResourceLocation GUARD_VILLAGER_NAME = new ResourceLocation("guardvillagers:guard");
    // private static final ResourceLocation SKELETON_VILLAGER_NAME = new ResourceLocation("savage_and_ravage:skeleton_villager");
    // private static final Map<Boolean, HumanoidModel<?>> CACHE = new HashMap<>();
    public ModelPart Helmet;

    public HornedHelmetModel(ModelPart root) {
        super(root);
        this.Helmet = root.getChild("helmet");
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        this.Helmet.copyFrom(this.head);
        return ImmutableList.of(this.Helmet);
    };

    public static LayerDefinition createLayer() {
        var mesh = HumanoidModel.createMesh(new CubeDeformation(0.0F), 0.0F);
        var root = mesh.getRoot();
        var horn = new CubeDeformation(0.12F, 0.5F, 0.12F);
        var builder = CubeListBuilder.create();

        builder.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F));
        builder.texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F));
        builder.texOffs(56, 22).addBox(-6.6F, -11.38F, -1.0F, 2.0F, 8.0F, 2.0F, horn);
        builder.texOffs(56, 22).mirror().addBox(4.6F, -11.38F, -1.0F, 2.0F, 8.0F, 2.0F, horn);

        root.addOrReplaceChild("helmet", builder, PartPose.ZERO);
        // root.addOrReplaceChild("helmet_illager", builder, PartPose.offset(0, 1, 0));

        return LayerDefinition.create(mesh, 64, 32);
    }

    // From S & R, still need a little polish though.
    // @SuppressWarnings("unchecked")
    // public static HumanoidModel<?> getModel(LivingEntity entity) {
    //     boolean illager = entity instanceof AbstractIllager ||
    //             entity instanceof ZombieVillager ||
    //             entity instanceof AbstractVillager ||
    //             entity.getType() == ForgeRegistries.ENTITIES.getValue(GUARD_VILLAGER_NAME) ||
    //             entity.getType() == ForgeRegistries.ENTITIES.getValue(SKELETON_VILLAGER_NAME);
    //     return CACHE.putIfAbsent(illager, new HornedHelmetModel(Minecraft.getInstance().getEntityModels().bakeLayer(NetheriteRemodeled.HORNED_HELMET_LOCATION), illager));
    // }
}
