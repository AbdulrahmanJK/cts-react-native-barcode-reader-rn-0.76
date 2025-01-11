# cts-react-native-barcode-reader-rn-0.76
CTS barcode reader for Unitech - EA600 (React Native 0.76+ compatible)

## Description
This library provides barcode scanning functionality for Unitech EA600 devices in React Native applications. It handles the barcode scanning broadcasts and provides an easy-to-use interface for React Native apps.

## Requirements
- React Native >= 0.76.0
- Android SDK >= 24 (Android 7.0)
- Unitech EA600 device

## Installation

```bash
npm install git+https://github.com/AbdulrahmanJK/cts-react-native-barcode-reader-rn-0.76.git --save
```

## Setup

### Android

1. Add the following to your `android/settings.gradle`:
```gradle
include ':cts-react-native-barcode-reader-rn-0.76'
project(':cts-react-native-barcode-reader-rn-0.76').projectDir = new File(rootProject.projectDir, '../node_modules/cts-react-native-barcode-reader-rn-0.76/android')
```

2. Update your `android/app/build.gradle`:
```gradle
dependencies {
    // ... other dependencies
    implementation project(':cts-react-native-barcode-reader-rn-0.76')
}
```

3. Update your `android/app/src/main/AndroidManifest.xml`:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application>
        <!-- Add this receiver inside the application tag -->
        <receiver
            android:name="com.barcodereader.BarcodeBroadcastReceiver"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.ACTION_DECODE_DATA"/>
            </intent-filter>
        </receiver>
    </application>
</manifest>
```

4. Add the package to your `MainApplication.kt`:
```kotlin
// android/app/src/main/java/com/your-app-name/MainApplication.kt

import com.barcodereader.RNAndroidBarcodeBroadcastPackage // Add this import

class MainApplication : Application(), ReactApplication {
    override val reactNativeHost: ReactNativeHost =
        object : DefaultReactNativeHost(this) {
            override fun getPackages(): List<ReactPackage> =
                PackageList(this).packages.apply {
                    // Add the package to the list
                    add(RNAndroidBarcodeBroadcastPackage())
                }
            
            // ... rest of your MainApplication code ...
        }
    
    // ... rest of your MainApplication code ...
}
```

## Usage

### Basic Usage
```javascript
import { useEffect } from 'react';
import { DeviceEventEmitter } from 'react-native';

function YourComponent() {
    useEffect(() => {
        // Add barcode scanner listener
        const barcodeListener = DeviceEventEmitter.addListener(
            'BarcodeScanerReceiver',
            (data) => {
                console.log('Scanned barcode:', data.barcode_data);
                // Handle the barcode data here
            }
        );

        // Cleanup listener on component unmount
        return () => {
            barcodeListener.remove();
        };
    }, []);

    return (
        // Your component JSX
    );
}
```

### Class Component Usage
```javascript
import React, { Component } from 'react';
import { DeviceEventEmitter } from 'react-native';

class YourComponent extends Component {
    componentDidMount() {
        this.barcodeListener = DeviceEventEmitter.addListener(
            'BarcodeScanerReceiver',
            this.handleBarcode
        );
    }

    componentWillUnmount() {
        if (this.barcodeListener) {
            this.barcodeListener.remove();
        }
    }

    handleBarcode = (data) => {
        console.log('Scanned barcode:', data.barcode_data);
        // Handle the barcode data here
    }

    render() {
        return (
            // Your component JSX
        );
    }
}
```

## Troubleshooting

### Common Issues

1. Barcode scanner not working
   - Make sure your device is Unitech EA600
   - Verify that the receiver is properly registered in AndroidManifest.xml
   - Check Android logs for any error messages

2. Build errors
   - Ensure your React Native version is 0.76.0 or higher
   - Make sure all the gradle configurations are correct
   - Try cleaning and rebuilding your project

### Debug Logs
The library includes debug logs that can help identify issues. Check your Android logs for entries with tag "ReactNativeJS".

## License
MIT

## Contributing
Feel free to submit issues and pull requests to improve the library.