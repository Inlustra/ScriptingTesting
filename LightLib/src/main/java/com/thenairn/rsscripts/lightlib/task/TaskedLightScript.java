package com.thenairn.rsscripts.lightlib.task;

import com.thenairn.rsscripts.lightlib.LightScript;
import com.thenairn.rsscripts.lightlib.task.type.BackgroundTask;
import com.thenairn.rsscripts.lightlib.task.type.ForegroundTask;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.*;
import java.util.List;


@Slf4j
public abstract class TaskedLightScript extends LightScript {
    List<TaskWrapper<BackgroundTask>> backgroundTasks = new ArrayList<>();
    Deque<TaskWrapper<ForegroundTask>> foregroundTasks = new LinkedList<>();

    @Override
    public void onStart() throws InterruptedException {
        super.onStart();
        addTasks();
        backgroundTasks.forEach((t) -> t.task.onStart(this));
        foregroundTasks.forEach((t) -> t.task.onStart(this));
    }

    @Override
    public void onExit() throws InterruptedException {
        backgroundTasks.forEach((t) -> t.task.onStop(this));
        foregroundTasks.forEach((t) -> t.task.onStop(this));
        super.onExit();
    }

    @Override
    public int safeOnLoop() {
        if (!runBackground()) {
            runForeground();
        }
        return 10;
    }

    private boolean runBackground() {
        boolean stopForeground = false;
        Iterator<TaskWrapper<BackgroundTask>> it = backgroundTasks.iterator();
        while (it.hasNext()) {
            TaskWrapper<BackgroundTask> wrapper = it.next();
            if (!wrapper.task.isRunning()) {
                it.remove();
            }
            stopForeground |= wrapper.task.stopForeground();
            wrapper.update();
        }
        return stopForeground;
    }

    private void runForeground() {
        TaskWrapper<ForegroundTask> wrapper = currentTask();
        if (wrapper == null) {
            return;
        }
        if (!wrapper.task.isRunning()) {
            log.debug("Task " + wrapper.task.getClass().getSimpleName() + " Finished.");
            foregroundTasks.addLast(foregroundTasks.removeFirst());
            runForeground();
            return;
        }
        wrapper.update();
    }

    private TaskWrapper<ForegroundTask> currentTask() {
        return foregroundTasks.peekFirst();
    }

    protected abstract void addTasks();

    public TaskedLightScript addTask(ForegroundTask task) {
        foregroundTasks.add(new TaskWrapper<>(task));
        return this;
    }

    public TaskedLightScript setTask(ForegroundTask task) {
        foregroundTasks.addFirst(new TaskWrapper<>(task));
        return this;
    }

    public TaskedLightScript addTask(BackgroundTask task) {
        backgroundTasks.add(new TaskWrapper<>(task));
        return this;
    }

    private static class TaskWrapper<T extends Task> {
        private T task;
        private long nextRun = 0;
        private long lastRun = 0;

        private TaskWrapper(T task) {
            this.task = task;
        }

        private void update() {
            long now = System.currentTimeMillis();
            if (now - lastRun < nextRun) return;
            nextRun = task.update();
            lastRun = now;
        }
    }

}
