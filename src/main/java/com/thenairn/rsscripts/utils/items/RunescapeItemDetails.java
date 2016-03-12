package com.thenairn.rsscripts.utils.items;

import com.thenairn.rsscripts.utils.misc.Cached;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
public class RunescapeItemDetails {

    private final String name;
    private final String description;
    private final boolean members;
    private final String iconSmallUrl;
    private final String iconUrl;
    private final ItemPrice today;
    private final ItemTrend day30;
    private final ItemTrend day90;
    private final ItemTrend day180;

    private RunescapeItemDetails(String name, String description, boolean members, String iconSmallUrl, String iconUrl,
                                 ItemPrice today, ItemTrend day30, ItemTrend day90, ItemTrend day180) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.iconSmallUrl = iconSmallUrl;
        this.iconUrl = iconUrl;
        this.today = today;
        this.day30 = day30;
        this.day90 = day90;
        this.day180 = day180;
    }

    public static RunescapeItemDetails fromJson(JSONObject object) {
        object = (JSONObject) object.get("item");
        if (object == null)
            throw new NullPointerException("Could not load item, invalid");

        RunescapeItemDetails details = new RunescapeItemDetails();
        details.name = (String) object.get("name");
        details.description =
        return details;
    }

    private Cached<BufferedImage> icon = new Cached<BufferedImage>() {
        @Override
        protected BufferedImage load() {
            try {
                return ImageIO.read(new URL(RunescapeItemDetails.this.iconUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    };


    private Cached<BufferedImage> thumb = new Cached<BufferedImage>() {
        @Override
        protected BufferedImage load() {
            try {
                return ImageIO.read(new URL(RunescapeItemDetails.this.iconSmallUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public boolean isMembers() {
        return members;
    }

    public ItemPrice getToday() {
        return today;
    }

    public ItemTrend getDay30() {
        return day30;
    }

    public ItemTrend getDay90() {
        return day90;
    }

    public ItemTrend getDay180() {
        return day180;
    }

    public BufferedImage getIcon() {
        return icon.get();
    }

    public BufferedImage getThumbnail() {
        return thumb.get();
    }

    private int getPrice() {
        return this.today.price;
    }

    private String getPriceFormatted() {
        String[] suffix = new String[]{"K", "M", "B", "T"};
        int size = (this.today.price != 0) ? (int) Math.log10(this.today.price) : 0;
        if (size >= 3) {
            while (size % 3 != 0) {
                size = size - 1;
            }
        }
        return (size >= 3) ? +(Math.round((this.today.price / Math.pow(10, size)) * 10) / 10d)
                + suffix[(size / 3) - 1]
                : +this.today.price + "";

    }

     enum Trend {
        POSITIVE, NEUTRAL, NEGATIVE;
    }

    private static class ItemTrend {


        private Trend positive;
        private double change;

        private ItemTrend(Trend positive, double change) {
            this.positive = positive;
            this.change = change;
        }

        public static ItemTrend fromJson(JSONObject object) {
            return new ItemTrend(
                    Trend.valueOf(((String) object.get("trend")).toUpperCase()),
                    (double) object.get("price"));// TODO
        }
    }

    private static class ItemPrice {
        private boolean positive;
        private int price;

        public static ItemTrend fromJson(JSONObject object) {
            return new ItemTrend(
                    Trend.valueOf(((String) object.get("trend")).toUpperCase()),
                    (double) object.get("price"));
        }
    }
}
