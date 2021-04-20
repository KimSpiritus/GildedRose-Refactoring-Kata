package com.gildedrose;

public class ConjuredItemUpdater extends NormalItemUpdater implements Updater {

    /**
     * "Conjured" items degrade in Quality twice as fast as normal items.
     *
     * @param item The item to update
     * @return the amount to update with.
     */
    @Override
    protected int getQualityUpdateAmount(final Item item) {
        return super.getQualityUpdateAmount(item) * 2;
    }
}
