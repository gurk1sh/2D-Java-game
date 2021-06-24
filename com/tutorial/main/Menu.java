package com.tutorial.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.tutorial.main.Game.STATE;

public class Menu extends MouseAdapter {
	
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx, my, Game.WIDTH/2-100, Game.HEIGHT/2-150, 200, 75) && game.gameState == STATE.Menu ) //PLAY BUTTON
		{
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(0, 0, ID.BasicEnemy, handler));
		}
		
		if(game.gameState == STATE.Menu && game.gameState != STATE.Game)
		{
			if(mouseOver(mx, my, Game.WIDTH/2-100, Game.HEIGHT/2+50, 200, 75)) //QUIT BUTTON
			{
				System.exit(1);
			}
		}
		if(game.gameState == STATE.Menu && game.gameState != STATE.Game)
		{
			if(mouseOver(mx, my, Game.WIDTH/2-100, Game.HEIGHT/2-50, 200, 75)) //HELP BUTTON
			{
				game.gameState = STATE.Help;
			}
		}
		if(game.gameState == STATE.Help && game.gameState != STATE.Game)
		{
			if(mouseOver(mx, my, Game.WIDTH/2-100, Game.HEIGHT/2+50, 200, 75))
			{
				game.gameState = STATE.Menu;
				return;
			}
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
		{
			if(my > y && my < y + height)
			{
				return true;
			} else return false;			
		} else return false;			
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{	
		if(game.gameState == STATE.Menu)
		{
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", Game.WIDTH/2-50, Game.HEIGHT/8);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawRect(Game.WIDTH/2-100, Game.HEIGHT/2-150, 200, 75);
		g.drawString("Play", Game.WIDTH/2-50, Game.HEIGHT/2-100);
		
		g.setFont(fnt2);
		g.drawRect(Game.WIDTH/2-100, Game.HEIGHT/2-50, 200, 75);
		g.drawString("Help", Game.WIDTH/2-50, Game.HEIGHT/2);
	
		
		g.setFont(fnt2);
		g.drawRect(Game.WIDTH/2-100, Game.HEIGHT/2+50, 200, 75);
		g.drawString("Quit", Game.WIDTH/2-50, Game.HEIGHT/2+100);
		}
		else if(game.gameState == STATE.Help)
		{
			Font fnt2 = new Font("arial", 1, 30);
			int gameHeight = Game.HEIGHT;
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH/2-50, gameHeight/8);
			
			g.setFont(fnt2);
			g.drawString("USE WASD keys to move",  Game.WIDTH/4,  gameHeight/2-125);
			g.drawString("Dodge the enemies through ",  Game.WIDTH/4,  gameHeight/2-75);
			g.drawString("the different levels",  Game.WIDTH/4,  gameHeight/2-25);
			
			
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2-100, gameHeight/2+50, 200, 75);
			g.drawString("Back", Game.WIDTH/2-50, gameHeight/2+100);
		}
	}
}
