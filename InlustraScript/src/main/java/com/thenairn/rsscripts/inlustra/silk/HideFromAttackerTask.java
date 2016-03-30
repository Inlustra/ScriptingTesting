package com.thenairn.rsscripts.inlustra.silk;

import com.thenairn.rsscripts.lightlib.utils.tasks.UnderAttackBackgroundTask;

/**
 * Created by thoma on 28/03/2016.
 */
public class HideFromAttackerTask extends UnderAttackBackgroundTask {
    @Override
    public boolean stopOnAttack() {
        return true;
    }

    @Override
    public boolean onAttackUpdate() {
        //script.myPlayer().isInteracting()
        return true;
    }
}
