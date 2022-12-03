import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.*;
import java.util.*;

public class CallGraphListener extends Java8BaseListener {
  String packageString;
  String lastClassName;
  String lastMethodName;
  static MyDataStructure dataStructure = new MyDataStructure();
  int nodeCounter = 0;

  public static void main(String[] args) throws Exception {
    ANTLRInputStream input = new ANTLRInputStream(System.in);
    Java8Lexer lexer = new Java8Lexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    Java8Parser parser = new Java8Parser(tokens);
    ParseTree tree = parser.compilationUnit();
    ParseTreeWalker walker = new ParseTreeWalker();
    CallGraphListener listener = new CallGraphListener();
    // This is where we trigger the walk of the tree using our listener.
    walker.walk(listener, tree);

    StringBuilder buf = new StringBuilder();
    buf.append("digraph G {\n");
    dataStructure.createDotNotation(buf);
    buf.append("}");
    System.out.println(buf.toString());
  }

  @Override
  public void enterPackageDeclaration(Java8Parser.PackageDeclarationContext ctx) {
    super.enterPackageDeclaration(ctx);
    packageString = ctx.getChild(1).toString() + ctx.getChild(2).toString() + ctx.getChild(3).toString();
  }

  @Override
  public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
    super.enterNormalClassDeclaration(ctx);
    String text = ctx.getText();
    String className = text.substring(text.indexOf("class") + 5, text.indexOf("{"));
    lastClassName = className;
  }

  @Override
  public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
    super.enterMethodDeclarator(ctx);
    lastMethodName = ctx.getText().substring(0, ctx.getText().length() - 2);
    String str = packageString + "/" + lastClassName + "/" + lastMethodName;
    if (!dataStructure.contains(str)) {
      dataStructure.addNode(packageString, lastClassName, lastMethodName, true);
    } else {
      dataStructure.changeNodeColor(str, true);
    }
  }

  @Override
  public void enterA(Java8Parser.AContext ctx) {
    super.enterA(ctx);
    String text = ctx.getText();
    String methodName = text.substring(0, text.indexOf("()"));
    String str = packageString + "/" + lastClassName + "/" + methodName;

    // Method implement edilmediyse. Node olarak graph'a eklenecek ve rengi beyaz
    // kalacaktir.
    if (!dataStructure.contains(str)) {
      dataStructure.addNode(packageString, lastClassName, methodName, false);
    }
    // Method implement edildiyse. Node'un rengi yesil yapilacaktir.
    else {
      dataStructure.changeNodeColor(str, true);
    }

    // Edge ekleme
    String last = packageString + "/" + lastClassName + "/" + lastMethodName;
    dataStructure.addEdge(last, str);
  }

  @Override
  public void enterB(Java8Parser.BContext ctx) {
    super.enterB(ctx);
    String text = ctx.getText();
    String methodName = text.substring(text.indexOf(".") + 1, text.indexOf("()"));
    String className = text.substring(0, text.indexOf("."));
    String str = packageString + "/" + className + "/" + methodName;

    // Method implement edilmediyse. Node olarak graph'a eklenecek ve rengi beyaz
    // kalacaktir.
    if (!dataStructure.contains(str)) {
      dataStructure.addNode(packageString, className, methodName, false);
    }
    // Edge ekleme
    String last = packageString + "/" + lastClassName + "/" + lastMethodName;
    dataStructure.addEdge(last, str);
  }
}
