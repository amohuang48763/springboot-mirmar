package com.demo.miramar.result;


public class ResultContants {
	
	public enum RESULT_CODE{
		LOGIN_ACCOUNT_IS_EXIST("101",101),
		EMPLOYEE_IS_EXIST("101",102),
		LOGIN_FAIL("104",104),
		SUCCESS("200",200),
		FAIL("199",199);
		
		private final int numberCode;
		private final String resultCode;

		private RESULT_CODE(String string, int i) {
			numberCode = i;
			resultCode = string;
        }
		
		/**
         * @return
         */
        public int getNumberCode() {
            return numberCode;
        }
        
        /**
         * @return
         */
        public String getResultCode() {
            return resultCode;
        }
        
        /**
         * @return
         */
        public String getCodeToString() {
            return String.valueOf(numberCode);
        }

	}


}
