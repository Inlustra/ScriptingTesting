package com.thenairn.rsscripts.lightlib.task.selector;

import com.thenairn.rsscripts.lightlib.LightAPI;
import com.thenairn.rsscripts.lightlib.LightScript;
import com.thenairn.rsscripts.lightlib.task.gui.ChatboxContainer;
import com.thenairn.rsscripts.lightlib.utils.gui.LightGUI;
import com.thenairn.rsscripts.lightlib.utils.gui.component.LightImage;

/**
 * Created by Thomas Nairn on 18/03/2016.
 */
public class TaskSelectorGUI extends LightGUI {

    private final LightScript script;
    private ChatboxContainer container;

    public TaskSelectorGUI(LightScript script, int width, int height) {
        super(width, height);
        this.script = script;
        setup();
    }

    private void setup() {
        this.container = new ChatboxContainer();
        for (int i = 0; i < script.getDescriptors().length; i++) {
            TaskDescriptor descriptor = script.getDescriptors()[i];
            LightImage image = new LightImage((40 * i) + 5, (int) (Math.floor(i / 8) * 40),
                    LightAPI.get().getItemApi().get(descriptor.itemId).getIcon());
            container.add(image);
        }
        add(container);
    }


}
