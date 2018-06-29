package com.motherload.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.motherload.game.Motherload;


public class GameOver implements Screen{

    
    private Texture gameover, background, skull, esc;
    private Image go, bg, s, press;
    private Motherload game;
    private Stage stage;
    
    public GameOver(Motherload g){
        this.game = g;
        stage = new Stage();
        
        gameover = new Texture(Gdx.files.internal("gameover.png"));
        skull = new Texture(Gdx.files.internal("skull.png"));
        background = new Texture(Gdx.files.internal("gobackground.png"));
        esc = new Texture(Gdx.files.internal("pressesc.png"));
        
        go = new Image(gameover);
        bg = new Image(background);
        s = new Image(skull);
        press = new Image(esc);
        
        stage.addActor(bg);
        stage.addActor(go);
        stage.addActor(s);
        stage.addActor(press);
        
        blink(press);
        
        stage.addListener(new InputListener(){
            @Override
            public boolean keyDown (InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE){                    
                    game.setScreen(new TitleScreen(game));
                    return true;
                }
                return true;
            }
        });
    }
    
    private void blink(final Image image){
        
        Texture texture = new Texture(Gdx.files.internal("blank.png"));
        final Image image1 = new Image(texture);
        
        SequenceAction actions=Actions.sequence(Actions.run(new Runnable() {
                @Override
                public void run() {
                    image.setVisible(true);
                    image1.setVisible(false);
                }
            }),Actions.delay(.2f),Actions.run(new Runnable() {
                @Override
                public void run() {
                    image.setVisible(false);
                    image1.setVisible(true);

                }
            }),Actions.delay(.2f));

        Action myAction=Actions.forever(actions);  //or not forever
        stage.addAction(myAction);
    }
    
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
       
    }

    @Override
    public void resize(int i, int i1) {
        
    }

    @Override
    public void pause() {
       
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
}
