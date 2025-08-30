# AppAiGenerator - AI-Powered Recipe Management System

A Spring Boot application that combines AI-powered recipe generation with traditional recipe management, featuring user authentication, JWT security, and integration with Ollama AI models.

## 🚀 Features

- **AI Recipe Generation**: Generate recipes using Ollama AI models based on ingredients, cuisine preferences, and dietary restrictions
- **Recipe Management**: Full CRUD operations for recipes with author attribution
- **User Authentication**: JWT-based authentication system with role-based access control
- **User Management**: User registration and management with USER/ADMIN roles
- **AI Integration**: Direct integration with Ollama for AI-powered features
- **RESTful API**: Comprehensive REST API with OpenAPI/Swagger documentation
- **Database Persistence**: MySQL database with JPA/Hibernate ORM

## 🛠️ Technology Stack

- **Backend**: Spring Boot 3.5.4
- **Java Version**: 17
- **Database**: MySQL 8.0+
- **Security**: Spring Security with JWT
- **AI Integration**: Spring AI with Ollama
- **Documentation**: OpenAPI 3.0 (Swagger)
- **Build Tool**: Maven
- **Lombok**: For reducing boilerplate code

## 📋 Prerequisites

Before running this application, ensure you have:

1. **Java 17** or higher installed
2. **Maven 3.6+** installed
3. **MySQL 8.0+** running locally
4. **Ollama** installed and running (for AI features)

## 🗄️ Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE recipedb;
CREATE USER 'devuser'@'localhost' IDENTIFIED BY '123456789';
GRANT ALL PRIVILEGES ON recipedb.* TO 'devuser'@'localhost';
FLUSH PRIVILEGES;
```

2. The application will automatically create tables on startup (Hibernate DDL auto-update)

## 🤖 Ollama Setup

1. Install Ollama from [https://ollama.ai/](https://ollama.ai/)
2. Pull the Llama model:
```bash
ollama pull llama3
```
3. Start Ollama service:
```bash
ollama serve
```

## 🚀 Running the Application

### Option 1: Using Maven
```bash
cd AppAiGenerator
mvn spring-boot:run
```

### Option 2: Using Maven Wrapper
```bash
cd AppAiGenerator
./mvnw spring-boot:run
```

### Option 3: Building and Running JAR
```bash
cd AppAiGenerator
mvn clean package
java -jar target/AppAiGenerator-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## 📚 API Documentation

Once the application is running, you can access the Swagger UI at:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## 🔐 Authentication

The application uses JWT-based authentication. To access protected endpoints:

1. **Login** to get a JWT token:
```bash
POST /api/auth/login
{
  "username": "your_username",
  "password": "your_password"
}
```

2. **Include the JWT token** in subsequent requests:
```
Authorization: Bearer <your_jwt_token>
```

## 🍳 API Endpoints

### Authentication
- `POST /api/auth/login` - User login

### Recipes
- `POST /api/recipes` - Create a new recipe
- `GET /api/recipes/{id}` - Get recipe by ID
- `GET /api/recipes/get-all` - Get all recipes
- `PUT /api/recipes/{id}` - Update recipe
- `DELETE /api/recipes/{id}` - Delete recipe
- `GET /api/recipes/author/{authorId}` - Get recipes by author

### AI Features
- `GET /api/ai-ask?prompt={text}` - Ask AI a general question
- `GET /api/generate-recipe?ingredients={ingredients}&cuisine={cuisine}&dietaryRestrictions={restrictions}` - Generate AI recipe

### Users
- User management endpoints (protected by ADMIN role)

## 🔧 Configuration

Key configuration options in `application.properties`:

- **Database**: MySQL connection settings
- **JWT Secret**: For token signing
- **Server Port**: Default 8080
- **Hibernate**: DDL auto-update enabled

## 🏗️ Project Structure

```
src/main/java/gr/aueb/AiAppGenerator/
├── authentication/          # JWT authentication services
├── config/                 # Configuration classes
├── controller/             # REST API controllers
├── core/                   # Core components (enums, exceptions)
├── dto/                    # Data Transfer Objects
├── mapper/                 # Object mappers
├── model/                  # JPA entities
├── repository/             # Data access layer
├── security/               # Security configuration
└── service/                # Business logic services
```

## 🧪 Testing

Run tests using Maven:
```bash
mvn test
```

## 🔒 Security Features

- **JWT Authentication**: Secure token-based authentication
- **Role-based Access Control**: USER and ADMIN roles
- **Password Encryption**: Secure password handling
- **CORS Configuration**: Configurable cross-origin requests
- **Input Validation**: Request validation using Hibernate Validator

## 🚨 Troubleshooting

### Common Issues

1. **Database Connection Error**:
   - Ensure MySQL is running
   - Check database credentials in `application.properties`
   - Verify database exists

2. **Ollama Connection Error**:
   - Ensure Ollama is running on port 11434
   - Check if llama3 model is pulled
   - Verify Ollama service status

3. **Port Already in Use**:
   - Change server port in `application.properties`
   - Kill existing process using the port

### Logs

Check application logs for detailed error information. The application logs to console by default.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 📞 Support

For support or questions, please open an issue in the repository.

---

**Note**: This application requires Ollama to be running locally for AI features to work. Make sure to follow the Ollama setup instructions above.
