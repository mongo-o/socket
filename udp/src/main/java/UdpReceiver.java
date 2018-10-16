import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author AYL    2018/10/16 9:32
 */
public class UdpReceiver {
    public static void main(String[] args) throws IOException {
        System.out.println("udp receiver started.");

        //作为一个接收者，指定数据接受端口为2000
        DatagramSocket socket = new DatagramSocket(2000);
        //创建一个用于接收数据的packet
        //这里最多能接收到数据为512byte，超出部分会被直接丢弃
        final byte[] buf = new byte[512];
        DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
        //阻塞等待接收数据
        socket.receive(receivePacket);

        //获得数据发送方的ip地址
        String host = receivePacket.getAddress().getHostAddress();
        System.out.println("sender host:" + host);
        //获得数据发送方的端口
        int port = receivePacket.getPort();
        System.out.println("sender port:" + port);

        //获得接收到的数据的string
        //如下三个方法都可以拿到数据
        String data1 = new String(buf);
        System.out.println("received buf data:" + data1);

        String data2 = new String(receivePacket.getData());
        System.out.println("received buf data2:" + data2);

        String data3 = new String(receivePacket.getData(), 0 , receivePacket.getLength());
        System.out.println("received buf data3:" + data3);

        //回送数据
        byte[] responseBuf= "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr".getBytes();
        //发送出去的packet，需要在报文中指定目标ip和端口，而不是在socket中
        DatagramPacket responsePacket = new DatagramPacket(responseBuf, responseBuf.length,
                receivePacket.getAddress(), port);
        socket.send(responsePacket);

        //关闭socket
        socket.close();
    }
}
