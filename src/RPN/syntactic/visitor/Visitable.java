package RPN.syntactic.visitor;

public interface Visitable {
    <A> void accept(VoidVisitor<A> visitor, A args);
}
