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
package zetrium.daedaluseditor.model;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 *
 * @author xzidek
 */
public interface PathPredicate extends Predicate<Path> {

    public static class Regex implements PathPredicate {

        private String pattern;

        public Regex(String pattern) {
            this.pattern = pattern;
        }

        @Override
        public boolean test(Path t) {
            return t.toString().matches(pattern);
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

    }

    public static class Prefix implements PathPredicate {

        private String prefix;

        public Prefix(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public boolean test(Path t) {
            return t.toString().startsWith(prefix);
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

    }

    public static class Suffix implements PathPredicate {

        private String suffix;

        public Suffix(String prefix) {
            this.suffix = prefix;
        }

        @Override
        public boolean test(Path t) {
            return t.toString().startsWith(suffix);
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

    }

}
