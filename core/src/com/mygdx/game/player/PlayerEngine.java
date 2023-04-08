package com.mygdx.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameScreen;
import com.mygdx.game.ingredient.IngredientTextures;
import com.mygdx.game.interact.InteractEngine;
import com.mygdx.game.player.PowerUps.PowerUpEngine;

import java.util.ArrayList;

/**
 * 
 * @author Thomas McCarthy
 * 
 * The PlayerEngine class creates and renders the games' three chefs. It also handles
 * chef switching and detects interaction input (although the handling of interactions
 * is then done by the InteractEngine)
 *
 */
public final class PlayerEngine {

	static SpriteBatch batch;

	static GameScreen gameScreen;

	static ArrayList<Player> chefs;
	static Player activeChef;
	static Rectangle[] interactableColliders;

	static Integer coins;

	static Label coinLabel;

	//==========================================================\\
	//                      INITIALISER                         \\
	//==========================================================\\

	public static void initialise(SpriteBatch gameBatch, GameScreen scrn)
	{
		batch = gameBatch;

		gameScreen = scrn;

		chefs = new ArrayList<>();
		chefs.add(new Player(0,  175,  350, "temp_chef_1.png"));
		chefs.add(new Player(1,  455,  350, "temp_chef_2.png"));

		activeChef = chefs.get(0);

		interactableColliders = new Rectangle[0];

		coins = 100;
		initialiseCoinLabel();



	}


	//==========================================================\\
	//                         UPDATE                           \\
	//==========================================================\\

	public static void update()
	{
		for(Player chef : chefs) {
			draw_coins();

			batch.draw(chef.getSprite(), chef.getXPos(), chef.getYPos());

			// Render the top three ingredients of the Chef's carry stack
			for(int i=2; i>-1; i--)
			{
				Sprite ingredientSprite = new Sprite(IngredientTextures.getTexture(chef.getIngredientStack().peekAtDepth(3 - i)));
				float size = 30f;

				ingredientSprite.setSize(size, size);
				ingredientSprite.setCenter(ingredientSprite.getWidth() / 2f, ingredientSprite.getHeight() / 2f);

				ingredientSprite.setAlpha(1 - ((2 - i) * 0.45f));

				float xOffset = ((i - 1) * -35f) + (chef.getSprite().getWidth() / 2) - (size / 2f);
				ingredientSprite.setX(chef.getXPos() + xOffset);
				ingredientSprite.setY(chef.getYPos() + 70f);

				ingredientSprite.draw(batch);
			}
		}

		activeChef.handleMovement(interactableColliders);
		activeChef.handlePowerUps(gameScreen.masterTimer);
		
		// Chef Quick-Switch with 'Q'
		if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			activeChef = chefs.get((activeChef.getID() + 1) % chefs.size());
		}
		// Chef switch with numbers 1-3
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			activeChef = chefs.get(0);
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			activeChef = chefs.get(1);
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
			if (chefs.size() == 3){
				activeChef = chefs.get(2);
			}
		}

		// Check for interaction input
		if(Gdx.input.isKeyJustPressed(Input.Keys.E))
		{
			InteractEngine.interact();
		}

		PowerUpEngine.interact();

	}

	private static void draw_coins() {
		coinLabel.draw(batch, 1);
		coinLabel.setText(coins);
	}

	private static void initialiseCoinLabel(){
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		BitmapFont font = new BitmapFont();
		font.getData().setScale(1.5f);
		labelStyle.font = font;
		labelStyle.fontColor = Color.GOLD;

		coinLabel = new Label(coins.toString(), labelStyle);
		coinLabel.setPosition(570, 25);
		coinLabel.setAlignment(Align.left);
	}


	//==========================================================\\
	//                    GETTERS & SETTERS                     \\
	//==========================================================\\
	
	public static Player getActiveChef() { return activeChef; }

	public static void setColliders(Rectangle[] colliders) { interactableColliders = colliders; }


	public static void swapChef(String key){
		if(key=="Q") {
			activeChef = chefs.get((activeChef.getID() + 1) % chefs.size());
		}
		// Chef switch with numbers 1-3
		else if(key=="1") {
			activeChef = chefs.get(0);
		}
		else if(key=="2") {
			activeChef = chefs.get(1);
		}
		else if(key=="3") {
			activeChef = chefs.get(2);
		}
	}
	public static float getMasterTime() {
		return gameScreen.masterTimer;
	}

	public static ArrayList<Player> getAllChefs() {
		return chefs;
	}

	public static void gainCoins(int numCoins) {
		coins += numCoins;
	}

	public static void loseCoins(int numCoins){
		coins -= numCoins;
	}

	public static int getCoins() {
		return coins;
	}

	public static void addNewChef() {
		chefs.add(new Player(2, 167, 125, "temp_chef_3.png"));
	}

}