package com.gildedrose;

public class SulfurasUpdater extends NormalItemUpdater implements Updater {

    /**
     * "Sulfuras", being a legendary item, never has to be sold or decreases in Quality.
     *
     * @param item The item to update.
     */
    @Override
    public void update(Item item) {
        // do nothing
    }
}
