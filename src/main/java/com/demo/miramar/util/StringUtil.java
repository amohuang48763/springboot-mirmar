package com.demo.miramar.util;

public class StringUtil {
	
    /**
    *
    * @param strings
    * @return
    */
   public static boolean checkString(String... strings) {
       if ((strings != null) && (strings.length > 0)) {
           boolean returnValue = Boolean.TRUE;
           for (int loop = 0; (returnValue == Boolean.TRUE) && (loop < strings.length); loop++) {
               returnValue = (strings[loop] != null) && (strings[loop].trim().length() > 0) ? Boolean.TRUE : Boolean.FALSE;
           }
           return returnValue;
       } else {
           return Boolean.FALSE;
       }
   }

}
