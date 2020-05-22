package ca.fadeclient.events;

import java.util.ArrayList;

public class Event
{
    public Event call()
    {
        final ArrayList<EventData> dataList = EventManager.get(this.getClass());

        if (dataList != null)
        {
            for (EventData eventdata : dataList)
            {
                try
                {
                    eventdata.target.invoke(eventdata.source, this);
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        }

        return this;
    }
}
