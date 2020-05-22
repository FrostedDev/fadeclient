package ca.fadeclient.mods.draggable;

import ca.fadeclient.mods.armorhud.ArmorHud;
import ca.fadeclient.mods.cps.ModCps;
import ca.fadeclient.mods.directionhud.DirectionHud;
import ca.fadeclient.mods.fpsmod.ModFPS;
import ca.fadeclient.mods.itemcounter.ItemCounterMod;
import ca.fadeclient.mods.keystroke.KeyStrokes1;
import ca.fadeclient.mods.potionhud.PotionHud;
import ca.fadeclient.mods.reachdisplaymod.ReachDisplayMod;
import ca.fadeclient.mods.togglesprintsneak.ModToggleSprintSneak;

public class ModInstances
{
    public static void register(HUDManager api)
    {
        api.register(new KeyStrokes1());
        api.register(new PotionHud());
        api.register(new ModFPS());
        api.register(new ModCps());
        api.register(new ArmorHud());
        api.register(new ItemCounterMod());
        api.register(new DirectionHud());
        api.register(new ModToggleSprintSneak());
        api.register(new ReachDisplayMod());
    }

    public static ModToggleSprintSneak getMTS(){
        return new ModToggleSprintSneak();
    }
}
