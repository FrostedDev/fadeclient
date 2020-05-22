package ca.fadeclient.clickgui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;

public class ClickGui extends GuiScreen {

    @Override
    public void initGui() {
        super.initGui();
    }

    public int mouseClickedButton = -1;

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        mouseClickedButton = mouseButton;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if(mouseClickedButton == 0){
            this.drawRect(this.width / 2, this.height / 2, this.width / 2 + 20, this.height / 2 + 2, -1);
        }

        this.drawString(this.fontRendererObj, mouseClickedButton + "", this.width / 2, this.height / 2 + 20, -1);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        mouseClickedButton = -1;

    }
}
