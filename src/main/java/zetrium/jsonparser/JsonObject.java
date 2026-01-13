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
 * @author Ryzen
 */
public class JsonObject extends JsonNode implements Map<String,JsonNode>{
    Map<String,JsonNode> children;

    public JsonObject() {
        this.children = new HashMap();
    }

    public JsonObject(Map<String, JsonNode> children) {
        this.children = children;
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
    public JsonNode get(Object key) {
        return children.get(key);
    }

    @Override
    public JsonNode put(String key, JsonNode value) {
        return children.put(key, value);
    }

    @Override
    public JsonNode remove(Object key) {
        return children.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends JsonNode> m) {
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
    public Collection<JsonNode> values() {
        return children.values();
    }

    @Override
    public Set<Entry<String, JsonNode>> entrySet() {
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
    public JsonNode getOrDefault(Object key, JsonNode defaultValue) {
        return children.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer<? super String, ? super JsonNode> action) {
        children.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super JsonNode, ? extends JsonNode> function) {
        children.replaceAll(function);
    }

    @Override
    public JsonNode putIfAbsent(String key, JsonNode value) {
        return children.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return children.remove(key, value);
    }

    @Override
    public boolean replace(String key, JsonNode oldValue, JsonNode newValue) {
        return children.replace(key, oldValue, newValue);
    }

    @Override
    public JsonNode replace(String key, JsonNode value) {
        return children.replace(key, value);
    }

    @Override
    public JsonNode computeIfAbsent(String key, Function<? super String, ? extends JsonNode> mappingFunction) {
        return children.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public JsonNode computeIfPresent(String key, BiFunction<? super String, ? super JsonNode, ? extends JsonNode> remappingFunction) {
        return children.computeIfPresent(key, remappingFunction);
    }

    @Override
    public JsonNode compute(String key, BiFunction<? super String, ? super JsonNode, ? extends JsonNode> remappingFunction) {
        return children.compute(key, remappingFunction);
    }

    @Override
    public JsonNode merge(String key, JsonNode value, BiFunction<? super JsonNode, ? super JsonNode, ? extends JsonNode> remappingFunction) {
        return children.merge(key, value, remappingFunction);
    }

    
    
}
