Here's an enhanced version of your README:

---

# Chat-Room 🗨️

A beginner-friendly introduction to networking in Java. Chat-Room is a rudimentary chat application that can be run locally across devices on the same network. Under the hood, it leverages Java sockets for communication.

## Features

- **Local Networking**: Connect multiple clients on the same local network.
- **User Identification**: Upon first launch, clients choose a username which is used throughout the session.
- **Unique ID Assignment**: Each client receives a unique ID from the server for identification.
- **Message Buffering**: Ensures the last 10 messages are always displayed, offering context in the conversation.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed.

### Compilation & Execution

1. Compile all source files:
   ```bash
   javac *.java
   ```

2. Start the server:
   ```bash
   java Server
   ```

3. Launch the client application (either on the same PC or a different one on the same network):
   ```bash
   java Client
   ```

## How It Works

1. **Client Initialization**: 
   - When you launch the client for the first time, you'll be prompted to choose a username.
   - This username, alongside a unique ID assigned by the server, represents the client in the chat.

2. **Message Broadcasting**:
   - When you send a message, it's dispatched to the server.
   - The server then broadcasts this message to all connected clients.

3. **Message Buffering**:
   - The application maintains a buffer of the last 10 messages. 
   - Every time you send a message, you'll also see the 10 most recent messages to keep the context of the conversation.

---

With this format, the README is structured, provides more context, and includes a touch of styling for a polished look.