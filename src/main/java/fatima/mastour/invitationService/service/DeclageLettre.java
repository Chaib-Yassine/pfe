package fatima.mastour.invitationService.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DeclageLettre {
    private String decalage(int value){
         String lettreNum="00";

         switch (value){
             case 1:lettreNum="01";
                break;
             case 2:lettreNum="52";
                 break;
             case 3:lettreNum="33";
                 break;
             case 4:lettreNum="64";
                 break;
             case 5:lettreNum="45";
                 break;
             case 6:lettreNum="56";
                 break;
             case 7:lettreNum="87";
                 break;
             case 8:lettreNum="18";
                 break;
             case 9:lettreNum="09";
                 break;
             case 10:lettreNum="13";
                 break;
             case 0:lettreNum="71";
                 break;
             default:
                 lettreNum = "00";
                 break;
         }

        return lettreNum;
    }

    public String codeBarre(){
        String code_barre="IN";
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        code_barre+= decalage((int)(Math.random()*10));
        code_barre+=calendar.get(Calendar.MILLISECOND)+calendar.get(Calendar.MINUTE)+calendar.get(Calendar.HOUR_OF_DAY)+calendar.get(Calendar.SECOND)+calendar.get(Calendar.DAY_OF_WEEK)+calendar.get(Calendar.DAY_OF_MONTH);
        code_barre+= (int)(Math.random()*10);
        code_barre+= decalage((int)(Math.random()*10));
        code_barre+="00000";
        code_barre=code_barre.substring(0,10);
        //System.out.println(decalage(prenom.substring(0,1)));
        return code_barre;
    }

}
