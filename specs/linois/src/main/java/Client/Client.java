package Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    final static int BUFFER_SIZE = 1024;
    final static Logger LOG = Logger.getLogger(Client.class.getName());

    Socket clientSocket;

    public void sendOp(String ipServer, int portServer, String operation)
    {
        Socket clientSocket = null;
        OutputStream out = null;
        InputStream in = null;

        try
        {
            clientSocket = new Socket(InetAddress.getByName(ipServer), portServer);

            out = clientSocket.getOutputStream();
            out.write(operation.getBytes());

            in = clientSocket.getInputStream();

            ByteArrayOutputStream serverResponse = new ByteArrayOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int newBytes;

            while ((newBytes = in.read(buffer)) != -1) {
                serverResponse.write(buffer, 0, newBytes);
            }

            LOG.log(Level.INFO, "Server sent : ");
            
            LOG.log(Level.INFO, responseBuffer.toString());
        }
        catch (IOException e)
        {
            LOG.log(Level.SEVERE, null, e);
        }
    }
}
