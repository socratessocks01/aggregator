# Aggregation Service

The Aggregation Service is a Spring Boot application designed to process and analyze video transcripts. It provides features to fetch the most frequently occurring words from a video's transcript and other transcript-related data.

## Features

- Retrieve the top N most frequent words from a video's transcript.
- REST API for easy integration.
- Supports data storage and retrieval using JPA repositories.

## Project Structure

- **`src/main/java`**: Contains the Java source code, organized into the following packages:
    - `controller`: Contains REST controllers to handle API requests.
    - `service`: Includes service interfaces and implementations for business logic.
    - `repository`: Defines JPA repositories for database interactions.
    - `entity`: Defines the data model entities.
- **`pom.xml`**: Maven configuration file for dependency management.
- **`persist_transcript.py`**: A script to fetch video transcripts using the YouTube API.

## Prerequisites

- Java 17 or higher.
- Maven 3.6 or higher.
- PostgreSQL (or any configured database).

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd aggregation
   ```

2. Configure the database:
    - Update the `src/main/resources/application.properties` file with your database credentials:
      ```properties
      spring.datasource.url=jdbc:postgresql://<host>:<port>/<database>
      spring.datasource.username=<username>
      spring.datasource.password=<password>
      spring.jpa.hibernate.ddl-auto=update
      ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Endpoints

### 1. Get Top N Words
**Endpoint**: `GET /top-words`

**Parameters:**
- `videoId` (String): The ID of the video.
- `topN` (int): The number of top words to retrieve.

**Response:**
Returns a list of words and their frequencies.

**Example:**
```bash
curl "http://localhost:8080/top-words?videoId=12345&topN=10"
```

### 2. Get Word Frequency by Video
**Endpoint**: `GET /word-frequency`

**Parameters:**
- `videoId` (String): The ID of the video.
- `word` (String): The word to retrieve the frequency for.

**Response:**
Returns the frequency of the specified word in the video's transcript.

**Example:**
```bash
curl "http://localhost:8080/word-frequency?videoId=12345&word=example"
```

## How to Contribute

1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with a detailed explanation of your changes.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact

For questions or suggestions, please contact the project maintainer at [email@example.com].

