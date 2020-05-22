package ca.fadeclient.mods.itemcounter;

import ca.fadeclient.mods.draggable.ModDraggable;
import ca.fadeclient.mods.draggable.ScreenPosition;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCounterMod extends ModDraggable {

    public int potionininv = 0;

    @Override
    public int getWidth() {
        return 18;
    }

    @Override
    public int getHeight() {
        return 18;
    }

    @Override
    public void render(ScreenPosition pos) {

        ItemStack stack = new ItemStack(Items.potionitem, 1, 16421);
        //stack.setItemDamage(16421);
        itemcount(stack);

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.potionitem, 1, (short)16421), pos.getAbsoluteX(), pos.getAbsoluteY());
        mc.getRenderItem().renderItemOverlayIntoGUI(this.font, stack, pos.getAbsoluteX(), pos.getAbsoluteY(), potionininv + "");
        RenderHelper.disableStandardItemLighting();

    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.potionitem, 1, (short)16421), pos.getAbsoluteX(), pos.getAbsoluteY());
        RenderHelper.disableStandardItemLighting();
    }

    public void itemcount(ItemStack item){
        potionininv = 0;
        for(int i = 0; i < mc.thePlayer.inventory.getSizeInventory(); i++){
            if(mc.thePlayer.inventory.getStackInSlot(i) != null && mc.thePlayer.inventory.getStackInSlot(i).getIsItemStackEqual(item)){
                potionininv++;
            }
        }
    }
}
