# Coupe Ton Arbre Web Application

## Project Description

The **Coupe Ton Arbre** web application provides a comprehensive platform for managing quote requests, appointments, feedback, and invoicing. Clients can easily schedule and view their quote requests and appointments, leave feedback, and receive invoices. Employees can access their schedules and view feedback received. Admins have the authority to manage all aspects of the platform, ensuring seamless coordination between clients and employees.

## Dependencies

- [Node.js](https://nodejs.org/en/download/) - 20.10.0 LTS
- [Docker](https://www.docker.com/get-started/) - Latest Version

## Setup Instructions

Follow these simple steps to set up and run the project locally:

1. Clone the repository to your local machine:

``` bash
git clone https://github.com/ebond07/CoupeTonArbre.git
```

2. Navigate to the project directory:
``` bash
cd CoupeTonArbre/
```

3. Navigate to the project's backend directory:
``` bash
cd coupetonarbrebackend/
```

4. Build the Docker containers
``` bash
docker-compose build
```
5. Start the Docker containers
``` bash
docker-compose up
```

The application will be accessible at `http://localhost:3000`

## Contributing Guidelines

Follow these guidelines to contribute:

- Create a new branch for your feature or bug fix with the following format: [type/Jira-TicketID_Story_Name]
- An example of a properly formatted branch name would be: feat/CTA-10_Add_Invoicing
- Make your changes and commit them with clear messages.
- Submit a pull request.

## Project Structure

The project is structured as follows:

- **`CoupeTonArbre/`**: Root directory containing the entire application.

  - **`coupetonarbrebackend/`**: Backend directory for the Spring Boot Java application.
  
    - **`src/`**: Contains the source code for the Spring Boot application.
    - **`docker/`**: Docker configuration files for the backend.

  - **`coupetonarbrefrontend/`**: Frontend directory for the React.js application.
  
    - **`src/`**: Contains the source code for the React.js application.
    - **`public/`**: Public assets and HTML template.

  - **`docker-compose.yml`**: Docker Compose configuration file for orchestrating the backend and frontend containers.

  - **`LICENSE`**: MIT License file.

  - **`README.md`**: Documentation file providing information about the project.

## Developers

The contributors who have worked on this project:

- [Evan Bond](https://github.com/ebond07)
- [David Rallo](https://github.com/drallo22)
- [Da Zhuo Xie](https://github.com/dazhuox)
- [Matthew Tan](https://github.com/Matthewtan9)
