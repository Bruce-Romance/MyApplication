package eventbus;

/**
 * EventBus事件常量池
 */
public class EventBusCode {

    /*请求失败*/
    public static final int SongCiFail = 0x10000;
    /*请求成功*/
    public static final int SongCiSuccess = 0x20000;
    /*开始请求*/
    public static final int SongCiStart = 0x30000;
    /*开始下载*/
    public static final int DataBaseStartDown = 0x40000;
    /*下载成功*/
    public static final int DataBaseDownSuccess = 0x50000;
    /*下载失败*/
    public static final int DataBaseDownFail = 0x60000;


}
