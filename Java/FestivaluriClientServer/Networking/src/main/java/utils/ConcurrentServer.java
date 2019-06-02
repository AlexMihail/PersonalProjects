package utils;

import Utils.IServer;
import objectprotocol.ClientObjectWorker;

import java.net.Socket;

public class ConcurrentServer extends AbstractServer {

    private IServer server;
    public ConcurrentServer(int port, IServer server) {
        super(port);
        this.server=server;
        System.out.println("Concurrent server");
    }
    protected void processRequest(Socket client) {
        Thread tw=createWorker(client);
        tw.start();
    }
    protected Thread createWorker(Socket client) {
        ClientObjectWorker worker=new ClientObjectWorker(server, client);
        Thread tw=new Thread(worker);
        return tw;
    }
}