package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a node in an expression tree.
 * Each node has a value, a type, and a list of child nodes.
 */
public class Node {

    /**
     * The value of the node. This could be a number, a variable, an operator, or a function name.
     */
    private String value;

    /**
     * The type of the node. This could be a NUMBER, a VARIABLE_OR_FUNCTION, or an OPERATOR.
     */
    private NodeType type;

    /**
     * The list of child nodes. For example, if this node is an operator, its children would be the operands.
     */
    private List<Node> children;

    /**
     * Constructs a new node with the given value and type.
     * The list of children is initially empty.
     *
     * @param value The value of the node.
     * @param type The type of the node.
     */
    public Node(String value, NodeType type) {
        this.value = value;
        this.type = type;
        this.children = new ArrayList<>();
    }

    /**
     * Returns the value of the node.
     *
     * @return The value of the node.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the type of the node.
     *
     * @return The type of the node.
     */
    public NodeType getType() {
        return type;
    }

    /**
     * Returns the list of child nodes.
     *
     * @return The list of child nodes.
     */
    public List<Node> getChildren() {
        return children;
    }

    /**
     * Adds a child node to the list of children.
     *
     * @param child The child node to be added.
     */
    public void addChild(Node child) {
        children.add(child);
    }

    /**
     * Returns a string representation of the node.
     *
     * @return A string representation of the node.
     */
    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", type=" + type +
                ", children=" + children +
                '}';
    }
}
