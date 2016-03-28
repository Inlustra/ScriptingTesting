package com.thenairn.rsscripts.lightlib.utils.tasks;

import com.thenairn.rsscripts.lightlib.task.TaskedLightScript;
import com.thenairn.rsscripts.lightlib.task.type.BackgroundTask;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class UnderAttackBackgroundTask implements BackgroundTask {

    protected TaskedLightScript script;
    private boolean stopForeground = false;
    @Setter
    private boolean isRunning = true;
    private boolean isInteracting = false;

    @Override
    public boolean stopForeground() {
        return stopForeground;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void onStart(TaskedLightScript script) {
        this.script = script;
    }

    @Override
    public void onStop(TaskedLightScript script) {

    }

    @Override
    public int update() {
        if (!script.myPlayer().isUnderAttack() && !isInteracting) {
            return 10;
        }
        isInteracting = onAttackUpdate();
        stopForeground = stopOnAttack();
        return 10;
    }

    public abstract boolean stopOnAttack();

    public abstract boolean onAttackUpdate();
}
