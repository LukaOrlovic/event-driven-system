# Event-Driven System

This repository contains a simple **event-driven system** implemented in Java with Spring Boot, demonstrating service integration using Kafka.

The goal of this project is to showcase **event-driven communication**, **service decoupling**, and **reliable message processing** in a microservices architecture.

## Architecture

The system consists of two main services:

### 1. Orders Microservice
- Exposes a REST endpoint: `POST /orders`.
- Receives order requests and publishes `OrderCreatedEvent` messages to Kafka.

### 2. Inventory Microservice
- Consumes `OrderCreatedEvent` messages from Kafka.
- Simulates inventory reservation based on available stock.
- Logs whether orders are successfully processed or rejected due to insufficient stock.

### Event Model
Shared between services:
- `OrderCreatedEvent`
- `OrderItemEvent`

Each event contains a `correlationId` for tracing across services.

---

## Features

- Event-driven communication via **Kafka**
- **Correlation ID** support for distributed tracing
- **In-memory inventory storage** (sufficient for demo purposes)



## Getting Started

Follow these steps to run the system locally:

1. **Prerequisites**: Ensure you have installed:
    - Docker & Docker Compose

2. **Clone the repository and navigate into it**:

```bash
git clone https://github.com/LukaOrlovic/event-driven-system.git
cd event-driven-system
```

3. **Build and start Kafka along with both services using Docker Compose**:

```bash
docker-compose up --build
```

This command will automatically start:

- Kafka broker
- Order API service
- Inventory Processing Service

4. **Test the Order API by sending a sample order**:

```bash
curl --location 'http://localhost:8081/orders' \
--header 'Content-Type: application/json' \
--data '{
        "orderId": "order123",
        "items": [
          {"itemId": "item-1", "quantity": 2},
          {"itemId": "item-2", "quantity": 1}
        ]
      }'
```

5. **Observe the Inventory Processing Service logs to see whether the order was successfully processed or rejected, including the correlation ID for tracing.**


## Project Structure

```text
event-driven-system/
├── orders-microservice/            # REST API service that produces OrderCreatedEvent
├── inventory-microservice/         # Service that consumes events and manages inventory
├── core/                           # Shared event models between services
├── docker-compose.yml              # Compose file to run Kafka and both services locally
└── README.md                       # Project documentation
