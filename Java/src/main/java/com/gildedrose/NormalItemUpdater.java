package com.gildedrose;

public class NormalItemUpdater implements Updater {

    private static final int QUALITY_MIN = 0;
    private static final int QUALITY_MAX = 50;
    private static final int QUALITY_UPDATE_AMOUNT = 1;
    private static final int SELL_IN_UPDATE_AMOUNT = 1;

    @Override
    public void update(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    protected void updateQuality(Item item) {
        int updateAmount = getQualityUpdateAmount(item);

        if (sellInDateHasPassed(item)) {
            updateAmount *= 2;
        }

        if (qualityIncreasesTheOlderItGets()) {
            updateAmount *= -1;
        }

        item.quality = getWithinBoundaries(item.quality - updateAmount);
    }

    protected int getQualityUpdateAmount(Item item) {
        return QUALITY_UPDATE_AMOUNT;
    }

    protected boolean sellInDateHasPassed(final Item item) {
        return item.sellIn <= 0;
    }

    protected boolean qualityIncreasesTheOlderItGets() {
        return false;
    }

    /**
     * All items have a SellIn value which denotes the number of days we have to sell the item. At the end of each day
     * our system lowers tha value for every item
     *
     * @param item The iem to update.
     */
    protected void updateSellIn(Item item) {
        item.sellIn = item.sellIn - SELL_IN_UPDATE_AMOUNT;
    }

    /**
     * The Quality of an item is never negative and the Quality of an item is never more than 50.
     *
     * @param quality The item quality
     * @return the quality, limited within the min and max boundaries.
     */
    private int getWithinBoundaries(final int quality) {
        return Integer.min(QUALITY_MAX, Integer.max(QUALITY_MIN, quality));
    }
}
