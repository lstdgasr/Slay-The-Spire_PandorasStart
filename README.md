# Pandora's Start

Local Slay the Spire 1 mod that adds a Neow option:

`Replace your starter relic with Pandora's Box.`

Choosing it removes the character's starter relic, obtains `Pandora's Box`, and lets the relic's normal `onEquip` behavior transform the starting Strike/Defend cards.

## Requirements

- Slay the Spire 1 on PC
- Java 8 JDK
- Maven
- ModTheSpire
- BaseMod

## Build

The `pom.xml` is already configured for this Steam install:

- `D:\STEAM\steamapps\common\SlayTheSpire\desktop-1.0.jar`
- `D:\STEAM\steamapps\workshop\content\646570\1605060445\ModTheSpire.jar`
- `D:\STEAM\steamapps\workshop\content\646570\1605833019\BaseMod.jar`

Run:

```powershell
mvn package
```

The package step also creates `D:\STEAM\steamapps\common\SlayTheSpire\mods` if needed and copies `PandorasStart.jar` there.

If the jars move later, pass paths explicitly:

```powershell
mvn package `
  "-Dsts.jar=C:\Path\To\SlayTheSpire\desktop-1.0.jar" `
  "-Dmts.jar=C:\Path\To\ModTheSpire.jar" `
  "-Dbasemod.jar=C:\Path\To\BaseMod.jar"
```

## Install

After `mvn package`, launch Steam with `Play with Mods` and enable:

- BaseMod
- Pandora's Start

## Notes

This mod is intentionally local-only. It does not include Steam Workshop metadata, a settings panel, or any changes to the rest of Neow's reward pool.
