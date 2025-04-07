package com.example.aiplayermod;

import com.example.aiplayermod.entity.AIPlayerEntity;
import com.example.aiplayermod.entity.AIPlayerEntityRenderer;
import com.example.aiplayermod.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AIPlayerMod implements ModInitializer {
    // 模组ID
    public static final String MOD_ID = "aiplayermod";
    
    // 日志记录器
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    // AI玩家实体类型
    public static final EntityType<AIPlayerEntity> AI_PLAYER_ENTITY = FabricEntityTypeBuilder
            .<AIPlayerEntity>create(SpawnGroup.CREATURE, AIPlayerEntity::new)
            .dimensions(EntityDimensions.fixed(0.6F, 1.8F)) // 与玩家相同的尺寸
            .trackRangeBlocks(32)
            .trackedUpdateRate(2)
            .build();

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing AI Player Mod");
        
        // 注册AI玩家实体
        Registry.register(Registries.ENTITY_TYPE, new Identifier(MOD_ID, "ai_player"), AI_PLAYER_ENTITY);
        
        // 初始化网络处理器
        NetworkHandler.init();
        
        // 注册其他组件
        registerComponents();
    }
    
    private void registerComponents() {
        // 注册命令、物品等其他组件
        LOGGER.info("Registering mod components");
    }
}