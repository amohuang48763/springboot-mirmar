package com.demo.miramar.result;


public class ResultContants {
	
	public enum RESULT_CODE{
		LOGIN_ACCOUNT_IS_EXIST(101),
		EMPLOYEE_IS_EXIST(102),
		LOGIN_FAIL(104),
		SUCCESS(200),
		FAIL(199);
		
		private final int code;

		private RESULT_CODE(int _code) {
            code = _code;
        }
		
		/**
         * @return
         */
        public int getCode() {
            return code;
        }
        
        /**
         * @return
         */
        public String getCodeToString() {
            return String.valueOf(code);
        }

	}


}
