# Photos Keylogger (Ethical Hacking Demo)

⚠️ **For educational use only. Do not use this project for malicious purposes.**

## 📱 Overview

This Android app demonstrates how keyloggers can abuse **Accessibility Services**. Disguised as the **Google Photos** app (same name and icon), it requests accessibility permission, launches the real Photos app to reduce suspicion, and logs user input in the background.

## 🔧 How It Works

1. App named **Photos** asks for Accessibility access.
2. On grant, it opens the real **Google Photos** app using an Intent.
3. Keylogger starts tracking input from the device.
4. Logged text is sent to a remote server (attacker's web).

## 🧪 Requirements

- Android Studio
- Android device or emulator (manually enable accessibility)
- Test server to receive logs

## 🧠 Purpose

- Demonstrate Android security risks
- Raise awareness on permission abuse
- For ethical hacking and research only

## ♻️ Reverse Engineering Note

The original project was accidentally deleted after building. Luckily, the APK remained on the phone, and using **JADX** we were able to reverse-engineer the APK and recover the source code.

## ⚠️ Warning

**Do not upload or distribute this app. Only use in secure test environments.**
