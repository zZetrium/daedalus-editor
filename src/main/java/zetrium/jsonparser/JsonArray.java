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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

/**
 *
 * @author Ryzen
 */
public class JsonArray extends JsonNode implements List<JsonNode> {
    List<JsonNode> children;

    public JsonArray() {
        this.children = new ArrayList<>();
    }
    
    public JsonArray(List children) {
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
    public boolean contains(Object o) {
        return children.contains(o);
    }

    @Override
    public Iterator<JsonNode> iterator() {
        return children.iterator();
    }

    @Override
    public Object[] toArray() {
        return children.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return children.toArray(a);
    }

    @Override
    public boolean add(JsonNode e) {
        return children.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return children.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return children.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends JsonNode> c) {
        return children.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends JsonNode> c) {
        return children.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return children.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return children.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<JsonNode> operator) {
        children.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super JsonNode> c) {
        children.sort(c);
    }

    @Override
    public void clear() {
        children.clear();
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
    public JsonNode get(int index) {
        return children.get(index);
    }

    @Override
    public JsonNode set(int index, JsonNode element) {
        return children.set(index, element);
    }

    @Override
    public void add(int index, JsonNode element) {
        children.add(index, element);
    }

    @Override
    public JsonNode remove(int index) {
        return children.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return children.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return children.lastIndexOf(o);
    }

    @Override
    public ListIterator<JsonNode> listIterator() {
        return children.listIterator();
    }

    @Override
    public ListIterator<JsonNode> listIterator(int index) {
        return children.listIterator(index);
    }

    @Override
    public List<JsonNode> subList(int fromIndex, int toIndex) {
        return children.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<JsonNode> spliterator() {
        return children.spliterator();
    }

    @Override
    public void addFirst(JsonNode e) {
        children.addFirst(e);
    }

    @Override
    public void addLast(JsonNode e) {
        children.addLast(e);
    }

    @Override
    public JsonNode getFirst() {
        return children.getFirst();
    }

    @Override
    public JsonNode getLast() {
        return children.getLast();
    }

    @Override
    public JsonNode removeFirst() {
        return children.removeFirst();
    }

    @Override
    public JsonNode removeLast() {
        return children.removeLast();
    }

    @Override
    public List<JsonNode> reversed() {
        return children.reversed();
    }
    
}
