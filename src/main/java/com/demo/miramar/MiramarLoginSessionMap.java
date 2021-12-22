package com.demo.miramar;

import java.text.SimpleDateFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import javax.websocket.Session;

import com.demo.miramar.util.StringUtil;

public class MiramarLoginSessionMap {

    private final ConcurrentHashMap<String, MiramarLoginSessionMapTO> concurrentHashMap = new ConcurrentHashMap();
//    private final ScheduledFuture scheduledFuture;
    private final static SimpleDateFormat YYYYMMDD_HHMMSSSSS = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss,SSS");

    private static class MiramarLoginSessionMapHolder {

        private static final MiramarLoginSessionMap INSTANCE = new MiramarLoginSessionMap();
    }
    
    /**
    *
    * @return
    */
   public static MiramarLoginSessionMap getInstance() {
       return MiramarLoginSessionMapHolder.INSTANCE;
   }
   
   private MiramarLoginSessionMap() {
       concurrentHashMap.clear();
//       scheduledFuture = TimerExecutors.getInstance().scheduleWithFixedDelay(runner, 5000L, 5 * 1000L);
   }
	
	
    /**
    *
    */
   public class MiramarLoginSessionMapTO {

       private String deviceId;
       private boolean hostingStatus;
       private String hostingConfId;
       private final String loginSession;
       private final Session sessionWebSocket=null;
       private long currentTimeMillis = System.currentTimeMillis();
       private final Object syncObject;

       private MiramarLoginSessionMapTO(String _loginSession) {
           deviceId = "";
           hostingStatus = false;
           hostingConfId = "";
           loginSession = _loginSession;
           syncObject = new Object();
       }

       /**
        *
        * @return
        */
       public Object getSyncObject() {
           return syncObject;
       }

       /**
        *
        * @return
        */
       public String getDeviceId() {
           return deviceId;
       }

       /**
        *
        * @return
        */
       public boolean isHostingStatus() {
           return hostingStatus;
       }

       /**
        *
        * @return
        */
       public String getHostingConfId() {
           return hostingConfId;
       }

       /**
        *
        * @return
        */
       public long getCurrentTimeMillis() {
           return currentTimeMillis;
       }

       /**
        *
        * @return
        */
       public String getLoginSession() {
           return loginSession;
       }

       /**
        *
        * @return
        */
       public Session getSessionWebSocket() {
           return sessionWebSocket;
       }

       /**
        *
        * @return
        */
       public boolean isTimeout() {
           return currentTimeMillis < (System.currentTimeMillis() - getTimeoutMS());
       }

       /**
        *
        * @return
        */
       @Override
       public String toString() {
           return "IManagerLoginSessionMapTO [ loginSession=" + loginSession + ", currentTimeMillis=" + YYYYMMDD_HHMMSSSSS.format(currentTimeMillis) + "]";
       }

       /**
        *
        * @param deviceId
        */
       public void setDeviceId(String deviceId) {
           this.deviceId = deviceId;
       }

       /**
        *
        * @param hostingStatus
        */
       public void setHostingStatus(boolean hostingStatus) {
           this.hostingStatus = hostingStatus;
       }

       /**
        *
        * @param hostingConfId
        */
       public void setHostingConfId(String hostingConfId) {
           this.hostingConfId = hostingConfId;
       }

       /**
        *
        * @return
        */
       public MiramarLoginSessionMapTO updateCurrentTimeMillis() {
           this.currentTimeMillis = System.currentTimeMillis();
           return this;
       }
   }
   
   /**
   *
   * @param userId
   * @param loginSession
   * @return
   */
  public MiramarLoginSessionMapTO addLoginSession(String userId, String loginSession) {
      if (StringUtil.checkString(loginSession) == Boolean.FALSE) {
          throw new RuntimeException("invalid login session");
      }

      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new RuntimeException("invalid user id");
      }
      return concurrentHashMap.put(userId, new MiramarLoginSessionMapTO(loginSession));
  }

  /**
   *
   * @param userId
   * @return
   */
  public boolean containsLoginSession(String userId) {
      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new RuntimeException("invalid userId");
      }
      return concurrentHashMap.containsKey(userId);
  }

  /**
   *
   * @param userId
   * @return
   */
  public boolean extensionLoginSession(String userId) {
      boolean returnValue = Boolean.FALSE;
      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new RuntimeException("invalid userId");
      }
      if (containsLoginSession(userId) == Boolean.TRUE) {
          final MiramarLoginSessionMapTO iManagerLoginSessionMapTO = concurrentHashMap.get(userId);
          if (iManagerLoginSessionMapTO != null) {
              iManagerLoginSessionMapTO.updateCurrentTimeMillis();
              returnValue = Boolean.TRUE;
          }
      }
      return returnValue;
  }

  public void removeLoginSession(String userId) {
      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new IllegalArgumentException("invalid loginSession");
      }
      if (containsLoginSession(userId) == Boolean.TRUE) {
          concurrentHashMap.remove(userId);
      }
  }

  private long getTimeoutMS() {
      /* 改為讀 starv.json 設定 */
      final long SECOND_TIMEOUT = 60000;
      return SECOND_TIMEOUT * 1000;
  }

  /**
   *
   * @param userId
   * @return
   */
  public MiramarLoginSessionMapTO getTO(String userId) {
      MiramarLoginSessionMapTO returnValue = null;
      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new RuntimeException("invalid userId");
      }
      if (containsLoginSession(userId) == Boolean.TRUE) {
          final MiramarLoginSessionMapTO iManagerLoginSessionMapTO = concurrentHashMap.get(userId);
          if (iManagerLoginSessionMapTO != null) {
              returnValue = iManagerLoginSessionMapTO;
          }
      }
      return returnValue;
  }

  /**
   *
   * @param userId
   * @return
   */
  public String getDeviceid(String userId) {
      String returnValue = null;
      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new RuntimeException("invalid userId");
      }
      if (containsLoginSession(userId) == Boolean.TRUE) {
          final MiramarLoginSessionMapTO iManagerLoginSessionMapTO = concurrentHashMap.get(userId);
          if (iManagerLoginSessionMapTO != null) {
              returnValue = iManagerLoginSessionMapTO.getDeviceId();
          }
      }
      return returnValue;
  }

  /**
   *
   * @param to
   * @return
   */
  public boolean isTimeout(MiramarLoginSessionMapTO to) {
      if (to == null) {
          throw new RuntimeException("invalid imanager login session map to");
      }
      return to.isTimeout();
  }

  /**
   *
   * @param userId
   * @return
   */
  public boolean isTimeout(String userId) {
      boolean returnValue = Boolean.FALSE;
      if (StringUtil.checkString(userId) == Boolean.FALSE) {
          throw new RuntimeException("invalid userId");
      }
      final MiramarLoginSessionMapTO to = getTO(userId);
      if (to != null) {
          returnValue = isTimeout(to);
      } else {
//          logger.debug("userId not found[{}]", userId);
      }
      return returnValue;
  }
   
   
	
}
