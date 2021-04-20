package com.gildedrose;

public class AgedBrieUpdater extends NormalItemUpdater implements Updater {

    /**
     * "Aged Brie" actually increases in Quality the older it gets.
     *
     * @return true
     */
    @Override
    protected boolean qualityIncreasesTheOlderItGets() {
        return true;
    }
}
