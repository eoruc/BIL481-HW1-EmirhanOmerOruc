public class Node {
  private String packageName;
  private String className;
  private String methodName;
  private String nodeString;
  private boolean isGreen;
  private static int counter = 0;
  
  public Node(String packageName, String className, String methodName, boolean isGreen) {
    this.packageName = packageName;
    this.className = className;
    this.methodName = methodName;
    this.nodeString = packageName + "/" + className + "/" + methodName;
    this.isGreen = isGreen;
  }

  public String getPackageName() {
    return packageName;
  }

  public String getClassName() {
    return className;
  }

  public String getMethodName() {
    return methodName;
  }

  public String getNodeString() {
    return nodeString;
  }
  
  public String getColor(){
    if (isGreen) {
      return "green";
    }
    return "white";
  }

  public void setGreen(boolean isGreen) {
    this.isGreen = isGreen;
  }

  
}
