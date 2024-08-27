
<p align="center">
  <br>
  <img src="https://github.com/user-attachments/assets/9d05eaf1-8875-4666-bca0-3883a2ad7abe" style="width:60%;">
</p>

### Basic Networking Concepts Overview

#### **1. OSI Model**
The OSI (Open Systems Interconnection) model is a conceptual framework used to understand and implement network protocols in seven layers. Each layer performs a specific function and interacts with the layers directly above and below it.

- **Layer 1: Physical Layer**
  - Deals with the physical connection between devices, including cables, switches, and electrical signals.
  
- **Layer 2: Data Link Layer**
  - **Function:** Handles the transfer of data between two devices on the same network. It ensures that the data is error-free and properly sequenced.
  - **Protocols:** Ethernet, Wi-Fi.
  - **Key Components:** MAC (Media Access Control) addresses, which uniquely identify devices on a network.
  
- **Layer 3: Network Layer**
  - **Function:** Manages the routing of data across different networks. It determines the best path for data to reach its destination.
  - **Protocols:** IP (Internet Protocol), ICMP (Internet Control Message Protocol).
  - **Key Components:** IP addresses, which uniquely identify devices on a network and allow them to communicate across networks.
  
- **Layer 4: Transport Layer**
  - **Function:** Provides end-to-end communication services for applications. It ensures that data is transferred reliably and in the correct order.
  - **Protocols:** TCP (Transmission Control Protocol), UDP (User Datagram Protocol).
  - **Key Components:** Ports, which help to direct data to the correct application on a device.

- **Layer 5: Session Layer**
  - Manages sessions or connections between applications.

- **Layer 6: Presentation Layer**
  - Translates data between the application layer and the network.

- **Layer 7: Application Layer**
  - Provides network services directly to end-user applications (e.g., web browsers, email clients).

#### **2. TCP/IP Protocol Suite**

The TCP/IP model, often compared to the OSI model, is a more practical framework for understanding how data is transmitted over the internet. It's divided into four layers: Link, Internet, Transport, and Application.

- **IP (Internet Protocol)**
  - **Function:** Responsible for routing packets of data from the source to the destination across different networks. It handles addressing and fragmentation.
  - **Versions:** IPv4 (32-bit addresses), IPv6 (128-bit addresses).

- **TCP (Transmission Control Protocol)**
  - **Function:** Provides reliable, connection-oriented communication between devices. It ensures that data packets are delivered in order and without errors.
  - **Use Cases:** Web browsing, email, file transfers.

- **UDP (User Datagram Protocol)**
  - **Function:** Offers connectionless, unreliable communication. It sends data without establishing a connection, making it faster but less reliable than TCP.
  - **Use Cases:** Streaming, online gaming, VoIP (Voice over IP).

- **HTTP/HTTPS (Hypertext Transfer Protocol / Secure)**
  - **Function:** HTTP is the protocol used for transmitting web pages over the internet. HTTPS is the secure version of HTTP, encrypting data using TLS/SSL to protect sensitive information.
  - **Use Cases:** Web browsing, API communication.

- **DNS (Domain Name System)**
  - **Function:** Resolves human-readable domain names (like www.example.com) into IP addresses that computers use to identify each other on the network.
  - **Importance:** It’s the backbone of internet browsing, as it allows users to access websites without needing to remember numerical IP addresses.

#### **3. Network Protocols**
Understanding the following network protocols is crucial for networking and cybersecurity roles:

- **IPv4/IPv6**
  - **IPv4:** Uses 32-bit addresses, allowing for approximately 4.3 billion unique addresses.
  - **IPv6:** Uses 128-bit addresses, vastly increasing the number of available addresses to accommodate the growing number of internet-connected devices.

- **TCP/UDP**
  - **TCP:** Provides reliable communication with error-checking and data recovery.
  - **UDP:** Provides faster communication without error-checking, suitable for time-sensitive applications.

- **SCTP (Stream Control Transmission Protocol)**
  - **Function:** A transport layer protocol that combines the features of TCP and UDP, offering reliable, message-oriented communication with multi-homing and multi-streaming capabilities.

- **TLS (Transport Layer Security)**
  - **Function:** Ensures secure communication over a computer network by encrypting data and verifying the integrity and authenticity of messages.
  - **Use Cases:** Secure web browsing (HTTPS), email, VPNs.

- **HTTP/REST**
  - **HTTP:** The protocol used for transferring web pages on the internet.
  - **REST (Representational State Transfer):** An architectural style for designing networked applications, often using HTTP for communication between client and server.

- **OAuth (Open Authorization)**
  - **Function:** An open standard for access delegation, commonly used to grant websites or applications limited access to user information without exposing passwords.
  - **Use Cases:** Social media logins, API authentication.

Understanding these concepts is critical for roles that involve networking, cybersecurity, software development, and IT infrastructure. Each layer of the OSI model and each protocol in the TCP/IP suite plays a specific role in how data is transmitted and received across the internet and other networks.


------

#### Routing vs Forwarding: 
**Routing vs. Forwarding:**

- **Routing:**
  - **Function:** The process of determining the path that data packets should take across a network to reach their destination. It involves making decisions based on routing tables and algorithms.
  - **Scope:** Routing happens at Layer 3 (Network Layer) and is concerned with the global or network-wide path a packet will take.
  - **Complexity:** Involves complex calculations and decision-making to optimize paths, avoid congestion, and ensure data delivery.

- **Forwarding:**
  - **Function:** The process of moving packets from one interface to another within a router or switch based on the routing table's decision. It's essentially the action of sending the packet to the next hop.
  - **Scope:** Forwarding is a simpler, localized action that occurs at each router or switch, dealing with the immediate task of passing the packet along the route.
  - **Complexity:** It’s straightforward, involving looking up the destination in a forwarding table and directing the packet to the correct output interface.

In summary, **routing** is about planning the path, while **forwarding** is about executing that plan by sending the packet on its next step towards the destination.
