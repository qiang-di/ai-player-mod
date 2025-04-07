package com.example.aiplayermod.client.gui;

import com.example.aiplayermod.AIPlayerMod;
import com.example.aiplayermod.network.NetworkHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;

public class AIControlScreen extends Screen {
    private TextFieldWidget commandInput;
    private ButtonWidget sendButton;
    private String aiResponse = "";
    
    public AIControlScreen() {
        super(Text.translatable("screen." + AIPlayerMod.MOD_ID + ".ai_control"));
    }
    
    @Override
    protected void init() {
        // 创建命令输入框
        this.commandInput = new TextFieldWidget(
            this.textRenderer,
            this.width / 2 - 100,
            this.height - 40,
            200,
            20,
            Text.translatable("gui." + AIPlayerMod.MOD_ID + ".command_input")
        );
        this.addDrawableChild(this.commandInput);
        
        // 创建发送按钮
        this.sendButton = ButtonWidget.builder(
            Text.translatable("gui." + AIPlayerMod.MOD_ID + ".send"),
            button -> sendCommand()
        ).dimensions(this.width / 2 + 110, this.height - 40, 60, 20).build();
        this.addDrawableChild(this.sendButton);
        
        // 注册网络响应处理
        registerNetworkHandlers();
    }
    
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
        
        // 渲染AI响应文本
        context.drawTextWithShadow(this.textRenderer, Text.literal(aiResponse),
            this.width / 2 - 100, this.height - 60, 0xFFFFFF);
    }
    
    private void sendCommand() {
        String command = this.commandInput.getText();
        if (!command.isEmpty()) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString(command);
            ClientPlayNetworking.send(NetworkHandler.AI_COMMAND_CHANNEL, buf);
            this.commandInput.setText("");
        }
    }
    
    private void registerNetworkHandlers() {
        // 处理来自服务器的AI响应
        ClientPlayNetworking.registerReceiver(NetworkHandler.AI_RESPONSE_CHANNEL,
            (client, handler, buf, responseSender) -> {
                String response = buf.readString();
                client.execute(() -> this.aiResponse = response);
            }
        );
    }
    
    @Override
    public boolean shouldPause() {
        return false;
    }
}