  # Extractly: AI-powered Content Extractor

  A full-stack web app to extract, summarize, and display key points from any public URL using open-source NLP. Built with Java Spring Boot (backend), React.js + Next.js (frontend), Tailwind CSS, and shadcn/ui for a modern Notion-like UI. Features PDF export, inline editing, search, pagination, and more.

  ---

  ## ✨ Features
  - Extracts main content from any public URL (removes nav/footer/ads, prefers main/article/largest/div)
  - Summarizes content using open-source NLP (local or HuggingFace Inference API)
  - Produces a 2–3 paragraph summary and key points based on the summary
  - Notion-style editable/searchable key points table
  - Inline editing and deletion of key points (with animation)
  - Search with highlight and instant filtering
  - Pagination for large lists of key points
  - Export summary and key points as PDF (jsPDF)
  - Modern, responsive UI (Tailwind CSS + shadcn/ui)
  - Loading spinners, skeletons, and error toasts
  - Fully tested (Jest, Testing Library, Maven)
  - Vercel-deployable frontend, Railway-deployable backend

  ---

  ## 🖼️ Screenshots

  ### Summary Extraction with PDF Export
  ![Screenshot](./screenshots/image1.png)

  ### Key Points
  ![Screenshot](./screenshots/image2.png)

  ### Summary Extraction History
  ![Screenshot](./screenshots/image3.png)

  ### Search Functionality
  ![Screenshot](./screenshots/image4.png)

  ### Edit Functionality
  ![Screenshot](./screenshots/image5.png)

  ---

  ## 🛠️ Setup & Deployment

  ### 1. Backend (Spring Boot on Railway)
  - **Requirements:** Java 17+, Maven
  - **Setup Locally:**
    ```sh
    cd backend
    mvn clean install
    mvn spring-boot:run
    ```
  - **Environment variables:**
    - `HUGGINGFACE_API_KEY` (optional, for better summaries)
    - `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` (for PostgreSQL)
  - **API URL:**
    - By default: `http://localhost:8080/api/extract`

  #### Deploy Backend (Railway)
  - Push your code to GitHub.
  - Go to [Railway](https://railway.app/), create a new project, and connect your repo.
  - Set the service root to `backend/`.
  - Add the PostgreSQL plugin in Railway and copy the connection details.
  - In Railway, set these environment variables for your backend service:
    ```env
    SPRING_DATASOURCE_URL=jdbc:postgresql://<private-host>:<port>/<database>
    SPRING_DATASOURCE_USERNAME=<user>
    SPRING_DATASOURCE_PASSWORD=<password>
    HUGGINGFACE_API_KEY=<your_huggingface_api_key>
    ```
  - Make sure your `application.properties` contains:
    ```properties
    server.port=${PORT:8080}
    ```
  - Deploy and copy your public backend URL for frontend config (e.g., `https://extractly-ai-powered-content-extractor-production.up.railway.app/api`).

  ### 2. Frontend (Next.js on Vercel)
  - **Requirements:** Node.js 18+, npm
  - **Setup Locally:**
    ```sh
    cd frontend
    npm install
    npm run dev
    ```
  - **Environment variables:**
    - Create a `.env.local` file in `frontend/`:
      ```env
      NEXT_PUBLIC_API_BASE_URL=https://extractly-ai-powered-content-extractor-production.up.railway.app/api
      ```
  - **Deploy Frontend (Vercel):**
    - Push your code to GitHub.
    - Go to [Vercel](https://vercel.com/), import your frontend repo, and deploy as a Next.js app.
    - In Vercel dashboard, set `NEXT_PUBLIC_API_BASE_URL` to your backend's public URL.

  ---

  ## 🌐 Environment Variables
  - **Backend (Railway):**
    - `HUGGINGFACE_API_KEY` (optional): Use a free HuggingFace Inference API key for better summaries. [Get one here.](https://huggingface.co/settings/tokens)
    - `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`: Provided by Railway PostgreSQL plugin.
  - **Frontend (Vercel):**
    - `NEXT_PUBLIC_API_BASE_URL`: The base URL of your backend API (e.g., `https://extractly-ai-powered-content-extractor-production.up.railway.app/api`)

  ---

  ## 🤖 API Usage
  - **POST** `/api/extract`
    - **Request Body:** `{ "url": "https://example.com" }`
    - **Response:** `{ "summary": "...", "keyPoints": ["...", ...] }`
    - **Errors:** Returns `{ "error": "..." }` for invalid URLs, fetch failures, or unexpected errors.
  - **GET** `/api/extracted`
    - Returns all extracted content stored in the database, including the summary for each entry.

  ---

  ## 📄 PDF Export & Table Features
  - Export the current summary and visible key points as a PDF (jsPDF)
  - Only non-deleted, filtered, and edited key points are exported
  - Key points table supports:
    - Inline editing (double-click or Edit button)
    - Deletion with smooth animation
    - Search with highlight (case-insensitive)
    - Pagination (5 per page, stays on page after deletion)
    - Empty state and no-results message
  - **Extraction History:**
    - The "Edit" button allows you to edit only the summary for each entry.
    - The "Export as PDF" button exports only the summaries (not the full extracted content) for all entries.

  ---

  ## 🏗️ Project Structure

  - **backend/**: Java Spring Boot REST API for extracting and summarizing content from public URLs.
  - **frontend/**: Next.js app for submitting URLs and displaying extracted summaries and key points in a Notion-style table.

  ---

  ## 🧪 Testing

  ### Backend
  - Run all backend tests:
    ```sh
    cd backend
    mvn test
    ```

  ### Frontend
  - Run all frontend tests:
    ```sh
    cd frontend
    npm run test
    ```
  - Example tests are provided in `frontend/__tests__/App.test.jsx` and cover both error and success cases for the main UI.

  ---

  ## 🖥️ Deployment Architecture

  ```mermaid
  flowchart LR
      User["User (Browser)"] --> Vercel["Frontend: Vercel (Next.js)"]
      Vercel --> Backend["Backend: Railway (Spring Boot)"]
      Backend -->|Fetches| External["External URLs"]
      Backend -->|Stores| PostgreSQL["PostgreSQL: Railway Plugin"]
  ```

  ---

  ## 🙏 Acknowledgements
  - [JSoup](https://jsoup.org/) for HTML parsing
  - [HuggingFace Inference API](https://huggingface.co/inference-api) for summarization
  - [shadcn/ui](https://ui.shadcn.com/) and [Tailwind CSS](https://tailwindcss.com/) for UI
  - [jsPDF](https://github.com/parallax/jsPDF) for PDF export

  ---

  ## 💡 Contributing
  Pull requests welcome! Please open an issue first to discuss major changes.

  ---

  ## 🛠️ Troubleshooting
  - **Frontend cannot reach backend:**
    - Make sure `NEXT_PUBLIC_API_BASE_URL` is set to your backend's public URL in Vercel.
    - Ensure CORS is enabled on your backend.
  - **PDF export missing key points:**
    - Only visible (filtered, non-deleted) key points are exported.
  - **Build or deploy errors:**
    - Check Node/Java versions, environment variables, and logs in Vercel/Railway.

  ---

  # Database Integration

  This project uses PostgreSQL for storing extracted content, managed by Railway in production.

  ## Local Development with PostgreSQL

  1. Ensure Docker is installed and running.
  2. In the project root, run:
    ```sh
    docker-compose up -d
    ```
    This will start a PostgreSQL instance at `localhost:5432` with:
    - Database: `aiextractor`
    - User: `postgres`
    - Password: `postgres`

  ## Spring Boot Backend Configuration

  The backend is configured to connect to this database using `src/main/resources/application.properties` for local dev, and Railway environment variables in production.

  ## API Endpoints

  - `POST /api/extract` — Extracts and summarizes content from a URL, saves the raw extracted content to the database, and returns the summary and key points.
  - `GET /api/extracted` — Returns all extracted content stored in the database.

  ## Entity

  - `ExtractedContent`: Stores the URL and the generated summary.

  ## Development Workflow

  - Start the database with Docker Compose (for local dev).
  - Build and run the backend as usual (`mvn clean install` and `mvn spring-boot:run`).
  - Use the new endpoints to extract and retrieve content.
