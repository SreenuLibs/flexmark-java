package com.vladsch.flexmark.html.renderer;

/**
 * Specifies the node part for which attributes can be provided
 */
public class AttributablePart {
    final public static AttributablePart NODE = new AttributablePart("NODE");
    final public static AttributablePart NODE_WITH_POSITION = new AttributablePart("NODE_WITH_POSITION");
    final public static AttributablePart LINK = new AttributablePart("LINK");
    final public static AttributablePart ID = new AttributablePart("ID");
    
    final private String myName;

    public AttributablePart(String name) {
        this.myName = name;
    }

    public String getName() {
        return myName;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
