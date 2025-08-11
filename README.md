# 🧠 Answify AI Chrome Extension

**"Summarize, solve, and save — all in one click!"**

---

## 📖 Description

**Answify AI** is a powerful Chrome extension with an **AI-powered Spring Boot backend** that allows users to:

- **Summarize long articles, research papers, or any online text instantly**
- **Solve Multiple Choice Questions (MCQs) with high accuracy**
- **Save AI-generated summaries to your personal notes panel**

Designed for **students, professionals, and researchers**, Answify AI boosts productivity by eliminating the need to manually copy, paste, or process large amounts of text.

---

## ✨ Features

1. **AI-Powered Summarization** – Get concise and clear summaries of any selected content.
2. **MCQ Solver** – Paste or select MCQs and let the AI suggest the correct answers instantly.
3. **Side Panel Notes** – Save your AI summaries into a fixed side panel for quick reference.
4. **Spring Boot Backend** – Secure and scalable backend for processing AI requests.
5. **Modern UI** – A clean, distraction-free design for seamless user experience.

---

## 🧰 Tech Stack

| Frontend | Backend | Tools |
| --- | --- | --- |
| HTML, CSS, JavaScript (Chrome Extension APIs) | Spring Boot (Java) | Maven, REST APIs |
| Chrome Extension Manifest v3 | Spring Web, Lombok | Git, GitHub, API integration |

---

## 📁 Project Structure

├── frontend/ # Chrome extension files
│ ├── manifest.json
│ ├── sidepanel.html
│ ├── sidepanel.css
│ ├── sidepanel.js
│ └── icons/
│
├── backend/ # Spring Boot application
│ ├── src/main/java/... # Java source files
│ ├── pom.xml
│ └── application.yml
│
└── README.md

---

## ⚙️ How to Use

## 2️⃣ Frontend (Chrome Extension)

1. Open **Chrome** → Go to `chrome://extensions/`
2. Enable **Developer mode** (toggle in the top-right corner)
3. Click **Load unpacked**
4. Select the **frontend/** folder from the project

