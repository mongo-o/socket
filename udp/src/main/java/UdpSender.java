import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * @author AYL    2018/10/16 10:19
 */
public class UdpSender {
    public static void main(String[] args) throws IOException {
        System.out.println("upd sender started.");

        DatagramSocket sender = new DatagramSocket();
        final byte[] buf = "hello udp!".getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getLocalHost(), 2000);

        int remotePort = sender.getPort();
        //这里的打印结果是：remoteAddr:, remotePort:-1
        //所以用于发送的socket不需要指定端口和ip，而是需要在发生的报文中指定端口和ip
        System.out.println("remoteAddr:" + "" + ", remotePort:" + remotePort);
        //发送数据
        sender.send(packet);

        //接下来接收数据
        /**
         * 测试结果：
         * 接收到数据：rrrrrrrr
         * 说明：如果接收使用的buf长度小于udp包数据，将会被截取，超过接收buf容量的数据将会被直接丢失。
         */
        final byte[] receiveBuf = new byte[8];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuf, receiveBuf.length);
        sender.receive(receivePacket);
        System.out.println("接收到数据：" + new String(receivePacket.getData()));

    }
}
