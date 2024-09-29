package Parser.node;

import Parser.dto.TextInfo;

public class ClassNode {
    private String modifier; // public, private, package private
    private boolean isAbstract;
    private boolean isFinal;
    private String[] extendArr;
    private String[] implementationArr;
    private String name;
    private boolean isNested;
    private boolean isNestedStatic;
    private TextInfo clazz;
}
