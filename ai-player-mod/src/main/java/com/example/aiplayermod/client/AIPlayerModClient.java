package com.example.aiplayermod.client;

import com.example.aiplayermod.AIPlayerMod;
import com.example.aiplayermod.client.gui.AIControlScreen;
import com.example.aiplayermod.client.render.AIPlayerEntityRenderer;
import com.example.aiplayermod.network.NetworkHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AIPlayerModClient implements ClientModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AIPlayerMod.MOD_ID + "_client");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing AI Player Mod Client");
        
        // 注册AI玩家实体的渲染器
        EntityRendererRegistry.register(AIPlayerMod.AI_PLAYER_ENTITY, AIPlayerEntityRenderer::new);
        
        // 注册客户端网络处理
        registerClientNetworking();
        
        // 注册UI组件
        registerScreens();
    }
    
    private void registerClientNetworking() {
        LOGGER.info("Registering client networking");
        // 注册客户端网络处理器
        ClientPlayNetworking.registerReceiver(NetworkHandler.AI_RESPONSE_CHANNEL, (client, handler, buf, responseSender) -> {
            String response = buf.readString();
            LOGGER.info("Received AI response: {}", response);
        });
    }
    
    private void registerScreens() {
        LOGGER.info("Registering screens");
        // 注册AI控制界面
        HandledScreens.register(AIPlayerMod.AI_CONTROL_SCREEN_HANDLER, AIControlScreen::new);
        
        // 注册按键绑定
        KeyBinding openAIControlKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + AIPlayerMod.MOD_ID + ".open_ai_control",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            "category." + AIPlayerMod.MOD_ID
        ));
        
        // 注册按键事件处理
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openAIControlKey.wasPressed()) {
                client.setScreen(new AIControlScreen());
            }
        });
    }
}