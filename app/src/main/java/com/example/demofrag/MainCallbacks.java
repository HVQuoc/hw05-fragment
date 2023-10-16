package com.example.demofrag;

import android.os.Bundle;

public interface MainCallbacks
{
    public void onMsgFromFragToMain (String sender, Bundle value);
}
