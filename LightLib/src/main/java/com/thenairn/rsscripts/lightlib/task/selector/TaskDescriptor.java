package com.thenairn.rsscripts.lightlib.task.selector;

import com.thenairn.rsscripts.lightlib.task.LightTask;

public class TaskDescriptor {
    LightTask script;
    String name;
    int itemId;

    private TaskDescriptor(LightTask script, String name, int itemId) {
        this.script = script;
        this.name = name;
        this.itemId = itemId;
    }

    public static Builder of(LightTask script) {
        return new Builder(script);
    }

    public static class Builder {
        private LightTask script;
        private String name;
        private int itemId;

        private Builder(LightTask script) {
            this.script = script;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder item(int itemId) {
            this.itemId = itemId;
            return this;
        }

        public TaskDescriptor build() {
            return new TaskDescriptor(script, name, itemId);
        }
    }
}
