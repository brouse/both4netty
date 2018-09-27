import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * 测试服务端接受socket内容
 * Created by zhangheng Created on 2018/9/27
 */
public class SendHeart {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8000);
            while (true) {
                socket.getOutputStream().write((new Date() + " hello").getBytes());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
