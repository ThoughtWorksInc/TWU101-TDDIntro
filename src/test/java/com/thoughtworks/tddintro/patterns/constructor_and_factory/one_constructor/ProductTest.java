package com.thoughtworks.tddintro.patterns.constructor_and_factory.one_constructor;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductTest {
    @Test
    public void pricePerItemShouldBeSameAsTotalPriceWhenThereIsOneItem() {
        Product product = new Product(1, 14.0);

        assertThat(product.pricePerItem(), is(14.0));
    }
    @Test
    @Ignore // This test fails due to price per item being Infinity
    public void pricePerItemShouldBeSameAsTotalPriceWhenThereIsOneItemWithDefaultConstructor() {
        Product product = new Product();
        product.setTotalPrice(14.0);
        assertThat(product.pricePerItem(), is(14.0));
    }
}