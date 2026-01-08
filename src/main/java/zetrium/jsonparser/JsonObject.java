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
package zetrium.jsonparser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author Tomáš Zídek
 */
public class JsonObject extends JsonElement implements Map<String, JsonElement> {

    Map<String, JsonElement> children;

    public JsonObject(Map<String, JsonElement> children, int startOffset, int endOffset, int precedingWs) {
        super(startOffset, endOffset, precedingWs);
        this.children = children;
    }

    

    public JsonObject(int startOffset, int endOffset, int precedingWs) {
        this(new HashMap<>(), startOffset, endOffset,  precedingWs);
    }

    @Override
    public int size() {
        return children.size();
    }

    @Override
    public boolean isEmpty() {
        return children.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return children.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return children.containsValue(value);
    }

    @Override
    public JsonElement get(Object key) {
        return children.get(key);
    }

    @Override
    public JsonElement put(String key, JsonElement value) {
        return children.put(key, value);
    }

    @Override
    public JsonElement remove(Object key) {
        return children.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends JsonElement> m) {
        children.putAll(m);
    }

    @Override
    public void clear() {
        children.clear();
    }

    @Override
    public Set<String> keySet() {
        return children.keySet();
    }

    @Override
    public Collection<JsonElement> values() {
        return children.values();
    }

    @Override
    public Set<Entry<String, JsonElement>> entrySet() {
        return children.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        return children.equals(o);
    }

    @Override
    public int hashCode() {
        return children.hashCode();
    }

    @Override
    public JsonElement getOrDefault(Object key, JsonElement defaultValue) {
        return children.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super JsonElement> action) {
        children.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super JsonElement, ? extends JsonElement> function) {
        children.replaceAll(function);
    }

    @Override
    public JsonElement putIfAbsent(String key, JsonElement value) {
        return children.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return children.remove(key, value);
    }

    @Override
    public boolean replace(String key, JsonElement oldValue, JsonElement newValue) {
        return children.replace(key, oldValue, newValue);
    }

    @Override
    public JsonElement replace(String key, JsonElement value) {
        return children.replace(key, value);
    }

    @Override
    public JsonElement computeIfAbsent(String key, Function<? super String, ? extends JsonElement> mappingFunction) {
        return children.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public JsonElement computeIfPresent(String key, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> remappingFunction) {
        return children.computeIfPresent(key, remappingFunction);
    }

    @Override
    public JsonElement compute(String key, BiFunction<? super String, ? super JsonElement, ? extends JsonElement> remappingFunction) {
        return children.compute(key, remappingFunction);
    }

    @Override
    public JsonElement merge(String key, JsonElement value, BiFunction<? super JsonElement, ? super JsonElement, ? extends JsonElement> remappingFunction) {
        return children.merge(key, value, remappingFunction);
    }

}
