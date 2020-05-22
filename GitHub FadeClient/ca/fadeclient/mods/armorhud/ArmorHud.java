package ca.fadeclient.mods.armorhud;

import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ArmorHud extends ModDraggable {
    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void render(ScreenPosition pos) {
        GameSettings gamesettings = this.mc.gameSettings;
        if (GameSettings.armorHudValue)
        {
            if (this.mc.gameSettings.showDebugInfo)
            {
                return;
            }

            if (this.mc.currentScreen instanceof GuiChat)
            {
                return;
            }

            for (int i = 0; i < this.mc.thePlayer.inventory.armorInventory.length; ++i)
            {
                ItemStack itemstack = this.mc.thePlayer.inventory.armorInventory[i];
                this.renderItemStack(pos, i, itemstack, 1);
            }
        }
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        GameSettings gamesettings = this.mc.gameSettings;

        if (GameSettings.armorHudValue)
        {
            ItemStack itemstack = new ItemStack(Items.diamond_helmet);
            ItemStack itemstack1 = new ItemStack(Items.diamond_chestplate);
            ItemStack itemstack2 = new ItemStack(Items.diamond_leggings);
            ItemStack itemstack3 = new ItemStack(Items.diamond_boots);
            this.renderItemStack(pos, 3, itemstack, 1);
            this.renderItemStack(pos, 2, itemstack1, 1);
            this.renderItemStack(pos, 1, itemstack2, 1);
            this.renderItemStack(pos, 0, itemstack3, 1);
        }
    }

    private void renderItemStack(ScreenPosition pos, int i, ItemStack is, double scaledFactor)
    {
        GlStateManager.pushMatrix();
        GL11.glScaled(scaledFactor, scaledFactor, scaledFactor);
        double newAbsolute = 1.0 / scaledFactor;
        if (is != null)
        {
            new BlockPos(this.mc.thePlayer.getPosition());

            if (is.getItem().isDamageable())
            {
                int yAdd = (16 * i) - 48;
                RenderHelper.enableGUIStandardItemLighting();
                this.mc.getRenderItem().renderItemAndEffectIntoGUI(is, pos.getAbsoluteX() * newAbsolute, pos.getAbsoluteY() * newAbsolute - yAdd);
                int j = (is.getMaxDamage() - is.getItemDamage()) * 100 / is.getMaxDamage();
                int value = (is.getMaxDamage() - is.getItemDamage());
                int maxDamage = is.getMaxDamage();

                if(this.mc.gameSettings.armorHudValue) {
                    if (j >= 90)
                    {
                        this.font.drawStringWithShadow("" + value + "/" + maxDamage, (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY()  * newAbsolute - yAdd + 5), (new Color(15, 154, 0)).getRGB());
                    }
                    else if(j >= 80){
                        this.font.drawStringWithShadow("§a" + value + "/" + maxDamage, (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY()  * newAbsolute - yAdd + 5), (new Color(15, 154, 0)).getRGB());

                    }
                    else if (j >= 60)
                    {
                        this.font.drawStringWithShadow("§e" + value + "/" + maxDamage, (float)(pos.getAbsoluteX()  * newAbsolute + 20), (float)(pos.getAbsoluteY()  * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                    else if (j >= 40)
                    {
                        this.font.drawStringWithShadow("§6" + value + "/" + maxDamage, (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY() * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                    else if (j >= 20)
                    {
                        this.font.drawStringWithShadow("§c" + value + "/" + maxDamage, (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY() * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                    else if (j >= 0)
                    {
                        this.font.drawStringWithShadow("§4" + value + "/" + maxDamage, (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY() * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                }else {
                    if (j >= 90)
                    {
                        this.font.drawStringWithShadow("" + j + "%", (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY()  * newAbsolute - yAdd + 5), (new Color(15, 154, 0)).getRGB());
                    }
                    else if(j >= 80){
                        this.font.drawStringWithShadow("§a" + j + "%", (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY()  * newAbsolute - yAdd + 5), (new Color(15, 154, 0)).getRGB());

                    }
                    else if (j >= 60)
                    {
                        this.font.drawStringWithShadow("§e" + j + "%", (float)(pos.getAbsoluteX()  * newAbsolute + 20), (float)(pos.getAbsoluteY()  * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                    else if (j >= 40)
                    {
                        this.font.drawStringWithShadow("§6" + j + "%", (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY() * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                    else if (j >= 20)
                    {
                        this.font.drawStringWithShadow("§c" + j + "%", (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY() * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                    else if (j >= 0)
                    {
                        this.font.drawStringWithShadow("§4" + j + "%", (float)(pos.getAbsoluteX() * newAbsolute + 20), (float)(pos.getAbsoluteY() * newAbsolute - yAdd + 5), Color.DARK_GRAY.getRGB());
                    }
                }
            }
        }

        GL11.glScaled(1.0, 1.0, 1.0);
        GlStateManager.popMatrix();
    }

    public boolean isEnable()
    {
        return this.mc.gameSettings.reducedDebugInfo;
    }
}
