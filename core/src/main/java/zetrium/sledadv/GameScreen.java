package zetrium.sledadv;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Comparator;
import java.util.Random;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class GameScreen implements Screen {

    float camY = 0;
    float camX = 0;
    SpriteBatch spriteBatch;
    Viewport view;

    Texture treeTexture;
    Sprite treeSprite;
    Texture sledTexture;
    Sprite sledSprite;
    Sled sled;
    Texture snowTexture;
    Sprite snowSprite;
    Array<Entity> entities = new Array<>();

    Random rng = new Random();

    World world;
    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();
    OrthographicCamera debugCam = new OrthographicCamera();

    @Override

    public void show() {
        entities.ordered = false;

        // box2d initialization
        world = new World(new Vector2(0, 6), true);
        debugCam.setToOrtho(false, 3f, 2f);


        sledTexture = new Texture("newsled.png");
        sledSprite = new Sprite(sledTexture);
        sledSprite.setSize(0.4f, 0.4f);
        sled = new Sled(0, 0, world, sledSprite);
        entities.add(sled);

        snowTexture = new Texture("snow.png");
        snowSprite = new Sprite(snowTexture);
        snowSprite.setSize(8, 1);

        treeTexture = new Texture("tree.png");
        treeSprite = new Sprite(treeTexture);
        treeSprite.setSize(2.25f, 3);

        //sledSprite.setOriginCenter();
        //setPositionByOrigin(sledSprite, 0f, -0.4f);
        view = new FitViewport(3, 2);
        spriteBatch = new SpriteBatch();

        for (int i = 0; i < 1000; i++) {
            entities.add(new Tree(
                    rng.nextFloat(-4, 4),
                    rng.nextFloat(1, 500),
                    world,
                    treeSprite));
        }
         for (int i = 0; i < 0; i++) {
                    entities.add(new Sled(rng.nextFloat(-4, 4), rng.nextFloat(-10, 100), world, sledSprite));

        }

    }

    @Override
    public void render(float delta) {
        input(delta);
        logic(delta);
        draw(delta);
    }

    private void input(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            sled.getBody().applyForceToCenter(new Vector2(-1, 0), true);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            sled.getBody().applyForceToCenter(new Vector2(1, 0), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            sled.getBody().applyForceToCenter(new Vector2(0, .0F), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            sled.getBody().applyForceToCenter(new Vector2(0, -1.5f), true);
        }
    }

    private void logic(float delta) {
        world.step(delta, 6, 2);

        camY = sled.getDisplayPosition().y - 1;
        camX = (sled.getDisplayPosition().x + camX * 10) / 11f;
    }

    public static void setPositionByOrigin(Sprite s, float x, float y) {
        s.setPosition(x - s.getOriginX(), y - s.getOriginY());
    }
    //float snowOffset = 0;
    float cameraOffset = 0.3f;

    private void draw(float delta) {
        // snowOffset = (snowOffset + delta / 1f);
        ScreenUtils.clear(Color.BLACK);
        view.apply();
        spriteBatch.setProjectionMatrix(view.getCamera().combined);
        spriteBatch.begin();
        entities.sort(Entity.FROM_FURTHEST);
        int i = 0;
        for (float distance = 20 - camY % 0.3f; distance >= 0; distance -= 0.3f) {
            //float i = 20 - distance;
            // snowSprite.setSize(8, 1f);
            snowSprite.setOriginCenter();
            setPositionByOrigin(snowSprite, -camX, -1);
            snowSprite.setOrigin(-snowSprite.getX(), -snowSprite.getY());
            snowSprite.setScale(1 / distance);
            snowSprite.setY(snowSprite.getY() - distance / 100 + cameraOffset);
            snowSprite.draw(spriteBatch);
            while (i < entities.size && entities.get(i).getDisplayPosition().y - camY - 1 > distance) {
                drawEntity(entities.get(i));
                i++;
            }
        }
        while (i < entities.size && drawEntity(entities.get(i++))) {
        }

        /*for (var entity : entities) {
            drawEntity(entity);
        }*/
        //sled.draw(spriteBatch);
        spriteBatch.end();
        debugCam.position.x = sled.getBody().getPosition().x;
        debugCam.position.y = sled.getBody().getPosition().y+0.25f;
        //debugCam.zoom = 0.01f;
        //
        // System.out.println(sled.getBody().getPosition());
        //System.out.println(debugCam.combined);

        debugCam.update();

        debugRenderer.render(
                world, debugCam.combined);
    }

    private boolean drawEntity(Entity entity) {
        var displayPos = entity.getDisplayPosition();
        float x = displayPos.x - camX;
        float y = displayPos.y - camY;
        if (y < 0) {
            return false;
        }

        var sprite = entity.getSprite();
        sprite.setOriginCenter();
        setPositionByOrigin(sprite, x, -.5f);
        sprite.setOrigin(-sprite.getX(), -sprite.getY());
        sprite.setScale(1 / y);
        sprite.setY(sprite.getY() - y / 100 + cameraOffset);
        sprite.draw(spriteBatch);
        return true;
    }

    private Body spawnBody(World world, BodyDef bodyDef, FixtureDef... fixtureDefs) {
        Body body = world.createBody(bodyDef);
        for (FixtureDef fixtureDef : fixtureDefs) {
            /*Fixture ballFixture =*/ body.createFixture(fixtureDef);
        }
        return body;

    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if (width <= 0 || height <= 0) {
            return;
        }
        view.update(width, height);
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }

    public static <T> void insertSorted(Array<T> array, T value, Comparator<T> comparator) {
        int i = 0;
        for (; i < array.size; i++) {
            if (comparator.compare(value, array.get(i)) < 0) {
                break;
            }
        }
        array.insert(i, value);
    }

}
