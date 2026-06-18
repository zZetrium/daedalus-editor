/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zetrium.daedaluseditor.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author xzidek
 */
public sealed interface ProjectNode permits ProjectNode.Folder, ProjectNode.File {

    String getName();

    void setName(String name);

    default void rename(String name) {
        setName(name);
    }

    Folder getParent();

    void setParent(Folder parent);

    default void move(Folder destination) {
        setParent(destination);
    }

    public List<ProjectNode> getChildren();

    default Optional<Folder> getParentOptional() {
        return Optional.ofNullable(getParent());
    }

    default boolean isRoot() {
        return getParent() == null;
    }

    public final class Folder implements ProjectNode {

        private String name;
        private List<ProjectNode> children;
        private Folder parent;

        public Folder(String name, ProjectNode... children) {
            this(name, List.of(children), null);
        }

        public Folder(String name, List<ProjectNode> children, Folder parent) {
            this.name = name;
            this.children = children;
            this.parent = parent;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }
        
        /**
         *  should probably be cached? maybe on caller side ig
         * @return
         */
        @Override
        public List<ProjectNode> getChildren() {
            return List.copyOf(children);
        }

        public void setChildren(List<ProjectNode> children) {
            this.children = new ArrayList<>(children);
        }
        
        public void add(ProjectNode node) {
            children.add(node);
        }
        
        public void remove(ProjectNode node) {
            children.remove(node);
        }

        @Override
        public Folder getParent() {
            return parent;
        }

        @Override
        public void setParent(Folder parent) {
            this.parent.remove(this);
            this.parent = parent;
            this.parent.add(this);
            
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + Objects.hashCode(this.name);
            hash = 29 * hash + Objects.hashCode(this.children);
            hash = 29 * hash + Objects.hashCode(this.parent);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Folder other = (Folder) obj;
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            if (!Objects.equals(this.children, other.children)) {
                return false;
            }
            return Objects.equals(this.parent, other.parent);
        }
        

    }

    public final class File implements ProjectNode {

        private String name;
        private Folder parent;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public Folder getParent() {
            return parent;
        }

        @Override
        public void setParent(Folder parent) {
            this.parent = parent;
        }

        @Override
        public List<ProjectNode> getChildren() {
            return Collections.emptyList();
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 89 * hash + Objects.hashCode(this.name);
            hash = 89 * hash + Objects.hashCode(this.parent);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final File other = (File) obj;
            if (!Objects.equals(this.name, other.name)) {
                return false;
            }
            return Objects.equals(this.parent, other.parent);
        }
        

    }
}
