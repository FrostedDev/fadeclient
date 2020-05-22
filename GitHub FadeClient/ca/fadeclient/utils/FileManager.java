package ca.fadeclient.utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager
{
    private static Gson gson = new Gson();
    private static File ROOT_DIR = new File("FadeClient");
    private static File MODS_DIR = new File(ROOT_DIR, "Mods");

    public static void init()
    {
        if (!ROOT_DIR.exists())
        {
            ROOT_DIR.mkdirs();
        }

        if (!MODS_DIR.exists())
        {
            MODS_DIR.mkdirs();
        }
    }

    public static Gson getGson()
    {
        return gson;
    }

    public static File getModsDirectory()
    {
        return MODS_DIR;
    }

    public static boolean writeJsonToFile(File file, Object obj)
    {
        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileOutputStream fileoutputstream = new FileOutputStream(file);
            fileoutputstream.write(gson.toJson(obj).getBytes());
            fileoutputstream.flush();
            fileoutputstream.close();
            return true;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return false;
        }
    }

    public static <T> T readFromJson(File file, Class<T> c)
    {
        try
        {
            FileInputStream fileinputstream = new FileInputStream(file);
            InputStreamReader inputstreamreader = new InputStreamReader(fileinputstream);
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
            StringBuilder stringbuilder = new StringBuilder();
            String s;

            while ((s = bufferedreader.readLine()) != null)
            {
                stringbuilder.append(s);
            }

            bufferedreader.close();
            inputstreamreader.close();
            fileinputstream.close();
            return (T)gson.fromJson(stringbuilder.toString(), c);
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return (T)null;
        }
    }
}
