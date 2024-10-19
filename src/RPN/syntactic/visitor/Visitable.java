package RPN.syntactic.visitor;

public interface Visitable {
    <A> void accept(VoidVisitor<A> visitor, A args);

    <R, A> R accept(GenericVisitor<R, A> visitor, A args);
}
