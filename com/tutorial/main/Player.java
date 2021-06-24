package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	float InvincibilityTime = 0;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x , (int)y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
	
		x = Game.clamp(x, 0,  Game.WIDTH - 30);		
		y = Game.clamp(y, 0,  Game.HEIGHT - 50);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 20, 20, 0.09f, handler));
		
		collision();
		InvincibilityTime--;

	}
	
	private void collision()
	{
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss)
			{
				if (getBounds().intersects(tempObject.getBounds()))
				{
					if (InvincibilityTime < 0) 
					{
						HUD.HEALTH -= 20;
						InvincibilityTime=100;
					}
				}
			} 
		}
	}
	/*if (HUD.HEALTH==0)
	{
		System.exit(1); //Exits the game if you die
	}*/
	
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 20, 20);
	}
}