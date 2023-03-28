package com.mygdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.ingredient.IngredientName;
import com.mygdx.game.ingredient.IngredientTextures;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

@RunWith(GdxTestRunner.class)
public class IngredientTest {

	@Test
	public void testIngredientNames() {
		Assert.assertEquals("LETTUCE_CUT", IngredientName.LETTUCE_CUT.name());
		Assert.assertEquals("LETTUCE_UNCUT", IngredientName.LETTUCE_UNCUT.name());
		Assert.assertEquals("BUNS_UNTOASTED", IngredientName.BUNS_UNTOASTED.name());
		Assert.assertEquals("BUNS_TOASTED", IngredientName.BUNS_TOASTED.name());
		Assert.assertEquals("ONION_CUT", IngredientName.ONION_CUT.name());
		Assert.assertEquals("ONION_UNCUT", IngredientName.ONION_UNCUT.name());
		Assert.assertEquals("PATTY_COOKED", IngredientName.PATTY_COOKED.name());
		Assert.assertEquals("PATTY_RAW", IngredientName.PATTY_RAW.name());
		Assert.assertEquals("TOMATO_CUT", IngredientName.TOMATO_CUT.name());
		Assert.assertEquals("TOMATO_UNCUT", IngredientName.TOMATO_UNCUT.name());
		Assert.assertEquals("JACKET_POTATO_COOKED", IngredientName.JACKET_POTATO_COOKED.name());
		Assert.assertEquals("JACKET_POTATO_UNCOOKED", IngredientName.JACKET_POTATO_UNCOOKED.name());
		Assert.assertEquals("TUNA", IngredientName.TUNA.name());
		Assert.assertEquals("CHEESE", IngredientName.CHEESE.name());
		Assert.assertEquals("PIZZA_BASE", IngredientName.PIZZA_BASE.name());
		Assert.assertEquals("NULL_INGREDIENT", IngredientName.NULL_INGREDIENT.name());

	}

	@Test
	public void testAssembledFoodNames() {
		Assert.assertEquals("JACKET_POTATO_FINISHED", IngredientName.JACKET_POTATO_FINISHED.name());
		Assert.assertEquals("BURGER", IngredientName.BURGER.name());
		Assert.assertEquals("SALAD", IngredientName.SALAD.name());
		Assert.assertEquals("PIZZA", IngredientName.PIZZA.name());

	}

	/** DOC- couldn't test this. in-built gdl Texture class doesn't allow .equals()
	@Test
	public void testIngredientTextures() {
		HashMap actual = new HashMap<IngredientName, Texture>()
		{{
			put(IngredientName.NULL_INGREDIENT, new Texture("ingredient_null.png"));
			put(IngredientName.LETTUCE_UNCUT, new Texture("ingredient_lettuce_uncut.png"));
			put(IngredientName.LETTUCE_CUT, new Texture("ingredient_lettuce_cut.png"));
			put(IngredientName.TOMATO_UNCUT, new Texture("ingredient_tomato_uncut.png"));
			put(IngredientName.TOMATO_CUT, new Texture("ingredient_tomato_cut.png"));
			put(IngredientName.ONION_UNCUT, new Texture("ingredient_onion_uncut.png"));
			put(IngredientName.ONION_CUT, new Texture("ingredient_onion_cut.png"));
			put(IngredientName.PATTY_RAW, new Texture("ingredient_patty_raw.png"));
			put(IngredientName.PATTY_COOKED, new Texture("ingredient_patty_cooked.png"));
			put(IngredientName.BUNS_UNTOASTED, new Texture("ingredient_bun_untoasted.png"));
			put(IngredientName.BUNS_TOASTED, new Texture("ingredient_bun_toasted.png"));
			put(IngredientName.BURGER, new Texture("ingredient_burger.png"));
			put(IngredientName.SALAD, new Texture("ingredient_salad.png"));
		}};

		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.NULL_INGREDIENT),
				actual.get(IngredientName.NULL_INGREDIENT).);
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.LETTUCE_UNCUT),
				actual.get(IngredientName.LETTUCE_UNCUT));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.LETTUCE_CUT),
				actual.get(IngredientName.LETTUCE_CUT));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.ONION_CUT),
				actual.get(IngredientName.ONION_CUT));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.ONION_UNCUT),
				actual.get(IngredientName.ONION_UNCUT));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.TOMATO_CUT),
				actual.get(IngredientName.TOMATO_CUT));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.TOMATO_UNCUT),
				actual.get(IngredientName.TOMATO_UNCUT));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.PATTY_COOKED),
				actual.get(IngredientName.PATTY_COOKED));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.PATTY_RAW),
				actual.get(IngredientName.PATTY_RAW));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.BUNS_TOASTED),
				actual.get(IngredientName.BUNS_TOASTED));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.BUNS_UNTOASTED),
				actual.get(IngredientName.BUNS_UNTOASTED));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.SALAD),
				actual.get(IngredientName.SALAD));
		Assert.assertEquals(IngredientTextures.getTexture(IngredientName.BURGER),
				actual.get(IngredientName.BURGER));
	}
	**/

}