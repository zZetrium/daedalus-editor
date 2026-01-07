
  
/*
    Copyright (c) 2025 Tomáš Zídek

    Permission is hereby granted, free of charge, to any person
    obtaining a copy of this software and associated documentation
    files (the "Software"), to deal in the Software without
    restriction, including without limitation the rights to use,
    copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the
    Software is furnished to do so, subject to the following
    conditions:

    The above copyright notice and this permission notice shall be
    included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
    EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
    OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
    NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
    WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
    FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
    OTHER DEALINGS IN THE SOFTWARE.*/
package zetrium.sledadv;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author
 */
public class Tree extends Entity {

    public static final Vector2 DISPLAY_OFFSET = new Vector2(0.1f, 0.1f);


    private static Body createBody(float x, float y, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.fixedRotation = true;
        bodyDef.position.x = x;
        bodyDef.position.y = y;
        

        var shape = new CircleShape();
        shape.setRadius(.2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 5f;
        fixtureDef.shape = shape;
        fixtureDef.restitution = .0f;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setLinearDamping(.8f);

        shape.dispose();
        return body;
    }

    public Tree(float x, float y, World world, Sprite sprite) {
        super(createBody(x, y, world), sprite, DISPLAY_OFFSET);
    }

}

   
    

