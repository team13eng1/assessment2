package com.mygdx.game.ingredient;

import static org.junit.Assert.*;

import org.junit.Test;

public class IngredientNameTest {

	@Test
	public void testLettuceCutName() {
		assertEquals("LETTUCE_CUT", IngredientName.LETTUCE_CUT.name());
	}

	@Test
	public void testOnionCutName() {
		assertEquals("ONION_CUT", IngredientName.ONION_CUT.name());
	}

	@Test
	public void testTomatoCutName() {
		assertEquals("TOMATO_CUT", IngredientName.TOMATO_CUT.name());
	}

	@Test
	public void testSaladName() {
		assertEquals("SALAD", IngredientName.SALAD.name());
	}

	@Test
	public void testBreadName() {
		assertEquals("BREAD", IngredientName.BREAD.name());
	}

	@Test
	public void testCheeseName() {
		assertEquals("CHEESE", IngredientName.CHEESE.name());
	}
}