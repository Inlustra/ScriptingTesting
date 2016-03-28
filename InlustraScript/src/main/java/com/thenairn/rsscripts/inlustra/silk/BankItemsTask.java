package com.thenairn.rsscripts.inlustra.silk;

import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import com.thenairn.rsscripts.lightlib.task.type.ForegroundTask;
import org.osbot.rs07.api.map.constants.Banks;

/**
 * Created by thoma on 28/03/2016.
 */
public class BankItemsTask implements ForegroundTask {

    private TaskedLightScript script;

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void onStart(TaskedLightScript script) {

    }

    @Override
    public void onStop(TaskedLightScript script) {

    }

    @Override
    public int update() {
        if (!script.getLocalWalker().walk(Banks.ARDOUGNE_NORTH, true)) {
            return 40;
        }
        if (!script.getBank().isOpen()) {
            try {
                script.getBank().open();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 40;
        }
        script.getBank().depositAll();
        return 0;
    }
}
