package com.thenairn.rsscripts.utils.script.selector;

import com.thenairn.rsscripts.utils.script.LightInnerScript;

public class InnerScriptDescriptor {
    LightInnerScript script;
    String name;
    int itemId;

    private InnerScriptDescriptor(LightInnerScript script, String name, int itemId) {
        this.script = script;
        this.name = name;
        this.itemId = itemId;
    }

    public static Builder of(LightInnerScript script) {
        return new Builder(script);
    }

    public static class Builder {
        private LightInnerScript script;
        private String name;
        private int itemId;

        private Builder(LightInnerScript script) {
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

        public InnerScriptDescriptor build() {
            return new InnerScriptDescriptor(script, name, itemId);
        }
    }
}
