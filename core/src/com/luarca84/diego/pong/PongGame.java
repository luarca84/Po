package com.luarca84.diego.pong;

import com.badlogic.gdx.Game;


public class PongGame extends Game {


	@Override
	public void create() {
		ShowMenuScreen();
	}

	public void ShowMenuScreen(){setScreen(new MenuScreen(this));}
	public void ShowPlayScreen(int level){setScreen(new PlayScreen(this,level));}
	public void ShowStatusScreen(boolean victory,int level){setScreen(new StatusScreen(this,victory,level));}


}
