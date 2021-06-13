package fatima.mastour.venteService.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DeclageLettre {
    private String decalage(String value){
         String lettreNum="00";
        if (value == null) {
            return lettreNum;
        }
         switch (value.toLowerCase()){
             case "a":lettreNum="01";
                break;
             case "b":lettreNum="02";
                 break;
             case "c":lettreNum="03";
                 break;
             case "d":lettreNum="04";
                 break;
             case "e":lettreNum="05";
                 break;
             case "f":lettreNum="06";
                 break;
             case "g":lettreNum="07";
                 break;
             case "h":lettreNum="08";
                 break;
             case "i":lettreNum="09";
                 break;
             case "j":lettreNum="10";
                 break;
             case "k":lettreNum="11";
                 break;
             case "l":lettreNum="12";
                 break;
             case "m":lettreNum="13";
                 break;
             case "n":lettreNum="14";
                 break;
             case "o":lettreNum="15";
                 break;
                 case "p":lettreNum="16";
                 break;
             case "q":lettreNum="17";
                 break;
             case "r":lettreNum="18";
                 break;
             case "s":lettreNum="19";
                 break;
             case "t":lettreNum="20";
                 break;
             case "u":lettreNum="21";
                 break;
             case "v":lettreNum="22";
                 break;
             case "w":lettreNum="23";
                 break;
             case "x":lettreNum="24";
                 break;
             case "y":lettreNum="25";
                 break;
             case "z":lettreNum="23";
                 break;
             default:
                 lettreNum = "00";
                 break;
         }

        return lettreNum;
    }

    public String codeBarre(String nom,String prenom){
        String code_barre="BD";
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        code_barre+= decalage(nom.substring(0,1));
        code_barre+=calendar.get(Calendar.MILLISECOND)+calendar.get(Calendar.MINUTE)+calendar.get(Calendar.HOUR_OF_DAY)+calendar.get(Calendar.SECOND)+calendar.get(Calendar.DAY_OF_WEEK)+calendar.get(Calendar.DAY_OF_MONTH);
        code_barre+= decalage(prenom.substring(0,1));
        code_barre+="00000";
        code_barre=code_barre.substring(0,10);
        //System.out.println(decalage(prenom.substring(0,1)));
        return code_barre;
    }

}
