
package Controles;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JOptionPane;


public class Comparacion {
    public Pattern pattern;
    public Matcher matcher;
    public boolean compare;
    public String comparar(String info,String model, String variable){
        do {
            pattern = Pattern.compile(model);
            matcher = pattern.matcher(info);
            if (matcher.matches()) {
                 compare =true;
                 System.out.println(info);
                 return info; 
                 
            } else {
                 
                 JOptionPane.showMessageDialog(null,"Error al ingresar los datos en : "+ variable+"recuerde agregar los datos segun el formato");
                 compare = false;
                return null;      
            }
        } while (compare == false);
    }
}
