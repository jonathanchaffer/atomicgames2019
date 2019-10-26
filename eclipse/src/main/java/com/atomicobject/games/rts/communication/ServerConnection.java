package com.atomicobject.games.rts.communication;

import com.atomicobject.games.rts.updates.GameUpdate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Closeable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerConnection {
    int port;
    MessageSerializer messageSerializer;
    BufferedReader input;
    OutputStreamWriter output;
    ServerSocket serverSocket;

    public ServerConnection(int port, MessageSerializer messageSerializer) {
        this.port = port;
        this.messageSerializer = messageSerializer;
    }

    public void acceptConnection() throws IOException {
        serverSocket = new ServerSocket(port);

        System.out.println("Listening for server connection on port: " + port);
        Socket socket = serverSocket.accept();

        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new OutputStreamWriter(socket.getOutputStream());
    }

    public void close() throws IOException {
        closeQuietly(input);
        closeQuietly(output);
        closeQuietly(serverSocket);
    }

    private void closeQuietly(Closeable stream) {
        try {
            stream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public GameUpdate readUpdate() throws IOException {
        String json = input.readLine();
        System.out.println("Read from server: " + json);
        if (json == null) return null;
        return messageSerializer.parseUpdate(json);
    }

    public void sendCommands(List<AICommand> commandsToSend) throws IOException {
        System.out.println("Sending commands list with length: " + commandsToSend.size());
        var message = new AICommandsMessage(commandsToSend);
        var serialized = messageSerializer.serializeAICommandsMessage(message);
        if (commandsToSend.size() > 0)
        {
            System.out.println("Sending commands to server: " + serialized);
        }
        output.write(serialized);
        output.flush();
    }
}
