package network;

import java.net.Socket;

public interface ServerInterface {
	void service(Socket s);
}
