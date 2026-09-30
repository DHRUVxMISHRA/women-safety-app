<div align="center">

<!-- 📸 ADD LOGO HERE: save as assets/logo.png -->
<img src="assets/logo.png" alt="Clefairy Logo" width="140"/>

# Clefairy

### A Women Safety Application

**One tap. Automatic protection. Help that reaches you faster.**

[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](#)
[![Language](https://img.shields.io/badge/Kotlin-Native-7F52FF?logo=kotlin&logoColor=white)](#)
[![Backend](https://img.shields.io/badge/Backend-FastAPI-009688?logo=fastapi&logoColor=white)](#)
[![ML](https://img.shields.io/badge/ML-scikit--learn-F7931E?logo=scikitlearn&logoColor=white)](#)
[![Firebase](https://img.shields.io/badge/Firebase-Auth%20%26%20DB-FFCA28?logo=firebase&logoColor=black)](#)
[![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue)](#)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](#license)

*Built by Team **Tech10***

[Features](#-key-features) · [Screenshots](#-screenshots) · [Architecture](#-architecture) · [Getting Started](#-getting-started) · [Roadmap](#-future-scope)

</div>

---

## Table of Contents

- [About the Project](#-about-the-project)
- [Problem Statement](#-problem-statement)
- [Our Solution](#-our-solution)
- [Key Features](#-key-features)
- [Screenshots](#-screenshots)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
- [Security & Privacy](#-security--privacy)
- [What Makes Clefairy Different](#-what-makes-clefairy-different)
- [Future Scope](#-future-scope)
- [Team](#-team)
- [Contributing](#-contributing)
- [License](#license)

---

## About the Project

**Clefairy** is a native Android application designed to give women faster, more reliable access to help in emergencies. It combines multiple SOS triggers, live location tracking, an ML-powered safe-route system, an AI safety assistant (**Sakhi AI**), community support, and direct access to verified government help centers, all in one integrated app.

Instead of forcing a user to unlock a phone, find an app, and make a call during a moment of panic, Clefairy is built to act **for** her: automatically detecting distress, alerting trusted contacts, and sharing her live location.

<!-- 📸 ADD HERO BANNER HERE: a wide image or GIF showing the app in action -->
<p align="center">
  <img src="assets/hero-banner.png" alt="Clefairy Hero Banner" width="90%"/>
</p>

---

## Problem Statement

Women in danger often cannot reach for help in time. The key gaps Clefairy addresses:

- Delayed emergency response during dangerous situations
- Inability to manually call for help in moments of panic
- No quick access to nearby support or helpers
- Low awareness of verified women's safety and help centers
- Existing apps are fragmented and not fully integrated
- Weak connection with community and government support systems
- A need for faster and more reliable safety solutions

---

## Our Solution

Clefairy brings prevention, detection, and response into a single app:

| Stage | What Clefairy does |
|-------|--------------------|
| **Prevent** | ML-based safest-route suggestions, daily safety tips and risk-based guidance |
| **Detect** | Sensor-based motion anomaly detection and distress audio detection |
| **Respond** | One-tap / triple-click / automatic SOS, emergency SMS, live location sharing, audio recording |
| **Support** | Sakhi AI assistant, community feed, helplines, and government help center directory |

---

## Key Features

### Multiple SOS Triggers
- **One-Tap SOS**: single tap to trigger an emergency alert
- **Triple-Click SOS**: discreet trigger without opening the app
- **Sensor-Based SOS**: uses device sensors to detect abnormal motion
- **Automatic SOS**: motion anomaly plus distress audio detection
- GPS location is captured and shared automatically
- **Cancel option** to stop false alarms

### Live Track Me and Automatic Travel SOS
- SOS protection stays **active for the entire journey** until the user reaches her destination
- Any disruption that stops the user's movement or deviates from the route can trigger an alert
- Shows current location, destination, ETA, and distance

### ML-Based Safe Route Finder
- Suggests the **safest route** instead of just the shortest one
- Powered by a scikit-learn risk-score prediction model served through FastAPI

### Sakhi AI, Your Safety Assistant
- AI assistant trained for women's safety scenarios
- Understands requests like *"I'm in danger, please send my current location to my contacts"*
- Helps locate the nearest One Stop Centre (OSC) and guides the user step by step

### Emergency SMS
- Sends alerts to registered trusted contacts (family, friends, etc.)
- Includes the user's live location

### Audio Recording
- One-tap evidence recording during an incident

### Helplines and Government Support
- National and common helpline numbers in one place
- Directory of official support bodies, including:
  - One Stop Centre (OSC) Administrators and SNOs
  - Shakti Sadan and Shakti Sadan SNOs
  - Protection Officers
  - Child Marriage Prohibition Officers
  - Anti-Human Trafficking Unit
  - District Programme Officers

### Community and Awareness
- Community feed with posts, comments, and people
- Daily safety awareness tips and risk-based guidance
- Integrates schemes such as **BBBP**, **OSC**, and **MRY** on the home screen

### Secure Onboarding
- Registration with name, Aadhaar number, email, and password
- Verified user profiles
- End-to-end encrypted personal messages

---

## Screenshots

> Replace each placeholder below with a real screenshot. Suggested path: `assets/screenshots/`.
> Tip: keep all phone screenshots the same width (e.g. `width="220"`) for a clean layout.

### Onboarding & Authentication

| Splash | Register | Login |
|:------:|:--------:|:-----:|
| <!-- 📸 --> <img src="assets/screenshots/splash.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/register.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/login.png" width="220"/> |

### Home & Profile

| Home | Profile / Quick Actions | Settings |
|:----:|:-----------------------:|:--------:|
| <!-- 📸 --> <img src="assets/screenshots/home.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/profile.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/settings.png" width="220"/> |

### Emergency Features

| SOS | Record | Live Track Me |
|:---:|:------:|:-------------:|
| <!-- 📸 --> <img src="assets/screenshots/sos.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/record.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/live-track.png" width="220"/> |

| Safe Route | Emergency SMS | Helpline Numbers |
|:----------:|:-------------:|:----------------:|
| <!-- 📸 --> <img src="assets/screenshots/safe-route.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/emergency-sms.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/helpline.png" width="220"/> |

### AI & Community

| Sakhi AI | Safety Tips | Communities |
|:--------:|:-----------:|:-----------:|
| <!-- 📸 --> <img src="assets/screenshots/sakhi-ai.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/safety-tips.png" width="220"/> | <!-- 📸 --> <img src="assets/screenshots/communities.png" width="220"/> |

### Government Help Centers

| Help Center Directory |
|:---------------------:|
| <!-- 📸 --> <img src="assets/screenshots/govt-help-centers.png" width="220"/> |

### Demo Video

<!-- 📸 ADD DEMO GIF / VIDEO LINK HERE -->
<p align="center">
  <a href="YOUR_DEMO_VIDEO_LINK">
    <img src="assets/demo-thumbnail.png" alt="Watch Demo" width="60%"/>
  </a>
</p>

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| **Mobile App** | Android (Kotlin, Native) |
| **Android System Components** | SensorManager, Location Services, Foreground Services |
| **Maps** | Google Maps SDK for live location tracking |
| **Backend** | FastAPI (Python), REST APIs |
| **Machine Learning** | scikit-learn (risk score prediction) |
| **Auth & Database** | Firebase |
| **Networking** | Retrofit |
| **Security** | JWT-based authentication and encryption |
| **Architecture** | MVVM |
| **UI/UX Design** | Figma |

---

## Architecture

```
┌──────────────────────┐        REST (Retrofit)        ┌──────────────────────┐
│   Android App        │  ───────────────────────────▶ │   FastAPI Backend    │
│   (Kotlin, MVVM)     │  ◀───────────────────────────  │   (Python)           │
│                      │           JSON / JWT          │                      │
│ • SensorManager      │                               │ • Auth (JWT)         │
│ • Location Services  │                               │ • Risk score API     │
│ • Foreground Service │                               │ • scikit-learn model │
│ • Google Maps SDK    │                               └──────────┬───────────┘
└──────────┬───────────┘                                          │
           │                                                      │
           ▼                                                      ▼
   ┌──────────────┐                                     ┌──────────────────┐
   │   Firebase   │                                     │  Safe-Route /    │
   │ Auth + DB    │                                     │  Risk Prediction │
   └──────────────┘                                     └──────────────────┘
```

<!-- 📸 OPTIONAL: replace or supplement the diagram above with an image -->
<p align="center">
  <img src="assets/architecture.png" alt="Architecture Diagram" width="80%"/>
</p>

---

## Project Structure

```
women-safety-app/
├── android-app/     # Native Android application (Kotlin, MVVM)
├── backend/         # FastAPI service and ML risk-prediction model
├── ui design/       # Figma / UI design assets
├── .gitignore
└── README.md
```

---

## Getting Started

> ⚠️ Commands below are standard templates. Adjust file names and paths to match your project.

### Prerequisites

- Android Studio (latest stable)
- JDK 17+
- Python 3.10+
- A Firebase project
- A Google Maps API key

### 1. Clone the repository

```bash
git clone https://github.com/DHRUVxMISHRA/women-safety-app.git
cd women-safety-app
```

### 2. Backend setup

```bash
cd backend
python -m venv venv
source venv/bin/activate        # Windows: venv\Scripts\activate
pip install -r requirements.txt
uvicorn main:app --reload
```

The API will be available at `http://127.0.0.1:8000`, with interactive docs at `/docs`.

Create a `.env` file in `backend/`:

```env
JWT_SECRET=your_secret_key
FIREBASE_CREDENTIALS=path/to/serviceAccountKey.json
```

### 3. Android app setup

1. Open the `android-app/` folder in **Android Studio**.
2. Add your `google-services.json` to `android-app/app/`.
3. Add your Google Maps API key in `local.properties`:
   ```properties
   MAPS_API_KEY=your_google_maps_api_key
   ```
4. Set the backend base URL in the Retrofit configuration (use `http://10.0.2.2:8000` for the Android emulator).
5. Sync Gradle, then **Run** on an emulator or physical device.

### Required Permissions

| Permission | Purpose |
|-----------|---------|
| Location (fine and background) | Live tracking and SOS location sharing |
| SMS | Emergency alerts to trusted contacts |
| Microphone | Audio recording and distress detection |
| Foreground Service | Continuous protection during travel |
| Sensors | Motion anomaly detection |

---

## Security & Privacy

- JWT-based authentication for API access
- Encrypted data handling
- End-to-end encrypted personal messages
- Verified user profiles
- Sensitive keys and credentials are excluded via `.gitignore` and must never be committed

---

## What Makes Clefairy Different

- Three SOS modes in one app: One Tap, Sensor-Based, and Triple Click
- **Automatic SOS** using motion anomaly and distress audio detection
- **ML-based safest route** finding, not just shortest route
- SOS that stays active for the whole journey
- Emergency SMS, helplines, and audio recording built in
- Community support combined with government help centers
- **Sakhi AI**, an assistant trained for women's safety
- Daily safety awareness and risk-based guidance

---

## Future Scope

- 💍 Smart ring and wearable device integration for hands-free SOS activation
- 🧠 Advanced AI-based risk prediction
- 📢 Safety awareness campaigns
- 🛍️ App-related safety merchandise
- 🤝 Large-scale deployment through partnerships with NGOs, educational institutions, and government initiatives

---

## Team

**Team Tech10**

| Name | Role | GitHub |
|------|------|--------|
| Dhruv Mishra | Developer | [@DHRUVxMISHRA](https://github.com/DHRUVxMISHRA) |
| _Add member_ | _Role_ | _link_ |
| _Add member_ | _Role_ | _link_ |

---

## Contributing

Contributions are welcome.

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

---

## License

Distributed under the MIT License. See `LICENSE` for more information.

---

<div align="center">

**Built with 💜 for a safer tomorrow.**

If you find this project useful, please consider giving it a ⭐

</div>