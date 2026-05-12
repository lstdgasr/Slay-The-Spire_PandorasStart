package pandorasstart.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.PandorasBox;

import java.util.ArrayList;
import java.util.List;

public class NeowPandorasBoxPatch {
    private static final String OPTION_TEXT = "Replace your starter relic with Pandora's Box.";
    private static final String RESULT_TEXT = "Neow opens Pandora's Box. Your starter relic is gone, and your starting deck is transformed.";
    private static final String LEAVE_TEXT = "[Leave]";

    private static int pandoraOptionIndex = -1;

    @SpirePatch(clz = NeowEvent.class, method = "blessing")
    public static class AddPandorasBoxOptionToFullBlessing {
        public static void Postfix(NeowEvent __instance) {
            addPandorasBoxOption(__instance);
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "miniBlessing")
    public static class AddPandorasBoxOptionToMiniBlessing {
        public static void Postfix(NeowEvent __instance) {
            addPandorasBoxOption(__instance);
        }
    }

    @SpirePatch(clz = NeowEvent.class, method = "buttonEffect")
    public static class HandlePandorasBoxOption {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(NeowEvent __instance, int buttonPressed) {
            int screenNum = ReflectionHacks.getPrivate(__instance, NeowEvent.class, "screenNum");
            if (screenNum == 3 && buttonPressed == pandoraOptionIndex) {
                ReflectionHacks.privateMethod(NeowEvent.class, "dismissBubble").invoke(__instance);
                __instance.roomEventText.clearRemainingOptions();
                replaceStarterRelicWithPandorasBox();
                __instance.imageEventText.updateBodyText(RESULT_TEXT);
                ReflectionHacks.setPrivate(__instance, NeowEvent.class, "screenNum", 99);
                __instance.roomEventText.updateDialogOption(0, LEAVE_TEXT);
                __instance.roomEventText.clearRemainingOptions();
                NeowEvent.waitingToSave = true;
                return SpireReturn.Return(null);
            }

            return SpireReturn.Continue();
        }
    }

    private static int getDialogOptionCount(NeowEvent event) {
        List<?> optionList = ReflectionHacks.getPrivate(event.roomEventText, event.roomEventText.getClass(), "optionList");
        return optionList == null ? 0 : optionList.size();
    }

    private static void addPandorasBoxOption(NeowEvent event) {
        pandoraOptionIndex = getDialogOptionCount(event);
        event.roomEventText.addDialogOption(OPTION_TEXT);
    }

    private static void replaceStarterRelicWithPandorasBox() {
        String starterRelicId = getStarterRelicId();
        if (starterRelicId != null && AbstractDungeon.player.hasRelic(starterRelicId)) {
            AbstractDungeon.player.loseRelic(starterRelicId);
        }

        AbstractDungeon.bossRelicPool.remove(PandorasBox.ID);
        AbstractRelic pandorasBox = new PandorasBox();
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                Settings.WIDTH / 2.0F,
                Settings.HEIGHT / 2.0F,
                pandorasBox
        );
    }

    private static String getStarterRelicId() {
        ArrayList<String> starterRelics = AbstractDungeon.player.getStartingRelics();
        if (starterRelics == null || starterRelics.isEmpty()) {
            return null;
        }
        return starterRelics.get(0);
    }
}
