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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import java.util.Comparator;

/**
 *
 * @author Ryzen
 */
public class Entity {

    private final Vector2 displayOffset;
    private Sprite sprite;
    private Body body;

    public Entity(Body body, Sprite sprite, Vector2 displayOffset) {
        this.body = body;
        this.sprite = sprite;
        this.displayOffset = displayOffset;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Vector2 getDisplayOffset() {
        return displayOffset;
    }

    public Vector2 getDisplayPosition() {
        return new Vector2(body.getPosition()).add(displayOffset);
    }

    public static final Comparator<Entity> FROM_NEAREST = (Entity o1, Entity o2) -> {
        return Float.compare(o1.getBody().getPosition().y, o2.getBody().getPosition().y);
    };
    public static final Comparator<Entity> FROM_FURTHEST = FROM_NEAREST.reversed();

}
