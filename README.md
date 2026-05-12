# Pandora's Start / 潘多拉开局

一个本地《杀戮尖塔》Mod：在 Neow 开局奖励中额外添加一个选项，可以把角色初始遗物替换成 `Pandora's Box`。

A local Slay the Spire mod that adds an extra Neow reward option to replace your starter relic with `Pandora's Box`.

## 中文说明

### 功能

- 在 Neow 开局奖励界面额外添加：
  `Replace your starter relic with Pandora's Box.`
- 选择后会移除当前角色的初始遗物。
- 随后正常获得 `Pandora's Box`，触发原版潘多拉魔盒效果，将初始牌组中的打击/防御牌转换为随机牌。
- 不替换原版 Neow 奖励，只是在原有选项基础上额外增加一个选项。

### 环境要求

- PC 版 Slay the Spire
- Java 8 JDK
- Maven
- ModTheSpire
- BaseMod

### 构建

当前 `pom.xml` 默认配置为本机路径：

- `D:\STEAM\steamapps\common\SlayTheSpire\desktop-1.0.jar`
- `D:\STEAM\steamapps\workshop\content\646570\1605060445\ModTheSpire.jar`
- `D:\STEAM\steamapps\workshop\content\646570\1605833019\BaseMod.jar`

运行：

```powershell
mvn package
```

构建成功后，`target/PandorasStart.jar` 会自动复制到：

```text
D:\STEAM\steamapps\common\SlayTheSpire\mods
```

如果你的游戏或依赖路径不同，可以显式指定：

```powershell
mvn package `
  "-Dsts.jar=C:\Path\To\SlayTheSpire\desktop-1.0.jar" `
  "-Dmts.jar=C:\Path\To\ModTheSpire.jar" `
  "-Dbasemod.jar=C:\Path\To\BaseMod.jar"
```

### 安装和使用

1. 运行 `mvn package`。
2. 从 Steam 启动 Slay the Spire，选择 `Play with Mods`。
3. 勾选 `BaseMod` 和 `Pandora's Start`。
4. 进入新游戏，在 Neow 奖励界面选择潘多拉选项。

### 创意工坊更新

如果已经创建了 Workshop 上传工作区，更新 Mod 时：

1. 重新运行 `mvn package`。
2. 把新的 `PandorasStart.jar` 复制到上传工作区的 `content` 目录。
3. 使用游戏目录下的 `mod-uploader.jar` 重新上传。

## English

### Features

- Adds one extra Neow starting reward option:
  `Replace your starter relic with Pandora's Box.`
- Removes the current character's starter relic.
- Grants `Pandora's Box` through the normal relic obtain flow, so its vanilla `onEquip` behavior transforms the starting Strike/Defend cards.
- Keeps all vanilla Neow options intact and only appends an additional option.

### Requirements

- Slay the Spire on PC
- Java 8 JDK
- Maven
- ModTheSpire
- BaseMod

### Build

The current `pom.xml` defaults to this local Steam installation:

- `D:\STEAM\steamapps\common\SlayTheSpire\desktop-1.0.jar`
- `D:\STEAM\steamapps\workshop\content\646570\1605060445\ModTheSpire.jar`
- `D:\STEAM\steamapps\workshop\content\646570\1605833019\BaseMod.jar`

Run:

```powershell
mvn package
```

After packaging, `target/PandorasStart.jar` is copied automatically to:

```text
D:\STEAM\steamapps\common\SlayTheSpire\mods
```

If your paths are different, override them explicitly:

```powershell
mvn package `
  "-Dsts.jar=C:\Path\To\SlayTheSpire\desktop-1.0.jar" `
  "-Dmts.jar=C:\Path\To\ModTheSpire.jar" `
  "-Dbasemod.jar=C:\Path\To\BaseMod.jar"
```

### Install and Play

1. Run `mvn package`.
2. Start Slay the Spire from Steam and choose `Play with Mods`.
3. Enable `BaseMod` and `Pandora's Start`.
4. Start a new run and choose the Pandora option from Neow.

### Workshop Updates

If you already have a Steam Workshop upload workspace:

1. Run `mvn package` again.
2. Copy the new `PandorasStart.jar` into the workspace `content` directory.
3. Upload again with the game's `mod-uploader.jar`.

## Notes / 备注

- This mod is intended to be small and focused.
- It does not add a settings panel.
- It does not change the rest of Neow's reward pool.
- 本 Mod 只做一个小功能：额外添加潘多拉开局选项，不改其他奖励池。
