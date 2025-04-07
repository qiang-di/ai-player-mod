package com.example.aiplayermod.client.render;

import com.example.aiplayermod.entity.AIPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class AIPlayerEntityRenderer extends MobEntityRenderer<AIPlayerEntity, PlayerEntityModel<AIPlayerEntity>> {
    private static final Identifier TEXTURE = new Identifier("textures/entity/steve.png");

    public AIPlayerEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(PlayerEntityModel.LAYER_LOCATION), false), 0.5f);
    }

    @Override
    public Identifier getTexture(AIPlayerEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(AIPlayerEntity entity, float amount) {
        // 保持与玩家相同的大小
        super.scale(entity, amount);
    }
}