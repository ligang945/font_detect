# Font Detector

A lightweight Android application that scans and displays system fonts on your device, with optimized support for the Noto font family.

## Overview

Font Detector provides a simple interface to browse and preview fonts installed on your Android device. It scans the system font directory and presents each font with live text previews in both English and Chinese.

## Features

- **System Font Scanning** - Automatically detects fonts from `/system/fonts`
- **Live Preview** - See each font rendered with sample text
- **Bilingual Support** - Displays English and Chinese test samples
- **Android TV Compatible** - Built with Leanback library for TV devices
- **Landscape Mode** - Optimized for large screen displays

## Requirements

- Android 5.0 (API 21) or higher
- JDK 11 or higher
- Android Studio Hedgehog or later

## Building

```bash
# Clone the repository
git clone <repository-url>
cd font_detect

# Build the APK
./gradlew assembleRelease

# Install on connected device
./gradlew installDebug
```

## Usage

1. Launch the app
2. Browse through the detected fonts
3. View sample text rendered in each font

**Note:** Full system font access may require root privileges on some devices.

## Customization

### Font Filtering

Edit `MainActivity.java` to change which fonts are displayed:

```java
// Show only Noto fonts (default)
if (!fontName.toLowerCase().contains("noto")) {
    continue;
}

// Show all fonts
// Remove or comment out the filter condition
```

### Preview Text

Modify test samples in `FontAdapter.java`:

```java
holder.fontSample.setText("Your custom text here");
```

## Project Structure

```
app/src/main/java/com/example/fontdetect/
├── MainActivity.java    # Font detection and initialization
└── FontAdapter.java      # RecyclerView adapter for font list
```

## Version

- Current: 1.0
