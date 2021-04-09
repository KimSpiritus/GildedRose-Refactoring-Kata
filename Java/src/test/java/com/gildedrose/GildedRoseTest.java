package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    Item[] items;
    Item someItem, agedBrie, sulfurasWithSellinZero, sulfurasWithSellinNegative, backstagePassesWithSellin15, backstagePassesWithSellin10, backstagePassesWithSellin5, conjuredManaCake;

    GildedRose app;

    @BeforeEach
    void setUp() {
        someItem = new Item("Elixir of the Mongoose", 5, 20);
        agedBrie = new Item("Aged Brie", 2, 0);
        sulfurasWithSellinZero = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        sulfurasWithSellinNegative = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        backstagePassesWithSellin15 = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        backstagePassesWithSellin10 = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        backstagePassesWithSellin5 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        conjuredManaCake = new Item("Conjured Mana Cake", 3, 20);

        items = new Item[]{
                someItem,
                agedBrie,
                sulfurasWithSellinZero,
                sulfurasWithSellinNegative,
                backstagePassesWithSellin15,
                backstagePassesWithSellin10,
                backstagePassesWithSellin5,
                conjuredManaCake};

        app = new GildedRose(items);
    }

    @Test
    void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void eachDayTheSystemLowersSellInAndQuality() {
        assertEquals(5, someItem.sellIn);
        assertEquals(20, someItem.quality);

        app.updateQuality();

        assertEquals(4, someItem.sellIn);
        assertEquals(19, someItem.quality);

        app.updateQuality();

        assertEquals(3, someItem.sellIn);
        assertEquals(18, someItem.quality);
    }

    @Test
    void onceTheSellByDateHasPassedQualityDegradesTwiceAsFast() {
        assertEquals(5, someItem.sellIn);
        assertEquals(20, someItem.quality);

        updateQuality(5);

        assertEquals(0, someItem.sellIn);
        assertEquals(15, someItem.quality);

        updateQuality(1);

        assertEquals(-1, someItem.sellIn);
        assertEquals(13, someItem.quality);

        updateQuality(1);

        assertEquals(-2, someItem.sellIn);
        assertEquals(11, someItem.quality);
    }

    @Test
    void theQualityOfAnItemIsNeverNegative() {
        assertEquals(5, someItem.sellIn);
        assertEquals(20, someItem.quality);

        updateQuality(12);

        assertEquals(-7, someItem.sellIn);
        assertEquals(1, someItem.quality);

        updateQuality(1);

        assertEquals(-8, someItem.sellIn);
        assertEquals(0, someItem.quality);

        updateQuality(1);

        assertEquals(-9, someItem.sellIn);
        assertEquals(0, someItem.quality);
    }

    @Test
    void agedBrieIncreasesInQualityTheOlderItGets() {
        assertEquals(2, agedBrie.sellIn);
        assertEquals(0, agedBrie.quality);

        app.updateQuality();

        assertEquals(1, agedBrie.sellIn);
        assertEquals(1, agedBrie.quality);

        app.updateQuality();

        assertEquals(0, agedBrie.sellIn);
        assertEquals(2, agedBrie.quality);

        app.updateQuality();

        assertEquals(-1, agedBrie.sellIn);
        assertEquals(4, agedBrie.quality);
    }

    @Test
    void theQualityOfAnItemIsNeverMoreThan50() {
        assertEquals(0, agedBrie.quality);

        updateQuality(50);

        assertEquals(50, agedBrie.quality);

        updateQuality(1);

        assertEquals(50, agedBrie.quality);
    }

    @Test
    void sulfurasBeingALegendaryItemNeverHasToBeSoldOrDecreasesInQuality() {
        assertEquals(0, sulfurasWithSellinZero.sellIn);
        assertEquals(80, sulfurasWithSellinZero.quality);
        assertEquals(-1, sulfurasWithSellinNegative.sellIn);
        assertEquals(80, sulfurasWithSellinNegative.quality);

        updateQuality(5);

        assertEquals(0, sulfurasWithSellinZero.sellIn);
        assertEquals(80, sulfurasWithSellinZero.quality);
        assertEquals(-1, sulfurasWithSellinNegative.sellIn);
        assertEquals(80, sulfurasWithSellinNegative.quality);

    }

    @Test
    void backstagePassesQualityIncreasesByTwoWhenThereAreTenDaysOrLess() {
        assertEquals(10, backstagePassesWithSellin10.sellIn);
        assertEquals(20, backstagePassesWithSellin10.quality);

        updateQuality(1);

        assertEquals(9, backstagePassesWithSellin10.sellIn);
        assertEquals(22, backstagePassesWithSellin10.quality);

        updateQuality(1);

        assertEquals(8, backstagePassesWithSellin10.sellIn);
        assertEquals(24, backstagePassesWithSellin10.quality);
    }

    @Test
    void backstagePassesQualityIncreasesByThreeWhenThereAreFiveDaysOrLess() {
        assertEquals(5, backstagePassesWithSellin5.sellIn);
        assertEquals(20, backstagePassesWithSellin5.quality);

        updateQuality(1);

        assertEquals(4, backstagePassesWithSellin5.sellIn);
        assertEquals(23, backstagePassesWithSellin5.quality);

        updateQuality(1);

        assertEquals(3, backstagePassesWithSellin5.sellIn);
        assertEquals(26, backstagePassesWithSellin5.quality);
    }

    @Test
    void backstagePassesQualityDropsToZeroAfterTheConcert() {
        assertEquals(5, backstagePassesWithSellin5.sellIn);
        assertEquals(20, backstagePassesWithSellin5.quality);

        updateQuality(5);

        assertEquals(0, backstagePassesWithSellin5.sellIn);
        assertEquals(35, backstagePassesWithSellin5.quality);

        updateQuality(1);

        assertEquals(-1, backstagePassesWithSellin5.sellIn);
        assertEquals(0, backstagePassesWithSellin5.quality);
    }

    @Test
    void conjuredItemsDegradeInQualityTwiceAsFastAsNormalItems() {
        assertEquals(3, conjuredManaCake.sellIn);
        assertEquals(20, conjuredManaCake.quality);

        updateQuality(1);

        assertEquals(2, conjuredManaCake.sellIn);
        assertEquals(18, conjuredManaCake.quality);

        updateQuality(2);

        assertEquals(0, conjuredManaCake.sellIn);
        assertEquals(14, conjuredManaCake.quality);

        updateQuality(1);

        assertEquals(-1, conjuredManaCake.sellIn);
        assertEquals(10, conjuredManaCake.quality);

        updateQuality(1);

        assertEquals(-2, conjuredManaCake.sellIn);
        assertEquals(6, conjuredManaCake.quality);
    }

    private void updateQuality(final int times) {
        for (int i = 0; i < times; i++) {
            app.updateQuality();
        }
    }
}
