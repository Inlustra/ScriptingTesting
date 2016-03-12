package com.thenairn.rsscripts.utils.items;

import com.thenairn.rsscripts.utils.misc.Cached;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Robin Nairn on 12/03/2016.
 */
public class RunescapeItemDetails {

    private static final String RUNESCAPE_DB_URL = "http://services.runescape.com/m=itemdb_oldschool" +
            "/api/catalogue/detail.json?item=";
    private static final JSONParser parser = new JSONParser();

    private final String name;
    private final String description;
    private final boolean members;
    private final String iconSmallUrl;
    private final String iconUrl;
    private final ItemPrice current;
    private final ItemPrice today;
    private final ItemTrend day30;
    private final ItemTrend day90;
    private final ItemTrend day180;

    private RunescapeItemDetails(String name, String description, boolean members, String iconSmallUrl, String iconUrl,
                                 ItemPrice current, ItemPrice today, ItemTrend day30, ItemTrend day90, ItemTrend day180) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.iconSmallUrl = iconSmallUrl;
        this.iconUrl = iconUrl;
        this.current = current;
        this.today = today;
        this.day30 = day30;
        this.day90 = day90;
        this.day180 = day180;
    }

    public static RunescapeItemDetails load(int item) {
        URL url = null;
        try {
            url = new URL(RUNESCAPE_DB_URL + item);
            return RunescapeItemDetails.fromJson((JSONObject)
                    parser.parse(new InputStreamReader(url.openStream())));
        } catch (IOException e) {
            System.err.println("Could not load item: "+item);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static RunescapeItemDetails fromJson(JSONObject object) {
        object = (JSONObject) object.get("item");
        if (object == null)
            throw new NullPointerException("Could not load item, invalid.");
        return new RunescapeItemDetails((String) object.get("name"),
                (String) object.get("description"),
                Boolean.parseBoolean((String)object.get("members")),
                (String) object.get("icon"),
                (String) object.get("icon_large"),
                ItemPrice.fromJson((JSONObject) object.get("current")),
                ItemPrice.fromJson((JSONObject) object.get("today")),
                ItemTrend.fromJson((JSONObject) object.get("day30")),
                ItemTrend.fromJson((JSONObject) object.get("day90")),
                ItemTrend.fromJson((JSONObject) object.get("day180")));
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

    public ItemPrice getCurrent() {
        return current;
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
        return this.current.price;
    }

    private String getPriceFormatted() {
        String[] suffix = new String[]{"K", "M", "B", "T"};
        int size = (this.getPrice() != 0) ? (int) Math.log10(this.getPrice()) : 0;
        if (size >= 3) {
            while (size % 3 != 0) {
                size = size - 1;
            }
        }
        return (size >= 3) ? +(Math.round((this.getPrice() / Math.pow(10, size)) * 10) / 10d)
                + suffix[(size / 3) - 1]
                : +this.getPrice() + "";

    }

    enum Trend {
        POSITIVE, NEUTRAL, NEGATIVE;
    }

    public static class ItemTrend {

        private Trend trend;
        private double change;

        private ItemTrend(Trend trend, double change) {
            this.trend = trend;
            this.change = change;
        }

        public static ItemTrend fromJson(JSONObject object) {
            try {
                return new ItemTrend(
                        Trend.valueOf(((String) object.get("trend")).toUpperCase()),
                        Double.parseDouble(((String) object.get("change")).replace('%', ' ')));// TODO
            }catch(Exception e) {
                System.err.println(object.toString());
                return null;
            }
        }

        public Trend getTrend() {
            return trend;
        }

        public double getChange() {
            return change;
        }
    }

    public static class ItemPrice {
        private Trend trend;
        private int price;

        public ItemPrice(Trend trend, int price) {
            this.trend = trend;
            this.price = price;
        }

        public static ItemPrice fromJson(JSONObject object) {
            return new ItemPrice(
                    Trend.valueOf(((String) object.get("trend")).toUpperCase()),
                    (int) (Long.parseLong(String.valueOf(object.get("price"))
                            .replaceAll(" ","")
                    .replaceAll(",",""))));
        }

        public Trend getTrend() {
            return trend;
        }

        public int getPrice() {
            return price;
        }
    }

    @Override
    public String toString() {
        return "RunescapeItemDetails{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", members=" + members +
                ", iconSmallUrl='" + iconSmallUrl + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", current=" + current +
                ", today=" + today +
                ", day30=" + day30 +
                ", day90=" + day90 +
                ", day180=" + day180 +
                '}';
    }
}
