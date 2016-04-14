

package br.cefetmg.inf.expression;

/**
 *
 * @author Felipe Rabelo
 */
public class ExpressionBuilder {
    private String expression;
    
    public ExpressionBuilder (String expression) {this.expression = expression;}
    
    public String run (String expression) {
        while (expression != null) {
            if (expression.contains("(")) {
                int i = expression.lastIndexOf("(");
                int j = expression.indexOf(")", i);
                String subExpression = expression.substring(i, j);
                
                return run(subExpression);
                }
            if (expression.contains("*")) {
                int i = expression.indexOf("*");
                
            }
            if (expression.contains("/")) {

            }
            if (expression.contains("-")) {

            }
            if (expression.contains("+")) {

            }
        }
        throw new RuntimeException("operador desconhecido");
    }
    
}

