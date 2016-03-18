package com.thenairn.rsscripts.utils.gui.component;

import com.thenairn.rsscripts.utils.gui.event.LightMouseEvent;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

/**
 * Created by Thomas Nairn on 17/03/2016.
 */
public abstract class LightLabelButton extends LightLabel {
    @Getter
    @Setter
    private Paint foregroundHover;
    @Getter
    @Setter
    private Paint backgroundHover;

    @Getter
    private boolean hovered = false;

    public LightLabelButton(int x, int y, String text,
                            Font font,
                            float size,
                            int margin,
                            Paint foreground,
                            Paint background,
                            Paint foregroundHover,
                            Paint backgroundHover) {
        super(x, y, text, font, size, margin);
        this.foreground = foreground;
        this.background = background;
        this.foregroundHover = foregroundHover;
        this.backgroundHover = backgroundHover;
    }

    @Override
    public Paint getBackground() {
        return hovered ? backgroundHover : background;
    }

    @Override
    public Paint getForeground() {
        return hovered ? foregroundHover : foreground;
    }

    @Override
    public boolean mouseEntered(LightMouseEvent event) {
        hovered = true;
        return true;
    }

    @Override
    public boolean mouseExited(LightMouseEvent event) {
        hovered = false;
        return true;
    }

    @Override
    public abstract boolean mouseClicked(LightMouseEvent event);

}
