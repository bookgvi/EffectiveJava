package RPN.syntactic;

public interface VisitableStmt {
    <R, A> R accept(Stmt.GenericVisitor<R, A> visitor, A args);
}
