package com.melody.system.api;

/**
 * 读取系统参数服务接口
 */
public interface SysConfigService {

  public final static String ID = "sysConfigService";

  /**
   * 读取字典配置
   * @param sysType
   * @return
   */
  public void refreshSysParameter(String sysType);

  /**
   * 预留方法
   * @param className
   * @param itemName
   * @return
     */
  public String getSysParameter(String className, String itemName);

  /**
   * 建议使用方法
   * @param itemName
   * @return
     */
  public String getSysParameter(String itemName);

}
