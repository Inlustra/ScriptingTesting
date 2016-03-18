package com.thenairn.rsscripts.utils.script.selector;

import com.thenairn.rsscripts.utils.gui.LightGUI;
import com.thenairn.rsscripts.utils.gui.component.LightImage;
import com.thenairn.rsscripts.utils.items.ItemAPI;
import com.thenairn.rsscripts.utils.script.LightScript;
import com.thenairn.rsscripts.utils.script.gui.ChatboxContainer;

/**
 * Created by Thomas Nairn on 18/03/2016.
 */
public class ScriptSelectorGUI extends LightGUI {

    private final LightScript script;
    private ChatboxContainer container;

    public ScriptSelectorGUI(LightScript script, int width, int height) {
        super(width, height);
        this.script = script;
        setup();
    }

    private void setup() {
        this.container = new ChatboxContainer();
        for (int i = 0; i < script.getDescriptors().length; i++) {
            InnerScriptDescriptor descriptor = script.getDescriptors()[i];
            LightImage image = new LightImage((40 * i) + 5, (int) (Math.floor(i / 8) * 40),
                    ItemAPI.get(descriptor.itemId).getIcon());
            container.add(image);
        }
        add(container);
    }


}
