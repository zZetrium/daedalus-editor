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

/**
 *
 * @author Tomáš Zídek
 */
public class JsonParser {

    public static void main(String[] args) throws LexingException {
        var lexer = new JsonLexer();
        for (int i = 0; i < 100; i++) {
            System.out.println(lexer.lex("{ \"type\": \"minecraft:entity\", \"pools\": [ { \"bonus_rolls\": 0.0, \"entries\": [ { \"type\": \"minecraft:item\", \"functions\": [ { \"add\": false, \"count\": { \"type\": \"minecraft:uniform\", \"max\": 2.0, \"min\": 0.0 }, \"function\": \"minecraft:set_count\" }, { \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"enchantment\": \"minecraft:looting\", \"function\": \"minecraft:enchanted_count_increase\" } ], \"name\": \"minecraft:arrow\" } ], \"rolls\": 1.0 }, { \"bonus_rolls\": 0.0, \"entries\": [ { \"type\": \"minecraft:item\", \"functions\": [ { \"add\": false, \"count\": { \"type\": \"minecraft:uniform\", \"max\": 2.0, \"min\": 0.0 }, \"function\": \"minecraft:set_count\" }, { \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"enchantment\": \"minecraft:looting\", \"function\": \"minecraft:enchanted_count_increase\" } ], \"name\": \"minecraft:bone\" } ], \"rolls\": 1.0 }, { \"bonus_rolls\": 0.0, \"conditions\": [ { \"condition\": \"minecraft:killed_by_player\" } ], \"entries\": [ { \"type\": \"minecraft:item\", \"functions\": [ { \"add\": false, \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"function\": \"minecraft:set_count\" }, { \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"enchantment\": \"minecraft:looting\", \"function\": \"minecraft:enchanted_count_increase\", \"limit\": 1 }, { \"function\": \"minecraft:set_potion\", \"id\": \"minecraft:poison\" } ], \"name\": \"minecraft:tipped_arrow\" } ], \"rolls\": 1.0 } ], \"random_sequence\": \"minecraft:entities/bogged\" }{ \"type\": \"minecraft:entity\", \"pools\": [ { \"bonus_rolls\": 0.0, \"entries\": [ { \"type\": \"minecraft:item\", \"functions\": [ { \"add\": false, \"count\": { \"type\": \"minecraft:uniform\", \"max\": 2.0, \"min\": 0.0 }, \"function\": \"minecraft:set_count\" }, { \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"enchantment\": \"minecraft:looting\", \"function\": \"minecraft:enchanted_count_increase\" } ], \"name\": \"minecraft:arrow\" } ], \"rolls\": 1.0 }, { \"bonus_rolls\": 0.0, \"entries\": [ { \"type\": \"minecraft:item\", \"functions\": [ { \"add\": false, \"count\": { \"type\": \"minecraft:uniform\", \"max\": 2.0, \"min\": 0.0 }, \"function\": \"minecraft:set_count\" }, { \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"enchantment\": \"minecraft:looting\", \"function\": \"minecraft:enchanted_count_increase\" } ], \"name\": \"minecraft:bone\" } ], \"rolls\": 1.0 }, { \"bonus_rolls\": 0.0, \"conditions\": [ { \"condition\": \"minecraft:killed_by_player\" } ], \"entries\": [ { \"type\": \"minecraft:item\", \"functions\": [ { \"add\": false, \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"function\": \"minecraft:set_count\" }, { \"count\": { \"type\": \"minecraft:uniform\", \"max\": 1.0, \"min\": 0.0 }, \"enchantment\": \"minecraft:looting\", \"function\": \"minecraft:enchanted_count_increase\", \"limit\": 1 }, { \"function\": \"minecraft:set_potion\", \"id\": \"minecraft:poison\" } ], \"name\": \"minecraft:tipped_arrow\" } ], \"rolls\": 1.0 } ], \"random_sequence\": \"minecraft:entities/bogged\" }").toString());
        }
    }

    //public 
}
