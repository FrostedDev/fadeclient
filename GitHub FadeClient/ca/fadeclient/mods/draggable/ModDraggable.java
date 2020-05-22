package ca.fadeclient.mods.draggable;

import ca.fadeclient.FadeClient;
import ca.fadeclient.utils.FileManager;
import net.minecraft.client.gui.ScaledResolution;

import java.io.File;

public abstract class ModDraggable extends Mod implements IRenderer
{
    protected ScreenPosition pos = this.loadPositionFromFile();
    protected FadeClient client;

    public ScreenPosition load()
    {
        return this.pos;
    }

    public void save(ScreenPosition pos)
    {
        this.pos = pos;
        this.savePositionToFile();
    }

    private File getFolder()
    {
        File file1 = new File(FileManager.getModsDirectory(), this.getClass().getSimpleName());
        file1.mkdirs();
        return file1;
    }

    private void saveEnabled()
    {
        FileManager.writeJsonToFile(new File(this.getFolder(), "save.json"), true);
    }

    private boolean loadPositionFromFile1()
    {
        return false;
    }

    private void savePositionToFile()
    {
        FileManager.writeJsonToFile(new File(this.getFolder(), "pos.json"), this.pos);
    }

    private ScreenPosition loadPositionFromFile()
    {
        ScreenPosition screenposition = (ScreenPosition)FileManager.readFromJson(new File(this.getFolder(), "pos.json"), ScreenPosition.class);

        if (screenposition == null)
        {
            ScaledResolution sr = new ScaledResolution(mc);
            screenposition = ScreenPosition.fromAbsolute(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2);
            this.pos = screenposition;
            this.savePositionToFile();
        }

        return screenposition;
    }

    public final int getLineOffset(ScreenPosition pos, int lineNum)
    {
        return pos.getAbsoluteY() + this.getLineOffset(lineNum);
    }

    private int getLineOffset(int lineNum)
    {
        return (this.font.FONT_HEIGHT + 3) * lineNum;
    }

    public boolean isEnable()
    {
        return false;
    }
}
