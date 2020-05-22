package ca.fadeclient.mods.togglesprintsneak;

import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.gui.ScaledResolution;

public class ModToggleSprintSneak extends ModDraggable {

    public boolean flyBoost = true;
    public float flyBoostFactor = 3;
    public int keyHoldTicks = 7;

    @Override
    public int getWidth() {
        return font.getStringWidth("[Sprinting (Key Toggled)]");
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT;
    }

    @Override
    public void render(ScreenPosition pos) {
        this.font.drawStringWithShadow(mc.thePlayer.movementInput.getDisplayText(), pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        this.font.drawStringWithShadow("[Sprinting (Key Toggled)]", pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
    }
}
