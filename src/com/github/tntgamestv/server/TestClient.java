package com.github.tntgamestv.server;

import com.github.tntgamestv.console.Out;
import com.github.tntgamestv.events.FutureListener;
import com.github.tntgamestv.server.serializable.packets.TimePacket;
import com.github.tntgamestv.server.threads.ClientServerManager;
import com.github.tntgamestv.server.threads.ClientThread;

public class TestClient {

	public static void main(String[] args) {
		ClientThread clientThread = new ClientThread("localhost", ClientServerManager.SERVER_PORT);

		FutureListener futureListener = new FutureListener() {

			@Override
			public void execute() {
				clientThread.sendPacket(new TimePacket(System.currentTimeMillis()));
				Out.clientThread("Packet was sent");
				clientThread.close();
			}
		};
		clientThread.setFutureListeners(futureListener);
		clientThread.init();
		clientThread.start();
	}
}