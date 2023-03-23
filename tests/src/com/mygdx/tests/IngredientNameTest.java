package com.mygdx.tests;

import static org.junit.Assert.*;

import com.mygdx.game.ingredient.IngredientName;
import org.junit.Assert;
import org.junit.Test;

public class IngredientNameTest {

	@Test
	public void testLettuceCutName() {
		Assert.assertEquals("LETTUCE_CUT", IngredientName.LETTUCE_CUT.name());
	}

	@Test
	public void testOnionCutName() {
		Assert.assertEquals("ONION_CUT", IngredientName.ONION_CUT.name());
	}

	@Test
	public void testTomatoCutName() {
		Assert.assertEquals("TOMATO_CUT", IngredientName.TOMATO_CUT.name());
	}

	@Test
	public void testSaladName() {
		Assert.assertEquals("SALAD", IngredientName.SALAD.name());
	}

	@Test
	public void testBreadName() {
		Assert.assertEquals("BREAD", IngredientName.TOMATO_CUT.name());
	}

	@Test
	public void testCheeseName() {
		Assert.assertEquals("CHEESE", IngredientName.CHEESE.name());
	}
}