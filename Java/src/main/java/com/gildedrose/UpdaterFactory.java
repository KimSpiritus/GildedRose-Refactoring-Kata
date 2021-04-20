package com.gildedrose;

public class UpdaterFactory {
    private static UpdaterFactory instance;

    private UpdaterFactory() {
    }

    public static UpdaterFactory get() {
        if (instance == null) {
            instance = new UpdaterFactory();
        }
        return instance;
    }

    private static boolean isSulfuras(final Item item) {
        return item.name.toLowerCase().contains("sulfuras");
    }

    private static boolean isBackstagePasses(final Item item) {
        return item.name.toLowerCase().contains("backstage passes");
    }

    private static boolean isAgedBrie(final Item item) {
        return item.name.equalsIgnoreCase("aged Brie");
    }

    private static boolean isConjured(final Item item) {
        return item.name.toLowerCase().contains("conjured");
    }

    public Updater createUpdaterFor(Item item) {
        if (isSulfuras(item)) {
            return new SulfurasUpdater();
        }
        if (isAgedBrie(item)) {
            return new AgedBrieUpdater();
        }
        if (isBackstagePasses(item)) {
            return new BackstagePassesUpdater();
        }
        if (isConjured(item)) {
            return new ConjuredItemUpdater();
        }

        return new NormalItemUpdater();
    }

}
