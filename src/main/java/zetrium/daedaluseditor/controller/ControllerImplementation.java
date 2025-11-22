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
package zetrium.daedaluseditor.controller;

import zetrium.daedaluseditor.model.Model;
import zetrium.daedaluseditor.model.Project;

/**
 *
 * @author Tomáš Zídek
 */
public class ControllerImplementation implements Controller {

    private Model model;
    private MessageDisplayer messageDisplayer;

    public ControllerImplementation(Model model, MessageDisplayer messageDisplayer) {
        this.model = model;
        this.messageDisplayer = messageDisplayer;
    }

    @Override
    public void openProject(Project project) {
        model.getProjects().add(project);
        updateProject(project);
    }

    @Override
    public void updateProject(Project project) {

    }
    


    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    public MessageDisplayer getMessageDisplayer() {
        return messageDisplayer;
    }

    public void setMessageDisplayer(MessageDisplayer messageDisplayer) {
        this.messageDisplayer = messageDisplayer;
    }

    @Override
    public void sourceEdited(String source) {

    }

}
