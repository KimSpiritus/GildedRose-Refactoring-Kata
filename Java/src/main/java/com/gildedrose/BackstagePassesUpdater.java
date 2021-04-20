package com.gildedrose;

public class BackstagePassesUpdater extends NormalItemUpdater implements Updater {

    /**
     * Quality drops to 0 after the concert.
     *
     * @param item The item to update
     */
    @Override
    protected void updateQuality(Item item) {
        if (sellInDateHasPassed(item)) {
            item.quality = 0;
        } else {
            super.updateQuality(item);
        }
    }

    /**
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     *
     * @return true;
     */
    @Override
    protected boolean qualityIncreasesTheOlderItGets() {
        return true;
    }

    /**
     * Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less.
     *
     * @param item The item
     * @return the update amount for the quality.
     */
    @Override
    protected int getQualityUpdateAmount(final Item item) {
        if (item.sellIn <= 5) {
            return 3;
        } else if (item.sellIn <= 10) {
            return 2;
        }
        return super.getQualityUpdateAmount(item);
    }
}
