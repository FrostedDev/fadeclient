package ca.fadeclient.mods.draggable;

public interface IRenderConfig
{
    public void save(ScreenPosition pos);

    public ScreenPosition load();

    void render(ScreenPosition var1);

    void renderDummy(ScreenPosition var1);
}
