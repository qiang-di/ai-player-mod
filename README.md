# AI Player Mod

一个将语言模型集成到Minecraft中作为可交互AI玩家的模组。

## 功能特性

- 集成OpenAI的GPT-3.5-turbo模型作为AI玩家
- 支持与AI玩家进行自然语言交互
- 实时响应玩家的指令和对话
- 服务端和客户端网络通信支持

## 安装要求

- Minecraft 1.21
- Fabric Loader >= 0.15.0
- Fabric API
- Java 17或更高版本

## 安装步骤

1. 确保已安装Fabric Loader和Fabric API
2. 下载AI Player Mod的最新版本
3. 将mod文件放入游戏的`mods`文件夹中
4. 启动游戏

## 使用方法

### 配置API密钥

在使用mod之前，你需要设置OpenAI API密钥：

1. 获取OpenAI API密钥（需要在OpenAI官网注册账号）
2. 在游戏中使用命令设置API密钥（具体命令将在后续版本中提供）

### 与AI玩家交互

1. 在游戏中，你可以通过特定的命令或聊天界面与AI玩家进行交互
2. AI玩家会理解你的自然语言指令并做出响应
3. 支持中文交互，AI会以自然的方式回应你的命令

## 注意事项

- 确保API密钥安全，不要分享给他人
- AI响应可能需要几秒钟的处理时间
- 建议在使用时保持稳定的网络连接

## 常见问题

**Q: 为什么收到"错误：未设置API密钥"的提示？**
A: 这说明你还没有配置OpenAI API密钥，请按照上述配置步骤设置密钥。

**Q: 为什么AI没有响应？**
A: 可能的原因：
- 网络连接不稳定
- API密钥无效或已过期
- OpenAI服务暂时不可用

## 许可证

本项目基于MIT许可证开源。

## 贡献

欢迎提交Issue和Pull Request来帮助改进这个模组！

## 联系方式

- 项目主页：https://example.com/
- 源代码：https://github.com/qiang-di/ai-player-mod
