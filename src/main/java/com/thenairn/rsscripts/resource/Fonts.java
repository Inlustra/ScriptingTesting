package com.thenairn.rsscripts.resource;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Thomas Nairn on 16/03/2016.
 */
public class Fonts {
    public static final Font RUNESCAPE_CHAT;
    public static final Font RUNESCAPE_CHAT_BOLD;

    static {
        RUNESCAPE_CHAT = loadFont("fonts/runescape_chat.ttf");
        RUNESCAPE_CHAT_BOLD = loadFont("fonts/runescape_chat_bold.ttf");
    }

    private static Font loadFont(String resource) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,
                    ClassLoader.getSystemClassLoader().getResource(resource)
                            .openStream());
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
